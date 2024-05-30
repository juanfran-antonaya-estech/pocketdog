package com.juanfra.pocketdog.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.models.combate.Resultado
import com.juanfra.pocketdog.ui.MainActivity
import com.juanfra.pocketdog.ui.adapter.CombateAdapter

class RegistroBatallasFragment : Fragment() {

    private lateinit var combateAdapter: CombateAdapter
    private lateinit var recyclerView: RecyclerView
    private var resultados: ArrayList<Resultado> = arrayListOf() // Inicializa tu lista de resultados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializa la lista de resultados aquí si es necesario
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registro_batallas, container, false)

        // Configura el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicializa el adaptador
        combateAdapter = CombateAdapter(resultados)
        recyclerView.adapter = combateAdapter

        return view
    }

    override fun onResume() {
        super.onResume()
        // Cambiar el título de la Toolbar
        (activity as? MainActivity)?.setToolbarTitle("Log")
    }

}