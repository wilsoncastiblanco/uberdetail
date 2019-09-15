package com.example.uberdetail.api

import com.example.uberdetail.model.Bill
import com.example.uberdetail.model.Driver
import com.example.uberdetail.model.Trip
import retrofit2.http.GET
import retrofit2.http.Path

interface UberTripService {
    @GET("trips/{tripId}")
    suspend fun getTrip(@Path("tripId") tripId: String): Trip

    @GET("users/{userId}")
    suspend fun getTripDriver(@Path("userId") userId: String): Driver

    @GET("bills/{billdId}")
    suspend fun getTripBill(@Path("billdId") billId: String): Bill
}