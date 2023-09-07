package com.sachin.app.easyride.authentication.ui.screen

import android.os.CountDownTimer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sachin.app.easyride.authentication.ui.navigation.PHONE_NUMBER_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        OtpUiState(phoneNumber = savedStateHandle[PHONE_NUMBER_ARG]!!)
    )
    val uiState = _uiState.asStateFlow()

    private val countDownTimer = object : CountDownTimer(OTP_RESEND_TIMEOUT, 1000L) {
        override fun onTick(millisUntilFinished: Long) {
            val formattedTime = formatMillis(millisUntilFinished)
            _uiState.update { it.copy(countDownTime = formattedTime) }
        }

        override fun onFinish() {
            _uiState.update { it.copy(isResendEnabled = true) }
        }

    }

    private fun formatMillis(timeInMillis: Long): String {
        val min = String.format("%02d", timeInMillis / (60 * 1000))
        val seconds = String.format("%02d", (timeInMillis % (60 * 1000)).toInt() / 1000)
        return "$min:$seconds"
    }

    fun startCountDown() {
        countDownTimer.start()
    }

    fun onOtpChanged(value: String) {
        _uiState.update { it.copy(otp = value) }
    }

    fun verify() {

    }

    companion object {
        private const val OTP_RESEND_TIMEOUT = 2 * 60 * 1000L
    }
}

data class OtpUiState(
    val otp: String = "",
    val phoneNumber: String = "",
    val countDownTime: String = "",
    val isLoading: Boolean = false,
    val isResendEnabled: Boolean = false
)