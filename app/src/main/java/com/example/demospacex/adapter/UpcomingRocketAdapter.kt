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
import com.example.demospacex.databinding.UpcomingRocketLayoutAdapterBinding
import com.example.demospacex.models.UpcomingRocketsItem
import com.example.demospacex.view.fragment.upcomingRocket.UpcomingRocketClickHandler

class UpcomingRocketAdapter(private val context: Context, private val clickHandler: UpcomingRocketClickHandler) : RecyclerView.Adapter<UpcomingRocketAdapter.MyViewHolder>() {

    inner class MyViewHolder(val viewBinding: UpcomingRocketLayoutAdapterBinding) :
        RecyclerView.ViewHolder(viewBinding.root),
        View.OnClickListener {

        init {
            viewBinding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentRocket = upcomingRockets[adapterPosition]
            clickHandler.clickedItem(currentRocket)

        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<UpcomingRocketsItem>(){
        override fun areItemsTheSame(oldItem: UpcomingRocketsItem, newItem: UpcomingRocketsItem): Boolean {
            return oldItem.flight_number == newItem.flight_number
        }

        override fun areContentsTheSame(oldItem: UpcomingRocketsItem, newItem: UpcomingRocketsItem): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var upcomingRockets: List<UpcomingRocketsItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(UpcomingRocketLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRocket = upcomingRockets[position]

        //yapılacak
        holder.viewBinding.apply {

            if (!currentRocket.links.flickr_images.isNullOrEmpty()){
                Glide.with(context)
                    .load(currentRocket.links.flickr_images[0])
                    .placeholder(R.drawable.rocket)
                    .into(imageViewRocket)
            }else{
                imageViewRocket.setImageResource(R.drawable.rocket)

            }

            textViewMissionName.text = currentRocket.mission_name
            textViewLaunchYear.text = currentRocket.launch_year


            textViewRocketName.text = currentRocket.rocket.rocket_name
        }
    }

    override fun getItemCount() = upcomingRockets.size

}