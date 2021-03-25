package io.erikrios.github.canvaskitmovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.databinding.ItemMovieBinding

class MovieAdapter(
    private val movies: List<Movie>,
    private val onClickListener: ((Movie) -> Unit)
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) =
        holder.bindItem(movies[position], onClickListener)

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(movie: Movie, clickListener: ((Movie) -> Unit)) {
            binding.apply {
                Glide.with(imgPoster.context)
                    .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                    .into(imgPoster)

                tvTitle.text = movie.title
                rbVoteAverage.rating = movie.voteAverage.toFloat()
                tvVoteAverage.text = itemView.context.getString(
                    R.string.tv_vote_average_container,
                    movie.voteAverage
                )
                tvReleaseDate.text = movie.releaseDate
                tvVoteCount.text =
                    itemView.context.getString(R.string.tv_vote_count_container, movie.voteCount)
            }
            itemView.setOnClickListener { clickListener(movie) }
        }
    }
}