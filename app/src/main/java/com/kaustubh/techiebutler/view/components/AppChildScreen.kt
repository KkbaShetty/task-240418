package com.kaustubh.techiebutler.view.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.kaustubh.techiebutler.navigation.navigateBack

@Composable
fun AppChildScreen(
    screenTitle: String,
    navController: NavHostController,
    innerScreen: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBarWithBack(screenTitle) {
                navController.navigateBack()
            }
        },
    ) { innerPadding ->
        innerScreen(innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarWithBack(
    screenTitle: String,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        ),
        navigationIcon = {
            BackIconButton {
                onBackClicked()
            }
        },
        title = { Text(text = screenTitle) },
    )
}

@Composable
fun BackIconButton(onBackClicked: () -> Unit) {
    IconButton(onClick = {
        onBackClicked()
    }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back Icon"
        )
    }
}