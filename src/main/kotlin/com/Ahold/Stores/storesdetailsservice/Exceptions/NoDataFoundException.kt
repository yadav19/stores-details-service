package com.Ahold.Stores.storesdetailsservice.Exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NoDataFoundException(message : String) : RuntimeException(message) {

}
