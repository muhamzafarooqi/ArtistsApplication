package com.music.myartistsapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.music.myartistsapplication.R
import com.music.myartistsapplication.api.MyArtistsQuery
import com.music.myartistsapplication.databinding.ItemArtistBinding
import com.music.myartistsapplication.models.Node
import com.music.myartistsapplication.shared.DataBoundListAdapter
import javax.inject.Inject


class ArtistsListAdapter @Inject constructor() : DataBoundListAdapter<MyArtistsQuery.Node, ItemArtistBinding>(artistItemCallback) {
    override fun createBinding(parent: ViewGroup): ItemArtistBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_artist,parent,false)
    }

    override fun submitList(list: List<MyArtistsQuery.Node?>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    companion object {
        private val artistItemCallback = object : DiffUtil.ItemCallback<MyArtistsQuery.Node>() {
            override fun areItemsTheSame(oldItem: MyArtistsQuery.Node, newItem: MyArtistsQuery.Node) = oldItem.id== newItem.id

            override fun areContentsTheSame(oldItem: MyArtistsQuery.Node, newItem: MyArtistsQuery.Node) = oldItem == newItem
        }
    }

    override fun bind(binding: ItemArtistBinding, item: MyArtistsQuery.Node) {
        binding.data = item
    }
}