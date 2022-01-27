package com.airongomes.moviedatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.LoadState
import com.airongomes.moviedatabase.R
import com.airongomes.moviedatabase.adapter.MovieAdapter
import com.airongomes.moviedatabase.viewModel.HomeViewModel
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
        movieAdapter.apply {
            onClick = { openMovieDetail(it) }
            setLoadListener(this)
        }

        movieList.apply {
            adapter = movieAdapter
            setHasFixedSize(true)
        }
        fetchData()

    }

    //TODO: CHANGE LOCAL?
    private fun setLoadListener(adapter: MovieAdapter) {
        adapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading)
                    progressBar.visibility = View.VISIBLE
            else {
                progressBar.visibility = View.GONE

                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(requireContext(), R.string.error_loading_data, Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun fetchData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchMoviesPage().collectLatest {
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