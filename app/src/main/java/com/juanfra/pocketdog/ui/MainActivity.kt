package com.juanfra.pocketdog.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
<<<<<<< HEAD
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
=======
import androidx.navigation.findNavController
>>>>>>> origin/rama-juanfran
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.databinding.ActivityMainBinding
import com.juanfra.pocketdog.databinding.FragmentInicioBinding
import com.juanfra.pocketdog.ui.fragment.BuscarBatallaFragment
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
<<<<<<< HEAD
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
=======
    val viewModel by viewModels<PesetasViewModel > {
        PesetasViewModel.PesetasViewModelFactory(this)
    }
>>>>>>> origin/rama-juanfran
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.loadDoggos()
        /*
        CoroutineScope(Dispatchers.IO).launch {
        viewModel.buyDoggo(viewModel.getRandomDoggo("comun").refdog.id,0)
        viewModel.buyDoggo(viewModel.getRandomDoggo("comun").refdog.id,0)
        viewModel.buyDoggo(viewModel.getRandomDoggo("comun").refdog.id,0)
        viewModel.buyDoggo(viewModel.getRandomDoggo("comun").refdog.id,0)
        }

        */



        setSupportActionBar(binding.toolbar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHost
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

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

<<<<<<< HEAD
=======
        binding.fabToBattle.setOnClickListener {
            BuscarBatallaFragment.viewModel = viewModel
            binding.fcv.findNavController().navigate(R.id.action_inicioFragment_to_buscarBatallaFragment)
        }
>>>>>>> origin/rama-juanfran
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}