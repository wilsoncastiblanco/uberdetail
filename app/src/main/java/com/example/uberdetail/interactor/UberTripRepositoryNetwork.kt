package com.example.uberdetail.interactor

import com.example.uberdetail.api.RetrofitFactory.uberTripService
import com.example.uberdetail.model.Bill
import com.example.uberdetail.model.Driver
import com.example.uberdetail.model.Trip
import java.lang.IllegalArgumentException

class UberTripRepositoryNetwork {

    private val service = uberTripService()

    suspend fun getTrip(tripId: String): Trip {
        return service.getTrip(tripId)
    }

    suspend fun getTripDriver(userId: String): Driver {

        return service.getTripDriver(userId)
    }

    suspend fun getTripBill(billId: String): Bill {
        return service.getTripBill(billId)
    }

}