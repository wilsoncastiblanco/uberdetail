package com.example.uberdetail.interactor

import com.example.uberdetail.api.RetrofitFactory.uberTripService
import com.example.uberdetail.model.Bill
import com.example.uberdetail.model.Driver
import com.example.uberdetail.model.Trip
import com.example.uberdetail.presenter.UberTripDetailPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UberTripRepositoryNetwork {

    private val service = uberTripService()

    fun getTrip(tripId: String, event: UberTripDetailPresenter.Event<Trip>) {

        service.getTrip(tripId).enqueue(object: Callback<Trip> {

            override fun onFailure(call: Call<Trip>, t: Throwable) {
                event.onError(t)
            }

            override fun onResponse(call: Call<Trip>, response: Response<Trip>) {
                event.onSuccess(response.body())
            }

        })
    }

    fun getTripDriver(userId: String, event: UberTripDetailPresenter.Event<Driver>) {

        service.getTripDriver(userId).enqueue(object: Callback<Driver> {

            override fun onFailure(call: Call<Driver>, t: Throwable) {
                event.onError(t)
            }

            override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
                event.onSuccess(response.body())
            }

        })
    }

    fun getTripBill(billId: String, event: UberTripDetailPresenter.Event<Bill>) {

        service.getTripBill(billId).enqueue(object: Callback<Bill> {

            override fun onFailure(call: Call<Bill>, t: Throwable) {
                event.onError(t)
            }

            override fun onResponse(call: Call<Bill>, response: Response<Bill>) {
                event.onSuccess(response.body())
            }

        })
    }

}