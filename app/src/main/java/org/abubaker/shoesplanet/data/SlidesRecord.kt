package org.abubaker.shoesplanet.data

import org.abubaker.shoesplanet.R
import org.abubaker.shoesplanet.model.Slide

object SlidesRecord {

    // This will contain dummy data for the slides that will be displayed using ViewPager2
    val Slides = listOf(

        // Slide #1
        Slide(
            R.drawable.shoe01,
            "Instruction #1",
            "Mauris quis iaculis ligula. Vivamus sollici \ntudin, diam vitae maximus pulvinar scing."
        ),

        // Slide #2
        Slide(
            R.drawable.shoe02,
            "Instruction #2",
            "Lorem ipsum dolor sit amet, consectetur adipi \nscing elit, sed do eiusmod tempor amet."
        ),

        // Slide #3
        Slide(
            R.drawable.shoe03,
            "Instruction #3",
            "Quisque id lacus sit amet magna ultricies lobo \nrtis ut aliquip ex ea commodo consequat."
        )

    )

}