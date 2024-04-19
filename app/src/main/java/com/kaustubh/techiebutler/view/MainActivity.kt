package com.kaustubh.techiebutler.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.kaustubh.techiebutler.model.TypeCodeItem
import com.kaustubh.techiebutler.navigation.AppNavViewModel
import com.kaustubh.techiebutler.navigation.AppRoutes
import com.kaustubh.techiebutler.view.screens.details.DetailedPostScreen
import com.kaustubh.techiebutler.view.screens.launch.LaunchScreen
import com.kaustubh.techiebutler.view.theme.TechieButlerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechieButlerTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TechieButlerAppScreens()
                }
            }
        }
    }
}

@Composable
fun TechieButlerAppScreens() {
    val navStack = hiltViewModel<AppNavViewModel>()
    val navController = navStack.controller
    NavHost(
        navController = navController,
        startDestination = AppRoutes.landingPage
    ) {
        composable(AppRoutes.landingPage) {
            LaunchScreen()
        }

        composable(AppRoutes.detailsPage) {
            val task = it.arguments?.getString("post")
            if (task.isNullOrEmpty()) {
                DetailedPostScreen(TypeCodeItem())
            } else {
                DetailedPostScreen(Gson().fromJson(task, TypeCodeItem::class.java))
            }
        }
    }
}

