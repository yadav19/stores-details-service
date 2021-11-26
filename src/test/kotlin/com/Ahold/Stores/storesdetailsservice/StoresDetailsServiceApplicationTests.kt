package com.Ahold.Stores.storesdetailsservice

import com.Ahold.Stores.storesdetailsservice.model.Stores
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class StoresDetailsServiceApplicationTests() {

	val testRestTemplate = TestRestTemplate()
	@Test
	fun contextLoads() {
	}
	@Test
	fun getAllStoresTest(){
		val result = testRestTemplate.getForEntity<List<Stores>>("http://localhost:9090/store-service/v1/stores/", List::class)
		assertEquals(result.body!!.size,3)
		assertEquals(result.statusCodeValue, 200)
	}
	@Test
	fun getStoreIdTest(){
		val result = testRestTemplate.getForEntity<Stores>("http://localhost:9090/store-service/v1/stores/1001", Stores::class)
		assertEquals(result.body!!.id,1001)
		assertEquals(result.statusCodeValue, 200)
	}
}
