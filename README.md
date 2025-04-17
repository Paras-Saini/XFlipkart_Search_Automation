                    FlipKart Search Automation

This project automates the testing of Flipkart's search functionality using Java, Selenium WebDriver, and TestNG.

I implement custom Selenium wrapper methods for interacting with web elements and validating different search and filter scenarios.

Test Cases

    testCase01:
       1. Navigate to flipkart.com.
       2. Search for "Washing Machine".
       3. Sort the results by Popularity.
       4. Print the count of items having a rating less than or equal to 4 stars.

    testCase02:
       1. Search for "iPhone".
       2. Identify and print the Titles and Discount Percentages of items offering more than 17% discount.

    testCase03:
       1. Search for "Coffee Mug".
       2. Apply a filter for 4 stars & above.
       3. Print the Title and Image URL of the top 5 items with the highest number of reviews.

How to Run
1.Clone the repository.
2.Open in your IDE (IntelliJ/Eclipse/VSCode).
3.Make sure all dependencies (Selenium, TestNG) are installed.
4.Run the TestCases.java as a TestNG test suite.
