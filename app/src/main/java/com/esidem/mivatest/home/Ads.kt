package com.esidem.mivatest.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import com.esidem.mivatest.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Ads() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .semantics { testTagsAsResourceId = true }.testTag("Ads"),
        painter = painterResource(id = R.drawable.ad),
        contentDescription = "Ad"
    )
}