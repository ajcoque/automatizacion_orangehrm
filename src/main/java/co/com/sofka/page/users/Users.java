package co.com.sofka.page.users;

import co.com.sofka.model.admin.usermanagement.users.UserModel;
import co.com.sofka.page.common.CommonActionsOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Users extends CommonActionsOnPages {

    private static final Logger LOGGER = Logger.getLogger(Users.class);
    private UserModel userModel;
    private static final String MODEL_NULL_MESSAGE = "El modelo del formulario es nulo.";

    private final By linkTextAdmin = By.linkText("Admin");
    private final By linkTextUserManagement = By.linkText("User Management");
    private final By linkTextUsers = By.linkText("Users");

    private final By optionUsers = By.id("menu_admin_viewSystemUsers");

    //For input test cases.
    private final By username = By.id("searchSystemUser_userName");
    private final By search = By.id("searchBtn");


    //For Assertions test case.
    private final By assertionUsername = By.xpath("//*[@id=\"resultTable\"]/tbody/tr[1]/td[2]/a");

    //were not used because user data is changing
    private final By assertionRole = By.xpath("//*[@id=\"resultTable\"]/tbody/tr[1]/td[3]");
    private final By assertionEmployeeName = By.xpath("//*[@id=\"resultTable\"]/tbody/tr[1]/td[4]");
    private final By assertionStatus = By.xpath("//*[@id=\"resultTable\"]/tbody/tr[1]/td[5]");

    public Users(WebDriver driver, UserModel userModel) {
        super(driver);
        this.userModel = userModel;
    }

    public Users(WebDriver driver, UserModel userModel, int secondsForExplicitWait) {

        super(driver, secondsForExplicitWait);

        if (userModel == null)
            LOGGER.warn(MODEL_NULL_MESSAGE);

        this.userModel = userModel;

    }

    public void fillUserForm() {

        try {
            getSection();
            scrollTo(username);
            withExplicitWaitClear(username);
            withExplicitWaitTypeInto(username, userModel.getUsername());

            scrollTo(search);
            clickOn(search);

        } catch (Exception exception) {
            LOGGER.warn(exception.getMessage());
        }
    }


    public void getSection() {

        actionMove(linkTextAdmin);
        actionMove(linkTextUserManagement);
        actionMove(linkTextUsers);
        clickOn(optionUsers);

    }

    public String isUsersDone() {
        System.out.println(getText(assertionUsername).trim());
        return getText(assertionUsername).trim();
    }

}
