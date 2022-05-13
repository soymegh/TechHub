package com.jonareas.android.techhub.ui.login.signIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jonareas.android.techhub.core.presentation.model.User
import com.jonareas.android.techhub.databinding.FragmentSignInBinding
import com.jonareas.android.techhub.utils.showToast
import com.jonareas.android.techhub.utils.string
import com.jonareas.android.techhub.viewmodel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding : FragmentSignInBinding? = null
    private val binding : FragmentSignInBinding
        get() = _binding!!

    private val viewModel : SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.loginStatus.observe(viewLifecycleOwner) { isLoggedIn ->
            if(isLoggedIn)
                findNavController().navigate(SignInFragmentDirections.actionSignInToCourses())

        }
    }

    private fun setupListeners() : Unit = binding.run {
        buttonSignIn.setOnClickListener {
            viewModel.onUserSignIn(User(binding.editTextFullName.string, binding.editTextPassword.string))
            showToast("EXPECTING TOKEN VALUE")
        }
       toolbar.setNavigationOnClickListener {
           findNavController().navigateUp()
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
