package com.sachin.app.easyride.countrycodepicker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sachin.app.easyride.countrycodepicker.model.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodeSheet(
    onDismissRequest: () -> Unit,
) {

    ModalBottomSheet(onDismissRequest = onDismissRequest) {
        LazyColumn(content = )
    }
}

@Composable
fun CountryCodeItem(country: Country) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val resId = LocalContext.current.resources.getIdentifier(
            country.name.lowercase(),
            "drawable",
            "com.sachin.app.easyride"
        )
        Image(painter = painterResource(id = resId), contentDescription = null)
        Text(text = country.name)
    }
}

@Preview(showBackground = true)
@Composable
fun CountryCodeItemPreview() {
    CountryCodeItem(country = Country("India", "+91", "IN"))
}