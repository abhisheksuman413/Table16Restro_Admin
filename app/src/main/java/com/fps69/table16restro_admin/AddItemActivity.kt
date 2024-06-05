package com.fps69.table16restro_admin

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.pm.PackageInfoCompat
import com.fps69.table16restro_admin.ModelClass.AllMenu
import com.fps69.table16restro_admin.databinding.ActivityAddItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding

    // Var for firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    // Var for input form Edit Text
    private lateinit var foodName: String
    private lateinit var foodPrice: String
    private lateinit var foodDescription: String
    private lateinit var foodIngIngredients: String
    private var foodImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val pickImage =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                if (uri != null) {
                    binding.SelectedImage.setImageURI(uri)
                    foodImageUri = uri
                }

            }
        binding.apply {
            SelectImage.setOnClickListener {
//                pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.GE).toString())
                pickImage.launch("image/*")

            }
            BackButtonAddItem.setOnClickListener {
                finish()
            }
            AddItemButtonBTN.setOnClickListener {
                foodName = binding.EnterItemNameET.text.toString().trim()
                foodPrice = binding.EnterFoodPriceET.text.toString().trim()
                foodDescription = binding.EnterFoodDecET.text.toString().trim()
                foodIngIngredients = binding.EnterFoodIngredientsET.text.toString().trim()

                if (!(foodName.isBlank() || foodPrice.isBlank() || foodDescription.isBlank() || foodIngIngredients.isBlank())) {
                    addDataToFirebase()
                    Toast.makeText(
                        this@AddItemActivity,
                        "Item Adding in progress ",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()

                } else {
                    Toast.makeText(
                        this@AddItemActivity,
                        "Please Fill All Details",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun addDataToFirebase() {
        // Get a reference to the "menu" node in the Database
        val menuRef: DatabaseReference = database.getReference("menu")
        //Generate a unique for the each menu item
        val newItemKey: String? = menuRef.push().key

        // Check FoodImage Uri is not null
        if (foodImageUri != null) {
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("menu_image/${newItemKey}.jpg")
            val UploadImage = imageRef.putFile(foodImageUri!!)
            UploadImage.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                    // Check image is uploaded successfully or not

                    //Create New menu Item
                    val newItem = AllMenu(
                        foodName = foodName,
                        foodPrice = foodPrice,
                        foodImag = downloadUrl.toString(),
                        foodDec = foodDescription,
                        foodIngredient = foodIngIngredients

                    )
                    // For item we make a unique key
                    newItemKey?.let { key ->
                        menuRef.child(key).setValue(newItem).addOnSuccessListener {

                            Toast.makeText(
                                this@AddItemActivity,
                                " Item Added Successfully ",
                                Toast.LENGTH_LONG
                            ).show()
                        }.addOnFailureListener {
                            Toast.makeText(
                                this@AddItemActivity,
                                " Adding Item Fail ",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                }
            }.addOnFailureListener {
                Toast.makeText(this@AddItemActivity, " Adding Item Fail ", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this@AddItemActivity, " Please select Image ", Toast.LENGTH_LONG).show()
        }

    }
}