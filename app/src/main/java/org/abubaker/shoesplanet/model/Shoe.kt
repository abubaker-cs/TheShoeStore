package org.abubaker.shoesplanet.model

import androidx.annotation.NonNull
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
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
    var id: Long,

    // Model
    @ColumnInfo(name = "model_number")
    var modelNumber: String,

    // Brand / Designer
    @NonNull @ColumnInfo(name = "brand_name")
    var brandName: String,

    // Footwear
    @NonNull @ColumnInfo(name = "shoe_type")
    var shoeType: String,

    // Price
    @NonNull @ColumnInfo(name = "shoe_price")
    var shoePrice: String,

    // Color
    @NonNull @ColumnInfo(name = "shoe_color")
    var shoeColor: String,

    // Size
    @NonNull @ColumnInfo(name = "shoe_size")
    var shoeSize: String,

    // Stock Availability
    @ColumnInfo(name = "in_stock")
    var inStock: Boolean,

    // Notes
    @ColumnInfo(name = "notes")
    var notes: String?

) : BaseObservable() {

    // @PrimaryKey(autoGenerate = true)
    var userId: Long? = id

    // 2. Model Name
    @get:Bindable
    @Ignore // This annotation will tell Room to not create a new column
    var oModelNumber: String = ""
        set(value) {
            field = value
            modelNumber = value
            notifyPropertyChanged(BR.oModelNumber)
        }

    // 2. Brand or Designer
    @get:Bindable
    @Ignore
    var oBrandName: String = ""
        set(value) {
            field = value
            brandName = value
            notifyPropertyChanged(BR.oBrandName)
        }

    // 3. Shoe Type
    @get:Bindable
    @Ignore
    var oShoeType: String = ""
        set(value) {
            field = value
            shoeType = value
            notifyPropertyChanged(BR.oShoeType)
        }

    // 4. PRice
    @get:Bindable
    @Ignore
    var oShoePrice: String = ""
        set(value) {
            field = value
            shoePrice = value
            notifyPropertyChanged(BR.oShoePrice)
        }

    // 5. Color
    @get:Bindable
    @Ignore
    var oShoeColor: String = ""
        set(value) {
            field = value
            shoeColor = value
            notifyPropertyChanged(BR.oShoeColor)
        }

    // 6. Size
    @get:Bindable
    @Ignore
    var oShoeSize: String = ""
        set(value) {
            field = value
            shoeSize = value
            notifyPropertyChanged(BR.oShoeSize)
        }

    // 7. Stock Availability
    @get:Bindable
    @Ignore
    var oInStock: Boolean = false
        set(value) {
            field = value
            inStock = value
            notifyPropertyChanged(BR.oInStock)
        }

    // 8. Notes
    @get:Bindable
    @Ignore
    var oNotes: String = ""
        set(value) {
            field = value
            notes = value
            notifyPropertyChanged(BR.oNotes)
        }

}

/**
 * References which I studied to understand and implement Observables in this @Entity data class
 * =============================================================================================
 *
 * 1. Advanced Data Binding in Android: Observables
 * https://www.raywenderlich.com/27690200-advanced-data-binding-in-android-observables
 *
 * 2. Android’s Data Binding with Kotlin
 * https://medium.com/@jencisov/androids-data-binding-with-kotlin-df94a24ffc0f
 *
 * 3. Android’s Data Binding’s BaseObservable class and @Bindable annotation in Kotlin
 * https://medium.com/@jencisov/androids-data-binding-s-baseobservable-class-and-bindable-annotation-in-kotlin-1a5c6682a3c1
 */