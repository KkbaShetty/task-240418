package com.kaustubh.techiebutler.view.screens.launch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.kaustubh.techiebutler.navigation.AppRoutes
import com.kaustubh.techiebutler.view.components.AppTopBanner
import com.kaustubh.techiebutler.view.screens.posts.PostsListScreen

@Composable
fun LaunchScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTopBanner()
        PostsListScreen {
            navController.navigate(AppRoutes.detailsPage.replace("{post}", Gson().toJson(it)))
        }
    }
}
