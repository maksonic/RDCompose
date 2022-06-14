package ru.maksonic.rdcompose.domain.categories

import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.domain.base.BaseUseCase
import javax.inject.Inject

/**
 * @Author maksonic on 31.05.2022
 */
class FetchCategoryByIdUseCase @Inject constructor(
    private val repository: CategoriesRepository
) : BaseUseCase<Flow<Result<CategoryDomain>>, Long> {
    override suspend fun invoke(args: Long?) = repository.fetchCategoryById(args ?: 0)
}