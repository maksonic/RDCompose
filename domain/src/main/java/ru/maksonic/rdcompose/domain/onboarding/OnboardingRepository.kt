package ru.maksonic.rdcompose.domain.onboarding

/**
 * @Author maksonic on 23.05.2022
 */
interface OnboardingRepository {
    fun fetchOnboardingList(): List<Onboarding>
}