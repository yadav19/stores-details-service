package com.Ahold.Stores.storesdetailsservice.Configuration

const val BASE_URL = "/store-service/v1"
const val ALL_STORES = "${BASE_URL}/stores"
const val STORE_BY_ID = "${ALL_STORES}/{id}"
const val ADD_STORES = "${BASE_URL}/addStore"
const val DELETE_STORE_BY_ID = "${BASE_URL}/delete/{id}"
const val UPDATE_STORE_BY_ID = "${BASE_URL}/updateStore"