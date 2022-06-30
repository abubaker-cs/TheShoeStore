package org.abubaker.shoesplanet.model

import androidx.annotation.NonNull
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
    val id: Long = 0,

    // Model
    @ColumnInfo(name = "model_number")
    val modelNumber: String,

    // Brand
    @NonNull @ColumnInfo(name = "brand_name")
    val brandName: String,

    // Price
    @NonNull @ColumnInfo(name = "shoe_price")
    val shoePrice: String,

    // Color
    @NonNull @ColumnInfo(name = "shoe_color")
    val shoeColor: String,

    // Size
    @NonNull @ColumnInfo(name = "shoe_size")
    val shoeSize: String,

    // Stock Availability
    @ColumnInfo(name = "in_stock")
    val inStock: Boolean,

    // Notes
    @ColumnInfo(name = "notes")
    val notes: String?

)