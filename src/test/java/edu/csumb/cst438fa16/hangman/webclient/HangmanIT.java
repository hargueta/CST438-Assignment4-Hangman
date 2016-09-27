package edu.csumb.cst438fa16.hangman.webclient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * User story:
 *
 *   As a user, I want to be able to enter a letter, so that I can play hangman.
 *
 * Class name ends in IT so it runs with "mvn integration-test", not "mvn test".
 */
public class HangmanIT {
	WebDriver driver = new ChromeDriver();
	
	@Before
    public void setUp() {
        driver.get("http://localhost:8080/hangman.html");
    }
	
	@After
    public void tearDown() {
        driver.quit(); // close browser
    }
	
	/**
     * Acceptance test:
     *
     *   Given I am on the hangman page and the word to guess is "cat"
     *   When I enter the letter "p"
     *   Word contains the text "..." indicating that the letter was incorrect
     */
	@Test
	public void testWrongLetterSubmit() {
		driver.findElement(By.id("newGuesses")).sendKeys("p");
		driver.findElement(By.id("submit")).click();
		
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.textToBe(By.id("pattern"), "..."));
	}
	
	/**
     * Acceptance test:
     *
     *   Given I am on the hangman page and the word to guess is "cat"
     *   When I enter the letter "c", "a", "t"
     *   I am prompted that I have won.
     */
	@Test
	public void testCorrectWordSubmit() {
		driver.findElement(By.id("newGuesses")).sendKeys("c");
		driver.findElement(By.id("submit")).click();
		
		driver.findElement(By.id("newGuesses")).sendKeys("a");
		driver.findElement(By.id("submit")).click();
		
		driver.findElement(By.id("newGuesses")).sendKeys("t");
		driver.findElement(By.id("submit")).click();
		
		Alert alert = driver.switchTo().alert();
		
		(new WebDriverWait(driver, 10))
			.until(ExpectedConditions.alertIsPresent());
		
		alert.accept();
	}	
}
