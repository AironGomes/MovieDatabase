package com.airongomes.moviedatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.airongomes.moviedatabase.R
import com.airongomes.moviedatabase.adapter.MovieListAdapter
import com.airongomes.moviedatabase.domain.remote.NetworkResult
import com.airongomes.moviedatabase.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val adapter by lazy { MovieListAdapter() }

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
        movieList.adapter = adapter.apply {
            onClick = { openMovieDetail(it) }
        }//TODO: Implement ScrollListener to adapter
        handleObserver()
        fetchData()
    }

    private fun fetchData() {
        viewModel.fetchMovieList()
    }

    private fun handleObserver() {
        viewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    progressBar.visibility = View.GONE
                    response.data?.results?.let {
                        adapter.items = it
                    }
                }
                is NetworkResult.Error -> {
                    progressBar.visibility = View.GONE
                }
                is NetworkResult.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun openMovieDetail(movieId: Int) {
        val direction = HomeFragmentDirections.openMovieDetails(movieId)
        NavHostFragment.findNavController(this).navigate(direction)
    }

}