package com.jonareas.android.techhub.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jonareas.android.techhub.databinding.FragmentMyCoursesBinding

class MyCoursesFragment : Fragment() {

    private var _binding : FragmentMyCoursesBinding? = null
    private val binding : FragmentMyCoursesBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

}