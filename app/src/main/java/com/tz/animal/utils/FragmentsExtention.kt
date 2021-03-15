package com.tz.animal.utils


import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes

fun androidx.fragment.app.Fragment.replaceFragmentSafely(
    fragment: androidx.fragment.app.Fragment,
    tag: String,
    allowStateLoss: Boolean = false,
    addToBackStack: Boolean = false,
    @IdRes containerViewId: Int,
    @StyleRes style: Int = 0,
    @AnimRes enterAnimation: Int = 0,
    @AnimRes exitAnimation: Int = 0,
    @AnimRes popEnterAnimation: Int = 0,
    @AnimRes popExitAnimation: Int = 0
) {
    val ft = activity?.supportFragmentManager?.beginTransaction()?.setTransitionStyle(style)
        ?.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        ?.replace(containerViewId, fragment, tag)
    if (addToBackStack) ft?.addToBackStack(tag)
    if (!activity?.supportFragmentManager?.isStateSaved!!) {
        ft?.commit()
    } else if (allowStateLoss) {
        ft?.commitAllowingStateLoss()
    }
}


