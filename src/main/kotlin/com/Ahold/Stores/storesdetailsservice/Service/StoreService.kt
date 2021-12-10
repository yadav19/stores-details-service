package com.Ahold.Stores.storesdetailsservice.Service

import com.Ahold.Stores.storesdetailsservice.Exceptions.BadRequestException
import com.Ahold.Stores.storesdetailsservice.Exceptions.NoDataCreatedException
import com.Ahold.Stores.storesdetailsservice.Exceptions.NoDataFoundException
import com.Ahold.Stores.storesdetailsservice.model.Stores
import com.Ahold.Stores.storesdetailsservice.repository.StoresRepository
//import org.apache.juli.logging.LogFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import java.time.LocalDate

@Service
class StoreService(private val storesRepo: StoresRepository) {

    companion object {
        var log: Logger = LoggerFactory.getLogger(StoreService::class.java)
    }

    fun getAllStores(refdate: LocalDate?,ff: Boolean): List<Stores>{

        var ret: List<Stores> = storesRepo.findAll().ifEmpty { throw NoDataFoundException("no data found") }

        // refDate is optional, if null: then return all data

        // If futureFlag = false then, Return all the stores dateValidFrom <= refdate <dateValidUntil ,i.e, Present Active stores
        // if futureFlag = true then, Return all the store  refdate < dateValidUntil , i.e., Future Stores to be active

//        var query: Query = Query()
//        query.addCriteria(Criteria.where("addressPeriod.dateValidUntil"))


        if (ff) {
            ret.forEach { it1 ->
                it1.addressPeriod =
                    it1.addressPeriod.filter { it2 ->  it2.dateValidUntil?.isAfter(refdate)?: true}
            }
        } else {
            ret.forEach { it1 ->
                it1.addressPeriod =
                    it1.addressPeriod.filter { it2 -> it2.dateValidFrom <= refdate && (it2.dateValidUntil?.isAfter(refdate)?: true ) }
            }
        }


        ret = ret.filter{it.addressPeriod.isNotEmpty()}.ifEmpty { throw NoDataFoundException("Queried Data is empty") }
        return ret
        }

    fun getStoreById( storeId: Int): ResponseEntity<Stores>{
        var tmp: Optional<Stores> = storesRepo.findById(storeId)

        if(tmp.isPresent)
            return ResponseEntity.ok(tmp?.get())
        else
            throw NoDataFoundException("id-" + storeId + ", data not found")
    }


    fun saveStore( store: Stores): Any {

        store.addressPeriod.forEach  { iter ->
            if (iter.dateValidFrom> iter.dateValidUntil)
                throw BadRequestException("Invalid Date Range")
        }

        log.trace("service.saveStore")
        log.info("service.saveStore")
        if(storesRepo.findById((store.id)).isPresent) {
            throw NoDataCreatedException("Data Already Exists")
        }
        else {
            storesRepo.save(store)
            log.trace("service.saveStore.save")
            log.info("service.saveStore.save")
            val tmp: Stores = storesRepo.findById(store.id)!!.get()
            return ResponseEntity.ok(tmp)
        }
    }
}