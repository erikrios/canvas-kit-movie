package io.erikrios.github.canvaskitmovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.databinding.ItemCinemaBinding

class CinemaAdapter<T>(
    private val cinemas: List<T>,
    private val onClickListener: ((T) -> Unit)
) :
    RecyclerView.Adapter<CinemaAdapter<T>.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaAdapter<T>.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCinemaBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CinemaAdapter<T>.ViewHolder, position: Int) =
        holder.bindItem(cinemas[position], onClickListener)

    override fun getItemCount(): Int = cinemas.size

    inner class ViewHolder(private val binding: ItemCinemaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(cinema: T, clickListener: ((T) -> Unit)) {
            val posterPathLeadingUrl = "https://image.tmdb.org/t/p/w500"
            val posterPath: String
            val title: String
            val voteAverage: Float

            when (cinema) {
                is Movie -> {
                    posterPath = cinema.posterPath.toString()
                    title = cinema.title
                    voteAverage = cinema.voteAverage.toFloat()
                }
                is TvShow -> {
                    posterPath = cinema.posterPath.toString()
                    title = cinema.name
                    voteAverage = cinema.voteAverage.toFloat()
                }
                else -> throw IllegalArgumentException("Only Movie or TvShow instance are accepted.")
            }
            binding.apply {
                Glide.with(imgPoster.context)
                    .load(posterPathLeadingUrl.plus(posterPath))
                    .into(imgPoster)

                tvTitle.text = title
                rbVoteAverage.rating = (voteAverage / 3.333).toFloat()
                tvVoteAverage.text = itemView.context.getString(
                    R.string.tv_vote_average_container, voteAverage
                )
            }
            itemView.setOnClickListener { clickListener(cinema) }
        }
    }
}