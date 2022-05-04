package com.jonareas.android.techhub.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jonareas.android.techhub.databinding.FragmentExploreCoursesBinding
import com.jonareas.android.techhub.utils.animation.SpringAddItemAnimator
import com.jonareas.android.techhub.viewmodel.ExploreCoursesViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Explore all courses, a [Fragment] which displays the list of all current available courses.
 */
@AndroidEntryPoint
class ExploreCoursesFragment : Fragment() {

    private var _binding: FragmentExploreCoursesBinding? = null
    private val binding: FragmentExploreCoursesBinding
        get() = _binding!!

    private val viewModel: ExploreCoursesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentExploreCoursesBinding.inflate(inflater, container, false).apply {
            val onClick: CourseViewClick = object : CourseViewClick {
                override fun onClick(view: View, courseId: Int) {

                }
            }

            featuredGrid.apply {

                itemAnimator = SpringAddItemAnimator()
                adapter = ExploreCoursesListAdapter(onClick).apply {
                    // add data after layout so that animations run
                    doOnNextLayout {
                        viewModel.courses.observe(viewLifecycleOwner) { submitList(it) }
                        doOnNextLayout {
                            startPostponedEnterTransition()
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}