package com.example.uberdetail

import com.example.uberdetail.api.UberTripService
import com.example.uberdetail.interactor.TripRepository
import com.example.uberdetail.model.Bill
import com.example.uberdetail.model.Driver
import com.example.uberdetail.model.Trip
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UberTripRepositoryNetworkTest {

    lateinit var service: UberTripService

    @Before
    fun setup() {
        val client = OkHttpClient.Builder()
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dynamic-services.herokuapp.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        service = retrofit.create(UberTripService::class.java)
    }

    @Test
    fun `test repository returns Trip object after execute the coroutine`() = runBlocking {
        val repository = TripRepository(service)

        val expectedTrip = Trip(id = "2", userId = "1", billId = "1")
        val trip = repository.getTrip("2")

        assertEquals(expectedTrip, trip)
    }

    @Test
    fun `test repository returns Driver object after execute the coroutine`() = runBlocking {
        val repository = TripRepository(service)

        val expectedDriver = Driver(id = "1", name = "Wilson Castiblanco", email = "wcastiblancoq@gmail.com")
        val driver = repository.getTripDriver("1")

        assertEquals(expectedDriver, driver)
    }

    @Test
    fun `test repository returns Bill object after execute the coroutine`() = runBlocking {
        val repository = TripRepository(service)

        val expectedBill = Bill(id = "1", price = "25.50", date = "05/05/2019 23:20:00")
        val bill = repository.getTripBill("1")

        assertEquals(expectedBill, bill)
    }
}