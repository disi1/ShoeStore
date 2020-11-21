package com.udacity.shoestore.ui.shoeDetails

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoesViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding

// I decided to use a BottomSheetDialogFragment instead of a Fragment because I think it was a better idea in terms of UX
// rather than using the entire screen for just a few fields and a couple of buttons
class ShoeDetailsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentShoeDetailsBinding
    private val shoeViewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentShoeDetailsBinding>(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )

        binding.shoeViewModel = shoeViewModel

        binding.cancelButton.setOnClickListener {
            shoeViewModel.cancel()
            dismiss()
        }

        binding.doneButton.setOnClickListener {
            if(isNameValid() && isCompanyValid() && isSizeValid() && isDescriptionValid()) {
                shoeViewModel.addShoe()
                dismiss()
            }
        }

        return binding.root
    }

    private fun isNameValid(): Boolean {
        return if(binding.nameEditText.text.toString() == "") {
            binding.nameEditText.error = getString(R.string.invalidNameError)
            false
        } else {
            binding.nameEditText.error = null
            true
        }
    }

    private fun isCompanyValid(): Boolean {
        return if(binding.companyEditText.text.toString() == "") {
            binding.companyEditText.error = getString(R.string.invalidCompanyError)
            false
        } else {
            binding.companyEditText.error = null
            true
        }
    }

    private fun isSizeValid(): Boolean {
        return if(binding.sizeEditText.text.toString() == "") {
            binding.sizeEditText.error = getString(R.string.invalidSizeError)
            false
        } else {
            binding.sizeEditText.error = null
            true
        }
    }

    private fun isDescriptionValid(): Boolean {
        return if(binding.descriptionEditText.text.toString() == "") {
            binding.descriptionEditText.error = getString(R.string.invalidDescriptionError)
            false
        } else {
            binding.descriptionEditText.error = null
            true
        }
    }
}