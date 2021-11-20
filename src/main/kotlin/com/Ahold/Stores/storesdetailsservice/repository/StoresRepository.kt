package com.Ahold.Stores.storesdetailsservice.repository

import com.Ahold.Stores.storesdetailsservice.model.Stores
import org.springframework.data.mongodb.repository.MongoRepository

interface StoresRepository: MongoRepository<Stores, String> {
}