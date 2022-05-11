package com.jonareas.android.techhub.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.databinding.FragmentCourseDetailBinding
import com.jonareas.android.techhub.ui.explore.CourseViewClick
import com.jonareas.android.techhub.utils.transitions.MaterialContainerTransition
import com.jonareas.android.techhub.viewmodel.CourseDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

/**
 * A [Fragment] displaying the learn a course screen.
 */
@AndroidEntryPoint
class CourseDetailFragment : Fragment() {
    private var _binding: FragmentCourseDetailBinding? = null
    private val binding: FragmentCourseDetailBinding
        get() = _binding!!

    private val navArgs: CourseDetailFragmentArgs by navArgs()
    private val viewModel: CourseDetailViewModel by viewModels()

    val onClick: CourseViewClick = object : CourseViewClick {
        override fun onClick(view: View, courseId: Int) {

            val action =
                CourseDetailFragmentDirections.actionCourseDetailToSelf(courseId)
            findNavController().navigate(action)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val courseId = navArgs.courseId
        viewModel.getCourseById(courseId)
        viewModel
        _binding = FragmentCourseDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel.course.observe(viewLifecycleOwner) { selectedCourse ->
                course = selectedCourse
            }
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            postponeEnterTransition(300L, TimeUnit.MILLISECONDS)
            val interp = AnimationUtils.loadInterpolator(
                context,
                android.R.interpolator.fast_out_slow_in
            )
            sharedElementEnterTransition = MaterialContainerTransition(R.id.scroll).apply {
                duration = 300L
                interpolator = interp
            }
//            enterTransition = DiagonalSlide().apply {
//                addTarget(R.id.lessons_sheet)
//                startDelay = 200L
//                duration = 200L
//                interpolator = interp
//            }
            sharedElementReturnTransition = MaterialContainerTransition().apply {
                duration = 300L
                interpolator = interp
            }
//            returnTransition = DiagonalSlide().apply {
//                addTarget(R.id.lessons_sheet)
//                duration = 100L
//                interpolator = interp
//            }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.relatedCourses.observe(viewLifecycleOwner) { listOfCourses ->
            val relatedCoursesAdapter = RelatedCoursesAdapter(onClick)
            binding.alsoLikeList.adapter = relatedCoursesAdapter
            relatedCoursesAdapter.submitList(listOfCourses)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}