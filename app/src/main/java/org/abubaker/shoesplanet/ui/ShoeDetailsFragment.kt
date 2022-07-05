package org.abubaker.shoesplanet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.abubaker.shoesplanet.BaseApplication
import org.abubaker.shoesplanet.R
import org.abubaker.shoesplanet.databinding.FragmentShoeDetailsBinding
import org.abubaker.shoesplanet.model.Shoe
import org.abubaker.shoesplanet.ui.viewmodel.ShoeViewModel
import org.abubaker.shoesplanet.ui.viewmodel.ShoeViewModelFactory

class ShoeDetailsFragment : Fragment() {

    // This reference will be used to retrieve the ID, so information about correct record can be displayed.
    private val navigationArgs: ShoeDetailsFragmentArgs by navArgs()

    private lateinit var shoe: Shoe

    private var _binding: FragmentShoeDetailsBinding? = null

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

        // Inflate: @layout/fragment_shoe_details.xml
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // We are ID of shoe record, that was clicked by the user in the "List Fragment",
        // This reference will be helpful for passing the information and allowing the user
        // to modify correct record.
        val id = navigationArgs.id

        // This is an observer method, we will use it to pass the user's provided data and start
        // the storage process.
        viewModel.getShoe(id).observe(this.viewLifecycleOwner) { selectedShoe ->

            // Store the user provide data in the shoe variable
            shoe = selectedShoe

            // Pass in the user's provided data, and initialize the bindShoe() function
            bindShoe(shoe)

        }


    }

    // This function will be used to store information
    private fun bindShoe(shoe: Shoe) {

        // Shorthand, i.e. the binding wrapper
        binding.apply {

            // Shoe Model Number
            shoeModel.text = shoe.modelNumber

            // Shoe Designer or Brand
            brandName.text = shoe.brandName

            // Price
            shoePrice.text = shoe.shoePrice

            // Shoe Type
            shoeType.text = shoe.shoeType

            // Color
            shoeColor.text = shoe.shoeColor

            // Shoe Size
            shoeSize.text = shoe.shoeSize

            // In Stock?
            // Conditional : We need to first check if the user has selected "in stock" option
            inStock.text = shoe.inStock.toString()
            if (shoe.inStock) {
                inStock.text = getString(R.string.in_stock)
            } else {
                inStock.text = getString(R.string.out_of_stock)
            }

            // Notes
            shoeNotes.text = shoe.notes

            // FAB: Edit Button
            editShoeFab.setOnClickListener {

                // Navigate the user to the (@layout/fragment_add_shoe.xml) for editing the existing
                // record. I will be using the same "Add Fragment", by replacing the "Add Button" with
                // Save and Delete options (buttons).
                val action =
                    ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToAddShoeFragment(shoe.id)
                findNavController().navigate(action)

            }

        }

    }

}