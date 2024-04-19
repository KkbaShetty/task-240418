package com.kaustubh.techiebutler.navigation.di

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.kaustubh.techiebutler.navigation.AppNavViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppNavModule {

    @Singleton
    class NavigationService @Inject constructor(
        @ApplicationContext context: Context
    ) {
        val navController = NavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
            navigatorProvider.addNavigator(DialogNavigator())
        }
    }

    @Provides
    @Singleton
    fun provideAppNavViewModel(
        navigationService: NavigationService
    ): AppNavViewModel {
        return AppNavViewModel(navigationService)
    }

}