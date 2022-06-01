package ru.maksonic.rdcompose.domain.categories

import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.domain.base.BaseUseCase
import ru.maksonic.rdcompose.domain.base.CommonRepository
import javax.inject.Inject

/**
 * @Author maksonic on 01.06.2022
 */
class FetchCategoryByIdUseCase @Inject constructor(
    private val repository: CommonRepository<CategoryDomain>
) : BaseUseCase<Flow<Result<CategoryDomain>>, String> {

    override fun invoke(args: String?) = repository.fetchItemById(args ?: "")
}
