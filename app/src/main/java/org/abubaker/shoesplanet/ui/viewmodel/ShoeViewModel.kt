package org.abubaker.shoesplanet.ui.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.abubaker.shoesplanet.data.ShoeDao
import org.abubaker.shoesplanet.model.Shoe

/**
 * FILE 03
 *
 * Shared ViewModel to provide data to the:
 * 1. ShoeListFragment
 * 2. ShoeDetailFragment
 *
 * and allow for interaction the the ShoeDao
 */
class ShoeViewModel(private val shoeDao: ShoeDao) : ViewModel() {

//    private val _modelNumber = MutableLiveData<String>()
//    val modelNumber: LiveData<String>
//        get() = _modelNumber

    // MutableLiveData = It is the data whose values can be changed (Variable)
    private var _model = MutableLiveData<String>() // R/W for Internal
    val models: LiveData<String>
        get() = _model // Write Only | For External


    // This property will set a list of all shoes from the DAO
    val allShoes: LiveData<List<Shoe>> = shoeDao.getShoes().asLiveData()

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