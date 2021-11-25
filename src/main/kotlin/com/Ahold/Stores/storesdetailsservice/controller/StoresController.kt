package com.Ahold.Stores.storesdetailsservice.controller

import com.Ahold.Stores.storesdetailsservice.Exceptions.NoDataCreatedException
import com.Ahold.Stores.storesdetailsservice.Exceptions.NoDataFoundException
import com.Ahold.Stores.storesdetailsservice.Service.StoreService
import com.Ahold.Stores.storesdetailsservice.model.Stores
import com.Ahold.Stores.storesdetailsservice.repository.StoresRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.data.convert.Jsr310Converters
import org.springframework.format.annotation.DateTimeFormat
//import com.Ahold.Stores.storesdetailsservice.repository.StoresRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*


@RestController
@RequestMapping("/store-service/v1/")
open class StoresController (private val storeServe: StoreService,private val storesRepo: StoresRepository) {

    companion object {
        var log: Logger = LoggerFactory.getLogger(StoresController::class.java)
    }

    @PostMapping("/addStore")
    fun saveStore(@RequestBody store: Stores): String {
        log.trace("controller.save-store")
        log.info("controller.save-store")
        print(store)
        storeServe.saveStore(store)
        return "Saved"
    }

    @GetMapping("/stores")
    fun getAllStores(
        @RequestParam(name = "refDate", required = false) refdate: LocalDate? = null,
        @RequestParam(name = "futureFlag", required = false) ff: String? = "F"
    ): List<Stores> {

        var ret: List<Stores> = storeServe.getAllStores()
        if(refdate!=null) {
            if (ff == "F" || ff == "f") {
                ret.forEach { it1 ->
                    it1.addressPeriod =
                        it1.addressPeriod.filter { it2 -> it2.dateValidUntil != null && it2.dateValidUntil!! <= refdate }
                }
            } else if (ff == "t" || ff == "T") {
                ret.forEach { it1 ->
                    it1.addressPeriod =
                        it1.addressPeriod.filter { it2 -> it2.dateValidUntil == null || it2.dateValidUntil!! >= refdate }
                }
            }
        }
        return ret.filter{it.addressPeriod.size>0}
    }

    @GetMapping("/stores/{storeId}")
    fun getStore(
        @PathVariable storeId: Int
    ): ResponseEntity<Stores> {

        return storeServe.getStoreById(storeId)

    }


}
