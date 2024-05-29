package com.juanfra.pocketdog.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.juanfra.pocketdog.R
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
        viewModel.resetBattle()

        setSupportActionBar(binding.toolbar)
        binding.bottomNavigationView.itemActiveIndicatorColor = null

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        menuNavegacion()

        binding.fabToBattle.setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.inicioFragment -> {
                    BuscarBatallaFragment.viewModel = viewModel
                    navController.navigate(R.id.action_inicioFragment_to_buscarBatallaFragment)
                }

                R.id.tiendaFragment -> {
                    BuscarBatallaFragment.viewModel = viewModel
                    gotoInicio()
                    navController.navigate(R.id.action_inicioFragment_to_buscarBatallaFragment)
                }

                R.id.misPerrosFragment -> {
                    BuscarBatallaFragment.viewModel = viewModel
                    gotoInicio()
                    navController.navigate(R.id.action_inicioFragment_to_buscarBatallaFragment)
                }

                R.id.registroBatallasFragment -> {
                    BuscarBatallaFragment.viewModel = viewModel
                    gotoInicio()
                    navController.navigate(R.id.action_inicioFragment_to_buscarBatallaFragment)
                }

                R.id.batallaFragment -> {
                    navController.navigateUp()
                }

                R.id.buscarBatallaFragment -> {

                }
            }
        }




    }

    private fun gotoInicio() {
        while (navController.currentDestination?.id != R.id.inicioFragment) {
            navController.popBackStack()
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