package by.bsuir.dvornikova.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateUserPage extends BasePage {

    @FindBy(xpath = "//form/div/table/tbody/tr/td/input[@type='text']")
    private List<WebElement> textInputFields;

    @FindBy(xpath = "//form/div/table/tbody/tr/td/input[@type='password']")
    private List<WebElement> passwordInputFields;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@name='password1']")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@name='password2']")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//input[@name='fullname']")
    private WebElement fullNameInput;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[text()='Create User']")
    private WebElement createUserButton;

    public WebElement getCreateUserButton() {
        return createUserButton;
    }

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public void setUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        confirmPasswordInput.sendKeys(password);
    }

    public void setFullName(String fullName) {
        fullNameInput.sendKeys(fullName);
    }

    public void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    public boolean isCorrectForm() {
        return (textInputFields.size() == 3) && (passwordInputFields.size() == 2);
    }

    public boolean isAllFieldsEmpty() {
        for (WebElement e : textInputFields) {
            if(!e.getText().isEmpty()) return false;
        }
        for (WebElement e : passwordInputFields) {
            if(!e.getText().isEmpty()) return false;
        }

        return true;
    }

    public void submit() {
        createUserButton.click();
    }

}
