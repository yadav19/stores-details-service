package com.Ahold.Stores.storesdetailsservice.model

data class AddressPeriod(
    var dateValidFrom: String,
    var dateValidUntil: String?,
    var storeAddress: StoreAddress
)
