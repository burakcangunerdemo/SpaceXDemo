package com.example.demospacex.view.fragment.upcomingRocket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.demospacex.R
import com.example.demospacex.databinding.FragmentUpcomingDetailBinding
import com.example.demospacex.databinding.FragmentUpcomingRocketsBinding
import com.example.demospacex.models.FavoriteRocket
import com.example.demospacex.viewmodel.UpcomingRocketDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingDetailFragment : Fragment(R.layout.fragment_upcoming_detail) {

    private var _binding: FragmentUpcomingDetailBinding? = null
    private val viewBinding get() = _binding!!

    private val viewModel: UpcomingRocketDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpcomingDetailBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.responseOneUpcomingRocket.observe(viewLifecycleOwner, {upcomingRocket ->

            viewBinding.textViewRocketName.text = upcomingRocket.rocket.rocket_name

            viewBinding.textViewDescripton.text = upcomingRocket.details

            viewBinding.textViewMissionName.text = upcomingRocket.mission_name
            viewBinding.textViewLaunchYear.text = upcomingRocket.launch_year
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}