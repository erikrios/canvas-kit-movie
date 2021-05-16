package io.github.erikrios.canvaskitmovie.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.erikrios.canvaskitmovie.core.databinding.ItemCreatorBinding
import io.github.erikrios.canvaskitmovie.core.domain.model.Creator
import io.github.erikrios.canvaskitmovie.core.utils.ImageConfigurations
import io.github.erikrios.canvaskitmovie.core.utils.ImageConfigurations.generateFullImageUrl

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
                    val imageUrl = generateFullImageUrl(
                        it,
                        ImageConfigurations.ImageType.PROFILE,
                        profileSize = ImageConfigurations.ProfileSize.WIDTH_185
                    )
                    Glide.with(civProfile.context)
                        .load(imageUrl)
                        .into(civProfile)
                }
                tvCreatorName.text = creator.name
            }
        }
    }
}