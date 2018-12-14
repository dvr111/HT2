package by.bsuir.dvornikova;

import by.bsuir.dvornikova.model.*;
import org.junit.*;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

    private static WebDriver driver;

    private static ManagePage managePage;

    private static ManageUsersPage manageUsersPage;

    private static CreateUserPage createUserPage;

    private static DeleteUserDialog deleteUserDialog;

    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();

        driver.navigate().to("http://localhost:8082/");

        SignInPage signInPage = new SignInPage(driver);

        signInPage.setUsername("admin");
        signInPage.setPassword("password");

        signInPage.submit();
    }

    @Test
    public void stepAOne() {
        NavigationPane navigationPane = new NavigationPane(driver);
        navigationPane.manage();

        managePage = new ManagePage(driver);

        assertTrue(managePage.hasManageUsersSection());
    }

    @Test
    public void stepBTwo() {
        managePage.manageUsers();

        manageUsersPage = new ManageUsersPage(driver);

        assertTrue(manageUsersPage.hasCreateUserLink());
    }

    @Test
    public void stepCThree() {
        manageUsersPage.createUser();

        createUserPage = new CreateUserPage(driver);

        assertTrue(createUserPage.isCorrectForm());
        assertTrue(createUserPage.isAllFieldsEmpty());
    }

    @Test
    public void stepDFour() {
        createUserPage.setUsername("someuser");
        createUserPage.setPassword("somepassword");
        createUserPage.setConfirmPassword("somepassword");
        createUserPage.setFullName("Some Full Name");
        createUserPage.setEmail("some@addr.com");

        Assert.assertEquals("#4b758b", Color.fromString(createUserPage.getCreateUserButton().getCssValue("background-color")).asHex());

        createUserPage.submit();

        manageUsersPage = new ManageUsersPage(driver);

        assertTrue(manageUsersPage.hasUser("someuser"));
    }

    @Test
    public void stepEFive() {
        manageUsersPage.deleteUser("someuser");

        deleteUserDialog = new DeleteUserDialog(driver);

        assertTrue(deleteUserDialog.hasDeletingUserWarning());
    }

    @Test
    public void stepFSix() {
        Assert.assertEquals("#4b758b", Color.fromString(deleteUserDialog.getYesButton().getCssValue("background-color")).asHex());

        deleteUserDialog.pressYesButton();

        manageUsersPage = new ManageUsersPage(driver);

        manageUsersPage.hasUser("someuser");
        assertFalse(manageUsersPage.hasUser("someuser"));
        assertFalse(manageUsersPage.isDeletableUser("someuser"));
    }

    @Test
    public void stepGSeven() {
        assertFalse(manageUsersPage.isDeletableUser("admin"));
    }

    @Test
    public void stepHEight() {
        NavigationPane np = new NavigationPane(driver);

        assertTrue(np.hasEnableAutoRefreshLink() && !np.hasDisableAutoRefreshLink());

        np.toggleAutoRefresh();

        assertTrue(!np.hasEnableAutoRefreshLink() && np.hasDisableAutoRefreshLink());

        np.toggleAutoRefresh();

        assertTrue(np.hasEnableAutoRefreshLink() && !np.hasDisableAutoRefreshLink());
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }

}
