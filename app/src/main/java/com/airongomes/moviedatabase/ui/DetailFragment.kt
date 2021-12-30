package com.airongomes.moviedatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.airongomes.moviedatabase.R
import com.airongomes.moviedatabase.domain.model.MovieDetail
import com.airongomes.moviedatabase.domain.remote.NetworkResult
import com.airongomes.moviedatabase.extensions.loadImage
import com.airongomes.moviedatabase.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val viewModel by viewModels<DetailViewModel>()
    private val args = navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        handleObserver()
        fetchData()
    }

    private fun fetchData() {
        viewModel.fetchMovieDetail(args.value.movieId)
    }

    private fun handleObserver() {
        viewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    progressBar.visibility = View.GONE
                    val movieDetail = response.data
                    showData(movieDetail)
                }
                is NetworkResult.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_loading_data),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkResult.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showData(movieDetail: MovieDetail?) {
        movieDetail?.let {
            ivMovie.loadImage(it.posterPath)
            tvTitle.text = it.title
            tvReleaseDate.text = requireContext().getString(R.string.release, it.releaseDate)
            tvOverview.text = it.overview
            tvGenre.text = it.genres.firstOrNull()?.name ?: ""
        }
    }

}

