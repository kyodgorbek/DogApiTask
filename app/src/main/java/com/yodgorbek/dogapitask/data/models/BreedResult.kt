package com.yodgorbek.dogapitask.data.models

import com.yodgorbek.dogapitask.doglist.domain.model.Breed

data class BreedResult(
    val bred_for: String?,
    val breed_group: String?,
    val country_code: String?,
    val description: String?,
    val height: Height?,
    val history: String?,
    val id: String?,
    val image: Image?,
    val life_span: String?,
    val name: String?,
    val origin: String?,
    val reference_image_id: String?,
    val temperament: String?,
    val weight: Weight?
) {
    data class Height(
        val imperial: String?,
        val metric: String?
    )

    data class Image(
        val height: Int?,
        val id: String?,
        val url: String?,
        val width: Int?
    )

    data class Weight(
        val imperial: String?,
        val metric: String?
    )
}

fun BreedResult.mapToDomain() =
    Breed(this.id!!, this.name!!)
