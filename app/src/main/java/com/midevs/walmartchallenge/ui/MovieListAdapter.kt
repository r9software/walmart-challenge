package com.midevs.walmartchallenge.ui

import android.view.Gravity
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.midevs.walmartchallenge.R
import com.midevs.walmartchallenge.models.Movie


class MovieListAdapter(data: MutableList<Movie>) :
    BaseQuickAdapter<Movie, BaseViewHolder>(R.layout.movie_item, data) {

    override fun convert(helper: BaseViewHolder, item: Movie) {
        helper.setText(R.id.movie_name, item.title)
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + item.poster_path)
            .into(helper.getView(R.id.movie_image))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (position % 2 == 0) {
            val params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.END
                marginEnd = 40
                bottomMargin = 40
            }
            holder.getView<CardView>(R.id.movie_cardview).layoutParams = params
        } else {
            val params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.START
                marginStart = 40
                bottomMargin = 40
            }
            holder.getView<CardView>(R.id.movie_cardview).layoutParams = params
        }
        super.onBindViewHolder(holder, position)
    }
}