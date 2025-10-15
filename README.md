# Booking.com UI Test Automation

## Overview
This project automates a key booking.com user flow using Java, Selenide, and TestNG, applying Page Object Model (POM) and Allure reporting.

## Features Tested
- Search for any destination on booking.com
- Select check-in date 1 week from today and stay for 7 nights
- Set number of occupants to 5 adults
- Verify that a list of properties is returned
- Apply the "Hot tub" filter
- Open the first property in the results list
- Verify that the property displays a price and lists "Hot tub" in amenities
- Add the property to the wishlist
- Scroll to the Recommended section and initiate booking request
- Fill in personal details and proceed to the final booking details page

## Technologies
- Java 21
- Selenide for UI automation
- TestNG as test framework
- Allure for test reporting
- Maven for build and dependencies

## Setup Instructions
1. Clone the repository
2. Ensure Java JDK and Maven are installed and configured
3. Run tests with Maven:
   ```
   mvn clean test
4. Generate Allure report after tests:
   ```
   allure serve target/allure-results

## Project Structure
- `/src/main/java/com/booking/pages` - Page Object classes
- `/src/test/java/com/booking` - Test classes
- `/src/test/resources` - Configuration and test data files

## Notes
- Tests are designed as independent, reusable POM methods with clear `@Step` annotations.
- Handles dynamic waits automatically via Selenide's smart waits.

## Screenshots
<img width="1440" height="773" alt="image" src="https://github.com/user-attachments/assets/814bce3e-11ea-4fd4-b3b2-abbdf5f025d9" /> 
<img width="1440" height="773" alt="image" src="https://github.com/user-attachments/assets/13f5bc63-8058-40f4-895a-3f6cb6bbe44b" /> 
<img width="1440" height="773" alt="image" src="https://github.com/user-attachments/assets/5cae861d-e4cf-4ba1-87cb-4a81b03c5bab" />



