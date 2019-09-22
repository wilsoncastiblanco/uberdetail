package com.example.uberdetail.interactor

import com.example.uberdetail.api.RetrofitFactory.uberTripService
import com.example.uberdetail.api.UberTripService
import com.example.uberdetail.model.Bill
import com.example.uberdetail.model.Driver
import com.example.uberdetail.model.Trip

class TripRepository(private val service: UberTripService = uberTripService()) :
    UberTripRepository {

    override suspend fun getTrip(tripId: String): Trip {
        return service.getTrip(tripId)
    }

    override suspend fun getTripDriver(userId: String): Driver {
        return service.getTripDriver(userId)
    }

    override suspend fun getTripBill(billId: String): Bill {
        return service.getTripBill(billId)
    }

}