package com.Ahold.Stores.storesdetailsservice.model


data class StoreAddress(
    var street: String,
    var houseNumber: String,
    var houseNumberSuffix: String,
    var postalCode: String,
    var city: String,
    var country: String
)
