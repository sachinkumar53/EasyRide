package com.sachin.app.easyride.authentication.ui.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onPhoneNumberChanged(value: String) {
        if (value.length <= 10) {
            _uiState.update { it.copy(phoneNumber = value, phoneNumberError = null) }
        }
    }

    fun login(
        onNavigateToOtp: () -> Unit
    ) {
        if (!validateInputs()) return
        onNavigateToOtp()
    }

    private fun validateInputs(): Boolean {
        val phoneNumber = _uiState.value.phoneNumber
        /*if (phoneNumber.isBlank()) {
            _uiState.update { it.copy(phoneNumberError = "Please enter valid phone number") }
            return false
        }*/

        if (!isPhoneValid(phoneNumber)) {
            _uiState.update { it.copy(phoneNumberError = "Please enter valid phone number") }
            return false
        }
        return true
    }

    private fun isPhoneValid(phone: String): Boolean {
        return Pattern.matches(PHONE_VALIDATION_REGEX, phone)
    }

    companion object {
        //private const val TAG = "LoginViewModel"
        private const val PHONE_VALIDATION_REGEX = "[0-9]{10}"
    }
}

data class LoginUiState(
    val phoneNumber: String = "",
    val phoneNumberError: String? = null,
    val isLoading: Boolean = false,
)