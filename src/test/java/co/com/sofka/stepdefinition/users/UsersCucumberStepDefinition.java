package co.com.sofka.stepdefinition.users;

import co.com.sofka.model.admin.usermanagement.users.UserModel;

import co.com.sofka.page.users.Users;
import co.com.sofka.stepdefinition.common.LoginSuccess;
import co.com.sofka.stepdefinition.setup.WebUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.util.Seconds.TEN_SECONDS;

public class UsersCucumberStepDefinition extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(UsersCucumberStepDefinition.class);
    private UserModel userModel;
    private Users users;
    private LoginSuccess loginSuccess;

    private static final String ASSERTION_EXCEPTION_MESSAGE_USER_DATA = "No se muestran los datos del usuario consultado";

    @Given("el administrador del sistema se encuentra en la pagina de users")
    public void elAdministradorDelSistemaSeEncuentraEnLaPaginaDeUsers() {

        try {
            generalSetUp();
            loginSuccess = new LoginSuccess();
            loginSuccess.successLogin(driver);
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }
    @When("el administrador ingresa el username del usuario existente que desea buscar y presiona en el boton Search")
    public void elAdministradorIngresaElUsernameDelUsuarioExistenteQueDeseaBuscarYPresionaEnElBotonSearch() {
        try {
            dataUser();
            users = new Users(driver, userModel, TEN_SECONDS.getValue());
            users.fillUserForm();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }

    }
    @Then("el sistema debera mostrar los datos del usuario consultado")
    public void elSistemaDeberaMostrarLosDatosDelUsuarioConsultado() {
       try {
            Assertions.assertEquals(
                    expectedSuccessSearch(),
                    users.isUsersDone(),
                    ASSERTION_EXCEPTION_MESSAGE_USER_DATA
            );
            quitDriver();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    private void dataUser(){
        userModel = new UserModel();
        userModel.setUsername("Admin");
    }

    private String expectedSuccessSearch(){
        return userModel.getUsername();
    }

}
