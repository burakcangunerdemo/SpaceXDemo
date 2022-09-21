package com.example.demospacex.view.fragment.upcomingRocket

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
import com.example.demospacex.adapter.UpcomingRocketAdapter
import com.example.demospacex.databinding.FragmentUpcomingRocketsBinding
import com.example.demospacex.models.UpcomingRocketsItem
import com.example.demospacex.viewmodel.UpcomingRocketsViewModel

class UpcomingRocketsFragment : Fragment(R.layout.fragment_upcoming_rockets),
    UpcomingRocketClickHandler {

    private var _binding : FragmentUpcomingRocketsBinding? = null
    private val viewBinding get() = _binding!!

    private val viewModel: UpcomingRocketsViewModel by activityViewModels()

    private lateinit var upcomingRocketAdapter: UpcomingRocketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpcomingRocketsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRv()
    }

    private fun setUpRv(){
        upcomingRocketAdapter = UpcomingRocketAdapter(requireContext(), this)

        viewBinding.recyclerViewUpcomingRockets.apply {
            adapter = upcomingRocketAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }

        viewModel.responseUpcomingRockets.observe(viewLifecycleOwner, { listRockets ->
            upcomingRocketAdapter.upcomingRockets = listRockets

        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun clickedItem(rocket: UpcomingRocketsItem) {
        val bundle = bundleOf("rocket" to rocket)
        findNavController().navigate(R.id.upcomingDetailFragment, bundle)
    }
}