package com.kudos.roominfocus.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudos.roominfocus.databinding.ActivityMainBinding
import com.kudos.roominfocus.viewmodel.AppViewModel
import com.kudos.roominfocus.views.adapters.ScheduleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var binding: ActivityMainBinding

    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupScheduleList()
    }

    private fun setupScheduleList() {
        binding.apply {
            scheduleAdapter = ScheduleAdapter {
                startActivity(
                    Intent(
                        this@MainActivity,
                        BusStopActivity::class.java
                    ).putExtra("STOP_NAME", it.stopName)
                )
            }
            scheduleRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            scheduleRecyclerView.adapter = scheduleAdapter
            lifecycleScope.launch {
                appViewModel.fullSchedule().collect {
                    scheduleAdapter.submitList(it)
                }
            }
        }
    }
}