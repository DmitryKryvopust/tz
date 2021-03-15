package com.tz.animal.ui.fragments.dogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tz.animal.R
import com.tz.animal.dataFlow.network.model.DogResponse
import com.tz.animal.ui.MainViewModel
import com.tz.animal.ui.fragments.base.BaseFragment
import com.tz.animal.ui.fragments.dogs.adapter.DogDetailModel
import com.tz.animal.ui.fragments.dogs.adapter.DogsAdapter
import com.tz.animal.ui.fragments.dogs.details.DOGS
import com.tz.animal.ui.fragments.dogs.details.DogDetailsFragment
import com.tz.animal.ui.fragments.dogs.details.DogDetailsFragment.Companion.TAG
import com.tz.animal.utils.replaceFragmentSafely
import kotlinx.android.synthetic.main.fragment_dog_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DogsFragment : BaseFragment() {

    private lateinit var dogsAdapter: DogsAdapter
    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dog_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        getDogs()
        showNavBar(true)
    }

    private fun initUI() {

        dogsAdapter = DogsAdapter(onClick = { item, position ->
            onItemClick(item, position)
        })
        rvDogs.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = dogsAdapter
        }
    }

    private fun onItemClick(item: DogResponse.DataItem, position: Int) {
        val bundle = Bundle()
        val dogModel = DogDetailModel(title = item.title, url = item.url, position = position)
        bundle.putParcelable(DOGS, dogModel)
        replaceFragmentSafely(
            DogDetailsFragment.newInstance(bundle),
            TAG,
            false,
            true,
            R.id.container
        )
    }

    private fun getDogs() {
        viewModel.dogs().observe(this, Observer {
            it?.let {
                dogsAdapter.setList(it)
            }
        })
    }


    private fun saveScrolledPosition() {
        val layoutManager = rvDogs.layoutManager
        if (layoutManager != null && layoutManager is LinearLayoutManager) {
            viewModel.dogsScrollPosition.postValue((layoutManager).findFirstCompletelyVisibleItemPosition())
        }
    }

    private fun loadLastPositionIfListIsScrolled() {
        if (viewModel.dogsScrollPosition.value != null && viewModel.dogsScrollPosition.value!! > 0)
            rvDogs.scrollToPosition(viewModel.dogsScrollPosition.value!!)
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
