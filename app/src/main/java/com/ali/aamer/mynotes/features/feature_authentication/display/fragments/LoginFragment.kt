package com.ali.aamer.mynotes.features.feature_authentication.display.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ali.aamer.mynotes.R
import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.TokenManager
import com.ali.aamer.mynotes.databinding.FragmentLoginBinding
import com.ali.aamer.mynotes.features.feature_authentication.data.models.UserLoginRequestModel
import com.ali.aamer.mynotes.features.feature_authentication.display.events.AuthenticationEvents
import com.ali.aamer.mynotes.features.feature_authentication.display.viewmodels.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var tokenManager: TokenManager

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AuthenticationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        if (tokenManager.getToken() != null) {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onRegisterHandler()
        onRegisterObserver()
    }

    private fun onRegisterObserver() {
        authViewModel.userLoginStatusLiveData.observe(viewLifecycleOwner) { userLoginResponse ->
            when (userLoginResponse) {
                is Resource.Loading -> {
                    binding.progressIndicator.isVisible = true
                }
                is Resource.Success -> {
                    binding.progressIndicator.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        "User LoginSuccess fully...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Failure -> {
                    binding.progressIndicator.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        userLoginResponse.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun onRegisterHandler() {
        binding.btnLogin.setOnClickListener {
            val userLoginRequestModel = UserLoginRequestModel(
                binding.etEmail.text.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
            authViewModel.authenticationEvents(AuthenticationEvents.UserLogin(userLoginRequestModel))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}