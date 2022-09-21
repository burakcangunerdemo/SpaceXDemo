package com.example.demospacex.view.fragment.rocket

import android.os.Bundle
import android.util.Log
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
import com.example.demospacex.databinding.FragmentRocketsBinding
import com.example.demospacex.models.RocketsItem
import com.example.demospacex.viewmodel.RocketsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketsFragment : Fragment(R.layout.fragment_rockets), RocketClickHandler {

    private var _binding : FragmentRocketsBinding? = null
    private val viewBinding get() = _binding!!

    private val viewModel: RocketsViewModel by activityViewModels()

    private lateinit var rocketAdapter: RocketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRocketsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRv()
    }

    private fun setUpRv(){
        rocketAdapter = RocketAdapter(requireContext(), this)

        viewBinding.recyclerViewRockets.apply {
            adapter = rocketAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }

        viewModel.responseRockets.observe(viewLifecycleOwner, {listRockets ->
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