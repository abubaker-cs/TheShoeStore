package org.abubaker.shoesplanet.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.abubaker.shoesplanet.R
import org.abubaker.shoesplanet.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    // Inflate XML file:
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // @layout/fragment_sign_up.xml
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Back Arrow: Take the user back to the Signup Fragment
        binding.btnBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_signUpFragment_to_signInFragment
            )
        }

        // Signup Button:  Take the user back to the Signup Fragment
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(
                R.id.action_signUpFragment_to_signInFragment
            )
        }

    }

}