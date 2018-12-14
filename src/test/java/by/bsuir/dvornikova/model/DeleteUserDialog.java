package by.bsuir.dvornikova.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteUserDialog extends BasePage {

    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement yesButton;

    public DeleteUserDialog(WebDriver driver) {
        super(driver);
    }

    public void pressYesButton() {
        yesButton.click();
    }

    public WebElement getYesButton() {
        return yesButton;
    }

    public boolean hasDeletingUserWarning() {
        return driver.getPageSource().contains("Are you sure about deleting the user from Jenkins?");
    }

}
