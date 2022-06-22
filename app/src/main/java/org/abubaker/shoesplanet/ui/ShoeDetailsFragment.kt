package org.abubaker.shoesplanet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailsFragment : Fragment() {

    private val navigationArgs: ShoeDetailsFragmentArgs by navArgs()

    // TO DO: Refactor the creation of the view model to take an instance of
    //  shoeViewModelFactory. The factory should take an instance of the Database retrieved
    //  from BaseApplication
    private val viewModel: ShoeViewModel by activityViewModels {
        ShoeViewModelFactory(
            (activity?.application as BaseApplication).database.shoeDao()
        )
    }

    private lateinit var shoe: Shoe

    private var _binding: FragmentShoeDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentShoeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id

        // T ODO: Observe a shoe that is retrieved by id, set the shoe variable,
        //  and call the bind shoe method

        viewModel.getShoe(id).observe(this.viewLifecycleOwner) { selectedShoe ->

            shoe = selectedShoe

            bindShoe(shoe)

        }


    }

    private fun bindShoe(shoe: Shoe) {

        binding.apply {
            brandName.text = shoe.brandName
            shoePrice.text = shoe.shoePrice
            shoeColor.text = shoe.shoeColor
            shoeSize.text = shoe.shoeSize
            inStock.text = shoe.inStock.toString()

            if (shoe.inStock) {
                inStock.text = getString(R.string.in_stock)
            } else {
                inStock.text = getString(R.string.out_of_stock)
            }

            shoeNotes.text = shoe.notes

            editShoeFab.setOnClickListener {
                val action =
                    ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToAddShoeFragment(shoe.id)
                findNavController().navigate(action)
            }

        }

    }

}