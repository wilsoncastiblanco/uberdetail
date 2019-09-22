package com.example.uberdetail

import com.example.uberdetail.api.UberTripService
import com.example.uberdetail.interactor.TripRepository
import com.example.uberdetail.model.Bill
import com.example.uberdetail.model.Driver
import com.example.uberdetail.model.Trip
import com.example.uberdetail.presenter.UberTripDetailPresenter
import com.example.uberdetail.view.UberTripDetailView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.*

class UberTripDetailPresenterTest {

    @Test
    fun `test presenter dispatching the render view after to execute the suspended functions`() = runBlocking {

        val view = mock(UberTripDetailView::class.java)
        val service = mock(UberTripService::class.java)
        val repository = TripRepository(service)
        val presenter = UberTripDetailPresenter(
            view = view,
            coroutineCtx = Dispatchers.Unconfined,
            repository = repository
        )

        val expectedTrip = Trip(id = "2", userId = "1", billId = "1")
        val expectedDriver = Driver(id = "1", name = "Wilson Castiblanco", email = "wcastiblancoq@gmail.com")
        val expectedBill = Bill(id = "1", price = "25.50", date = "05/05/2019 23:20:00")

        `when`(repository.getTrip("2")).thenReturn(expectedTrip)
        `when`(repository.getTripDriver("1")).thenReturn(expectedDriver)
        `when`(repository.getTripBill("1")).thenReturn(expectedBill)

        presenter.showUberTripDetail("2")

        verify(view).renderTripDetail(expectedTrip, expectedDriver, expectedBill)
    }
}