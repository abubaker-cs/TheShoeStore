package org.abubaker.shoesplanet.ui.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.abubaker.shoesplanet.data.ShoeDao
import org.abubaker.shoesplanet.model.Shoe

/**
 * FILE 03
 *
 * Shared ViewModel to provide data to the:
 * 1. ShoeListFragment
 * 2. ShoeDetailFragment
 * 3. ShoeDetailsFragment
 *
 * and allow for interaction the the ShoeDao
 */
class ShoeViewModel(private val shoeDao: ShoeDao) : ViewModel() {

    // This property will set a list of all shoes from the DAO
    val allShoes: LiveData<List<Shoe>> = shoeDao.getShoes().asLiveData()

    // This method will initialize following query through ShoeDao.kt to retrieve the required shoe's record.
    // SELECT * from shoes_database WHERE id = :id
    fun getShoe(id: Long): LiveData<Shoe> {
        return shoeDao.getShoe(id).asLiveData()
    }

    // Action: Add a new shoe entry
    fun addShoe(
        model: String,
        brand: String,
        type: String,
        price: String,
        color: String,
        size: String,
        inStock: Boolean,
        notes: String
    ) {
        val shoe = Shoe(

            // Model
            modelNumber = model,

            // Brand
            brandName = brand,

            // Type
            shoeType = type,

            // Price
            shoePrice = price,

            // Color
            shoeColor = color,

            // Size
            shoeSize = size,

            // Shoe Availability
            inStock = inStock,

            // Notes
            notes = notes

        )

        // Launching a new coroutine to insert an item in a non-blocking way
        viewModelScope.launch {

            // Add the new entity to the database
            shoeDao.insert(shoe)

        }

    }

    // Action: Update an existing record
    fun updateShoe(
        id: Long,
        model: String,
        brand: String,
        type: String,
        price: String,
        color: String,
        size: String,
        inStock: Boolean,
        notes: String
    ) {
        val shoe = Shoe(

            // Unique ID
            id = id,

            // Model
            modelNumber = model,

            // Name
            brandName = brand,

            // Type
            shoeType = type,

            // Price
            shoePrice = price,

            // Color
            shoeColor = color,

            // Size
            shoeSize = size,

            // Shoe Availability
            inStock = inStock,

            // Notes
            notes = notes
        )

        // Launching a new coroutine to insert an item in a non-blocking way
        viewModelScope.launch(Dispatchers.IO) {

            // This will update an existing shoe entry in the database
            shoeDao.update(shoe)

        }
    }

    // Action: Delete the selected record
    fun deleteShoe(shoe: Shoe) {

        // Launching a new coroutine to insert an item in a non-blocking way
        viewModelScope.launch(Dispatchers.IO) {

            // This will call the DAO method to delete the selected shoe record from the database
            shoeDao.delete(shoe)

        }

    }

    // This will validate user provide data, to make sure that no text field was left blank.
    fun isValidEntry(
        model: String,
        brand: String,
        type: String,
        price: String,
        color: String,
        size: String,
        notes: String
    ): Boolean {
        return model.isNotBlank() &&
                brand.isNotBlank() &&
                type.isNotBlank() &&
                price.isNotBlank() &&
                color.isNotBlank() &&
                size.isNotBlank() &&
                notes.isNotBlank()
    }

}

// This will instantiate the ShoeViewModel instance
// In summary, this viewModelFactory will take a ShoeDao as a property and create the ShoeViewModel
//
// Important Steps:
// 1. Pass in the same constructor parameter as the ShoeViewModel that is the ItemDao instance.
// 2. Extend the class from the ViewModelProvider.Factory class.
class ShoeViewModelFactory(private val shoeDao: ShoeDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        // Check if the modelClass is the same as the ShoeViewModel class
        if (modelClass.isAssignableFrom(ShoeViewModel::class.java)) {

            // If the model class is same then return an instance of it
            @Suppress("UNCHECKED_CAST")
            return ShoeViewModel(shoeDao) as T

        }

        // Error Handling: Otherwise, throw an exception error.
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}