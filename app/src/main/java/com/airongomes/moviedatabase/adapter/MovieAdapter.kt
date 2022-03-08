package com.airongomes.moviedatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.airongomes.moviedatabase.R
import com.airongomes.moviedatabase.domain.model.Movie
import com.airongomes.moviedatabase.extensions.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : PagingDataAdapter<Movie, MovieAdapter.ViewHolder>(MovieComparator()) {

    var onClick: ((movieId: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Movie) {
            with(itemView) {
                tvTitle.text = item.title
                tvDate.text = item.releaseDate
                ivPoster.loadImage(item.posterPath,"w200")

                setOnClickListener { onClick?.invoke(item.id) }
                tvGenre.visibility = View.GONE//TODO: Get genre list from api
            }
        }
    }

}

class MovieComparator: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}