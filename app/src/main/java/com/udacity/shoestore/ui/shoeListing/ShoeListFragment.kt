package com.udacity.shoestore.ui.shoeListing

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeStoreAppPreferences
import com.udacity.shoestore.ShoesViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.ui.onboarding.WelcomeFragmentDirections

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
            if(shoeList.isEmpty()) {
                binding.emptyListLinearLayout.visibility = View.VISIBLE
            } else {
                binding.emptyListLinearLayout.visibility = View.GONE

                binding.shoeItemsLinearLayout.removeAllViews()

                shoeList.forEach {
                    val itemBinding = DataBindingUtil.inflate<ShoeItemBinding>(inflater, R.layout.shoe_item, container,false)
                    itemBinding.shoe = it

                    // This is only a temporary solution since I don't know exactly how the images list would be populated
                    // In the case of the list being populated with the images URIs, I would load the corresponding image with Glide
                    if(it.images.isNotEmpty()) {
                        itemBinding.shoeImage.setImageResource(resources.getIdentifier(it.images[0], "drawable", activity?.packageName))
                    }

                    binding.shoeItemsLinearLayout.addView(itemBinding.root)
                }
            }
        })

        binding.addShoeButton.setOnClickListener { view: View ->
            view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
        }

        return binding.root
    }
}