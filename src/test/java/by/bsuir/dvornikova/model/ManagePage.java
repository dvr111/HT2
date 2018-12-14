package by.bsuir.dvornikova.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagePage extends BasePage {

    @FindBy(xpath = "//dt[text()='Manage Users']")
    private WebElement manageUsersDt;

    @FindBy(xpath = "//dd[text()='Create/delete/modify users that can log in to this Jenkins']")
    private WebElement manageUsersDd;

    public ManagePage(WebDriver driver) {
        super(driver);
    }

    public void manageUsers() {
        manageUsersDt.click();
    }

    public boolean hasManageUsersSection() {
        return manageUsersDt.isDisplayed() && manageUsersDd.isDisplayed();
    }

}
