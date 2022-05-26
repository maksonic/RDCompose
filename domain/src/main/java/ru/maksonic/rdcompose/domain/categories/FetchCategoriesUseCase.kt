package ru.maksonic.rdcompose.domain.categories

import ru.maksonic.rdcompose.domain.base.BaseUseCase
import ru.maksonic.rdcompose.domain.base.Categories
import ru.maksonic.rdcompose.domain.base.Repository
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
class FetchCategoriesUseCase @Inject constructor(
    private val repository: Repository<CategoryDomain>
) : BaseUseCase<Categories, Nothing> {
    override fun invoke(args: Nothing?) = repository.fetchCloudDataList()
}