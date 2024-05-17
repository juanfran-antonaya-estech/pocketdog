package com.juanfra.pocketdog.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanfra.pocketdog.data.Repository
import com.juanfra.pocketdog.data.doggos.Doggo

class JuanfraViewModel() : ViewModel() {
    var repo = Repository()

    fun getDoggo(s: String) : Doggo{
        val doggo
        val allbreeds = repo.dameRazas()
    }
}