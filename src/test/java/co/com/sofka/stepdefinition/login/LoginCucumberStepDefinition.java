package co.com.sofka.stepdefinition.login;

import co.com.sofka.model.login.LoginModel;
import co.com.sofka.page.login.Login;
import co.com.sofka.stepdefinition.setup.WebUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.util.Seconds.TEN_SECONDS;

public class LoginCucumberStepDefinition extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(LoginCucumberStepDefinition.class);
    private LoginModel loginModel;
    private Login login;

    private static final String ASSERTION_EXCEPTION_MESSAGE = "El aplicativo no redirige a la pagina esperada";
    private static final String ASSERTION_EXCEPTION_MESSAGE_INVALID_CREDENTIALS = "No se muestra el mensaje Invalid credentials";
    private static final String ASSERTION_EXCEPTION_MESSAGE_EMPTY_CREDENTIALS = "No se muestra el mensaje Username cannot be empty";

    private static final String ASSERTION_INVALID_DATA = "Invalid credentials";
    private static final String ASSERTION_EMPTY_DATA = "Username cannot be empty";
    private static final String ASSERTION_TITLE_PAG = "Dashboard";

    //Background:
    @Given("el administrador del sistema se encuentra en la pagina de inicio de sesion")
    public void elAdministradorDelSistemaSeEncuentraEnLaPaginaDeInicioDeSesion() {
        try{
            generalSetUp();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }


    @When("el administrador ingresa los campos username y password obligatorios y solicita login")
    public void elAdministradorIngresaLosCamposUsernameYPasswordObligatoriosYSolicitaLogin() {
        try {
            dataConfigurationSuccess();
            login = new Login(driver, loginModel, TEN_SECONDS.getValue());
            login.fillLoginForm();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }
    @Then("el sistema debera rederigir a la pagina dashboard del aplicativo web")
    public void elSistemaDeberaRederigirAlaPaginaDashboardDelAplicativoWeb() {
        try {
            Assertions.assertEquals(
                    expectedSuccessLogin(),
                    login.isLoginDone(),
                    ASSERTION_EXCEPTION_MESSAGE
            );
            quitDriver();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }



    @When("el administrador ingresa el campo username valido pero password invalido y solicita login")
    public void elAdministradorIngresaElCampoUsernameValidoPeroPasswordInvalidoYSolicitaLogin() {
        try {
            dataConfigurationPasswordInvalid();
            login = new Login(driver, loginModel, TEN_SECONDS.getValue());
            login.fillLoginForm();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }
    @Then("el sistema deberá mostrar un mensaje evidenciando que los datos ingresados son invalidos")
    public void elSistemaDeberáMostrarUnMensajeEvidenciandoQueLosDatosIngresadosSonInvalidos() {
        try {
            Assertions.assertEquals(
                    expectedFailedLogin(),
                    login.isLoginFail(),
                    ASSERTION_EXCEPTION_MESSAGE_INVALID_CREDENTIALS
            );
            quitDriver();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }



    @When("el administrador ingresa el campo username invalido y password valido y solicita login")
    public void elAdministradorIngresaElCampoUsernameInvalidoYPasswordValidoYSolicitaLogin() {
        try {
            dataConfigurationUsernameInvalid();
            login = new Login(driver, loginModel, TEN_SECONDS.getValue());
            login.fillLoginForm();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }
    @Then("el sistema deberá mostrar un mensaje que indique que los datos ingresados son invalidos")
    public void elSistemaDeberáMostrarUnMensajeQueIndiqueQueLosDatosIngresadosSonInvalidos() {
        try {
            Assertions.assertEquals(
                    expectedFailedLogin(),
                    login.isLoginFail(),
                    ASSERTION_EXCEPTION_MESSAGE_INVALID_CREDENTIALS
            );
            quitDriver();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }


    @When("el administrador no ingresa el campo username ni el campo password y solicita login")
    public void elAdministradorNoIngresaElCampoUsernameYNiElCampoPasswordYSolicitaLogin() {
        try {
            dataConfigurationEmpty();
            login = new Login(driver, loginModel, TEN_SECONDS.getValue());
            login.fillLoginForm();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }
    @Then("el sistema deberá mostrar un mensaje evidenciando que los campos no deben estar vacios")
    public void elSistemaDeberáMostrarUnMensajeEvidenciandoQueLosCamposNoDebenEstarVacios() {
        try {
            Assertions.assertEquals(
                    expectedEmptyData(),
                    login.isLoginFail(),
                    ASSERTION_EXCEPTION_MESSAGE_EMPTY_CREDENTIALS
            );
            quitDriver();
        } catch (Exception exception){
            quitDriver();
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    private void dataConfigurationSuccess(){
        loginModel = new LoginModel();
        loginModel.setUsername("admin");
        loginModel.setPassword("admin123");
    }

    private void dataConfigurationUsernameInvalid(){
        loginModel = new LoginModel();
        loginModel.setUsername("admin123");
        loginModel.setPassword("admin123");
    }

    private void dataConfigurationPasswordInvalid(){
        loginModel = new LoginModel();
        loginModel.setUsername("admin");
        loginModel.setPassword("123");
    }

    private void dataConfigurationEmpty(){
        loginModel = new LoginModel();
        loginModel.setUsername("");
        loginModel.setPassword("");
    }

    private String expectedSuccessLogin(){
        return ASSERTION_TITLE_PAG;
    }

    private String expectedFailedLogin(){
        return ASSERTION_INVALID_DATA;
    }

    private String expectedEmptyData(){
        return ASSERTION_EMPTY_DATA;
    }


}
