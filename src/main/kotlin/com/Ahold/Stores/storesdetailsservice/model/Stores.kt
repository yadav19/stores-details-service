package com.Ahold.Stores.storesdetailsservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime

@Document(collection = "StoreAddressDetails")
data class Stores(
    @Id
    var id: Int,
    var name: String,
    var status: String,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var createdAt: LocalDate,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var updatedAt: LocalDate,
    var addressPeriod: List<AddressPeriod>)

