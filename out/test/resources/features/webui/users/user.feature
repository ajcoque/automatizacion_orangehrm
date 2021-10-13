# new feature
# Tags: optional

Feature: Como empleado y con rol administrativo necesito buscar un usuario en el aplicativo web
          haciendo uso de los filtros de busqueda permitidos en el sistema.

  Scenario: Buscar usuario registrado en el sistema mediante el Username
    Given el administrador del sistema se encuentra en la pagina de users
    When el administrador ingresa el username del usuario existente que desea buscar y presiona en el boton Search
    Then el sistema debera mostrar los datos del usuario consultado
