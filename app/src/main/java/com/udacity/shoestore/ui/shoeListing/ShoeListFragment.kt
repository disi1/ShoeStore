package com.udacity.shoestore.ui.shoeListing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoesViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.databinding.ShoeItemBinding

class ShoeListFragment : Fragment() {

    private val shoeViewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        shoeViewModel.shoes.observe(viewLifecycleOwner, { shoeList ->
            var objectType = "items"
            if(shoeList.size == 1) {
                objectType = "item"
            }

            (activity as AppCompatActivity).supportActionBar?.title = "Shoes (${shoeList.size} ${objectType})"

            shoeList.forEach {
                val itemBinding = DataBindingUtil.inflate<ShoeItemBinding>(inflater, R.layout.shoe_item, container,false)
                itemBinding.shoe = it
                binding.shoeItemsLinearLayout.addView(itemBinding.root)
            }
        })

        binding.addShoeButton.setOnClickListener {
            Toast.makeText(this.context, "Add Shoe button was clicked.", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}