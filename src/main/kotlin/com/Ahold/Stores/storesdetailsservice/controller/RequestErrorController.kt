package com.Ahold.Stores.storesdetailsservice.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/error")
class RequestErrorController: ErrorController {

    @GetMapping
    fun giveSomeGlobalError(): Any
    {
        return object {
            val status: String = "Not Found"
            val Message: String = "Seems the Resource is not currently available"
            val errorCode: String = "1Ac6364Ddk12S3"
        }
    }
}