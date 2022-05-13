package com.jonareas.android.techhub.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jonareas.android.techhub.databinding.FragmentSearchCoursesBinding
import com.jonareas.android.techhub.animation.animators.SpringAddItemAnimator
import com.jonareas.android.techhub.viewmodel.SearchCoursesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCoursesFragment : Fragment() {

    private var _binding : FragmentSearchCoursesBinding? = null
    private val binding : FragmentSearchCoursesBinding
        get() = _binding!!

    private val searchViewModel : SearchCoursesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchCoursesBinding.inflate(inflater, container, false).apply {
                    searchResults.apply {
            itemAnimator = SpringAddItemAnimator()
            adapter = SearchAdapter().apply {
                // add data after layout so that animations run
                doOnNextLayout {
                    searchViewModel.topics.observe(viewLifecycleOwner) {
                        submitList(it)
                    }
                }
            }
        }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



}