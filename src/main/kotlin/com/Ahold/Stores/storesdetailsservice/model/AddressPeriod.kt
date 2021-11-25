package com.Ahold.Stores.storesdetailsservice.model

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.Mapping
import java.time.LocalDate
import java.time.LocalDateTime

data class AddressPeriod(
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var dateValidFrom: LocalDate,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var dateValidUntil: LocalDate?,
    var storeAddress: StoreAddress
)
