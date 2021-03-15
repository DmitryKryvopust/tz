package com.tz.animal.ui.fragments.cats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tz.animal.R
import com.tz.animal.dataFlow.network.model.CatResponse
import com.tz.animal.ui.MainViewModel
import com.tz.animal.ui.fragments.base.BaseFragment
import com.tz.animal.ui.fragments.cats.adapter.CatDetailModel
import com.tz.animal.ui.fragments.cats.adapter.CatsAdapter
import com.tz.animal.ui.fragments.cats.details.CATS
import com.tz.animal.ui.fragments.cats.details.CatDetailsFragment
import com.tz.animal.ui.fragments.cats.details.CatDetailsFragment.Companion.TAG
import com.tz.animal.utils.replaceFragmentSafely
import kotlinx.android.synthetic.main.fragment_cat_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CatsFragment : BaseFragment() {

    private lateinit var catsAdapter: CatsAdapter
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cat_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        getCats()
        showNavBar(true)
    }

    private fun initUI() {

        catsAdapter = CatsAdapter(onClick = { item, position ->
            onItemClick(item, position)
        })
        rvCats.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = catsAdapter
        }
    }

    private fun onItemClick(item: CatResponse.DataItem, position: Int) {
        val bundle = Bundle()
        val catModel = CatDetailModel(title = item.title!!, url = item.url!!, position = position)
        bundle.putParcelable(CATS, catModel)
        replaceFragmentSafely(
            CatDetailsFragment.newInstance(bundle),
            TAG,
            false,
            true,
            R.id.container
        )
    }

    private fun getCats() {
        viewModel.cats().observe(this, Observer {
            it?.let {
                catsAdapter.setList(it)
            }
        })
    }

    private fun saveScrolledPosition() {
        val layoutManager = rvCats.layoutManager
        if (layoutManager != null && layoutManager is LinearLayoutManager) {
            viewModel.catsScrollPosition.postValue((layoutManager).findFirstCompletelyVisibleItemPosition())
        }
    }

    private fun loadLastPositionIfListIsScrolled() {
        if (viewModel.catsScrollPosition.value != null && viewModel.catsScrollPosition.value!! > 0)
            rvCats.scrollToPosition(viewModel.catsScrollPosition.value!!)
    }


    override fun onStop() {
        super.onStop()
        saveScrolledPosition()
    }

    override fun onResume() {
        super.onResume()
        loadLastPositionIfListIsScrolled()
    }
}