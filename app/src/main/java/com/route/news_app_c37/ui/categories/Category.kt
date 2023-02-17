package com.route.news_app_c37.ui.categories

import com.route.news_app_c37.R

data class Category(
    val id: String,
    val name: String,
    val imageId: Int,
    val backgroundColorId: Int
) {
    companion object {
        fun getCategoriesList(): List<Category> {
            return listOf(
                Category(
                    id = "sports",
                    name = "Sports",
                    imageId = R.drawable.sports,
                    backgroundColorId = R.color.red
                ),
                Category(
                    id = "entertainment",
                    name = "Entertainment",
                    imageId = R.drawable.entertainment,
                    backgroundColorId = R.color.blue
                ),
                Category(
                    id = "health",
                    name = "Health",
                    imageId = R.drawable.health,
                    backgroundColorId = R.color.pink
                ),
                Category(
                    id = "business",
                    name = "Business",
                    imageId = R.drawable.business,
                    backgroundColorId = R.color.orange
                ),
                Category(
                    id = "technology",
                    name = "Technology",
                    imageId = R.drawable.technology,
                    backgroundColorId = R.color.babyBlue
                ),
                Category(
                    id = "science",
                    name = "Science",
                    imageId = R.drawable.science,
                    backgroundColorId = R.color.yellow
                ),

                )
        }
    }
}