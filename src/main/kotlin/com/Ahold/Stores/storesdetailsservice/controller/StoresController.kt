package com.Ahold.Stores.storesdetailsservice.controller

import com.Ahold.Stores.storesdetailsservice.Exceptions.NoDataCreatedException
import com.Ahold.Stores.storesdetailsservice.Exceptions.NoDataFoundException
import com.Ahold.Stores.storesdetailsservice.model.Stores
import com.Ahold.Stores.storesdetailsservice.repository.StoresRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/store-service/v1/")
class StoresController (private val storesRepo: StoresRepository) {

    @GetMapping("/stores")
    fun getAllStores(): ResponseEntity<Any> {
        var tmp: MutableList<Stores> = storesRepo.findAll()
        if(!tmp.isEmpty())
        {
            return ResponseEntity.ok(object {
                var status: String = "Success"
                var total: Int = tmp.size
                var data: List<Stores> = tmp
            })
        }
        else throw NoDataFoundException("DataBase Empty")
    }

    @GetMapping("/stores/{storeId}")
    fun getStore(@PathVariable storeId: String): ResponseEntity<Stores> {
        var tmp: Optional<Stores> = storesRepo.findById(storeId)
        if(tmp.isPresent) return ResponseEntity.ok(tmp?.get())
        else throw NoDataFoundException("id-" + storeId)
    }
    @PostMapping("/persiststore")
    fun saveStore(@RequestBody store: Stores ): ResponseEntity<Any> {

        if(storesRepo.findById((store.id)).isPresent) {
            return throw NoDataCreatedException("Data Already Exists")
        }
        else {
            storesRepo.save(store)
            var tmp: Optional<Stores> = storesRepo.findById(store.id)
            if (tmp.isPresent) return ResponseEntity.ok( object {
                var status: String ="Data Created"
                var id: String = tmp?.get().id
            })
            else throw NoDataCreatedException("No Data Created ")
        }
    }
}