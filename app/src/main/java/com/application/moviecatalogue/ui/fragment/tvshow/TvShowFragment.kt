package com.application.moviecatalogue.ui.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.moviecatalogue.core.ui.TvShowAdapter
import com.application.moviecatalogue.ui.DetailActivity
import com.application.moviecatalogue.databinding.FragmentTvShowBinding
import com.application.moviecatalogue.viewmodel.TvShowViewModel
import com.application.moviecatalogue.core.data.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private val tvShowViewModel: TvShowViewModel by viewModels()

    private lateinit var adapter: TvShowAdapter
    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowViewModel.tvShow.observe(viewLifecycleOwner, { listTvShow ->
            if (listTvShow != null) {
                when (listTvShow) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        showLoading(false)
                        binding.rvTvshow.adapter?.let { adapter ->
                            when (adapter) {
                                is TvShowAdapter -> {
                                    listTvShow.data?.let { adapter.setData(it) }
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        showLoading(true)
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

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.shimmerView.startShimmer()
            binding.shimmerView.visibility = View.VISIBLE
        } else {
            binding.shimmerView.stopShimmer()
            binding.shimmerView.visibility = View.GONE
        }
    }
}