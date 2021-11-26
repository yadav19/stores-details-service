package com.Ahold.Stores.storesdetailsservice.controller
import com.Ahold.Stores.storesdetailsservice.dto.errorResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import javax.servlet.RequestDispatcher

import javax.servlet.http.HttpServletRequest
@RestController
class RequestErrorController: ErrorController{

    companion object {
        val log: Logger = LoggerFactory.getLogger(RequestErrorController::class.java)
    }

    @RequestMapping("/error")
    fun globalErrorHandle(request: HttpServletRequest): errorResponse {

            log.error(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString())
            val date = LocalDate.now()
            val statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString()
            val message = when(statusCode) {
                "400" -> "Bad User Request"
                "404" -> "Resource Not Found"
                "500" -> " internal Server Error Occurred"
                else -> "${request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)} error occurred"

        }
       return errorResponse(timeStamp = date,details = message, responseCode = statusCode)
    }
}