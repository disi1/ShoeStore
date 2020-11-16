package com.udacity.shoestore.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentInstructionBinding>(
            inflater,
            R.layout.fragment_instruction,
            container,
            false
        )

        binding.getStartedButton.setOnClickListener {
            Toast.makeText(this.context, "Get Started clicked!", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}