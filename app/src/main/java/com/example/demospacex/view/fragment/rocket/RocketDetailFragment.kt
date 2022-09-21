package com.example.demospacex.view.fragment.rocket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demospacex.R
import com.example.demospacex.adapter.ImageAdapter
import com.example.demospacex.databinding.FragmentRocketDetailBinding
import com.example.demospacex.models.FavoriteRocket
import com.example.demospacex.viewmodel.RocketDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketDetailFragment : Fragment(R.layout.fragment_rocket_detail) {

    private var _binding: FragmentRocketDetailBinding? = null
    private val viewBinding get() = _binding!!

    private lateinit var imageAdapter: ImageAdapter

    private val viewModel: RocketDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRocketDetailBinding.inflate(inflater, container, false)
        return  viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageAdapter = ImageAdapter(requireContext())

        viewBinding.recyclerViewImage.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)

        }

        viewModel.responseRocket.observe(viewLifecycleOwner, {rocket ->
            imageAdapter.images = rocket.flickr_images

            viewBinding.textViewRocketName.text = rocket.rocket_name

            viewBinding.textViewDescripton.text = rocket.description

            if(rocket.isFavorite){
                viewBinding.imageViewFav.setImageResource(R.drawable.ic_baseline_star_50)
            }else{
                viewBinding.imageViewFav.setImageResource(R.drawable.ic_baseline_star_border_50)
            }

            viewBinding.imageViewFav.setOnClickListener {
                if (rocket.isFavorite){
                    var currentFavoriteRocket = FavoriteRocket(rocket.rocket_id, rocket)
                    viewModel.deleteFavoriteRocket(currentFavoriteRocket)
                    rocket.isFavorite = false

                }else{
                    var currentFavoriteRocket = FavoriteRocket(rocket.rocket_id, rocket)
                    viewModel.insertFavoriteRocket(currentFavoriteRocket)
                    rocket.isFavorite = true
                }

                viewModel.updateRocket(rocket)

            }
        })


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
