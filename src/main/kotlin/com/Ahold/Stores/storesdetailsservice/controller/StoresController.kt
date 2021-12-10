package com.Ahold.Stores.storesdetailsservice.controller

//import com.Ahold.Stores.storesdetailsservice.Configuration.*
//import com.Ahold.Stores.storesdetailsservice.Exceptions.NoDataCreatedException
//import com.Ahold.Stores.storesdetailsservice.Exceptions.NoDataFoundException
import com.Ahold.Stores.storesdetailsservice.Configuration.ALL_STORES
import com.Ahold.Stores.storesdetailsservice.Configuration.BASE_URL
import com.Ahold.Stores.storesdetailsservice.Configuration.STORE_BY_ID
import com.Ahold.Stores.storesdetailsservice.Service.StoreService
import com.Ahold.Stores.storesdetailsservice.model.Stores
//import com.Ahold.Stores.storesdetailsservice.repository.StoresRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
//import org.springframework.boot.web.servlet.error.ErrorController
//import org.springframework.data.convert.Jsr310Converters
//import org.springframework.format.annotation.DateTimeFormat
//import com.Ahold.Stores.storesdetailsservice.repository.StoresRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
//import java.util.*
//import com.Ahold.Stores.storesdetailsservice.Configuration.


@RestController
open class StoresController (private val storeServe: StoreService) {

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

}
