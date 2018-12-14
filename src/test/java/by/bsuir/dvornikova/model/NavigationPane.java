package by.bsuir.dvornikova.model;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationPane extends BasePage {

    @FindBy(linkText = "Manage Jenkins")
    private WebElement manageJenkinsLink;

    @FindBy(xpath = "//a[text()='ENABLE AUTO REFRESH']")
    private WebElement enableAutoRefreshLink;

    @FindBy(xpath = "//a[text()='DISABLE AUTO REFRESH']")
    private WebElement disableAutoRefreshLink;

    private boolean autoRefreshEnabled = false;

    public NavigationPane(WebDriver driver) {
        super(driver);
    }

    public void manage() {
        manageJenkinsLink.click();
    }

    public boolean hasEnableAutoRefreshLink() {
        try {
            enableAutoRefreshLink = driver.findElement(By.xpath("//a[text()='ENABLE AUTO REFRESH']"));
            return enableAutoRefreshLink.isDisplayed();
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean hasDisableAutoRefreshLink() {
        try{
            disableAutoRefreshLink = driver.findElement(By.xpath("//a[text()='DISABLE AUTO REFRESH']"));
            return disableAutoRefreshLink.isDisplayed();
        }catch (NoSuchElementException e) {
            return false;
        }
    }

    public void toggleAutoRefresh() {
        if(!autoRefreshEnabled) {
            enableAutoRefreshLink = driver.findElement(By.xpath("//a[text()='ENABLE AUTO REFRESH']"));
            enableAutoRefreshLink.click();
            autoRefreshEnabled = true;
        }else{
            disableAutoRefreshLink = driver.findElement(By.xpath("//a[text()='DISABLE AUTO REFRESH']"));
            disableAutoRefreshLink.click();
            autoRefreshEnabled = false;
        }
    }

}
