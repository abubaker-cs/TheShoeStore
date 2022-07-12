package org.abubaker.shoesplanet.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

    // Inflate XML file:
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // @layout/fragment_onboarding.xml
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding, container, false)

        // Bind the ViewPager2
        setupViewPager(binding)

        return binding.root
    }

    // Configuration settings for ViewPager2
    private val pager2Callback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {

            // Get reference to the current slide
            super.onPageSelected(position)

            // Update the button
            if (position == SlidesRecord.Slides.size - 1) {

                // If it is the last slide, then update the button with label: "Finish"
                binding.controllerBtn.text = "Finish"

                // Finish Button: Take the user to the
                binding.controllerBtn.setOnClickListener {

                    findNavController().navigate(
                        R.id.action_onboardingFragment_to_shoeListFragment
                    )

                }

            } else {

                // Default label for the Button: "Next"
                binding.controllerBtn.text = "Next"

                // Action: The the user to the next slide
                binding.controllerBtn.setOnClickListener {
                    viewPager2?.currentItem = position + 1
                }

            }

        }
    }

    // Setup for the ViewPager2
    private fun setupViewPager(binding: FragmentOnboardingBinding) {

        // Reference to the @data/SlidesRecord.kt file
        // It has the dummy data information, which we will use to populate the slides
        val adapter = SlideAdapter(SlidesRecord.Slides)

        // This is reference to #viewPager defined in the @layout/fragment_onboarding.xml file
        viewPager2 = binding.viewPager

        // We are binding dummy data with our view element
        viewPager2?.adapter = adapter

        // A callback that will be invoked whenever the page changes or is incrementally scrolled.
        viewPager2?.registerOnPageChangeCallback(pager2Callback)

        // Reference to the #dots_indicator defined in the @layout/fragment_onboarding.xml file,
        // This will be helpful for the user to visually understand the position of current slide.
        binding.dotsIndicator.attachTo(viewPager2!!)

    }

    override fun onDestroy() {
        super.onDestroy()

        // Remove a callback that was previously added via registerOnPageChangeCallback(pager2Callback)
        // in the setupViewPager()
        viewPager2?.unregisterOnPageChangeCallback(pager2Callback)

    }


}