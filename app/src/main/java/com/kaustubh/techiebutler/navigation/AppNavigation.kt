package com.kaustubh.techiebutler.navigation

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.kaustubh.techiebutler.model.TypeCodeItem
import com.kaustubh.techiebutler.navigation.AppRoutes.detailsPage
import com.kaustubh.techiebutler.navigation.di.AppNavModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

object AppRoutes {
    const val landingPage = "landingPage"
    const val detailsPage = "detailsPage/{post}"
}

@HiltViewModel
class AppNavViewModel @Inject constructor(
    navigationService: AppNavModule.NavigationService
) : ViewModel() {
    val controller = navigationService.navController

    fun goBack() {
        controller.popBackStack()
    }

    fun navigateToScreenDetailsPage(post: TypeCodeItem) {
        controller.navigate(detailsPage.replace("{post}", Gson().toJson(post)))
    }
}
