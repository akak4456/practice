package com.jo.practice

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(NAVIGATION_MODE_STANDARD, NAVIGATION_MODE_LIST, NAVIGATION_MODE_TABS)
annotation class NavigationMode

// Declare the constants.
const val NAVIGATION_MODE_STANDARD = 0
const val NAVIGATION_MODE_LIST = 1
const val NAVIGATION_MODE_TABS = 2

data class ActionBar(
    @NavigationMode
    var navigationMode: Int = NAVIGATION_MODE_LIST
)