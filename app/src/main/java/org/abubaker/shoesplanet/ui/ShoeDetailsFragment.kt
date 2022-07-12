package org.abubaker.shoesplanet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.abubaker.shoesplanet.BaseApplication
import org.abubaker.shoesplanet.R
import org.abubaker.shoesplanet.databinding.FragmentShoeDetailsBinding
import org.abubaker.shoesplanet.ui.viewmodel.ShoeViewModel
import org.abubaker.shoesplanet.ui.viewmodel.ShoeViewModelFactory

class ShoeDetailsFragment : Fragment() {

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

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Cancel Button:
        binding.cancelBtn.setOnClickListener {
            view.findNavController().navigate(R.id.shoeListFragment)
        }

        //  TODO Button: Save
        //  The save button will be only shown if the entry ID has not been found in the previous records.
        binding.saveBtn.setOnClickListener {

            // Save the new entry
            addShoe()

        }
    }

    // addShoe() - Add Function, this will create new entry in the database.
    private fun addShoe() {

        // We wil first validate user provided entries, to make sure that required data was provided.
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

    // This will capture and send user provided data to the ShoeViewModel.kt for validation
    private fun isValidEntry() = viewModel.isValidEntry(

        // Shoe Model
        binding.shoeModelInput.text.toString(),

        // Brand
        binding.tvShoeDesigner.text.toString(),

        // Shoe Type
        binding.tvShoeType.text.toString(),

        // Price
        binding.shoePriceInput.text.toString(),

        // Color
        binding.tvShoeColor.text.toString(),

        // Shoe Size
        binding.tvShoeSize.text.toString(),

        // Notes
        binding.notesInput.text.toString()

    )

    // This function will be used to populate lists in the dropdown menus
    // Note: The keywords are defined in the @res/values/strings.xml file
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

    // Safeguard - Populate Lists in Dropdown Menus onResume() lifecycle event.
    override fun onResume() {
        super.onResume()

        // This function will be used to populate lists in the dropdown menus
        // Note: The keywords are defined in the @res/values/strings.xml file
        bindDataWithExposedDropdownMenus()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}