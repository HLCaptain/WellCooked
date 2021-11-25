package hu.wellcooked.model

import java.util.*

data class Order(
    val id: String,
    val customer: Customer,
    val site: Site,
    val orderDate: Date,
    val completionDate: Date?,
    val status: OrderStatus
)