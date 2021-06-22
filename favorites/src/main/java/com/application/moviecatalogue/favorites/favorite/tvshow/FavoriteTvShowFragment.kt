package com.application.moviecatalogue.favorites.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.moviecatalogue.core.ui.TvShowAdapter
import com.application.moviecatalogue.databinding.FragmentFavoriteTvShowBinding
import com.application.moviecatalogue.di.FavoritesDependencies
import com.application.moviecatalogue.favorites.di.DaggerFavoritesComponent
import com.application.moviecatalogue.favorites.favorite.FavoriteViewModel
import com.application.moviecatalogue.favorites.utlis.ViewModelFactory
import com.application.moviecatalogue.ui.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteTvShowFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }

    private lateinit var binding: FragmentFavoriteTvShowBinding
    private lateinit var adapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater)
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

        favoriteViewModel.favoriteTvShow.observe(viewLifecycleOwner, { listTvShow ->
            if (listTvShow.isNullOrEmpty()) {
                binding.rvTvshow.visibility = View.GONE
                binding.noFavorite.visibility = View.VISIBLE
            } else {
                binding.rvTvshow.visibility = View.VISIBLE
                binding.noFavorite.visibility = View.GONE
                binding.rvTvshow.adapter?.let { adapter ->
                    when (adapter) {
                        is TvShowAdapter -> {
                            listTvShow.let {
                                binding.noFavorite.visibility = View.GONE
                            }
                            adapter.setData(listTvShow)
                        }
                    }
                }
            }
        })
        showRecyclerList()

        adapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_TV_SHOW, selectedData)
            startActivity(intent)
        }
    }

    private fun showRecyclerList() {
        adapter = TvShowAdapter()
        binding.rvTvshow.layoutManager = LinearLayoutManager(activity)
        binding.rvTvshow.adapter = adapter
    }
}