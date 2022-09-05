package com.music.myartistsapplication.features.artists.artistslisting

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import com.music.myartistsapplication.Result
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.music.myartistsapplication.ArtistsApplication
import com.music.myartistsapplication.R
import com.music.myartistsapplication.adapters.ArtistsListAdapter
import com.music.myartistsapplication.api.MyArtistsQuery
import com.music.myartistsapplication.base.BaseFragment
import com.music.myartistsapplication.databinding.FragmentArtistsBinding
import com.music.myartistsapplication.utils.observeNonNull
import com.music.myartistsapplication.viewmodels.ArtistsViewModelFactory
import javax.inject.Inject


class ArtistsFragment :
    BaseFragment<FragmentArtistsBinding, ArtistsViewModel>(R.layout.fragment_artists) {

    override lateinit var viewModel: ArtistsViewModel

    @Inject
    lateinit var artistsListAdapter: ArtistsListAdapter

    @Inject
    lateinit var artistsViewModelFactory: ArtistsViewModelFactory

    private var searchedValue = "as"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireBinding().artistsRecyclerView.apply {
            val layoutManager = layoutManager as LinearLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val LOAD_MORE_VISIBLE_ITEM_THRESHOLD = 5
                    if (layoutManager.itemCount <= layoutManager.findLastVisibleItemPosition() + LOAD_MORE_VISIBLE_ITEM_THRESHOLD) {
                        viewModel.loadMoreItems(searchedValue)
                    }
                }
            })
        }

        requireBinding().searchBar.searchCardEditText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchedValue = v.editableText.toString()
                viewModel.fetchData(searchedValue, true)
                return@OnEditorActionListener true
            }
            false
        })

        viewModel.artists.observeNonNull(
            this
        ) {
            when (it) {
                is Result.Success -> {
                    artistsListAdapter.submitList(it.value)
                }
                is Result.Error -> {
                    showSnackBar("Something went wrong")
                }
            }

        }

        requireBinding().artistsRecyclerView.adapter =
            artistsListAdapter.also {
                it.onItemClickListener = {
                    findNavController().navigate(
                        ArtistsFragmentDirections.actionArtistsFragmentToProfileFragment(
                            name = ((viewModel.artists.value as Result.Success).value)?.get(
                                it
                            )?.name,
                            dist = ((viewModel.artists.value as Result.Success).value)?.get(
                                it
                            )?.disambiguation
                        )
                    )
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ArtistsApplication.applicationComponent.inject(this)

        viewModel = ViewModelProvider(this, artistsViewModelFactory)[ArtistsViewModel::class.java]

    }
}