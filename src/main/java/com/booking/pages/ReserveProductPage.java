package com.booking.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object Model class representing the property reservation functionality.
 *
 * Handles user actions related to reserving a property such as:
 * - Clicking the wishlist
 * - Clicking reserve buttons for submitting reservation
 * - Handling availability checks and reservation confirmation
 *
 * Encapsulates interacting with key UI elements using Selenide.
 */

public class ReserveProductPage {

    private static final SelenideElement WISHLIST_BUTTON = $("button[data-testid='wishlist-button']");
    private static final SelenideElement AVAILABILITY_RESERVE_BUTTON = $(".hprt-reservation-cta");


    private SelenideElement clickReserveButton() {
        return $$(".submitButton").first();
    }


    public void userClicksOnReserveProperty() {
        WISHLIST_BUTTON.click();
        clickReserveButton().scrollTo().shouldHave(visible).click();

        if (AVAILABILITY_RESERVE_BUTTON.isDisplayed()) {
            AVAILABILITY_RESERVE_BUTTON.scrollTo().shouldHave(enabled).click();
        }
    }

}
