package com.juanfra.pocketdog.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juanfra.pocketdog.data.pesetas.Pesetas

open class PesetasViewModel() : ViewModel() {
    var liveData: MutableLiveData<Int> = MutableLiveData()

    fun setPesetas() {
        liveData.value = 1200
    }

    fun getPesetas() : Int? {
        return liveData.value
    }

    fun plusPesetas(sum: Int) {
        liveData.value?.plus(sum)
    }

    fun minusPesetas(min: Int) {
        liveData.value = liveData.value?.minus(min)
    }

}