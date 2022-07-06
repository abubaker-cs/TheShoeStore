package org.abubaker.shoesplanet.model

/**
 * This data class will create a single instance of Slide that will be used with ViewPager2
 */
data class Slide(

    // Thumbnail
    val photo: Int,

    // Title (Heading)
    val title: String,

    // Description
    val desc: String?
)