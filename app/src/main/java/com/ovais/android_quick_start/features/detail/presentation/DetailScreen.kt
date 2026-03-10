package com.ovais.android_quick_start.features.detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ovais.android_quick_start.R
import com.ovais.android_quick_start.utils.AvatarView
import com.ovais.android_quick_start.utils.NetworkImage

@Composable
fun DetailScreen(
    identifier: String,
    model: String,
    version: String,
    name: String = "Syed Ovais Akhtar"
) {
    Column(modifier = Modifier.fillMaxSize()) {

        NetworkImage(
            url = "https://timelinecovers.pro/facebook-cover/download/the-day-in-one-image-facebook-cover.jpg",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_background)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-40).dp),
            contentAlignment = Alignment.Center
        ) {
            AvatarView(
                imageUrl = "https://media.licdn.com/dms/image/v2/D4D03AQFyR1w0Cqgq1Q/profile-displayphoto-scale_200_200/B4DZe69YHDGYAY-/0/1751188344224?e=2147483647&v=beta&t=lZd82b48n5wWt931W6ezmsm8VNoS3lzt95MDBEnzSmY",
                name = name,
                size = 80.dp,
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                error = painterResource(R.drawable.ic_launcher_background)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                DetailRow("Name", name, valueTag = "NameText")

                Spacer(modifier = Modifier.height(12.dp))
                DetailRow("Model", model, valueTag = "ModelText")

                Spacer(modifier = Modifier.height(12.dp))
                DetailRow("Identifier", identifier, valueTag = "IdentifierText")

                Spacer(modifier = Modifier.height(12.dp))
                DetailRow("Android Version", version, valueTag = "VersionText")
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String, valueTag: String? = null) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = valueTag?.let { Modifier.testTag(it) } ?: Modifier
        )
    }
}