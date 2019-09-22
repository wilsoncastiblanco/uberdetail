package com.example.uberdetail.interactor

import com.example.uberdetail.model.Bill
import com.example.uberdetail.model.Driver
import com.example.uberdetail.model.Trip

interface UberTripRepository {

    suspend fun getTrip(tripId: String): Trip

    suspend fun getTripDriver(userId: String): Driver

    suspend fun getTripBill(billId: String): Bill
}