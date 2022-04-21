package com.jonareas.android.techhub.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jonareas.android.techhub.databinding.FragmentExploreCoursesBinding

/**
 * Explore all courses, a [Fragment] which displays the list of all current available courses.
 */
class ExploreCoursesFragment : Fragment() {

    private var _binding : FragmentExploreCoursesBinding? = null
    private val binding : FragmentExploreCoursesBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentExploreCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}