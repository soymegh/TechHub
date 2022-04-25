package com.jonareas.android.techhub.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import androidx.fragment.app.Fragment
import com.jonareas.android.techhub.core.data.provider.CourseProvider
import com.jonareas.android.techhub.databinding.FragmentExploreCoursesBinding
import com.jonareas.android.techhub.utils.animation.SpringAddItemAnimator

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
                        submitList(CourseProvider.courses)
                        doOnNextLayout {
                            startPostponedEnterTransition()
                        }
                    }
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}