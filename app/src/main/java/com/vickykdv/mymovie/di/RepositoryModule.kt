package com.vickykdv.mymovie.di

import androidx.paging.PagedList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import com.vickykdv.mymovie.data.database.RoomDb
import com.vickykdv.mymovie.data.factory.Factory
import com.vickykdv.mymovie.data.network.ApiService
import com.vickykdv.mymovie.data.repository.DataRepository
import com.vickykdv.mymovie.data.repository.local.LocalRepository
import com.vickykdv.mymovie.data.repository.remote.RemoteRepository
import javax.inject.Singleton



@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRemoteRepository(
        apiService: ApiService,
        config : PagedList.Config,
        factory : Factory
    ) : RemoteRepository = RemoteRepository(
            apiService,
            config,
            factory
        )

    @Singleton
    @Provides
    fun provideLocalRepository(
        database : RoomDb,
        config : PagedList.Config
    ) : LocalRepository = LocalRepository(database, config)


    @Singleton
    @Provides
    fun provideDataRepository(
        remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ) : DataRepository = DataRepository(
        remoteRepository,
        localRepository
    )
}