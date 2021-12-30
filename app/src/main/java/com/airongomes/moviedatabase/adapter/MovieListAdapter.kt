package com.airongomes.moviedatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airongomes.moviedatabase.R
import com.airongomes.moviedatabase.domain.model.Movie
import com.airongomes.moviedatabase.extensions.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var items = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClick: ((imageId: Int) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

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