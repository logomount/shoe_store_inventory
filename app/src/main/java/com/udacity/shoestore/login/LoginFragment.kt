package com.udacity.shoestore.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    var emailEntered: Boolean = false
    var passwordEntered: Boolean = false

    var canLogin: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        binding.emailEditText.doAfterTextChanged {
            emailEntered = true
            checkInfoComplete()
        }

        binding.editTextTextPassword.doAfterTextChanged {
            passwordEntered = true
            checkInfoComplete()
        }

        binding.loginButton.setOnClickListener{
            if(canLogin){
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            } else {
                Toast.makeText(activity, "Please, enter your email and password!", Toast.LENGTH_LONG).show()
            }

        }

        binding.createLoginButton.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }

        return binding.root
    }

    private fun checkInfoComplete(){
        if(emailEntered && passwordEntered){
            canLogin = true
        }
    }

}