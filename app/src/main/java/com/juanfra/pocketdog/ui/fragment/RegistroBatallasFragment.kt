package com.juanfra.pocketdog.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.ui.MainActivity
import com.juanfra.pocketdog.ui.adapter.CombateAdapter
import com.juanfra.pocketdog.ui.adapter.TiendaAdapter

class RegistroBatallasFragment : Fragment() {
    private lateinit var adapter: CombateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_batallas, container, false)
    }

    override fun onResume() {
        super.onResume()
        // Cambiar el título de la Toolbar
        (activity as? MainActivity)?.setToolbarTitle("Log")
    }



}