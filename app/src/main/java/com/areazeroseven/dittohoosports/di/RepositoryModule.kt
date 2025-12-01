package com.areazeroseven.dittohoosports.di

import com.areazeroseven.dittohoosports.feature_scores.data.repository.ScoresRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.areazeroseven.dittohoosports.feature_scores.domain.repository.IScoresRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun ScoresRepository(
        scoresRepository: ScoresRepository
    ): IScoresRepository
}