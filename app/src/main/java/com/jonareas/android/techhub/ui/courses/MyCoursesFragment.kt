package com.jonareas.android.techhub.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import androidx.fragment.app.Fragment
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.core.data.provider.CourseProvider.courses
import com.jonareas.android.techhub.databinding.FragmentMyCoursesBinding
import com.jonareas.android.techhub.utils.animation.BottomSpacingItemDecoration
import com.jonareas.android.techhub.utils.animation.SpringAddItemAnimator
import java.util.concurrent.TimeUnit

class MyCoursesFragment : Fragment() {

    private var _binding : FragmentMyCoursesBinding? = null
    private val binding : FragmentMyCoursesBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentMyCoursesBinding.inflate(inflater, container, false).apply {
            list.apply {
                itemAnimator = SpringAddItemAnimator()
                addItemDecoration(
                    BottomSpacingItemDecoration(resources.getDimensionPixelSize(R.dimen.grid_2))
                )
                adapter = MyCoursesAdapter().apply {
                    // add data after layout so that animations run
                    doOnNextLayout {
                        submitList(courses)
                        doOnNextLayout {
                            startPostponedEnterTransition()
                        }
                    }
                }
            }
        }
        postponeEnterTransition(1000L, TimeUnit.MILLISECONDS)
        return binding.root
    }

}
