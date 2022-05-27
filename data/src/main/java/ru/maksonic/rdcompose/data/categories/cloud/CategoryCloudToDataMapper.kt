package ru.maksonic.rdcompose.data.categories.cloud

import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.data.categories.CategoryData
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
class CategoryCloudToDataMapper @Inject constructor() : Mapper<CategoryCloud, CategoryData> {
    override fun mapTo(o: CategoryData) = CategoryCloud(o.id, o.name, o.description, o.image)
    override fun mapFrom(i: CategoryCloud) = CategoryData(i.id!!, i.name!!, i.description!!, i.image)
}