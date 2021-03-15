package com.tz.animal.ui.fragments.cats.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tz.animal.R
import com.tz.animal.ui.MainViewModel
import com.tz.animal.ui.fragments.base.BaseFragment
import com.tz.animal.ui.fragments.cats.adapter.CatDetailModel
import kotlinx.android.synthetic.main.fragment_cat_details_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val CATS = "CATS"

class CatDetailsFragment : BaseFragment() {

    private lateinit var details: CatDetailModel

    companion object {
        fun newInstance(bundle: Bundle) =
            CatDetailsFragment().apply {
                arguments = bundle
            }
        val TAG = "CatDetailsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cat_details_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        restoreDataFromBundle()
        initUI()
        showNavBar(false)
    }

    private fun initUI() {
        details?.let {
            tvPosition.text = details.position.toString()
            tvTitle.text = details.title
            Picasso.get().load(details.url).into(ivCatDetail)
        }
    }


    private fun restoreDataFromBundle() {
        arguments?.let {
            details = it.getParcelable(CATS)!!
        }
    }


}