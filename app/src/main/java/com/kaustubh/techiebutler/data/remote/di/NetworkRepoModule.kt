package com.kaustubh.techiebutler.data.remote.di

import com.kaustubh.techiebutler.data.remote.repo.DataServicesImpl
import com.kaustubh.techiebutler.domain.repo.DataServices
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkRepoModule {

    @Binds
    @Singleton
    abstract fun bindsRemoteDataServices(dataServices: DataServicesImpl): DataServices
}