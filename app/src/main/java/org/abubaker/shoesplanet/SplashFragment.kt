package org.abubaker.shoesplanet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

        activityScope.launch {
            delay(1500)

            findNavController().navigate(
                R.id.action_splashFragment_to_signInFragment
            )

        }


//        val thread: Thread = object : Thread() {
//            override fun run() {
//                try {
//                    sleep(1500)
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                } finally {
//                    findNavController().navigate(
//                        R.id.action_splashFragment_to_signInFragment
//                    )
//                }
//            }
//        }
//        thread.start()

    }


}