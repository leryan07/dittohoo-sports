package com.areazeroseven.dittohoosports.di

import com.areazeroseven.dittohoosports.matchups.data.repository.MatchupsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.areazeroseven.dittohoosports.matchups.domain.repository.IMatchupsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun MatchupsRepository(
        matchupsRepository: MatchupsRepository
    ): IMatchupsRepository
}