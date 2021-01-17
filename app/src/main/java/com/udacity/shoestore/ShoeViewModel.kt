package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    private var _shoes = MutableLiveData<List<Shoe>>()
    val shoes : LiveData<List<Shoe>>
        get() = _shoes

    init {
        _shoes.value = emptyList()
    }

    fun addShoe(shoe: Shoe) {
        _shoes.value = _shoes.value?.plus(shoe)
    }

}