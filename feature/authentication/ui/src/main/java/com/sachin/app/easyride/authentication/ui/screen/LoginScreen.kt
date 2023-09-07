package com.sachin.app.easyride.authentication.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sachin.app.easyride.authentication.ui.navigation.navigateToOtp
import com.sachin.app.easyride.core.ui.R
import com.sachin.app.easyride.core.ui.theme.EasyRideTheme
import com.sachin.app.easyride.core.ui.theme.coolGray

@Composable
fun LoginRoute(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    LoginScreen(
        uiState = uiState,
        onPhoneNumberChanged = viewModel::onPhoneNumberChanged,
        onLoginClick = {
            viewModel.login(
                onNavigateToOtp = {
                    navController.navigateToOtp(uiState.phoneNumber)
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginScreen(
    onLoginClick: () -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
    uiState: LoginUiState
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.cab),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                alignment = Alignment.BottomCenter
            )

            Spacer(modifier = Modifier.weight(0.1f))

            Text(
                text = "Ready to ride?",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Text(
                text = "Enter your phone number to start your seamless journey with us!",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            ErTextField(
                value = uiState.phoneNumber,
                onValueChange = onPhoneNumberChanged,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                placeholder = { Text(text = "Phone number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isError = !uiState.phoneNumberError.isNullOrBlank(),
                supportingText = { uiState.phoneNumberError?.let { Text(text = it) } }
            )

            Button(
                onClick = onLoginClick,
                content = { Text(text = "Continue") },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(0.3f))
        }
    }


}

@Composable
fun ErTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    isError: Boolean = false,
    placeholder: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    val lightOnSurfaceColor =
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    val containerColor = coolGray

    val colors = OutlinedTextFieldDefaults.colors(
        unfocusedPlaceholderColor = lightOnSurfaceColor,
        unfocusedBorderColor = Color.Transparent,
        focusedBorderColor = Color.Transparent,
        disabledBorderColor = Color.Transparent,
        errorBorderColor = Color.Transparent,
        focusedContainerColor = containerColor,
        unfocusedContainerColor = containerColor,
        errorContainerColor = containerColor,
        disabledContainerColor = containerColor
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = placeholder,
        colors = colors,
        shape = shape,
        readOnly = readOnly,
        singleLine = singleLine,
        enabled = enabled,
        isError = isError,
        keyboardOptions = keyboardOptions,
        supportingText = supportingText,
        prefix = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.flag_of_india),
                    contentDescription = null,
                    modifier = Modifier
                        .height(16.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "+91 ")
                Spacer(modifier = Modifier.width(2.dp))
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(20.dp)
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
        }

    )
}

@Preview
@Composable
private fun LoginScreenPreview() {
    EasyRideTheme {
        LoginScreen(
            onLoginClick = {},
            onPhoneNumberChanged = {},
            uiState = LoginUiState()
        )
    }
}