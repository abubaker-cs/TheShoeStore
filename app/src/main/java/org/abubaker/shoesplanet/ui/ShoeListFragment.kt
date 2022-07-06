package org.abubaker.shoesplanet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import org.abubaker.shoesplanet.BaseApplication
import org.abubaker.shoesplanet.R
import org.abubaker.shoesplanet.databinding.FragmentShoeListBinding
import org.abubaker.shoesplanet.databinding.ListItemShoeBinding
import org.abubaker.shoesplanet.model.Shoe
import org.abubaker.shoesplanet.ui.adapter.ShoeListAdapter
import org.abubaker.shoesplanet.ui.viewmodel.ShoeViewModel
import org.abubaker.shoesplanet.ui.viewmodel.ShoeViewModelFactory


/**
 * FILE 07
 *
 * This file will display all records from the database.
 */
class ShoeListFragment : Fragment() {

    private var _binding: FragmentShoeListBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    // Get reference to our viewModel i.e. ShoeViewModel
    private val viewModel: ShoeViewModel by activityViewModels {

        // The ShoeViewModelFactory will take an instance of the Database retrieved from BaseApplication
        ShoeViewModelFactory(
            (activity?.application as BaseApplication).database.shoeDao()
        )

    }

    /**
     * Inflate XML Layout
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate: @layout/fragment_shoe_list.xml
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.lifecycleOwner = this

        // Setup the adapter
        val adapter = ShoeListAdapter { shoe ->

            // Pass the reference of the currently selected shoe item, so the user can be navigated
            // to the "Details Screen" as defined in the @res/navigation/nav_graph.xml
            val action =
                ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(shoe.id)

            // Navigate the user to the "Shoe Detail" Fragment. @layout/fragment_shoe_details.xml
            findNavController().navigate(action)
        }

        // Observe the list of shoes from the viewModel and submit it the adapter
        viewModel.allShoes.observe(this.viewLifecycleOwner) { shoes ->

            // If the database is empty then display a message, otherwise show the RecyclerView,
            // so the list can be populated in it.
            if (shoes.isEmpty()) {

                // If the database is empty then display "No Records found" message.
                binding.apply {
                    llShoesList.visibility = View.GONE
                    emptyView.visibility = View.VISIBLE
                }

            } else {

                // Prepare the UI to display RecyclerView
                binding.apply {
                    llShoesList.visibility = View.VISIBLE
                    emptyView.visibility = View.GONE
                }

                shoes.forEach { shoe ->
                    addView(shoe)
                }

                // Populate the recyclerview with the records.
                shoes.let {
                    adapter.submitList(it)
                }

            }

        }

        // binding.apply is the shorthand, i.e. instead of typing bind.* for ever UI component,
        // we can simply wrap the code using binding.apply{} and then directly type the IDs
        binding.apply {

            // This is the reference to the adapter, which we recently defined in the same file.
            // recyclerView.adapter = adapter

            // FAB: Add New Shoe Entry
            // We are now passing the argument to the AddShoeFragment, so it can update the toolbar's
            // title to display "Add New Shoe"
            addShoeFab.setOnClickListener {

                // Navigate the user to the "Add Shoe" screen (@layout/fragment_add_shoe.xml)
                val action = ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment(
                    getString(R.string.add_fragment_title)
                )

                findNavController().navigate(action)

            }

        }

        return binding.root

    }


    private fun addView(shoe: Shoe) {

        val listEntry = ListItemShoeBinding.inflate(
            layoutInflater, null, false
        )

        // Reference to the dataBinding variable in list_item_shoe.xml
        listEntry.shoe = shoe

        // This will avoid duplication in the LinearLayout
        // Call this method to remove all child views from the ViewGroup.
        // binding.llShoesList.removeAllViews()

        binding.llShoesList.addView(
            listEntry.root
        )

    }

}