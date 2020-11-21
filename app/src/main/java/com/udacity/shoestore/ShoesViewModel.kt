package com.udacity.shoestore

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel: ViewModel() {

    var shoe: Shoe? = Shoe("", 0.0, "", "")
    val shoeObservable = ShoeSizeObservable()

    private val shoesList = mutableListOf(
        Shoe(
            "Converse Chuck Taylor",
            25.5,"Chuck Taylor All Star",
            "Unisex High Top Sneakers.\n\nThe iconic innovative shoes that made headlines when announced.\nNever worn, in their original box, mint condition.",
            listOf("img_unsplash_converse"))
    )

    private val _shoes = MutableLiveData<List<Shoe>>(shoesList)
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    fun addShoe() {
        shoe?.let {
            it.size = shoeObservable.shoeSize.toDouble()
            shoesList.add(it)
        }
        _shoes.value = shoesList
        cleanup()
    }

    fun cancel() {
        cleanup()
    }

    private fun cleanup() {
        shoeObservable.shoeSize = ""
        shoe = Shoe("", 0.0, "", "")
    }

    inner class ShoeSizeObservable : BaseObservable() {
        var shoeSize = ""

        @Bindable
        fun getSize() : String {
            return shoeSize
        }

        @Bindable
        fun setSize(size: String) {
            if (shoeSize != size) {
                shoeSize = size

                notifyPropertyChanged(BR.size)
            }
        }
    }
}