package com.Ahold.Stores.storesdetailsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.mongodb.core.aggregation.DateOperators
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class StoresDetailsServiceApplication



fun main(args: Array<String>) {
	runApplication<StoresDetailsServiceApplication>(*args)
}
