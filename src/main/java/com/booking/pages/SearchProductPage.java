package com.booking.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Page Object Model class representing the Search Product page on Booking.com.
 *
 * Encapsulates all elements and user interactions related to searching properties:
 * - Handling cookies acceptance
 * - Entering destination, dates, and occupancy details
 * - Applying filters such as amenities
 * - Validating property results, prices, and amenities presence
 *
 * Uses Selenide for UI element interaction and smart waits.
 * Methods return `this` for fluent method chaining when applicable.
 */

public class SearchProductPage {

    private static final SelenideElement BOOKING_COM_LOGO = $("a[aria-label='Booking.com']");
    private static final SelenideElement COOKIES_BANNER = $(byId("onetrust-banner-sdk"));
    private static final SelenideElement COOKIES_REJECT = $("button#onetrust-reject-all-handler");
    private static final SelenideElement DESTINATION = $("input[placeholder='Where are you going?']");
    private static final SelenideElement TODAY_DATE = $("#calendar-searchboxdatepicker span[aria-current='date']");
    private static final SelenideElement OCCUPANCY = $("button[data-testid='occupancy-config']");
    private static final SelenideElement GROUP_ADULTS = $("#group_adults");
    private static final SelenideElement PLUS_BUTTON = $("div[data-testid='occupancy-popup'] button:nth-of-type(2)");
    private static final SelenideElement POPUP_CLOSE = $("button[aria-label='Dismiss sign in information.']");
    private static final SelenideElement OVERVIEW_TAB = $(byId("overview-tab-trigger"));
    private static final SelenideElement PRICE = $(".totalPrice span");


    private SelenideElement autocompleteOption(String value) {
        return $$("li[id^='autocomplete-result']").findBy(text(value));
    }

    private SelenideElement getDate(LocalDate date) {
        return $("#calendar-searchboxdatepicker span[data-date='" + date + "']");
    }

    private SelenideElement getShowAllFacility_Button() {
        return $$("div[data-filters-group='hotelfacility'] button[data-testid='filters-group-expand-collapse']")
                .first()
                .scrollTo();
    }

    private SelenideElement getDone_Button() {
        return $$("button span").findBy(text("Done"));
    }

    private SelenideElement getSearch_Button() {
        return $$("button").findBy(text("Search"));
    }

    private SelenideElement getPopertiesFound_Lable() {
        return $$("h1").findBy(partialText("found"));
    }

    private SelenideElement getFilter(String facility) {
        return $$("div[data-testid='filters-group-label-content']")
                .findBy(text(facility))
                .scrollTo();
    }

    private SelenideElement getProductTitle() {
        return $$("div[data-testid='title'")
                .first()
                .scrollTo();
    }

    private SelenideElement getAminityPresent(String aminity){
        return $$("div[data-testid='property-most-popular-facilities-wrapper'] div")
                .findBy(text(aminity));
    }

    public SearchProductPage verifyHomePage() {

        if (COOKIES_BANNER.isDisplayed()) {
            COOKIES_REJECT.shouldBe(visible).click();
        }
        BOOKING_COM_LOGO.shouldBe(visible);
        return this;
    }

    public SearchProductPage enterDestination(String destination) {
        DESTINATION.setValue(destination);
        autocompleteOption(destination)
                .shouldBe(enabled)
                .click();
        return this;
    }

    public SearchProductPage enterDates() {
        TODAY_DATE.click();
        LocalDate today = LocalDate.now();
        LocalDate oneWeekLater = today.plusWeeks(1);
        getDate(oneWeekLater).click();
        return this;
    }

    public void enterNoOfOccupancyAndSearch(int totAdults) {
        OCCUPANCY.click();
        int count = Integer.parseInt(GROUP_ADULTS.getAttribute("value"));
        while (count < totAdults) {
            PLUS_BUTTON.click();
            count++;
        }

        getDone_Button().shouldBe(visible).click();
        getSearch_Button().shouldBe(visible).click();
    }

    public boolean isPropertiesDisplayed() {
        return getPopertiesFound_Lable().shouldBe(visible).isDisplayed();
    }

    public void applyHotTubFilter(String facility) {
        if (POPUP_CLOSE.isDisplayed()) {
            POPUP_CLOSE.shouldBe(interactable).click();
        }
        getShowAllFacility_Button().shouldBe(visible).click();
        getFilter(facility).click();
        getProductTitle().shouldBe(visible, Duration.ofSeconds(6000)).scrollTo().click();
        Selenide.Wait().until(webDriver ->
                Selenide.executeJavaScript("return document.readyState").equals("complete"));
    }

    public boolean isPriceDisplayedAndHotTubAvailable(String amenity){
        OVERVIEW_TAB.shouldBe(visible);
        boolean priceAvailable = PRICE.text().matches(".*\\d+.*");
        boolean hotTubAmenity = getAminityPresent(amenity).isDisplayed();
        return hotTubAmenity && priceAvailable;
    }
}


