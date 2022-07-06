package org.abubaker.shoesplanet.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.abubaker.shoesplanet.model.Shoe

/**
 * FILE 02
 *
 * Data Access Object for database interaction.
 */

@Dao
interface ShoeDao {

    // Insert a Shoe into the database by using the OnConflictStrategy.REPLACE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shoe: Shoe)

    // Update an existing Shoe Record
    @Update
    suspend fun update(shoe: Shoe)

    // Delete a Shoe from the database.
    @Delete
    suspend fun delete(shoe: Shoe)

    /**
     * Flow:
     *
     * Using Flow or LiveData as return type will ensure we get notified whenever the data in the
     * database changes. Because of the Flow return type, Room also runs the query on the background thread.
     * We don't need to explicitly make it a suspend function and call inside a coroutine scope.
     */

    // Retrieve a Shoe from the database by id
    @Query("SELECT * from shoes_database WHERE id = :id")
    fun getShoe(id: Long): Flow<Shoe>

    // Retrieve all Shoe from the database
    @Query("SELECT * from shoes_database ORDER BY brand_name ASC")
    fun getShoes(): Flow<List<Shoe>>

}