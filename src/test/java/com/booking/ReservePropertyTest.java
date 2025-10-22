package com.booking;

import com.booking.pages.FillDetailsPage;
import com.booking.pages.ReserveProductPage;
import com.booking.pages.SearchProductPage;
import com.booking.util.ReadConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

/**
 * This test suite validates the end-to-end Booking.com user journey:
 * Searching for a property, applying filters, reserving the property,
 * and filling in user details using Selenide and TestNG within the POM architecture.
 */
public class ReservePropertyTest extends BaseTest {

    // Configuration values loaded from Config.properties file
    private final String DESTINATION = ReadConfig.getDestination();
    private final int NO_OF_ADULTS = Integer.parseInt(ReadConfig.getNoOfAdults());
    private final String AMENITY = ReadConfig.getAminity();
    private final String FIRST_NAME = ReadConfig.getFirstName();
    private final String LAST_NAME = ReadConfig.getLastName();
    private final String EMAIL = ReadConfig.getEmail();
    private final String ADDRESS = ReadConfig.getAddress();
    private final String CITY = ReadConfig.getCity();
    private final String COUNTRY = ReadConfig.getCountry();
    private final int PHONE = Integer.parseInt(ReadConfig.getPhone());

    /**
     * Test 1: Search Property Flow
     * Purpose:
     *  - Validate that the user can perform a property search successfully.
     *  - Verify the search flow includes destination input, date selection, and occupancy setup.
     *  - Ensure search results are displayed, and the “Hot tub” filter can be applied.
     *  - Confirm the first property displays a price and lists the "Hot tub" amenity.
     */
    @Epic("Booking.com")
    @Feature("Search and Filter")
    @Story("Property Search with Amenities and Pricing")
    @Description("Verify the user can search for properties in a destination, "
            + "select dates, set occupancy, apply filters like Hot tub/Jacuzzi, "
            + "and validate properties and price display on Booking.com")
    @Severity(SeverityLevel.CRITICAL)

    @Test(priority = 1)
    public void verifyUserAbleToSearchProperty() {
        SearchProductPage searchProductPage = new SearchProductPage();
        searchProductPage.verifyHomePage()
                .enterDestination(DESTINATION)
                .enterDates()
                .enterNoOfOccupancyAndSearch(NO_OF_ADULTS);

        // Assert that search results are displayed
        Assert.assertTrue(searchProductPage.isPropertiesDisplayed(),
                "No search results found for the given destination.");

        // Apply Hot tub filter and verify property details
        searchProductPage.applyHotTubFilter(AMENITY);
        switchTo().window(1);
        Assert.assertTrue(searchProductPage.isPriceDisplayedAndHotTubAvailable(AMENITY),
                "Either price or Hot tub amenity not found in property details.");
    }

    /**
     * Test 2: Property Reservation Flow
     * Purpose:
     *  - Validate that the user can initiate the reservation process after opening a property page.
     */
    @Epic("Booking.com")
    @Feature("Reserve Property")
    @Story("Verify user is able to reserve property")
    @Description("Verify that the user is able to reserve a selected property on the website")
    @Severity(SeverityLevel.CRITICAL)

    @Test(dependsOnMethods = "verifyUserAbleToSearchProperty")
    public void verifyUserAbleToReserveProperty() {
        new ReserveProductPage().userClicksOnReserveProperty();
    }

    /**
     * Test 3: Fill Personal Details Flow
     * Purpose:
     *  - Validate that the booking form allows entering valid user details.
     *  - Ensure each required field accepts proper data (name, email, country, phone).
     *  - Confirm booking request can proceed successfully to the final confirmation stage.
     */
    @Epic("Booking.com")
    @Feature("Fill Details")
    @Story("Verify user able to fill personal details")
    @Description("Verify that the user can enter personal details and complete the booking form")
    @Severity(SeverityLevel.NORMAL)

    @Test(dependsOnMethods = "verifyUserAbleToReserveProperty")
    public void verifyUserAbleToFillDetails() {
        Assert.assertTrue(new FillDetailsPage().enterDetailsAndVerify(FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, CITY, COUNTRY, PHONE),
                "Verification of user details failed.");
    }

}
