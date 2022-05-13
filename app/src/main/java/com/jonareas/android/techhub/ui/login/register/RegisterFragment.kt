package com.jonareas.android.techhub.ui.login.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jonareas.android.techhub.core.presentation.model.User
import com.jonareas.android.techhub.databinding.FragmentRegisterBinding
import com.jonareas.android.techhub.utils.showToast
import com.jonareas.android.techhub.utils.string
import com.jonareas.android.techhub.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding : FragmentRegisterBinding
        get() = _binding!!

    private val viewModel : RegisterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }


    private fun setupListeners() : Unit = binding.run {
        buttonRegister.setOnClickListener {
            val user = binding.run { User(editTextFullName.string, editTextPassword.string, editTextEmail.string) }
            viewModel.onUserRegister(user)
            showToast("SENT REGISTER USER REQUEST")
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
