package com.kaustubh.techiebutler.navigation

import androidx.navigation.NavController

object AppRoutes {
    const val landingPage = "landingPage"
    const val detailsPage = "detailsPage/{post}"
}

fun NavController.navigateBack() {
    this.popBackStack()
}
