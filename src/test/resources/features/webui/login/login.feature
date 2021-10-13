# new feature
# Tags: optional

Feature: Como empleado y con rol administrativo necesito ingresar al sistema con el fin de
          administrar los usuarios del aplicativo web.

  Background:
    Given el administrador del sistema se encuentra en la pagina de inicio de sesion

  Scenario: Inicio de sesion exitoso
    When el administrador ingresa los campos username y password obligatorios y solicita login
    Then el sistema debera rederigir a la pagina dashboard del aplicativo web

  Scenario: Inicio de sesión donde el password es inválido
    When el administrador ingresa el campo username valido pero password invalido y solicita login
    Then el sistema deberá mostrar un mensaje evidenciando que los datos ingresados son invalidos

  Scenario: Inicio de sesión donde el usuario es inválido
    When el administrador ingresa el campo username invalido y password valido y solicita login
    Then el sistema deberá mostrar un mensaje que indique que los datos ingresados son invalidos

  Scenario: Inicio de sesión donde no se introducen credenciales
    When el administrador no ingresa el campo username ni el campo password y solicita login
    Then el sistema deberá mostrar un mensaje evidenciando que los campos no deben estar vacios


