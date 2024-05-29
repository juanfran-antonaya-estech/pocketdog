package com.juanfra.pocketdog.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.pesetas.Pesetas
import com.juanfra.pocketdog.databinding.ActivityMainBinding
import com.juanfra.pocketdog.ui.fragment.BuscarBatallaFragment
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<PesetasViewModel> {
        PesetasViewModel.PesetasViewModelFactory(this)
    }
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.loadDoggos()
        menuNavegacion()
        setSupportActionBar(binding.toolbar)
        binding.bottomNavigationView.itemActiveIndicatorColor = null
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.fabToBattle.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.inicioFragment -> {
                    BuscarBatallaFragment.viewModel = viewModel
                    navController.navigate(R.id.action_inicioFragment_to_buscarBatallaFragment)
                }

                R.id.tiendaFragment -> {
                    BuscarBatallaFragment.viewModel = viewModel
                    navController.navigate(R.id.action_tiendaFragment_to_buscarBatallaFragment)
                }

                R.id.misPerrosFragment -> {
                    BuscarBatallaFragment.viewModel = viewModel
                    navController.navigate(R.id.action_misPerrosFragment_to_buscarBatallaFragment)
                }

                R.id.registroBatallasFragment -> {
                    BuscarBatallaFragment.viewModel = viewModel
                    navController.navigate(R.id.action_registroBatallasFragment_to_buscarBatallaFragment)
                }

                R.id.batallaFragment -> {
                    binding.fcv.findNavController().navigateUp()
                }

                R.id.buscarBatallaFragment -> {
                    binding.fcv.findNavController().navigateUp()
                }
            }
        }




    }

    private fun menuNavegacion() {
        val bottomNavigationView = binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        NavigationUI.setupWithNavController(
            bottomNavigationView,
            navHostFragment.navController
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}