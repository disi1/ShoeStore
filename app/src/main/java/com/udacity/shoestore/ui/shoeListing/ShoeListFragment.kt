package com.udacity.shoestore.ui.shoeListing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class ShoeListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )
//
//        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(false)
//        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
//        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
//        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(null)

        binding.addShoeButton.setOnClickListener {
            Toast.makeText(this.context, "Add Shoe button was clicked.", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}