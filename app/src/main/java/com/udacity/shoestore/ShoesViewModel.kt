package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel: ViewModel() {

    private val shoesList = mutableListOf(
        Shoe(
            "Converse Chuck Taylor",
            25.5,"Chuck Taylor All Star",
            "Unisex High Top Sneakers.\n\nThe iconic innovative shoes that made headlines when announced.\nNever worn, in their original box, mint condition.",
            listOf("img_converse_shoes"))
    )

    private val _shoes = MutableLiveData<List<Shoe>>(shoesList)
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    fun addShoe(
        name: String,
        company:  String,
        size: Double,
        description: String
    ) {
        val shoe = Shoe(name, size, company, description, mutableListOf())

        shoesList.add(shoe)

        _shoes.value = shoesList
    }

    fun clearShoes() {
        shoesList.clear()
    }
}