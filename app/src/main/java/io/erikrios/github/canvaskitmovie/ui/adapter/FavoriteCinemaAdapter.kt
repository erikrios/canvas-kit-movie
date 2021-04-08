package io.erikrios.github.canvaskitmovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.databinding.ItemFavoriteBinding
import io.erikrios.github.canvaskitmovie.utils.CinemaDiffCallback
import io.erikrios.github.canvaskitmovie.utils.ImageConfigurations
import io.erikrios.github.canvaskitmovie.utils.ImageConfigurations.generateFullImageUrl
import jp.wasabeef.glide.transformations.BlurTransformation

class FavoriteCinemaAdapter<T>(private val onClickListener: ((T) -> Unit)) :
    RecyclerView.Adapter<FavoriteCinemaAdapter<T>.ViewHolder>() {

    private val cinemas = mutableListOf<T>()

    fun setCinemas(cinemas: List<T>) {
        val diffCallback = CinemaDiffCallback(this.cinemas, cinemas)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.cinemas.clear()
        this.cinemas.addAll(cinemas)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(cinemas[position], onClickListener)

    override fun getItemCount(): Int = cinemas.size

    inner class ViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cinema: T, clickListener: ((T) -> Unit)) {
            val posterPath: String?
            val backdropPath: String?
            val title: String
            val ratingInfo: Double

            when (cinema) {
                is Movie -> {
                    posterPath = cinema.posterPath
                    backdropPath = cinema.backdropPath
                    title = cinema.title
                    ratingInfo = cinema.voteAverage
                }
                is TvShow -> {
                    posterPath = cinema.posterPath
                    backdropPath = cinema.backdropPath
                    title = cinema.name
                    ratingInfo = cinema.voteAverage
                }
                else -> throw IllegalArgumentException("Only Movie or TvShow instance are accepted.")
            }

            binding.apply {
                backdropPath?.let {
                    val imageUrl = generateFullImageUrl(
                        it,
                        ImageConfigurations.ImageType.BACKDROP
                    )
                    Glide.with(imgBackdrop.context)
                        .load(imageUrl)
                        .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
                        .into(imgBackdrop)
                }
                posterPath?.let {
                    val imageUrl = generateFullImageUrl(
                        it,
                        ImageConfigurations.ImageType.POSTER,
                        posterSize = ImageConfigurations.PosterSize.WIDTH_500
                    )
                    Glide.with(imgPoster.context)
                        .load(imageUrl)
                        .into(imgPoster)
                }
                tvTitle.text = title
                tvRatingInfo.text = itemView.context.getString(R.string.rating, ratingInfo)
            }
            itemView.setOnClickListener { clickListener(cinema) }
        }
    }
}