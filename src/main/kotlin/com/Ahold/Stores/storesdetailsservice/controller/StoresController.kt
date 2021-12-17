package com.Ahold.Stores.storesdetailsservice.controller

import com.Ahold.Stores.storesdetailsservice.Configuration.*
import com.Ahold.Stores.storesdetailsservice.Service.StoreService
import com.Ahold.Stores.storesdetailsservice.model.Stores
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@RestController
open class StoresController (private val storeServe: StoreService) {

    companion object {
        var log: Logger = LoggerFactory.getLogger(StoresController::class.java)
    }

    @PostMapping(ADD_STORES)
    fun saveStore(@RequestBody store: Stores): String {
        log.trace("controller.save-store")
        log.info("controller.save-store")
        print(store)
        storeServe.saveStore(store)
        return "Saved"
    }

    @GetMapping(ALL_STORES)
    fun getAllStores(
        @RequestParam(name = "refDate", required = false) refdate: LocalDate?,
        @RequestParam(name = "futureFlag", required = false) ff: Boolean?
    ): List<Stores> {
        return storeServe.getAllStores(refdate ?: LocalDate.now(),ff ?: false)
    }

    @GetMapping(STORE_BY_ID)
    fun getStore(
        @PathVariable(name = "id") storeId: Int
    ): ResponseEntity<Stores> {
        return storeServe.getStoreById(storeId)
    }

    @DeleteMapping(DELETE_STORE_BY_ID)
    fun deleteStore(
        @PathVariable(name="id") storeId: Int
    ): ResponseEntity<Any> {
        return storeServe.deleteStoreById(storeId)
    }

    @PutMapping(UPDATE_STORE_BY_ID)
    fun updateStore( @RequestBody store: Stores ): ResponseEntity<Stores>{
        log.trace("endpoint update")
        return storeServe.updateStoreById(store)
    }

}
