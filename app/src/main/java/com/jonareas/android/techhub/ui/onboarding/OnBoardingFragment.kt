package com.jonareas.android.techhub.ui.onboarding

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.databinding.FragmentOnBoardingBinding
import com.jonareas.android.techhub.utils.invisible
import com.jonareas.android.techhub.utils.visible

/**
 * Onboarding [Fragment] which displays a list of Topics for a signed-in user to select.
 */
class OnBoardingFragment : DialogFragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding: FragmentOnBoardingBinding
        get() = _binding!!

    private val indicators = mutableListOf<View>()
    private var currentPosition = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.window?.attributes?.windowAnimations = R.style.DialogFullScreen
        return AppCompatDialog(context, R.style.DialogFullScreen)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setIndicators()
        updateIndicators()
        attachNextButtonListener()
        attachPreviousButtonListener()
    }

    private fun attachNextButtonListener() = binding.run {
        next.setOnClickListener {
            when (currentPosition) {
                0 -> next.navigate(R.id.firstTransition, R.id.secondTransition)
                1 -> next.navigate(R.id.secondTransition, R.id.thirdTransition)
                else -> {
                    findNavController().navigate(OnBoardingFragmentDirections.closeOnBoarding())
                }
            }
        }
    }

    private fun attachPreviousButtonListener() = binding.run {
        previous.setOnClickListener {
            when (currentPosition) {
                2 -> previous.navigate(R.id.thirdTransition, R.id.secondTransition)
                else -> previous.navigate(R.id.secondTransition, R.id.firstTransition)
            }
        }
    }

    private fun updateNavState() = binding.run {
        when {
            currentPosition > 0 -> previous.visible()
            else -> previous.invisible()
        }
    }

    private fun Button.navigate(startId: Int, endId: Int) = binding.run {
        currentPosition = when (id) {
            R.id.next -> currentPosition.inc()
            else -> currentPosition.dec()
        }
        updateNavState()
        if (endId == R.id.thirdTransition) {
            navContainer.setBackgroundColor(resources.getColor(R.color.color_onboarding_page3))
            next.setText(R.string.button_text_complete)
        } else {
            next.setText(R.string.button_text_next)
            navContainer.setBackgroundColor(resources.getColor(R.color.onboarding_background))
        }
        onBoardingMotionLayout.motionLayout.setTransition(startId, endId)
        onBoardingMotionLayout.motionLayout.transitionToEnd()
        updateIndicators()
    }

    private fun updateIndicators() {
        indicators.forEachIndexed { index, view ->
            val background = when (index) {
                currentPosition -> R.drawable.selected_dot
                else -> R.drawable.un_selected_dot
            }
            view.setBackgroundDrawable(context?.getDrawable(background))
        }
    }

    private fun setIndicators() = binding.run {
        val dotRadius: Int = convertDpToPixel(12f, context)
        val margin: Int = convertDpToPixel(4f, context)
        indicators.clear()
        indicatorsContainer.removeAllViews()
        for (i in 0 until 3) {
            val view = View(context)
            view.id = View.generateViewId()
            val layoutParams = FrameLayout.LayoutParams(dotRadius * 2, dotRadius * 2)
            layoutParams.setMargins(margin, margin, margin, margin)
            view.layoutParams = layoutParams
            indicators.add(view)
            indicatorsContainer.addView(view)
        }
    }

    private fun convertDpToPixel(dp: Float, context: Context?): Int {
        if (context != null) {
            return (dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
        }
        return 0
    }


}