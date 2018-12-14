package by.bsuir.dvornikova.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    @FindBy(xpath = "//input[@id='j_username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@name='j_password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@class='submit-button primary']")
    private WebElement submitButton;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void setUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void submit() {
        submitButton.click();
    }

}
