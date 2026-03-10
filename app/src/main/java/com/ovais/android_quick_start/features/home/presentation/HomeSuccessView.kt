package com.ovais.android_quick_start.features.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun HomeSuccessView(
    model: String,
    identifier: String,
    androidVersion: String,
    onNextClick: (String, String, String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("HomeSuccessView"),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Device Information",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                InfoRow("📱 Model", model, valueTag = "ModelText")
                Spacer(modifier = Modifier.height(12.dp))

                InfoRow("🆔 Identifier", identifier, valueTag = "IdentifierText")

                Spacer(modifier = Modifier.height(12.dp))

                InfoRow("🤖 Android Version", androidVersion, valueTag = "VersionText")
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        onNextClick(model, identifier, androidVersion)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("ContinueButton")
                ) {
                    Text("Continue")
                }
            }
        }
    }
}
@Composable
fun InfoRow(label: String, value: String, valueTag: String? = null) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = valueTag?.let { Modifier.testTag(it) } ?: Modifier
        )
    }
}