package org.abubaker.shoesplanet.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.abubaker.shoesplanet.databinding.ListItemShoeBinding
import org.abubaker.shoesplanet.model.Shoe

/**
 * FILE 06
 *
 * Adapter for the RecyclerView being used in the ShoeListFragment.kt file
 */
class ShoeListAdapter(
    private val clickListener: (Shoe) -> Unit
) : ListAdapter<Shoe, ShoeListAdapter.ShoeViewHolder>(DiffCallback) {

    //
    class ShoeViewHolder(
        private var binding: ListItemShoeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        //
        fun bind(shoe: Shoe) {
            binding.shoe = shoe
            binding.executePendingBindings()
        }

    }

    //
    companion object DiffCallback : DiffUtil.ItemCallback<Shoe>() {

        //
        override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
            return oldItem.id == newItem.id
        }

        //
        override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
            return oldItem == newItem
        }

    }

    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {

        return ShoeViewHolder(
            ListItemShoeBinding.inflate(LayoutInflater.from(parent.context))
        )


    }

    //
    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {

        //
        val currentItem = getItem(position)

        //
        holder.itemView.setOnClickListener {
            clickListener(currentItem)
        }

        //
        holder.bind(currentItem)
    }

}