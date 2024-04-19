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
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaustubh.techiebutler.navigation.AppNavViewModel

@Composable
fun AppChildScreen(
    screenTitle: String,
    innerScreen: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBarWithBack(screenTitle)
        },
    ) { innerPadding ->
        innerScreen(innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarWithBack(screenTitle: String) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        ),
        navigationIcon = { BackIconButton() },
        title = { Text(text = screenTitle) },
    )
}

@Composable
fun BackIconButton(
    appNavViewModel: AppNavViewModel = hiltViewModel()
) {
    IconButton(onClick = {
        appNavViewModel.goBack()
    }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back Icon"
        )
    }
}