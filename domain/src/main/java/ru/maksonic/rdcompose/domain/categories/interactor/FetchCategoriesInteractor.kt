package ru.maksonic.rdcompose.domain.categories.interactor

import ru.maksonic.rdcompose.domain.base.BaseInteractor
import ru.maksonic.rdcompose.domain.categories.Categories
import ru.maksonic.rdcompose.domain.categories.CategoriesRepository
import javax.inject.Inject

/**
 * @Author maksonic on 21.06.2022
 */
class FetchCategoriesInteractor @Inject constructor(
    private val repository: CategoriesRepository
) : BaseInteractor<Categories, Nothing> {
    override suspend fun fetchData(id: Nothing?): Categories = repository.fetchData()
    override suspend fun refreshData(id: Nothing?): Categories = repository.refreshData()
}
