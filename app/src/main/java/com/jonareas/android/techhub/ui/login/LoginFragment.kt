package com.jonareas.android.techhub.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.common.data.provider.Topics
import com.jonareas.android.techhub.databinding.FragmentLoginBinding
import com.jonareas.android.techhub.ui.onboarding.TopicsAdapter
import com.jonareas.android.techhub.utils.animation.OscillatingScrollListener
import com.jonareas.android.techhub.utils.animation.smoothScrollToPositionWithSpeed

/**
 * Login [Fragment] which displays a list of Topics for a signed-in user to select as well as
 * buttons to sign in or sign up.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false).apply {
            topicGrid.apply {
                adapter = TopicsAdapter(context).apply {
                    // We're setting reverseLayout on the RV to layout from RTL, but we still want
                    // data ordered LTR, so reverse it before setting
                    submitList(Topics.topics.reversed())
                }
                smoothScrollToPositionWithSpeed(Topics.topics.size)
                addOnScrollListener(
                    OscillatingScrollListener(resources.getDimensionPixelSize(R.dimen.grid_2))
                )
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() : Unit = binding.run {
        buttonContinue.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.closeLogin())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}