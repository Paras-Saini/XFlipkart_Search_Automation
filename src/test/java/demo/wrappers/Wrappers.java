package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    /*
     * textInput method is used to enter the text in the searchbar element. It will
     * clear the existing text and enter the new text in the searchbar element.
     */
    public static boolean textInput(WebDriver driver, By locator, String text) {
        boolean status = false;
        try {
            // Waiting for the searchBar element to be present in the DOM
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            searchBar.sendKeys(Keys.CONTROL + "a");
            searchBar.sendKeys(Keys.DELETE);
            searchBar.sendKeys(text);
            System.out.println("Text: " + text);
            searchBar.sendKeys(Keys.ENTER);
            status = true;

        } catch (Exception e) {
            System.out.println("Test case failed:" + e.getMessage());
        }
        return status;
    }

    /*
     * clickOption method is used to click on the element which is passed as
     * parameter.
     */
    public static boolean clickOption(WebDriver driver, By locator) {
        boolean status = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement clickOpt = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            clickOpt.click();
            status = true;

        } catch (Exception e) {
            System.out.println("Test case failed: " + e);
        }
        return status;
    }

    /*
     * getText method is used to get the text of the element which is passed as
     * parameter.
     */
    public static boolean starTitleRating(WebDriver driver, By locator) {
        boolean status = false;
        try {
            List<WebElement> allTitle = driver.findElements(locator);
            System.out.println("Total no of elements which have less than & equal to 4 Stars:" + allTitle.size());
            status = true;
        } catch (Exception e) {
            System.out.println("Test case Failed: " + e);
        }
        return status;
    }

    /*
     * discountTitle method is used to get the title and discount elements from the
     * page. It will check if the discount is greater than 17% and print the title
     * and discount.
     */
    public static boolean discountTitle(WebDriver driver, By locator) {
        boolean status = false;
        try {

            List<WebElement> allElements = driver.findElements(locator);
            int count = 0;
            for (WebElement element : allElements) {
                try {

                    String text = element.findElement(By.xpath(".//div[@class='UkUFwK']//span")).getText();
                    int i = text.indexOf("%");
                    int pos = Integer.parseInt(text.substring(0, i));

                    if (pos > 17) {
                        System.out.println(
                                "Title: " + element.findElement(By.xpath(".//div[@class='KzDlHZ']")).getText());
                        System.out.println("and Discount: " + pos);
                        count++;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            if (count < 1) {
                System.out.println("No elements found with discount greater than 17%");
            }
            status = true;
        } catch (Exception e) {
            System.out.println("Test case Failed: " + e);
        }
        return status;
    }

    /*
     * selectTitleImg method is used to select the title and image elements from the
     * page. It will sort the reviews in descending order and get the top 5 reviews.
     * It will print the title and image Url of the top 5 reviews.
     */
    public static boolean selectTitleImg(WebDriver driver, By locator) {
        boolean status = false;
        /*
         * Used 2 hashmaps to store title & review elements and title & img elements
         * ArrayList to store review elements to sort them in descending order and get
         * top 5 reviews
         */
        HashMap<Integer, String> titleReview = new HashMap<>();
        HashMap<String, String> titleImg = new HashMap<>();
        List<Integer> reviewList = new ArrayList<>();
        try {
            List<WebElement> elements = driver.findElements(locator);
            for (WebElement element : elements) {
                try {
                    // Locating title, Img and review elements using Xpath
                    String title = element.findElement(By.xpath(".//a[@class='wjcEIp']")).getText();
                    String review = element.findElement(By.xpath(".//span[contains(@class,'Wphh')]")).getText();
                    String img = element.findElement(By.xpath(".//img[contains(@class,'DByuf')]")).getAttribute("src");

                    String numRev = review.replaceAll("[^0-9]", "");
                    if (numRev.isEmpty()) {
                        continue;
                    }

                    Integer num = Integer.parseInt(numRev);
                    titleReview.put(num, title);
                    titleImg.put(title, img);
                    reviewList.add(num);
                } catch (Exception e) {
                    continue;
                }

            }
        } catch (Exception e) {
            System.out.println("Test case Failed: " + e);
            return status;
        }
        reviewList.sort(Collections.reverseOrder());
        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ": " + reviewList.get(i));
            System.out.println("Title " + titleReview.get(reviewList.get(i)));
            System.out.println("Image URL: " + titleImg.get(titleReview.get(reviewList.get(i))));
        }
        status = true;
        return status;
    }
}
