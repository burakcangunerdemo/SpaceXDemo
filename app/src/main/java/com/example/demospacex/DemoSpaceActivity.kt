package com.example.demospacex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.demospacex.databinding.ActivityDemoSpaceBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DemoSpaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDemoSpaceBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDemoSpaceBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.space_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavigationView
        setupWithNavController(bottomNavigationView, navController)



    }

}