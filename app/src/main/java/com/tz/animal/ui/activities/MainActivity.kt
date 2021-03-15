package com.tz.animal.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.tz.animal.R
import com.tz.animal.ui.MainViewModel
import com.tz.animal.ui.fragments.cats.CatsFragment
import com.tz.animal.ui.fragments.cats.details.CatDetailsFragment
import com.tz.animal.ui.fragments.dogs.DogsFragment
import com.tz.animal.ui.fragments.dogs.details.DogDetailsFragment
import com.tz.animal.utils.replaceFragmentSafely
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            replaceFragmentSafely(
                CatsFragment(),
                "CatsFragment",
                false,
                true,
                R.id.container
            )
        }
        initUI()
        getData()
    }

    private fun initUI() {
        tabLayout.addTab(tabLayout.newTab().setText("tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("tab 2"))
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    replaceFragmentSafely(
                        CatsFragment(),
                        "CatsFragment",
                        false,
                        true,
                        R.id.container
                    )
                } else if (tab?.position == 1) {
                    replaceFragmentSafely(
                        DogsFragment(),
                        "DogsFragment",
                        false,
                        true,
                        R.id.container
                    )
                }
            }
        })
    }

    private fun getData() {
        viewModel.getCats()
        viewModel.getDogs()
    }

    override fun onBackPressed() {
        if (currentFragmentIsDetails())
            supportFragmentManager.popBackStack()
        else
            finishAndRemoveTask()
    }

    private fun currentFragmentIsDetails(): Boolean {
        val catDetails: CatDetailsFragment? =
            supportFragmentManager.findFragmentByTag(CatDetailsFragment.TAG) as CatDetailsFragment?
        val dogDetails: DogDetailsFragment? =
            supportFragmentManager.findFragmentByTag(DogDetailsFragment.TAG) as DogDetailsFragment?
        return (catDetails != null && catDetails.isVisible) || (dogDetails != null && dogDetails.isVisible)

    }
}
