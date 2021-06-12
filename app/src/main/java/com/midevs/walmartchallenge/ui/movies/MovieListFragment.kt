package com.midevs.walmartchallenge.ui.movies

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.midevs.walmartchallenge.R
import com.midevs.walmartchallenge.base.BaseFragment
import com.midevs.walmartchallenge.databinding.FragmentMovieListBinding
import com.midevs.walmartchallenge.di.ViewModelFactory
import com.midevs.walmartchallenge.models.Genre
import com.midevs.walmartchallenge.models.Movie
import com.midevs.walmartchallenge.ui.PaginationListener

class MovieListFragment : BaseFragment<FragmentMovieListBinding, MovieListViewModel>() {

    private var moviesList: MutableList<Movie> = mutableListOf()
    private var genresList: MutableList<Genre> = mutableListOf()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mAdapter = MovieListAdapter(moviesList, genresList)
        val recyclerView = getViewDataBinding().movieRecyclerview
        getViewDataBinding().movieRecyclerview.adapter = mAdapter
        getViewDataBinding().viewModel = viewModel
        viewModel.moviesList.observe(viewLifecycleOwner, Observer { movies ->
            moviesList.addAll(movies)
            mAdapter.notifyDataSetChanged()
        })
        viewModel.genresList.observe(viewLifecycleOwner, Observer { genres ->
            genresList.addAll(genres)
        })
        recyclerView.addOnScrollListener(object :
            PaginationListener(recyclerView.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                viewModel.nextPage()
            }

            override fun isLastPage(): Boolean {
                return viewModel.isLastPage()
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading.get()!!
            }
        })
        mAdapter.setOnItemClickListener { adapter, view, position ->
            moviesList[position].id.also {
                val bundle = Bundle().apply {
                    putInt(MovieFragment.MOVIE_ID, it)
                }
                findNavController().navigate(R.id.action_movie_list_to_movieFragment, bundle)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getGenres()
    }

    override val layoutId: Int get() = R.layout.fragment_movie_list
    override val viewModel: MovieListViewModel
        get() = ViewModelProvider(
            this,
            ViewModelFactory(activity)
        ).get(MovieListViewModel::class.java)
}

