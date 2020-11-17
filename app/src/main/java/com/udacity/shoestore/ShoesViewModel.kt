package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel: ViewModel() {

    private val _shoes = MutableLiveData<List<Shoe>>(
        mutableListOf(
            Shoe("Unisex High Top Shoe", 23.5,"Chuck Taylor All Star", "Very good condition.")
        )
    )
    val shoes: LiveData<List<Shoe>>
        get() = _shoes
}