package com.Ahold.Stores.storesdetailsservice.Service

import com.Ahold.Stores.storesdetailsservice.Exceptions.NoDataCreatedException
import com.Ahold.Stores.storesdetailsservice.Exceptions.NoDataFoundException
import com.Ahold.Stores.storesdetailsservice.model.Stores
import com.Ahold.Stores.storesdetailsservice.repository.StoresRepository
//import org.apache.juli.logging.LogFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import java.util.*

import org.springframework.stereotype.Service

@Service
open class StoreService(private val storesRepo: StoresRepository) {

    companion object {
        var log: Logger = LoggerFactory.getLogger(StoreService::class.java)
    }

    fun getAllStores(): List<Stores>{

        var tmp: List<Stores> = storesRepo.findAll()
        if(!tmp.isEmpty())
            return tmp
        else
            throw NoDataFoundException("DataBase Empty")
    }

    fun getStoreById( storeId: Int): ResponseEntity<Stores>{
        var tmp: Optional<Stores> = storesRepo.findById(storeId)

        if(tmp.isPresent)
            return ResponseEntity.ok(tmp?.get())
        else
            throw NoDataFoundException("id-" + storeId)
    }

    fun saveStore( store: Stores): Any {

        println("came here")
        log.trace("service.saveStore")
        log.info("service.saveStore")
        if(storesRepo.findById((store.id)).isPresent) {
            return throw NoDataCreatedException("Data Already Exists")
        }
        else {
            storesRepo.save(store)
            log.trace("service.saveStore.save")
            log.info("service.saveStore.save")
            var tmp: Optional<Stores> = storesRepo.findById(store.id)
            if (tmp.isPresent) return ResponseEntity.ok( object {
                var status: String ="Data Created"
                var id: Int = tmp?.get().id
            })
            else throw NoDataCreatedException("No Data Created ")
        }
    }
}