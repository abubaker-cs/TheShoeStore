package org.abubaker.shoesplanet.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.abubaker.shoesplanet.databinding.ListItemSlideBinding
import org.abubaker.shoesplanet.model.Slide

class SlideAdapter(private val introList: List<Slide>) :
    RecyclerView.Adapter<SlideAdapter.SlideViewHolder>() {

    // Bind UI
    inner class SlideViewHolder(private val binding: ListItemSlideBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(slide: Slide) {

            // Thumbnail
            binding.iconIv.setImageResource(slide.photo)

            // Title
            binding.titleTv.text = slide.title

            // Description
            binding.descTv.text = slide.desc

        }
    }


    // Inflate XML file.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        return SlideViewHolder(
            ListItemSlideBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    //
    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        holder.bindItem(introList[position])
    }

    // Total Items to display
    override fun getItemCount(): Int {
        return introList.size
    }
}