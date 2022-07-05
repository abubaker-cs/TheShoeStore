package org.abubaker.shoesplanet.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.abubaker.shoesplanet.R
import org.abubaker.shoesplanet.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {


    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // _binding = FragmentSignInBinding.inflate(inflater, container, false)
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_shoeListFragment
            )
        }

        binding.btnCreateAccount.setOnClickListener {
            findNavController().navigate(
                R.id.action_signInFragment_to_signUpFragment
            )
        }

    }

}