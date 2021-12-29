package com.airongomes.moviedatabase.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.airongomes.moviedatabase.R
import com.airongomes.moviedatabase.adapter.MovieListAdapter
import com.airongomes.moviedatabase.domain.remote.NetworkResult
import com.airongomes.moviedatabase.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
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
        movieList.adapter = adapter
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
                    Log.i("TAG", "fetchData: ${response.data.toString()}")
                }
                is NetworkResult.Error -> {
                    progressBar.visibility = View.GONE
                    Log.i("TAG", "fetchData: ${response.message.toString()}")
                }
                is NetworkResult.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

}