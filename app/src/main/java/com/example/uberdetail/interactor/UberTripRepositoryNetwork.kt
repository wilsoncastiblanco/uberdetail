package com.example.uberdetail.interactor

import com.example.uberdetail.api.RetrofitFactory.uberTripService
import com.example.uberdetail.model.Bill
import com.example.uberdetail.model.Driver
import com.example.uberdetail.model.Trip
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.*

class UberTripRepositoryNetwork {

    private val service = uberTripService()

    fun getTrip(tripId: String): Single<Triple<Driver, Bill, Trip>> {
        return service.getTrip(tripId).flatMap { trip ->
            Single.zip(
                service.getTripDriver(trip.userId),
                service.getTripBill(trip.billId),
                BiFunction<Driver, Bill, Triple<Driver, Bill, Trip>> { driver, bill ->
                    Triple(
                        driver,
                        bill,
                        trip
                    )
                }
            )
        }
    }
}