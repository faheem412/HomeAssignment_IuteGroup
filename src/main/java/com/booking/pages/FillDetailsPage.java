package com.booking.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object Model class representing the personal details entry page.
 *
 * Provides methods to input user details including first name, last name,
 * email, country selection, and phone number.
 *
 * Handles interaction with form fields and the booking button,
 * and verifies successful navigation to the payment timing section.
 */

public class FillDetailsPage {
    private static final SelenideElement FIRSTNAME = $("input[data-testid= 'user-details-firstname'");
    private static final SelenideElement LASTNAME = $("input[data-testid= 'user-details-lastname'");
    private static final SelenideElement EMAIL = $("input[data-testid= 'user-details-email'");
    private static final SelenideElement COUNTRY = $("select[data-testid= 'user-details-cc1']");
    private static final SelenideElement PHONE = $("input[data-testid='phone-number-input']");
    private static final SelenideElement BOOK_BUTTON = $("button[name='book']");
    private static final SelenideElement PAYMENT = $("#payment-timing-options-header-title");

    public boolean enterDetailsAndVerify(String firstName, String lastName, String email, String country, int number) {
        FIRSTNAME.shouldBe(visible, Duration.ofSeconds(8)).setValue(firstName);
        LASTNAME.setValue(lastName);
        EMAIL.setValue(email);
        COUNTRY.selectOption(country);
        PHONE.setValue(String.valueOf(number));

        BOOK_BUTTON.scrollTo().click();
        return PAYMENT.shouldBe(visible, Duration.ofSeconds(8)).isDisplayed();
    }
}
