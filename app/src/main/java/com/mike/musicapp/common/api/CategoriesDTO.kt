package com.mike.musicapp.common.api

data class CategoriesDTO(
    val categories: CategoryDTO
)

data class CategoryDTO(
    val href: String,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int,
    val items: List<CategoryItemDTO>
)

data class CategoryItemDTO(
    val href: String,
    val icons: List<IconsDTO>,
    val id: String,
    val name: String,
)

data class IconsDTO(
    val height: Int,
    val url: String,
    val width: Int
)