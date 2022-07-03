package org.abubaker.shoesplanet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // TO DO: Refactor the creation of the view model to take an instance of
    //  ShoeViewModelFactory. The factory should take an instance of the Database retrieved
    //  from BaseApplication
    private val viewModel: ShoeViewModel by activityViewModels {
        ShoeViewModelFactory(
            (activity?.application as BaseApplication).database.shoeDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_shoe_list, container, false)

        // Inflate the layout for this fragment
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ShoeListAdapter { shoe ->

            // List > Details
            val action =
                ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment(shoe.id)

            //
            findNavController().navigate(action)
        }

        // lifecycle.coroutineScope.launch {}

        // Observe the list of shoes from the viewModel and submit it the adapter
        viewModel.allShoes.observe(this.viewLifecycleOwner) { shoes ->

            if (shoes.isEmpty()) {

                // Display "No records found" view
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

        binding.apply {

            recyclerView.adapter = adapter

            addShoeFab.setOnClickListener {

                findNavController().navigate(
                    R.id.action_shoeListFragment_to_addShoeFragment
                )

            }

        }
    }

}