package com.airongomes.moviedatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airongomes.moviedatabase.R
import kotlinx.android.synthetic.main.item_load_state_footer.view.*

class MovieLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_load_state_footer, parent, false)
        )
    }

    inner class LoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(loadState: LoadState) {
            with(itemView) {
                if (loadState is LoadState.Error) {
                    tvErrorMessage.text = loadState.error.localizedMessage
                }
                tvErrorMessage.isVisible = loadState is LoadState.Error
                progressBar.isVisible = loadState is LoadState.Loading
                btRetry.isVisible = loadState is LoadState.Error

                btRetry.setOnClickListener { retry.invoke() }
            }
        }
    }
}