package com.udacity.shoestore.ui.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        binding.loginButton.setOnClickListener { view: View ->
            if(isEmailAddressValid(binding.emailEditText.text) && (isPasswordValid(binding.passwordEditText.text))) {
                binding.passwordInputText.error = null
                binding.emailInputText.error = null

                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)

                view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            } else {
                if(!isPasswordValid(binding.passwordEditText.text)) {
                    binding.passwordInputText.error = getString(R.string.password_error)
                } else {
                    binding.passwordInputText.error = null
                }

                if(!isEmailAddressValid(binding.emailEditText.text)) {
                    binding.emailInputText.error = getString(R.string.email_error)
                } else {
                    binding.emailInputText.error = null
                }
            }
        }

        binding.createAccountButton.setOnClickListener { view: View ->
            if(isEmailAddressValid(binding.emailEditText.text)
                    && isPasswordValid(binding.passwordEditText.text)
                    && isConfirmPasswordValid(binding.passwordEditText.text, binding.confirmPasswordEditText.text)) {
                binding.passwordInputText.error = null
                binding.emailInputText.error = null
                binding.confirmPasswordInputText.error = null

                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)

                view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            } else {
                if(!isEmailAddressValid(binding.emailEditText.text)) {
                    binding.emailInputText.error = getString(R.string.email_error)
                } else {
                    binding.emailInputText.error = null
                }

                if(!isPasswordValid(binding.passwordEditText.text)) {
                    binding.passwordInputText.error = getString(R.string.password_error)
                } else {
                    binding.passwordInputText.error = null
                }

                if (!isConfirmPasswordValid(binding.passwordEditText.text, binding.confirmPasswordEditText.text)) {
                    binding.confirmPasswordInputText.error = getString(R.string.confirm_password_error)
                } else {
                    binding.confirmPasswordInputText.error = null
                }
            }
        }

        binding.signUpButton.setOnClickListener {
            binding.confirmPasswordInputText.visibility = View.VISIBLE
            binding.createAccountButton.visibility = View.VISIBLE
            binding.cancelButton.visibility = View.VISIBLE

            binding.loginButton.visibility = View.GONE
            binding.signUpButton.visibility = View.GONE
            binding.noAccountText.visibility = View.GONE
        }

        binding.cancelButton.setOnClickListener {
            binding.confirmPasswordInputText.visibility = View.GONE
            binding.cancelButton.visibility = View.GONE
            binding.createAccountButton.visibility = View.GONE

            binding.loginButton.visibility = View.VISIBLE
            binding.signUpButton.visibility = View.VISIBLE
            binding.noAccountText.visibility = View.VISIBLE

            binding.passwordEditText.text?.clear()
            binding.confirmPasswordEditText.text?.clear()
            binding.emailEditText.text?.clear()

            binding.passwordInputText.error = null
            binding.emailInputText.error = null
            binding.confirmPasswordInputText.error = null
        }

        return binding.root
    }

    private fun isEmailAddressValid(text: Editable?): Boolean {
        return text != null && Patterns.EMAIL_ADDRESS.matcher(text.toString()).matches()
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 4
    }

    private fun isConfirmPasswordValid(password: Editable?, confirmPassword: Editable?): Boolean {
        return confirmPassword != null && password.toString() == confirmPassword.toString()
    }
}