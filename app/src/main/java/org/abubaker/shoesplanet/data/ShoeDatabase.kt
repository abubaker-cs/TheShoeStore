package org.abubaker.shoesplanet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.abubaker.shoesplanet.model.Shoe

/**
 * FILE 04
 *
 * Room database to persist data for the Shoes Planet app.
 * This database stores a [Shoe] entity
 */
@Database(entities = [Shoe::class], version = 1, exportSchema = false)
abstract class ShoeDatabase : RoomDatabase() {

    //
    abstract fun shoeDao(): ShoeDao

    //
    companion object {

        // This helps in maintaining a single instance of the database opened at a given time.
        @Volatile
        private var INSTANCE: ShoeDatabase? = null

        // Action: get the reference to the database
        fun getDatabase(context: Context): ShoeDatabase {

            /**
             * Multiple threads can potentially run into a race condition and ask for a database
             * instance at the same time, resulting in two databases instead of one. Wrapping the
             * code to get the database inside a synchronized block means that only one thread of
             * execution at a time can enter this block of code, which makes sure the database only
             * gets initialized once.
             */
            return INSTANCE ?: synchronized(this) {

                // Use Room's Room.databaseBuilder to create (shoes_database) database only if it
                // doesn't exist. Otherwise, return the existing database.

                // Inside the synchronized block, we will initialize the instance variable, and
                // use the database builder to get the database.

                // Pass in:
                // 1. application context
                // 2. database class
                // 3. name for the database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoeDatabase::class.java,
                    "shoes_database"
                )
                    // Migration Strategy
                    .fallbackToDestructiveMigration()
                    .build()

                // Initialize the instance based on the:
                // 1. Application context,
                // 2. Database class and
                // 3. Database name
                INSTANCE = instance

                return instance
            }

        }

    }

}