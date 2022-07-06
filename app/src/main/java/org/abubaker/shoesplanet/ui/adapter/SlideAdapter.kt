package org.abubaker.shoesplanet.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.abubaker.shoesplanet.databinding.ListItemSlideBinding
import org.abubaker.shoesplanet.model.Slide

/**
 * This Adapter class will be used to bind data for ViewPager2 in the SlideFragment.kt
 */
class SlideAdapter(private val introList: List<Slide>) :
    RecyclerView.Adapter<SlideAdapter.SlideViewHolder>() {

    // SlideViewHolder: It will be used to bind data with UI components
    inner class SlideViewHolder(private val binding: ListItemSlideBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // This will receive a single instance of Slide and bind data with it.
        fun bindItem(slide: Slide) {

            // Thumbnail
            binding.iconIv.setImageResource(slide.photo)

            // Title
            binding.titleTv.text = slide.title

            // Description
            binding.descTv.text = slide.desc

        }

    }


    // Inflate XML file:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {

        // @layout/list_item_slide.xml
        return SlideViewHolder(
            ListItemSlideBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    // This will bind data and hold the record of the current Slide
    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        holder.bindItem(introList[position])
    }

    // This will return the total Items (rows) in the database
    override fun getItemCount(): Int {
        return introList.size
    }
}