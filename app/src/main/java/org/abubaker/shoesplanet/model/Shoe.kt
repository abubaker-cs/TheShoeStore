package org.abubaker.shoesplanet.model

import androidx.annotation.NonNull
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * FILE 01
 *
 * Shoe entity to be stored in the shoes_database.
 */
@Entity(tableName = "shoes_database")
data class Shoe(

    // @ColumnInfo(name = "id")
    // Primary Key
    @PrimaryKey(autoGenerate = true)
    val _id: Long,

    // Model
    @ColumnInfo(name = "model_number")
    var _modelNumber: String,

    // Brand / Designer
    @NonNull @ColumnInfo(name = "brand_name")
    val _brandName: String,

    // Footwear
    @NonNull @ColumnInfo(name = "shoe_type")
    val _shoeType: String,

    // Price
    @NonNull @ColumnInfo(name = "shoe_price")
    val _shoePrice: String,

    // Color
    @NonNull @ColumnInfo(name = "shoe_color")
    val _shoeColor: String,

    // Size
    @NonNull @ColumnInfo(name = "shoe_size")
    val _shoeSize: String,

    // Stock Availability
    @ColumnInfo(name = "in_stock")
    val _inStock: Boolean,

    // Notes
    @ColumnInfo(name = "notes")
    val _notes: String?

) : BaseObservable() {

    val id: Long
        get() = _id

    @get:Bindable
    var modelNumber: String = _modelNumber
        set(value) {
            field = value
            notifyPropertyChanged(BR.modelNumber)
        }

    @get:Bindable
    var brandName: String = _brandName
        set(value) {
            field = value
            notifyPropertyChanged(BR.brandName)
        }

    @get:Bindable
    var shoeType: String = _shoeType
        set(value) {
            field = value
            notifyPropertyChanged(BR.shoeType)
        }

    @get:Bindable
    var shoePrice: String = _shoePrice
        set(value) {
            field = value
            notifyPropertyChanged(BR.shoePrice)
        }

    @get:Bindable
    var shoeColor: String = _shoeColor
        set(value) {
            field = value
            notifyPropertyChanged(BR.shoeColor)
        }

    @get:Bindable
    var shoeSize: String = _shoeSize
        set(value) {
            field = value
            notifyPropertyChanged(BR.shoeSize)
        }

    @get:Bindable
    var inStock: Boolean = _inStock
        set(value) {
            field = value
            notifyPropertyChanged(BR.inStock)
        }

    @get:Bindable
    var notes: String? = _notes
        set(value) {
            field = value
            notifyPropertyChanged(BR.notes)
        }

}

/**
 * Advanced Data Binding in Android: Observables
 * https://www.raywenderlich.com/27690200-advanced-data-binding-in-android-observables
 *
 * Android’s Data Binding with Kotlin
 * https://medium.com/@jencisov/androids-data-binding-with-kotlin-df94a24ffc0f
 *
 * Android’s Data Binding’s BaseObservable class and @Bindable annotation in Kotlin
 * https://medium.com/@jencisov/androids-data-binding-s-baseobservable-class-and-bindable-annotation-in-kotlin-1a5c6682a3c1
 */