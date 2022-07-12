package org.abubaker.shoesplanet.ui

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.abubaker.shoesplanet.BaseApplication
import org.abubaker.shoesplanet.R
import org.abubaker.shoesplanet.databinding.FragmentShoeListBinding
import org.abubaker.shoesplanet.databinding.ListItemShoeBinding
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

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

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

                // Important, to clear all existing views from the #ll_shoes_list LinearLayout,
                // otherwise shoes.froEach() will recreate existing entries in the LinearLayout
                binding.llShoesList.removeAllViews()

                shoes.forEach {

                    DataBindingUtil.inflate<ListItemShoeBinding>(
                        layoutInflater,
                        R.layout.list_item_shoe,
                        binding.llShoesList,
                        true
                    ).apply {
                        this.shoe = it
                    }

                }


            }

        }

        // binding.apply is the shorthand, i.e. instead of typing bind.* for ever UI component,
        // we can simply wrap the code using binding.apply{} and then directly type the IDs
        binding.apply {

            // This is the reference to the adapter, which we recently defined in the same file.
            // recyclerView.adapter = adapter

            // FAB: Add New Shoe Entry
            // We are now passing the argument to the ShoeDetailsFragment, so it can update the toolbar's
            // title to display "Add New Shoe"
            addShoeFab.setOnClickListener {

                // Reset the value of _readyToSave
                viewModel.resetSaveState()

                // Navigate the user to the "Add Shoe" screen (@layout/fragment_add_shoe.xml)
                val action = ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment(
                    getString(R.string.add_fragment_title)
                )

                findNavController().navigate(action)

            }

        }

        return binding.root

    }

    // Logout Menu
    // Note setHasOptionsMenu(true) has been depreciated, we will be using addMenuProvider():
    // https://developer.android.com/jetpack/androidx/releases/activity#1.4.0-alpha01
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // The usage of an interface lets you inject your own implementation
        val menuHost: MenuHost = requireActivity()

        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                // Reference: https://developer.android.com/guide/navigation/navigation-navigate#id
                view.findNavController().navigate(R.id.signInFragment)
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

}