package com.example.uberdetail.api

import com.example.uberdetail.model.Bill
import com.example.uberdetail.model.Driver
import com.example.uberdetail.model.Trip
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface UberTripService {
    @GET("trips/{tripId}")
    fun getTrip(@Path("tripId") tripId: String): Deferred<Trip>

    @GET("users/{userId}")
    fun getTripDriver(@Path("userId") userId: String): Deferred<Driver>

    @GET("bills/{billdId}")
    fun getTripBill(@Path("billdId") billId: String): Deferred<Bill>
}