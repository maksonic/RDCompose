package ru.maksonic.rdcompose.domain.categories.usecase

import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.domain.base.BaseUseCase
import ru.maksonic.rdcompose.domain.categories.CategoriesRepository
import ru.maksonic.rdcompose.domain.categories.Category
import ru.maksonic.rdcompose.domain.categories.CategoryDomain
import javax.inject.Inject

/**
 * @Author maksonic on 31.05.2022
 */
class FetchCategoryByIdUseCase @Inject constructor(
    private val repository: CategoriesRepository
) : BaseUseCase<Category, Long?> {
    override suspend fun invoke(args: Long?) = repository.fetchItemById(args ?: 0)
}
