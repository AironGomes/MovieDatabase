package com.airongomes.moviedatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.LoadState
import com.airongomes.moviedatabase.R
import com.airongomes.moviedatabase.adapter.MovieAdapter
import com.airongomes.moviedatabase.adapter.MovieLoadStateAdapter
import com.airongomes.moviedatabase.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val movieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupAdapter()
        fetchData()
    }

    private fun setupAdapter() {
        rvMovieList.apply {
            adapter = movieAdapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter { movieAdapter.retry() }
            )
            setHasFixedSize(true)
        }
        movieAdapter.onClick = { openMovieDetail(it) }
        setupLoadState()
    }

    private fun setupLoadState() {
        viewLifecycleOwner.lifecycleScope.launch {
            movieAdapter.loadStateFlow.collect { loadState ->

                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && movieAdapter.itemCount == 0

                rvMovieList.isVisible = !isListEmpty
                include_empty_list.isVisible = isListEmpty
                homeProgressBar.isVisible = loadState.source.refresh is LoadState.Loading

                val hasError = loadState.source.refresh is LoadState.Error
                if (hasError) {
                    Snackbar
                        .make(
                            requireView(),
                            R.string.error_loading_data,
                            Snackbar.LENGTH_INDEFINITE
                        )
                        .setAction(R.string.retry) { movieAdapter.retry() }
                        .show()
                }
            }
        }
    }

    private fun fetchData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moviePagingFlow.collectLatest {
                    movieAdapter.submitData(it)
                }
            }
        }
    }

    private fun openMovieDetail(movieId: Int) {
        val direction = HomeFragmentDirections.openMovieDetails(movieId)
        NavHostFragment.findNavController(this).navigate(direction)
    }

}