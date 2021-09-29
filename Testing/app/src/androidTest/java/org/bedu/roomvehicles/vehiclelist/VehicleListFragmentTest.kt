package org.bedu.roomvehicles.vehiclelist

import android.view.View
import android.widget.ImageButton
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.bedu.roomvehicles.R
import org.bedu.roomvehicles.data.AndroidFakeVehicleRepository
import org.bedu.roomvehicles.data.Repository
import org.bedu.roomvehicles.data.ServiceLocator
import org.bedu.roomvehicles.data.VehicleHelper
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@SmallTest
@RunWith(AndroidJUnit4::class)
class VehicleListFragmentTest {

    val vehicles = listOf(
        VehicleHelper.vento,
        VehicleHelper.jetta
    )

    @Volatile
    private lateinit var vehicleRepository: Repository
        @VisibleForTesting set

    @Before
    fun setUp() {
        vehicleRepository = AndroidFakeVehicleRepository()
        ServiceLocator.repository = vehicleRepository
    }

    @After
    fun close() {
        ServiceLocator.resetRepository()
    }

    @Test
    fun removeButton_RemovesVehicle() {

        vehicleRepository.populateVehicles(vehicles)
        launchFragmentInContainer<VehicleListFragment>(null, R.style.Theme_RoomVehicles)

        Thread.sleep(4_000)
        val textMatcher = ViewMatchers.hasDescendant(
            ViewMatchers.withText("jetta")
        )

        Espresso.onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    textMatcher,
                    itemAction(R.id.button_delete)
                )
            )

        assertThat(vehicleRepository.getVehicles().value).doesNotContain(VehicleHelper.jetta)

    }

    fun itemAction(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on action button"

        override fun perform(uiController: UiController?, view: View?) {
            val button = view?.findViewById<ImageButton>(R.id.button_delete)
            button?.performClick()
        }

    }
}