package com.juanfra.pocketdog.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.databinding.ActivityMainBinding
import com.juanfra.pocketdog.databinding.FragmentInicioBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.bottomAppBar2.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        binding.bottomAppBar2.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.accelerator -> {
                    // Handle accelerator icon press
                    true
                }
                R.id.rotation -> {
                    // Handle rotation icon press
                    true
                }
                R.id.dashboard -> {
                    // Handle dashboard icon press
                    true
                }
                else -> false
            }
        }
    }
}