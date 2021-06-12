package com.midevs.walmartchallenge.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.midevs.walmartchallenge.databinding.FragmentMovieBinding
import com.midevs.walmartchallenge.R
import com.midevs.walmartchallenge.base.BaseFragment
import com.midevs.walmartchallenge.di.ViewModelFactory
import com.midevs.walmartchallenge.models.Movie
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.movie_item.*
import kotlinx.android.synthetic.main.movie_item.movie_genres
import kotlinx.android.synthetic.main.movie_item.movie_name
import kotlinx.android.synthetic.main.movie_item.movie_release_year
import kotlinx.android.synthetic.main.movie_item.movie_score


/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : BaseFragment<FragmentMovieBinding, MovieDetailViewModel>() {
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(MOVIE_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getViewDataBinding().viewModel = viewModel
        viewModel.mutableMovie.observe(viewLifecycleOwner, Observer { movie ->
            Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
                .into(movie_detail_image)
        })
    }


    override fun onResume() {
        super.onResume()
        viewModel.getMovie(movieId)
    }

    companion object {
        const val MOVIE_ID = "movie_id"

        @JvmStatic
        fun newInstance(movieId: Int) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID, movieId)
                }
            }
    }

    override val layoutId: Int
        get() = R.layout.fragment_movie

    override val viewModel: MovieDetailViewModel
        get() = ViewModelProvider(
            this,
            ViewModelFactory(activity)
        ).get(MovieDetailViewModel::class.java)
}