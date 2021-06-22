package com.application.moviecatalogue.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.application.moviecatalogue.BuildConfig.IMAGE_URL
import com.application.moviecatalogue.R
import com.application.moviecatalogue.core.domain.model.Movie
import com.application.moviecatalogue.core.domain.model.TvShow
import com.application.moviecatalogue.databinding.ActivityDetailBinding
import com.application.moviecatalogue.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private val detailViewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if (detailMovie != null) {
            populateDetailMovieData(detailMovie)
        }

        val detailTvShow = intent.getParcelableExtra<TvShow>(EXTRA_TV_SHOW)
        if (detailTvShow != null) {
            populateDetailTvShowData(detailTvShow)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateDetailMovieData(movieData: Movie) {
        binding.apply {
            tvTitle.text = movieData.title
            tvRelease.text = movieData.release
            tvScore.text = movieData.score.toString()
            tvOverview.text = movieData.overview

            Glide.with(this@DetailActivity)
                    .load(IMAGE_URL + movieData.preview)
                    .centerCrop()
                    .apply(RequestOptions().override(510, 300))
                    .into(imgPreview)

            Glide.with(this@DetailActivity)
                    .load(IMAGE_URL + movieData.poster)
                    .centerCrop()
                    .apply(RequestOptions().override(330, 500))
                    .into(imgPoster)

            var state = movieData.isFavorite
            setFavoriteState(state)
            fab.setOnClickListener {
                state = !state
                detailViewModel.setFavoriteMovie(movieData, state)
                setFavoriteState(state)

                if (state) {
                    Toast.makeText(this@DetailActivity, "Added to Favorite", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@DetailActivity, "Removed from Favorite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateDetailTvShowData(tvShowData: TvShow) {
        binding.apply {
            tvTitle.text = tvShowData.title
            tvRelease.text = tvShowData.release
            tvScore.text = tvShowData.score.toString()
            tvOverview.text = tvShowData.overview

            Glide.with(this@DetailActivity)
                    .load(IMAGE_URL + tvShowData.preview)
                    .centerCrop()
                    .apply(RequestOptions().override(510, 300))
                    .into(imgPreview)

            Glide.with(this@DetailActivity)
                    .load(IMAGE_URL + tvShowData.poster)
                    .centerCrop()
                    .apply(RequestOptions().override(330, 500))
                    .into(imgPoster)

            var state = tvShowData.isFavorite
            setFavoriteState(state)
            fab.setOnClickListener {
                state = !state
                detailViewModel.setFavoriteTvShow(tvShowData, state)
                setFavoriteState(state)

                if (state) {
                    Toast.makeText(this@DetailActivity, "Added to Favorite", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@DetailActivity, "Removed from Favorite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setFavoriteState(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fab.setImageResource(R.drawable.ic_baseline_favorite)
        } else {
            binding.fab.setImageResource(R.drawable.ic_baseline_unfavorite)
        }
    }
}