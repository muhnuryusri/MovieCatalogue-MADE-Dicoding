package com.application.moviecatalogue.favorites.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.moviecatalogue.core.ui.MovieAdapter
import com.application.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.application.moviecatalogue.di.FavoritesDependencies
import com.application.moviecatalogue.favorites.di.DaggerFavoritesComponent
import com.application.moviecatalogue.favorites.favorite.FavoriteViewModel
import com.application.moviecatalogue.favorites.utlis.ViewModelFactory
import com.application.moviecatalogue.ui.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteMovieFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }

    private lateinit var binding: FragmentFavoriteMovieBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DaggerFavoritesComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoritesDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onViewCreated(view, savedInstanceState)

        favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner, { listMovie ->
            if (listMovie.isNullOrEmpty()) {
                binding.rvMovie.visibility = View.GONE
                binding.noFavorite.visibility = View.VISIBLE
            } else {
                binding.rvMovie.visibility = View.VISIBLE
                binding.noFavorite.visibility = View.GONE
                binding.rvMovie.adapter?.let { adapter ->
                    when (adapter) {
                        is MovieAdapter -> {
                            listMovie.let {
                                binding.noFavorite.visibility = View.GONE
                            }
                            adapter.setData(listMovie)
                        }
                    }
                }
            }
        })
        showRecyclerList()


        adapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }
    }

    private fun showRecyclerList() {
        adapter = MovieAdapter()
        binding.rvMovie.layoutManager = LinearLayoutManager(activity)
        binding.rvMovie.adapter = adapter
    }
}