package com.application.moviecatalogue.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.moviecatalogue.core.BuildConfig.IMAGE_URL
import com.application.moviecatalogue.core.databinding.ItemUserBinding
import com.application.moviecatalogue.core.domain.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val listItems = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(data: List<Movie>) {
        listItems.clear()
        listItems.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    inner class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(IMAGE_URL + movie.poster)
                    .centerCrop()
                    .apply(RequestOptions().override(290, 410))
                    .into(imgPoster)
                tvTitle.text = movie.title
                tvScore.text = movie.score.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listItems[adapterPosition])
            }
        }
    }
}