package com.tz.animal.ui.fragments.base

import android.view.View
import androidx.fragment.app.Fragment
import com.tz.animal.ui.activities.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

open class BaseFragment : Fragment(), BaseFragmentView {

    override fun showNavBar(show: Boolean) {
        if (show)
            (activity as MainActivity).tabLayout.visibility = View.VISIBLE
        else
            (activity as MainActivity).tabLayout.visibility = View.GONE

    }


}