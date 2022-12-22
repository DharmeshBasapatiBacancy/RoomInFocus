package com.kudos.roominfocus.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudos.roominfocus.databinding.ActivityBusStopBinding
import com.kudos.roominfocus.viewmodel.AppViewModel
import com.kudos.roominfocus.views.adapters.BusStopAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BusStopActivity : AppCompatActivity() {

    private lateinit var busStopAdapter: BusStopAdapter
    private var STOP_NAME: String = ""
    private lateinit var binding: ActivityBusStopBinding

    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusStopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        STOP_NAME = intent.extras?.getString("STOP_NAME").toString()
        setupBusStopsList()
        observeBusStops()
    }

    private fun observeBusStops() {
        lifecycleScope.launch {
            appViewModel.scheduleForStopName(STOP_NAME).collect {
                busStopAdapter.submitList(it)
            }
        }
    }

    private fun setupBusStopsList() {
        busStopAdapter = BusStopAdapter {}
        binding.busStopsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@BusStopActivity)
            adapter = busStopAdapter
        }
    }
}