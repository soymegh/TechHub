package com.jonareas.android.techhub.ui.login.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    private var _binding : FragmentSignInBinding? = null
    private val binding : FragmentSignInBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() : Unit = binding.run {
        buttonSignIn.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInToCourses())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
