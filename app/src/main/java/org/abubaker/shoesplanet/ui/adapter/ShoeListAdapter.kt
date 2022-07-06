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

    // ListItemShoeBinding refers to generated binding classes for @layout/list_item_shoe.xml
    class ShoeViewHolder(
        private var binding: ListItemShoeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(shoe: Shoe) {

            // This is the reference to the shoe variable defined in the list_item_shoes.xml using dataBinding
            binding.shoe = shoe

            // Evaluates the pending bindings, updating any Views that have expressions bound to modified variables.
            binding.executePendingBindings()
        }

    }

    // DiffUtil.ItemCallback is the native class responsible for calculating the difference between
    // the two lists. TIt exists to improve RecyclerView’s performance when handling list updates.
    //
    // Reference:
    // https://www.raywenderlich.com/21954410-speed-up-your-android-recyclerview-using-diffutil
    companion object DiffCallback : DiffUtil.ItemCallback<Shoe>() {

        // An Item (i.e. Shoe record) consists of an:
        // 1. id,
        // 2. its value,
        // 3. timeStamp, and
        // 4. information stating if it’s done (checked) or not.
        override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
            return oldItem.id == newItem.id
        }

        // To avoid redesigning the entire list when there’s a change, only the items that have
        // different values between both lists will be updated.
        override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
            return oldItem == newItem
        }

    }

    // // Inflate XML file:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {

        // @layout/list_item_shoe.xml
        return ShoeViewHolder(
            ListItemShoeBinding.inflate(LayoutInflater.from(parent.context))
        )


    }

    // This will bind data and hold the record of the current Shoe
    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {

        // Get reference of the current Shoe, i.e. the unique #id
        val currentItem = getItem(position)

        // Setting the click listener in onBindViewHolder is easy because the adapter is holding the
        // list and we can access data items by their position in onBindViewHolder directly
        //
        // Reference:
        // https://oozou.com/blog/a-better-way-to-handle-click-action-in-a-recyclerview-item-60
        holder.itemView.setOnClickListener {
            clickListener(currentItem)
        }

        // This will pass the reference of the currentItem
        holder.bind(currentItem)
    }

}