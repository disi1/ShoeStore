package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel: ViewModel() {

    private val _shoes = MutableLiveData<List<Shoe>>(
        mutableListOf(
            Shoe("Converse Chuck Taylor", 25.5,"Chuck Taylor All Star", "Unisex High Top Sneakers.\n\nThe iconic innovative shoes that made headlines when announced.\n\nNever worn, in their original box, mint condition.")
        )
    )
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

}