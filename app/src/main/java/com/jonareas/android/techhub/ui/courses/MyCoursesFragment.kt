package com.jonareas.android.techhub.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.databinding.FragmentMyCoursesBinding
import com.jonareas.android.techhub.animation.animators.BottomSpacingItemDecoration
import com.jonareas.android.techhub.animation.animators.SpringAddItemAnimator
import com.jonareas.android.techhub.viewmodel.MyCoursesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MyCoursesFragment : Fragment() {

    private var _binding : FragmentMyCoursesBinding? = null
    private val binding : FragmentMyCoursesBinding
        get() = _binding!!

    private val viewModel : MyCoursesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentMyCoursesBinding.inflate(inflater, container, false).apply {
            list.apply {
                itemAnimator = SpringAddItemAnimator()
                addItemDecoration(BottomSpacingItemDecoration(resources.getDimensionPixelSize(R.dimen.grid_2)))
                adapter = MyCoursesAdapter().apply {
                    // ensuring a correct scroll position
                    stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                    // add data after layout so that animations run
                    doOnNextLayout {
                        viewModel.courses.observe(viewLifecycleOwner) { courses -> courses?.let { submitList(it) } }
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
