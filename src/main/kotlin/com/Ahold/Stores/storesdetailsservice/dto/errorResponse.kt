package com.Ahold.Stores.storesdetailsservice.dto

import java.time.LocalDate


data class errorResponse(
    var timeStamp: LocalDate,
    var responseCode: String,
    var details: String
)
