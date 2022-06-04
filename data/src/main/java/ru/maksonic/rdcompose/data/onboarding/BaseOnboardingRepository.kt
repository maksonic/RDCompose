package ru.maksonic.rdcompose.data.onboarding

import ru.maksonic.rdcompose.data.R
import ru.maksonic.rdcompose.domain.onboarding.Onboarding
import ru.maksonic.rdcompose.domain.onboarding.OnboardingRepository
import javax.inject.Inject

/**
 * @Author maksonic on 23.05.2022
 */
class BaseOnboardingRepository @Inject constructor() : OnboardingRepository {
    override fun fetchOnboardingList(): List<Onboarding> = listOf(
        Onboarding(
            "Добро пожаловать!",
            "Заряжайся, совершенствуйся и вдохновляйся.\nБолее 100 аудиоподкастов от Игоря Войтенко.",
            R.drawable.slide_first
        ),
        Onboarding(
            "Слушай в любое время",
            "Полный доступ к аудиоподкастам\nбез интернета 24/7.",
            R.drawable.slide_second
        ),
        Onboarding(
            "Минималистичный дизайн",
            "Добавляй в избранное, ставь лайки\nи делись подкастами с кем хочешь!\nТолько самые необходимые функции.",
            R.drawable.slide_third
        ),
        Onboarding(
            "Road to the Dream\nПодкасты",
            "Создано для людей\nкоторые не боятся мечтать\nи хотят сделать наш Мир лучше!",
            R.drawable.slide_fourth
        )
    )
}
