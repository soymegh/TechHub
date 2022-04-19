package com.jonareas.android.techhub.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jonareas.android.techhub.R

/**
 * Onboarding [Fragment] which displays a list of Topics for a signed-in user to select.
 */
class OnBoardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_on_boarding, container, false)
    }


}