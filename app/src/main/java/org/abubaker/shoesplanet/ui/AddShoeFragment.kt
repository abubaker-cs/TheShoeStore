package org.abubaker.shoesplanet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.abubaker.shoesplanet.BaseApplication
import org.abubaker.shoesplanet.R
import org.abubaker.shoesplanet.databinding.FragmentAddShoeBinding
import org.abubaker.shoesplanet.model.Shoe
import org.abubaker.shoesplanet.ui.viewmodel.ShoeViewModel
import org.abubaker.shoesplanet.ui.viewmodel.ShoeViewModelFactory

class AddShoeFragment : Fragment() {

    private val navigationArgs: AddShoeFragmentArgs by navArgs()

    private var _binding: FragmentAddShoeBinding? = null

    private lateinit var shoe: Shoe

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

        // Inflate: @layout/fragment_add_shoe.xml
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_shoe, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the ID of the Entry Item
        val id = navigationArgs.id

        // We are checking if the ID already exists in the Database
        if (id > 0) {

            // This is an observer method, we will use it to pass the user's provided data and start
            // the storage process.
            viewModel.getShoe(id).observe(this.viewLifecycleOwner) { selectedShoe ->

                // Store the user provide data in the shoe variable
                shoe = selectedShoe

                // Pass in the user's provided data, and initialize the bindShoe() function
                bindShoe(shoe)

            }

            // This will be helpful to let the user DELETE existing record, (in the Edit Mode)
            binding.deleteBtn.visibility = View.VISIBLE
            binding.deleteBtn.setOnClickListener {
                deleteShoe(shoe)
            }

        } else {

            //  Button: Save
            //  The save button will be only shown if the entry ID has not been found in the previous records.
            binding.saveBtn.setOnClickListener {

                // Save the new entry
                addShoe()

            }
        }
    }

    // Delete function
    private fun deleteShoe(shoe: Shoe) {

        // Pass the reference to selected entry, and initialize the delete function.
        // *****
        // We are passing the reference of our selected entry to the deleteShoe() function
        // stored in the ShoeViewModel.kt file.
        viewModel.deleteShoe(shoe)

        // After deleting, take the user back to the List Fragment.
        findNavController().navigate(
            R.id.action_addShoeFragment_to_shoeListFragment
        )

    }

    // Add Function
    private fun addShoe() {


        if (isValidEntry()) {

            viewModel.addShoe(

                // Shoe Model
                binding.shoeModelInput.text.toString(),

                // Brand / Designer
                binding.tvShoeDesigner.text.toString(),

                // Shoe Type
                binding.tvShoeType.text.toString(),

                // Price
                binding.shoePriceInput.text.toString(),

                // Color
                binding.tvShoeColor.text.toString(),

                // Shoe Size
                binding.tvShoeSize.text.toString(),

                // Shoe Availability
                binding.inStockCheckbox.isChecked,

                // Notes
                binding.notesInput.text.toString()

            )

            // After completing the addShoe() function, navigate the user back to the List Fragment.
            findNavController().navigate(
                R.id.action_addShoeFragment_to_shoeListFragment
            )

        } else {

            // If the Edit fields are left blank, then inform the user to provide complete data.
            Toast.makeText(
                requireContext(),
                "Please provide the complete data.",
                Toast.LENGTH_SHORT
            )
                .show()

        }
    }

    private fun updateShoe() {

        if (isValidEntry()) {

            //
            viewModel.updateShoe(
                id = navigationArgs.id,
                model = binding.shoeModelInput.text.toString(),
                brand = binding.tvShoeDesigner.text.toString(),
                type = binding.tvShoeType.text.toString(),
                price = binding.shoePriceInput.text.toString(),
                color = binding.tvShoeColor.text.toString(),
                size = binding.tvShoeSize.text.toString(),
                inStock = binding.inStockCheckbox.isChecked,
                notes = binding.notesInput.text.toString()
            )

            // Navigate the user back to the Shoe List Fragment
            findNavController().navigate(
                R.id.action_addShoeFragment_to_shoeListFragment
            )

        } else {

            // If the Edit fields are left blank, then inform the user to provide complete data.
            Toast.makeText(
                requireContext(),
                "Please provide the complete data.",
                Toast.LENGTH_SHORT
            )
                .show()

        }

    }

    /**
     * TODO
     */
    private fun bindShoe(shoe: Shoe) {
        binding.apply {

            // Shoe Model
            shoeModelInput.setText(shoe.modelNumber, TextView.BufferType.SPANNABLE)

            //
            tvShoeDesigner.setText(shoe.brandName, TextView.BufferType.SPANNABLE)

            //
            tvShoeType.setText(shoe.shoeType, TextView.BufferType.SPANNABLE)

            //
            shoePriceInput.setText(shoe.shoePrice, TextView.BufferType.SPANNABLE)

            //
            tvShoeColor.setText(shoe.shoeColor, TextView.BufferType.SPANNABLE)

            //
            tvShoeSize.setText(shoe.shoeSize, TextView.BufferType.SPANNABLE)

            //
            inStockCheckbox.isChecked = shoe.inStock

            //
            notesInput.setText(shoe.notes, TextView.BufferType.SPANNABLE)

            bindDataWithExposedDropdownMenus()

            saveBtn.setOnClickListener {
                updateShoe()
            }

        }

    }

    // Safeguard
    override fun onResume() {
        super.onResume()
        bindDataWithExposedDropdownMenus()
    }

    // TODO - add more fields for validation
    private fun isValidEntry() = viewModel.isValidEntry(
        binding.tvShoeDesigner.text.toString(),
        binding.shoeModelInput.text.toString()
    )

    private fun bindDataWithExposedDropdownMenus() {

        // Shoe Type
        val type = resources.getStringArray(R.array.shoe_type)
        val arrayAdapterFootwear =
            ArrayAdapter(requireContext(), R.layout.dropdown_menu_item, type)
        binding.tvShoeType.setAdapter(arrayAdapterFootwear)

        // Designer
        val designer = resources.getStringArray(R.array.shoe_designer)
        val arrayAdapterDesigner =
            ArrayAdapter(requireContext(), R.layout.dropdown_menu_item, designer)
        binding.tvShoeDesigner.setAdapter(arrayAdapterDesigner)

        // Color
        val color = resources.getStringArray(R.array.shoe_colors)
        val arrayAdapterColor = ArrayAdapter(requireContext(), R.layout.dropdown_menu_item, color)
        binding.tvShoeColor.setAdapter(arrayAdapterColor)

        // Size
        val size = resources.getStringArray(R.array.shoe_size)
        val arrayAdapterSize = ArrayAdapter(requireContext(), R.layout.dropdown_menu_item, size)
        binding.tvShoeSize.setAdapter(arrayAdapterSize)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}