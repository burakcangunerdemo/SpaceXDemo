package com.example.demospacex.view.fragment.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demospacex.R
import com.example.demospacex.adapter.RocketAdapter
import com.example.demospacex.databinding.FragmentFavoriteBinding
import com.example.demospacex.models.RocketsItem
import com.example.demospacex.view.fragment.rocket.RocketClickHandler
import com.example.demospacex.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite), RocketClickHandler {

    private var _binding : FragmentFavoriteBinding? = null
    private val viewBinding get() = _binding!!

    private val viewModel: FavoriteViewModel by activityViewModels()

    private lateinit var rocketAdapter: RocketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRv()
    }

    private fun setUpRv(){
        rocketAdapter = RocketAdapter(requireContext(), this)

        viewBinding.recyclerViewFavoriteRockets.apply {
            adapter = rocketAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }


        viewModel.responseFavoriteRockets.observe(viewLifecycleOwner, {listRockets ->
            rocketAdapter.rockets = listRockets
        })



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        viewModel.refresh()
    }

    override fun clickedItem(rocket: RocketsItem) {
        val bundle = bundleOf("rocket" to rocket)
        findNavController().navigate(R.id.rocketDetailFragment, bundle)
    }

}