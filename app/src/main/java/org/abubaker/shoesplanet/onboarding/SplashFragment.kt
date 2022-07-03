package org.abubaker.shoesplanet.onboarding

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.circularProgressBar.apply {
            // Set Progress
            progress = 0f

            // Set Progress Max.
            progressMax = 100f
            setProgressWithAnimation(100f, 2500)
            progressBarWidth = 20f
            backgroundProgressBarWidth = 15f
            // progressBarColor = Color.GREEN
            progressBarColorStart = Color.GREEN
            progressBarColorEnd = Color.BLUE
            progressBarColorDirection = CircularProgressBar.GradientDirection.LEFT_TO_RIGHT

            backgroundProgressBarColor = Color.WHITE

            roundBorder = true

        }

        activityScope.launch {
            delay(2550)

            findNavController().navigate(
                R.id.action_splashFragment_to_signInFragment
            )

        }

    }


}