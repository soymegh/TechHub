package com.jonareas.android.techhub.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.doOnNextLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jonareas.android.techhub.databinding.FragmentExploreCoursesBinding
import com.jonareas.android.techhub.animation.animators.SpringAddItemAnimator
import com.jonareas.android.techhub.viewmodel.ExploreCoursesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

/**
 * Explore all courses, a [Fragment] which displays the list of all current available courses.
 */
@AndroidEntryPoint
class ExploreCoursesFragment : Fragment(), OnQueryTextListener {

    private var _binding: FragmentExploreCoursesBinding? = null
    private val binding: FragmentExploreCoursesBinding
        get() = _binding!!

    private val viewModel: ExploreCoursesViewModel by viewModels()

    val onClick: CourseViewClick = object : CourseViewClick {
        override fun onClick(view: View, courseId: Int) {
            val extras = FragmentNavigatorExtras(
                view to "shared_element"
            )
            val action =
                ExploreCoursesFragmentDirections.actionExploreCoursesFragmentToCourseDetailFragment(
                    courseId)
            findNavController().navigate(action, extras)
        }
    }



            override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?,
            ): View {
                _binding = FragmentExploreCoursesBinding.inflate(inflater, container, false).apply {


                    searchView.setOnQueryTextListener(this@ExploreCoursesFragment)

                    featuredGrid.apply {

                        itemAnimator = SpringAddItemAnimator()
                        adapter = ExploreCoursesListAdapter(onClick).apply {
                            // ensuring a correct scroll position
                            stateRestorationPolicy =
                                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
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
                postponeEnterTransition(1000L, TimeUnit.MILLISECONDS)
                return binding.root
            }

            override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)
            }


            override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null)
                    viewModel.onSearchQuery(query)
                return false
            }


            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null && query.isEmpty())
                    viewModel.onSearchQuery(query)
                return false
            }

        }