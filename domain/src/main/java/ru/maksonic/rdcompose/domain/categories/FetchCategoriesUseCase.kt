package ru.maksonic.rdcompose.domain.categories

import ru.maksonic.rdcompose.domain.base.*
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
class FetchCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) : BaseUseCase<Categories, Nothing> {
    override suspend fun invoke(args: Nothing?) = repository.fetchCategories()
}