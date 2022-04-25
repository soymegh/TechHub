package com.jonareas.android.techhub.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.core.data.provider.TopicProvider.topics
import com.jonareas.android.techhub.databinding.FragmentLoginBinding
import com.jonareas.android.techhub.utils.animation.OscillatingScrollListener
import com.jonareas.android.techhub.utils.animation.smoothScrollToPositionWithSpeed
import com.jonareas.android.techhub.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Onboarding [Fragment] which displays a list of Topics for a signed-in user to select.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    private val loginViewModel : LoginViewModel by viewModels()

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
                    submitList(topics.reversed())
                }
                smoothScrollToPositionWithSpeed(topics.size)
                addOnScrollListener(
                    OscillatingScrollListener(resources.getDimensionPixelSize(R.dimen.grid_2))
                )
            }
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        lifecycleScope.launch {
            loginViewModel.topics.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setupListeners() : Unit = binding.run {
            buttonSignIn.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.closeLogin())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}