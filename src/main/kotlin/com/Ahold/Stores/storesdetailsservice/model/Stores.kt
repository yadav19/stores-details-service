package com.Ahold.Stores.storesdetailsservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "StoreAddressDetails")
data class Stores(
var name: String,
var status: String,
var lastUpdated: String,
var addressPeriod: List<AddressPeriod>){
@Id
var id: String = ""
}

