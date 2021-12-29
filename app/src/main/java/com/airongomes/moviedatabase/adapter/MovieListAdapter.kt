package com.airongomes.moviedatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airongomes.moviedatabase.R
import com.airongomes.moviedatabase.domain.model.Movie

class MovieListAdapter : RecyclerView.Adapter<ViewHolder>() {

    var items = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Movie) {
        with(itemView) {

        }
    }


}
