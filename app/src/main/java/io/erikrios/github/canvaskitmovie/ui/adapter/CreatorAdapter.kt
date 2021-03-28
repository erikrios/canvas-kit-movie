package io.erikrios.github.canvaskitmovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.erikrios.github.canvaskitmovie.data.model.Creator
import io.erikrios.github.canvaskitmovie.databinding.ItemCreatorBinding

class CreatorAdapter(private val creators: List<Creator>) :
    RecyclerView.Adapter<CreatorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCreatorBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(creators[position])

    override fun getItemCount(): Int = creators.size

    inner class ViewHolder(private val binding: ItemCreatorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(creator: Creator) {
            binding.apply {
                creator.profilePath?.let {
                    Glide.with(civProfile.context)
                        .load("https://image.tmdb.org/t/p/w185${creator.profilePath}")
                        .into(civProfile)
                }
                tvName.text = creator.name
            }
        }
    }
}