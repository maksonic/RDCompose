package ru.maksonic.rdcompose.feature.onboarding

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.domain.onboarding.OnboardingRepository
import ru.maksonic.rdcompose.feature.onboarding.program.OnboardingProgram
import ru.maksonic.rdcompose.navigation.api.GlobalNavigator
import javax.inject.Singleton

/**
 * @Author maksonic on 23.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object OnboardingFeatureModule {

    @Singleton
    @Provides
    fun provideOnboardingProgram(
        repository: OnboardingRepository,
        navigator: GlobalNavigator
    ): OnboardingProgram = OnboardingProgram(repository, navigator)

}