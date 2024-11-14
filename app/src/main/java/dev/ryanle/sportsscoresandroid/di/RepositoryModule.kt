package dev.ryanle.sportsscoresandroid.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ryanle.sportsscoresandroid.feature_scores.data.IScoresRepository
import dev.ryanle.sportsscoresandroid.feature_scores.data.ScoresRepository
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