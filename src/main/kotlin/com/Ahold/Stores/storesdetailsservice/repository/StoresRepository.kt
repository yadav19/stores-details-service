package com.Ahold.Stores.storesdetailsservice.repository

import com.Ahold.Stores.storesdetailsservice.model.Stores
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StoresRepository: MongoRepository<Stores, Int> {
}