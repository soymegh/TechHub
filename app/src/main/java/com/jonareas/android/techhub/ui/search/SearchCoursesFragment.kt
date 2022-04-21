package com.jonareas.android.techhub.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jonareas.android.techhub.databinding.FragmentSearchCoursesBinding


class SearchCoursesFragment : Fragment() {

    private var _binding : FragmentSearchCoursesBinding? = null
    private val binding : FragmentSearchCoursesBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

}