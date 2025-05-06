package com.mike.musicapp.common.modules

data class CategoriesDTO(
    val categories: CategoryDTO
)

data class CategoryDTO(
    val href: String,
    val total: Int,
    val items: List<CategoryItemDTO>
)

data class CategoryItemDTO(
    val href: String,
    val icons: List<ImageDTO>,
    val id: String,
    val name: String,
    )