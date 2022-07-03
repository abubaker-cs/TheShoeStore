package org.abubaker.shoesplanet.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import org.abubaker.shoesplanet.R
import org.abubaker.shoesplanet.data.SlidesRecord
import org.abubaker.shoesplanet.databinding.FragmentOnboardingBinding
import org.abubaker.shoesplanet.ui.adapter.SlideAdapter

class SlideFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private var viewPager2: ViewPager2? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        // Bind the viewpager
        setupViewPager(binding)

        return binding.root
    }

    private val pager2Callback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            // Update the button
            if (position == SlidesRecord.Slides.size - 1) {
                binding.controllerBtn.text = "Finish"

                binding.controllerBtn.setOnClickListener {

                    findNavController().navigate(
                        R.id.action_onboardingFragment_to_signInFragment
                    )

                }

            } else {
                binding.controllerBtn.text = "Next"

                binding.controllerBtn.setOnClickListener {
                    viewPager2?.currentItem = position + 1
                }
            }

        }
    }

    private fun setupViewPager(binding: FragmentOnboardingBinding) {

        val adapter = SlideAdapter(SlidesRecord.Slides)
        viewPager2 = binding.viewPager
        viewPager2?.adapter = adapter
        viewPager2?.registerOnPageChangeCallback(pager2Callback)
        binding.dotsIndicator.setViewPager2(viewPager2!!)

    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager2?.unregisterOnPageChangeCallback(pager2Callback)
    }


}