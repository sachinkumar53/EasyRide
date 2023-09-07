package com.sachin.app.easyride.authentication.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sachin.app.easyride.core.ui.R
import com.sachin.app.easyride.core.ui.theme.EasyRideTheme
import com.sachin.app.easyride.core.ui.theme.coolGray
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OtpRoute(
    viewModel: OtpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.startCountDown() }

    OtpScreen(
        uiState = uiState,
        onVerifyClick = viewModel::verify,
        onOtpChanged = viewModel::onOtpChanged
    )
}

@Composable
private fun OtpScreen(
    onVerifyClick: () -> Unit,
    onOtpChanged: (String) -> Unit,
    uiState: OtpUiState
) {
    Scaffold { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            Image(
                painter = painterResource(id = R.drawable.otp),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f),
                alignment = Alignment.BottomCenter
            )

            Spacer(modifier = Modifier.weight(0.1f))

            Text(
                text = "Verify your number",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "We've sent a verification code to +91 ${uiState.phoneNumber}.\nEnter it below to continue",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 20.dp),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )

            OtpTextField(
                value = uiState.otp,
                onValueChange = onOtpChanged,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 24.dp)
            )

            Button(
                onClick = onVerifyClick,
                content = { Text(text = "Verify OTP") },
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (uiState.isResendEnabled) {
                Text(
                    text = buildAnnotatedString {
                        append("Didn't receive OTP? ")
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("Resend")
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodySmall
                )
            } else {
                Text(
                    text = buildAnnotatedString {
                        append("Resend OTP in ")
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(uiState.countDownTime)
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.weight(0.3f))
        }
    }
}

@Composable
private fun OtpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    size: Int = 4
) {
    var isFocused by rememberSaveable { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        decorationBox = {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                repeat(size) { index ->
                    val shouldShowCursor = isFocused && value.length == index
                    DigitBox(
                        value = value.getOrNull(index),
                        showCursor = shouldShowCursor
                    )
                }
            }
        },
        modifier = modifier.onFocusChanged { isFocused = it.isFocused },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
private fun DigitBox(
    value: Char?,
    showCursor: Boolean = false
) {
    var drawCursor by remember { mutableStateOf(false) }
    val primaryColor = MaterialTheme.colorScheme.primary
    LaunchedEffect(showCursor, drawCursor) {
        if (showCursor) {
            launch {
                delay(500)
                drawCursor = !drawCursor
            }
        }
    }

    Box(
        modifier = Modifier
            .size(48.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .background(coolGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value?.toString() ?: "",
            modifier = Modifier.run {
                if (showCursor) {
                    drawBehind {
                        if (drawCursor) {
                            drawRect(
                                color = primaryColor,
                                size = Size(2.dp.toPx(), size.height)
                            )
                        }
                    }
                } else this
            }
        )
    }
}

@Preview
@Composable
fun DigitBoxPreview() {
    DigitBox(
        value = null,
        showCursor = true
    )
}

@Preview
@Composable
private fun OtpScreenPreview() {
    EasyRideTheme {
        OtpScreen(
            uiState = OtpUiState(),
            onOtpChanged = {},
            onVerifyClick = {}
        )
    }
}