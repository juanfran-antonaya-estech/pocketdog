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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.bottomAppBar2.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        binding.bottomAppBar2.setOnMenuItemClickListener { menuItem ->
            val navController = findNavController(R.id.fcv)
            when (menuItem.itemId) {

                R.id.menuInicio -> {
                    navController.navigate(R.id.inicioFragment)
                    true
                }
                R.id.menuInventario -> {
                    navController.navigate(R.id.inicioFragment)
                    true
                }
                R.id.menuTienda -> {
                    navController.navigate(R.id.tiendaFragment)
                    true
                }
                R.id.menuLog -> {
                    navController.navigate(R.id.inicioFragment)
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