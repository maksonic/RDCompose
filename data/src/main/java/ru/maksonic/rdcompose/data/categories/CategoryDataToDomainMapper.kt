package ru.maksonic.rdcompose.data.categories

import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.domain.categories.CategoryDomain
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
class CategoryDataToDomainMapper @Inject constructor() : Mapper<CategoryData, CategoryDomain> {
    override fun mapTo(o: CategoryDomain) = CategoryData(o.id, o.name, o.description, o.image)
    override fun mapFrom(i: CategoryData) = CategoryDomain(i.id, i.name, i.description, i.image)
}