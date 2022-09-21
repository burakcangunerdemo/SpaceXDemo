package com.example.demospacex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demospacex.R
import com.example.demospacex.databinding.ImageLayoutAdapterBinding


class ImageAdapter(private val context: Context) : RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {

    inner class MyViewHolder(val viewBinding: ImageLayoutAdapterBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var images: List<String>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ImageLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentImage = images[position]

        //yapılacak
        holder.viewBinding.apply {

            Glide.with(context)
                .load(currentImage)
                .into(imageViewImage)
        }
    }

    override fun getItemCount() = images.size

}