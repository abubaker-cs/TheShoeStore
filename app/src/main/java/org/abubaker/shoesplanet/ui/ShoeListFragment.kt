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
     * Inflate XML Layout: @layout/fragment_shoe_list.xml
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                    recyclerView.visibility = View.GONE
                    emptyView.visibility = View.VISIBLE
                }

            } else {

                // Prepare the UI to display RecyclerView
                binding.apply {
                    recyclerView.visibility = View.VISIBLE
                    emptyView.visibility = View.GONE
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
            recyclerView.adapter = adapter

            // FAB: Add New Shoe Entry
            addShoeFab.setOnClickListener {

                // Navigate the user to the "Add Shoe" screen (@layout/fragment_add_shoe.xml)
                findNavController().navigate(
                    R.id.action_shoeListFragment_to_addShoeFragment
                )

            }

        }

    }

}