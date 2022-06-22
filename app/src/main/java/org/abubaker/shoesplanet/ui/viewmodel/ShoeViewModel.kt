package org.abubaker.shoesplanet.ui.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.abubaker.shoesplanet.data.ShoeDao
import org.abubaker.shoesplanet.model.Shoe

/**
 * FILE 03
 *
 * Shared [ViewModel] to provide data to the [ShoeListFragment], [ShoeDetailFragment],
 * and [AddShoeFragment] and allow for interaction the the [ShoeDao]
 */
class ShoeViewModel(private val shoeDao: ShoeDao) : ViewModel() {

    // This property will set to a list of all shoes from the DAO
    val allShoes: LiveData<List<Shoe>> = shoeDao.getShoes().asLiveData()

    // This method will take id: Long as a parameter and retrieve a Shoe from the
    fun getShoe(id: Long): LiveData<Shoe> {
        return shoeDao.getShoe(id).asLiveData()
    }

    // Action: Add a new shoe entry
    fun addShoe(
        model: String,
        brand: String,
        price: String,
        color: String,
        size: String,
        inStock: Boolean,
        notes: String
    ) {
        val shoe = Shoe(
            modelNumber = model,
            brandName = brand,
            shoePrice = price,
            shoeColor = color,
            shoeSize = size,
            inStock = inStock,
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
        price: String,
        color: String,
        size: String,
        inStock: Boolean,
        notes: String
    ) {
        val shoe = Shoe(
            id = id,
            modelNumber = model,
            brandName = brand,
            shoePrice = price,
            shoeColor = color,
            shoeSize = size,
            inStock = inStock,
            notes = notes
        )
        viewModelScope.launch(Dispatchers.IO) {

            // TO DO: call the DAO method to update a shoe to the database here
            shoeDao.update(shoe)

        }
    }

    // Action: Delete the selected record
    fun deleteShoe(shoe: Shoe) {

        viewModelScope.launch(Dispatchers.IO) {

            // This will call the DAO method to delete the selected shoe record from the database
            shoeDao.delete(shoe)

        }

    }

    // Action: Validation
    fun isValidEntry(name: String, model: String): Boolean {
        return name.isNotBlank() && model.isNotBlank()
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