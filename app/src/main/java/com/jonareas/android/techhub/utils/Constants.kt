package com.jonareas.android.techhub.utils

import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.TechHubApp
import kotlin.random.Random

val RANDOM_GENERATOR : Random = Random(seed = 0)
val RANDOM_GENERATOR_SEED : String = "jxareas"
val DEFAULT_ANIMATION_DURATION : Long = TechHubApp.applicationContext.resources.getInteger(R.integer.default_duration).toLong()