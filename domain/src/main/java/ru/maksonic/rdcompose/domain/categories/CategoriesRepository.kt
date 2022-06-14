package ru.maksonic.rdcompose.domain.categories

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 13.06.2022
 */
typealias Categories = Flow<Result<List<CategoryDomain>>>
typealias Category = Flow<Result<CategoryDomain>>

interface CategoriesRepository {
    fun fetchCategories(): Categories
    fun refreshCategories(): Categories
    suspend fun fetchCategoryById(id: Long): Category
}