package com.jcdesign.headhunter.data.network.models


import com.google.gson.annotations.SerializedName
import com.jcdesign.headhunter.domain.models.Offer

data class ResponseDto(
    @SerializedName("vacancies") val vacancies: ArrayList<VacancyDto>,
    @SerializedName("offers") val offers: List<OfferDto>
)

data class OfferDto(
    @SerializedName("id") val offerId: String = "",
    @SerializedName("title") val offerTitle: String = "",
    @SerializedName("button") val offerButton: ButtonDto = ButtonDto(),
    @SerializedName("link") val offerLink: String = ""
)

data class ButtonDto(
    @SerializedName("text") val text: String = ""
)

data class VacancyDto(
    @SerializedName("address")
    val addressDto: AddressDto = AddressDto(),
    @SerializedName("appliedNumber")
    val appliedNumber: Int = 0,
    @SerializedName("company")
    val company: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("experience")
    val experienceDto: ExperienceDto = ExperienceDto(),
    @SerializedName("id")
    val id: String = "",
    @SerializedName("isFavorite")
    val isFavorite: Boolean = false,
    @SerializedName("lookingNumber")
    val lookingNumber: Int = 0,
    @SerializedName("publishedDate")
    val publishedDate: String = "",
    @SerializedName("questions")
    val questions: List<String> = listOf(),
    @SerializedName("responsibilities")
    val responsibilities: String = "",
    @SerializedName("salary")
    val salaryDto: SalaryDto = SalaryDto(),
    @SerializedName("schedules")
    val schedules: List<String> = listOf(),
    @SerializedName("title")
    val title: String = ""
)

data class AddressDto(
    @SerializedName("house")
    val house: String = "",
    @SerializedName("street")
    val street: String = "",
    @SerializedName("town")
    val town: String = ""
)

data class ExperienceDto(
    @SerializedName("previewText")
    val previewText: String = "",
    @SerializedName("text")
    val text: String = ""
)

data class SalaryDto(
    @SerializedName("full")
    val full: String = ""
)
