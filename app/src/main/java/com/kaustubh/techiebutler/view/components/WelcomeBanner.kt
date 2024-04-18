package com.kaustubh.techiebutler.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kaustubh.techiebutler.R
import com.kaustubh.techiebutler.view.theme.ButlerBlue

@Composable
fun AppWelcomeBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ButlerBlue)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(vertical = 16.dp),
            painter = painterResource(id = R.drawable.app_branding),
            contentDescription = "App Logo",
            contentScale = ContentScale.FillWidth
        )
    }
}
