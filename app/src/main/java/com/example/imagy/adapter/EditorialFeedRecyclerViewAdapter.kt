package com.example.imagy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imagy.databinding.EditorialPhotoViewHolderBinding
import com.example.imagy.network.EditorialFeedPhoto

class EditorialFeedRecyclerViewAdapter :
    ListAdapter<EditorialFeedPhoto, EditorialFeedRecyclerViewAdapter.EditorialFeedPhotoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EditorialFeedPhotoViewHolder {
        return EditorialFeedPhotoViewHolder(EditorialPhotoViewHolderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: EditorialFeedPhotoViewHolder,
        position: Int
    ) {
        val editorialFeedPhoto = getItem(position)
        holder.editorialFeedPhotoImageView.load(editorialFeedPhoto.imageUrls.regularImageUrl)
    }

    class EditorialFeedPhotoViewHolder(binding: EditorialPhotoViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
            val editorialFeedPhotoImageView: ImageView = binding.editorialFeedImageView
    }

    companion object DiffCallback: DiffUtil.ItemCallback<EditorialFeedPhoto>() {
        override fun areItemsTheSame(
            oldItem: EditorialFeedPhoto,
            newItem: EditorialFeedPhoto
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: EditorialFeedPhoto,
            newItem: EditorialFeedPhoto
        ): Boolean {
            return oldItem.imageUrls.regularImageUrl == newItem.imageUrls.regularImageUrl
        }

    }

}