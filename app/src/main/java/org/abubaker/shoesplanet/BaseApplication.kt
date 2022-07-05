package org.abubaker.shoesplanet

import android.app.Application
import org.abubaker.shoesplanet.data.ShoeDatabase

/**
 * File 05
 *
 * An application class that inherits from [Application], allows for the creation of a singleton
 * instance of the [ShoeDatabase]
 *
 */

// We will instantiate the database instance in the Application class
class BaseApplication : Application() {

    // I am using lazy delegate so the instance database is lazily created whenever access is needed
    // to the database (rather than when the app starts) .
    val database: ShoeDatabase by lazy {

        // Calling the getDatabase() while passing in the context will instantiate the database.
        ShoeDatabase.getDatabase(this)

    }

}