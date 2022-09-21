package com.example.demospacex.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demospacex.R
import com.example.demospacex.databinding.RocketLayoutAdapterBinding
import com.example.demospacex.models.FavoriteRocket
import com.example.demospacex.models.RocketsItem
import com.example.demospacex.view.fragment.rocket.RocketClickHandler
import java.net.URL


class RocketAdapter(private val context: Context, private val clickHandler: RocketClickHandler) : RecyclerView.Adapter<RocketAdapter.MyViewHolder>() {

    inner class MyViewHolder(val viewBinding: RocketLayoutAdapterBinding) :
        RecyclerView.ViewHolder(viewBinding.root),
        View.OnClickListener {

        init {
            viewBinding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentRocket = rockets[adapterPosition]
            clickHandler.clickedItem(currentRocket)

        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<RocketsItem>(){
        override fun areItemsTheSame(oldItem: RocketsItem, newItem: RocketsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RocketsItem, newItem: RocketsItem): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var rockets: List<RocketsItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RocketLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRocket = rockets[position]

        //yapılacak
        holder.viewBinding.apply {

            if (!currentRocket.flickr_images.isNullOrEmpty()){
                Glide.with(context)
                    .load(currentRocket.flickr_images[0])
                    .placeholder(R.drawable.rocket)
                    .into(imageViewRocket)
            }else{
                imageViewRocket.setImageResource(R.drawable.rocket)

            }

            textViewCompany.text = currentRocket.company
            textViewCountry.text = currentRocket.country

            if(currentRocket.isFavorite){
                imageViewFav.setImageResource(R.drawable.ic_baseline_star_24)
            }else{
                imageViewFav.setImageResource(R.drawable.ic_baseline_star_border_24)
            }

            textViewRocketName.text = currentRocket.rocket_name
        }
    }

    override fun getItemCount() = rockets.size

}