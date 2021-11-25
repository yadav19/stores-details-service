package com.Ahold.Stores.storesdetailsservice.Configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Configuration
open class MongoConversion {
    @Bean
    fun mongoCustomConversion(): MongoCustomConversions {
        val converters = mutableListOf<Converter<*, *>>()
        converters.add(DateToString())
        converters.add(StringToDate())
        return MongoCustomConversions(
            converters
        )
    }
}

@Component
@WritingConverter
internal class DateToString : Converter<LocalDate, String> {
    override fun convert(source: LocalDate): String {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return source.format(dateTimeFormatter)
    }
}

@Component
@ReadingConverter
class StringToDate: Converter<String, LocalDate> {
    override fun convert(source: String): LocalDate {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(source, dateTimeFormatter)
    }
}