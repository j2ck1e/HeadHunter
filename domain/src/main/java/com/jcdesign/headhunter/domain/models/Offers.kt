package com.jcdesign.headhunter.domain.models

data class Offers(
    val offers: List<Offer>
)

data class Offer(
    val id: String = "",
    val title: String = "",
    val textButton: String = "",
    val link: String = ""
)
