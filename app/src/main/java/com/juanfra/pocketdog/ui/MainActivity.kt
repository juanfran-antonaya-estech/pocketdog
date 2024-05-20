package com.juanfra.pocketdog.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.databinding.ActivityMainBinding
import com.juanfra.pocketdog.databinding.FragmentInicioBinding
import com.juanfra.pocketdog.ui.fragment.BuscarBatallaFragment
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val viewModel by viewModels<PesetasViewModel > {
        PesetasViewModel.PesetasViewModelFactory(this)
    }
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

        binding.fabToBattle.setOnClickListener {
            BuscarBatallaFragment.viewModel = viewModel
            binding.fcv.findNavController().navigate(R.id.action_inicioFragment_to_buscarBatallaFragment)
        }
    }
}