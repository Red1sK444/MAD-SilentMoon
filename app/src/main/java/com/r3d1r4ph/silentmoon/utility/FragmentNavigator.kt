package com.r3d1r4ph.silentmoon.utility

import android.os.Bundle
import androidx.fragment.app.Fragment

interface FragmentNavigator {
    fun navigateToFragment(tag: String, fragment: Fragment, addToBackStack: Boolean = false, bundle: Bundle? = null)
}