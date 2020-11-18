package com.udacity.shoestore.ui.shoeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding

class ShoeDetailsFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentShoeDetailsBinding>(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )

        binding.cancelButton.setOnClickListener { view: View ->
            Toast.makeText(this.context, "Cancel button clicked", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        binding.doneButton.setOnClickListener { view: View ->
            Toast.makeText(this.context, "Done button clicked", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}