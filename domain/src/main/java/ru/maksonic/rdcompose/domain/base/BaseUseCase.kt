package ru.maksonic.rdcompose.domain.base

/**
 * @Author maksonic on 26.05.2022
 */
interface BaseUseCase<T, D> {
    operator fun invoke(args: D? = null): T
}