package ru.maksonic.rdcompose.data.categories.cache

import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.data.categories.CategoryData
import javax.inject.Inject

/**
 * @Author maksonic on 27.05.2022
 */
class CategoryCacheToDataMapper @Inject constructor() : Mapper<CategoryCache, CategoryData> {

    override fun mapTo(o: CategoryData): CategoryCache =
        CategoryCache(o.id, o.categoryId, o.name, o.description, o.image)

    override fun mapFrom(i: CategoryCache): CategoryData =
        CategoryData(i.id, i.categoryId, i.name, i.description, i.image)
}