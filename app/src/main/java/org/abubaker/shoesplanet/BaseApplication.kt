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

    // Use lazy delegate so the instance database is lazily created when you first need/access the
    // reference (rather than when the app starts) .
    val database: ShoeDatabase by lazy {

        //  Instantiate the database instance by calling getDatabase() on ShoeDatabase passing in the context
        ShoeDatabase.getDatabase(this)

    }

}