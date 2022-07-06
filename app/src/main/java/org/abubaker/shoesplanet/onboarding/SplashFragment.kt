package org.abubaker.shoesplanet.onboarding

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.abubaker.shoesplanet.R
import org.abubaker.shoesplanet.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val activityScope = CoroutineScope(Dispatchers.Main)

    // Inflate XML file:
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // @layout/fragment_splash.xml
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return binding.root

    }

    /**
     * We will configure the settings for CircularProgressBar in onViewCreated() lifecycle event.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Shorthand: Binding wrapper
        //
        // I am using 3rd party library: https://github.com/lopspower/CircularProgressBar for using
        // Circular Progress bar in the Splash screen @layout/fragment_splash.xml
        binding.circularProgressBar.apply {

            // Starting position of the animation
            progress = 0f

            // Set Progress Max.
            progressMax = 100f

            // Circular Bar will take 2.5 seconds to complete 100 frames (100%)
            setProgressWithAnimation(100f, 2500)

            // This will be animated width of the bar, starting with Green and ending with Blue colors.
            progressBarWidth = 20f

            // Base width of the bar (the one with the white background color)
            backgroundProgressBarWidth = 15f

            // Initial Color
            progressBarColorStart = Color.GREEN

            // Ending Color
            progressBarColorEnd = Color.BLUE

            // Animation Starting Direction: Left to Right
            progressBarColorDirection = CircularProgressBar.GradientDirection.LEFT_TO_RIGHT

            // Background color
            backgroundProgressBarColor = Color.WHITE

            // Rounded Borders for the Bar
            roundBorder = true

        }

        activityScope.launch {

            // Wait for 2.55 seconds
            delay(2550)

            // Navigate the user to the SignIn Fragment
            findNavController().navigate(
                R.id.action_splashFragment_to_signInFragment
            )

        }

    }


}