package org.abubaker.shoesplanet.data

import org.abubaker.shoesplanet.R
import org.abubaker.shoesplanet.model.Slide

object SlidesRecord {

    // This will contain dummy data for the slides that will be displayed using ViewPager2
    val Slides = listOf(

        // Slide #1
        Slide(

            // Thumbnail
            R.drawable.shoe01,

            // Title
            "Instruction #1",

            // Description
            "Mauris quis iaculis ligula. Vivamus sollici tudin, diam vitae maximus pulvinar."
        ),

        // Slide #2
        Slide(

            // Thumbnail
            R.drawable.shoe02,

            // Title
            "Instruction #2",

            // Description
            "Lorem ipsum dolor sit amet, consectetur adipi scing elit, sed do eiusmod."
        ),

        // Slide #3
        Slide(

            // Thumbnail
            R.drawable.shoe03,

            // Title
            "Instruction #3",

            // Description
            "Quisque id lacus sit amet magna ultricies lobo rtis ut aliquip exea commodo."
        )

    )

}