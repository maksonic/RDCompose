package ru.maksonic.rdcompose.shared.ui_model.category

import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.domain.categories.CategoryDomain
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
class CategoryDomainToUiMapper @Inject constructor() : Mapper<CategoryDomain, CategoryUi> {
    override fun mapTo(o: CategoryUi) = CategoryDomain(o.id, o.name, o.description, o.image)
    override fun mapFrom(i: CategoryDomain) = CategoryUi(i.id, i.name, i.description, i.image)
}