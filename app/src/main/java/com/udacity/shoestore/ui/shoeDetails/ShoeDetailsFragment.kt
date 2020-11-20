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

        binding.cancelButton.setOnClickListener {
            shoeViewModel.shoeName.value = null
            shoeViewModel.shoeDescription.value = null
            shoeViewModel.shoeSize.value = null
            shoeViewModel.shoeCompany.value = null
            dismiss()
        }

        binding.nameEditText.afterTextChanged { shoeName ->
            shoeViewModel.shoeName.value = shoeName
        }

        binding.companyEditText.afterTextChanged { shoeCompany ->
            shoeViewModel.shoeCompany.value = shoeCompany
        }

        binding.descriptionEditText.afterTextChanged { shoeDescription ->
            shoeViewModel.shoeDescription.value = shoeDescription
        }

        binding.sizeEditText.afterTextChanged { shoeSize ->
            shoeViewModel.shoeSize.value = shoeSize
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
        if(binding.nameEditText.text.toString() == "") {
            binding.nameEditText.error = getString(R.string.invalidNameError)
            return false
        } else {
            binding.nameEditText.error = null
            return true
        }
    }

    private fun isCompanyValid(): Boolean {
        if(binding.companyEditText.text.toString() == "") {
            binding.companyEditText.error = getString(R.string.invalidCompanyError)
            return false
        } else {
            binding.companyEditText.error = null
            return true
        }
    }

    private fun isSizeValid(): Boolean {
        if(binding.sizeEditText.text.toString() == "") {
            binding.sizeEditText.error = getString(R.string.invalidSizeError)
            return false
        } else {
            binding.sizeEditText.error = null
            return true
        }
    }

    private fun isDescriptionValid(): Boolean {
        if(binding.descriptionEditText.text.toString() == "") {
            binding.descriptionEditText.error = getString(R.string.invalidDescriptionError)
            return false
        } else {
            binding.descriptionEditText.error = null
            return true
        }
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}