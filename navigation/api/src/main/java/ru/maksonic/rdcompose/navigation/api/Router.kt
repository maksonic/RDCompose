package ru.maksonic.rdcompose.navigation.api

/**
 * @Author maksonic on 23.05.2022
 */
interface Router {
    interface Global {
        fun onboardingToMain()
        fun mainToSettings()
        fun mainToUserProfile()
        fun showPrivacy(privacyId: String)
    }

    interface Main {
        fun categoriesToCategoryPodcasts(categoryId: Long)
    }
}