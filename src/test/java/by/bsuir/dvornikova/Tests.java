package by.bsuir.dvornikova;

import org.junit.*;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

    private static ChromeDriver driver = new ChromeDriver();

    private static WebElement manageUsersDt;

    private static WebElement createUserLink;

    @BeforeClass
    public static void signIn() {
        driver.navigate().to("http://localhost:8082/");

        WebElement signInUsernameInput = driver.findElementByXPath("//input[@id='j_username']");
        signInUsernameInput.sendKeys("admin");

        WebElement signInPasswordInput = driver.findElementByXPath("//input[@name='j_password']");
        signInPasswordInput.sendKeys("password");

        WebElement signInButton = driver.findElementByXPath("//input[@class='submit-button primary']");

        signInButton.click();
    }

    @Test()
    public void stepAOne() {
        WebElement manageJenkinsLink = driver.findElement(By.linkText("Manage Jenkins"));
        manageJenkinsLink.click();

        manageUsersDt = driver.findElementByXPath("//dt[text()='Manage Users']");
        WebElement manageUsersDd = driver.findElementByXPath("//dd[text()='Create/delete/modify users that can log in to this Jenkins']");

        Assert.assertTrue(manageUsersDt.isDisplayed());
        Assert.assertTrue(manageUsersDd.isDisplayed());
    }

    @Test
    public void stepBTwo() {
        manageUsersDt.click();

        createUserLink = driver.findElementByXPath("//a[text()='Create User']");
        Assert.assertTrue(createUserLink.isDisplayed());
    }

    @Test
    public void stepCThree() {
        createUserLink.click();

        List<WebElement> textInputFields = driver.findElementsByXPath("//form/div/table/tbody/tr/td/input[@type='text']");
        List<WebElement> passwordInputFields = driver.findElementsByXPath("//form/div/table/tbody/tr/td/input[@type='password']");

        Assert.assertEquals(3, textInputFields.size());
        Assert.assertEquals(2, passwordInputFields.size());

        textInputFields.forEach(field -> Assert.assertTrue(field.getText().isEmpty()));
        passwordInputFields.forEach(field -> Assert.assertTrue(field.getText().isEmpty()));
    }

    @Test
    public void stepDFour() {
        WebElement usernameInput = driver.findElementByXPath("//input[@name='username']");
        usernameInput.sendKeys("someuser");

        WebElement passwordInput = driver.findElementByXPath("//input[@name='password1']");
        passwordInput.sendKeys("somepassword");

        WebElement confirmPasswordInput = driver.findElementByXPath("//*[@name='password2']");
        confirmPasswordInput.sendKeys("somepassword");

        WebElement fullNameInput = driver.findElementByXPath("//input[@name='fullname']");
        fullNameInput.sendKeys("Some Full Name");

        WebElement emailInput = driver.findElementByXPath("//input[@name='email']");
        emailInput.sendKeys("some@addr.com");

        WebElement createUserButton = driver.findElementByXPath("//button[text()='Create User']");

        Assert.assertEquals("#4b758b", Color.fromString(createUserButton.getCssValue("background-color")).asHex());

        createUserButton.click();

        WebElement createdUserTableCell = driver.findElementByXPath("//tr/td/*[text()='someuser']");
        Assert.assertTrue(createdUserTableCell.isDisplayed());
    }

    @Test
    public void stepEFive() {
        WebElement deleteUserLink = driver.findElementByXPath("//*[@href='user/someuser/delete']/*");
        deleteUserLink.click();

        Assert.assertTrue(driver.getPageSource().contains("Are you sure about deleting the user from Jenkins?"));
    }

    @Test
    public void stepFSix() {
        WebElement deleteUserYesButton = driver.findElementByXPath("//button[text()='Yes']");
        Assert.assertEquals("#4b758b", Color.fromString(deleteUserYesButton.getCssValue("background-color")).asHex());
        deleteUserYesButton.click();

        Assert.assertTrue(driver.findElementsByXPath("//tr/td/*[text()='someuser']").isEmpty());
        Assert.assertTrue(driver.findElementsByXPath("//*[@href='user/someuser/delete']/*").isEmpty());
    }

    @Test
    public void stepGSeven() {
        Assert.assertTrue(driver.findElementsByXPath("//*[@href='user/admin/delete']/*").isEmpty());
    }

    @AfterClass
    public static void shutdown() {
        driver.quit();
    }

}
