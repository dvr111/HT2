package by.bsuir.dvornikova.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageUsersPage extends BasePage {

    @FindBy(xpath = "//a[text()='Create User']")
    private WebElement createUserLink;

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    public void createUser() {
        createUserLink.click();
    }

    public void deleteUser(String username) {
        WebElement deleteUserLink = driver.findElement(By.xpath("//*[@href='user/" + username + "/delete']/*"));
        deleteUserLink.click();
    }

    public boolean hasCreateUserLink() {
        return createUserLink.isDisplayed();
    }

    public boolean hasUser(String username) {
        return !driver.findElements(By.xpath("//tr/td/*[text()='" + username + "']")).isEmpty();
    }

    public boolean isDeletableUser(String username) {
        return !driver.findElements(By.xpath("//*[@href='user/" + username + "/delete']/*")).isEmpty();
    }

}
