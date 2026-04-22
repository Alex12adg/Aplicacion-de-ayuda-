# Aplicacion de Ayuda y Emergencias

Aplicacion de escritorio desarrollada en Java y JavaFX orientada a la asistencia personal en situaciones de emergencia. El sistema centraliza varios modulos de apoyo dentro de una misma interfaz grafica: emergencias manuales, alerta de peligro, deteccion por voz, monitor de salud, centros cercanos, historial medico y configuracion base.

El objetivo del proyecto es que el usuario pueda activar o supervisar funciones de seguridad desde la propia aplicacion, sin depender de la terminal como flujo principal de uso.

## Objetivo del proyecto

La aplicacion busca cubrir varios escenarios habituales dentro de un sistema de ayuda personal:

- Activar una emergencia manual indicando tipo, gravedad y ubicacion.
- Lanzar una alerta preventiva de peligro que puede escalar automaticamente.
- Simular una deteccion de emergencia por voz mediante palabra clave.
- Simular monitorizacion cardiaca con prealerta por ausencia de pulso.
- Consultar centros de emergencia cercanos.
- Gestionar informacion medica y contactos de emergencia.
- Preparar una escena de configuracion para futuras opciones del sistema.

## Tecnologias principales

- Java
- JavaFX con vistas FXML
- CSS para estilos de interfaz
- MariaDB en la capa de datos existente del proyecto

## Punto de entrada

La aplicacion arranca desde:

- `src/App/Main/Main.java`
- `src/GUI/AppGUI.java`

El arranque carga la vista de login y, tras la autenticacion, accede a la vista principal de la aplicacion.

## Modulos disponibles

### 1. Dashboard de inicio

La pantalla principal de inicio ya no es un placeholder. Ahora actua como panel de acceso rapido a los modulos principales.

Funciones principales:

- Bienvenida al usuario.
- Resumen visual del estado del panel.
- Tarjetas de acceso rapido a emergencia, peligro, salud, voz, centros, historial medico y configuracion.

Archivos relacionados:

- `src/GUI/Views/Home-view.fxml`
- `src/GUI/controllers/HomeController.java`
- `src/GUI/controllers/MainController.java`

### 2. Emergencia

Permite crear una alerta manual desde la interfaz grafica.

Funciones principales:

- Seleccion de tipo de emergencia.
- Nivel de gravedad configurable.
- Uso de ubicacion automatica o manual.
- Visualizacion del estado de la alerta.
- Listado de centros de emergencia cargados en la aplicacion.

Archivos relacionados:

- `src/GUI/Views/Emergency-view.fxml`
- `src/GUI/controllers/EmergencyController.java`
- `src/Resources/Emergency/EmergencyManager.java`
- `src/Resources/Emergency/EmergencyRequest.java`
- `src/Resources/Emergency/EmergencyProcessResult.java`

### 3. Alerta de peligro

Activa una alarma preventiva que bloquea la navegacion de la aplicacion y muestra una ventana modal centrada sobre la escena principal.

Comportamiento actual:

- Al iniciar la alerta, la aplicacion muestra un popup modal.
- Mientras la alerta esta activa no se puede cambiar a otras pestañas.
- El usuario solo dispone de un boton para terminar la alarma.
- Los intentos internos no se muestran en pantalla.
- Cada 3 segundos el sistema reduce automaticamente un intento.
- Cuando se agotan los intentos, la alerta escala a una emergencia real.

Archivos relacionados:

- `src/GUI/Views/Danger-view.fxml`
- `src/GUI/controllers/DangerController.java`
- `src/GUI/controllers/MainController.java`
- `src/Resources/Danger/DangerAlertSystem.java`
- `src/Resources/Danger/DangerAlertState.java`

### 4. Monitor de salud

Simula un monitor cardiaco dentro de la interfaz.

Funciones principales:

- Inicio y parada manual del monitor.
- Generacion de lecturas simuladas.
- Deteccion de lecturas consecutivas sin pulso.
- Activacion de prealerta cuando se alcanza el umbral configurado.
- Confirmacion manual del usuario desde la interfaz.

Archivos relacionados:

- `src/GUI/Views/health-view.fxml`
- `src/GUI/controllers/HealthController.java`
- `src/Resources/Heart/HeartRateMonitor.java`
- `src/Resources/Heart/HeartMonitorState.java`
- `src/Resources/Heart/HeartRateSensorSimulator.java`

### 5. Deteccion por voz

Simula el reconocimiento de una palabra clave desde la interfaz grafica.

Funciones principales:

- Configuracion de palabra clave.
- Inicio y parada del modo escucha.
- Introduccion manual de frases simuladas.
- Disparo de emergencia cuando la frase contiene la palabra clave.

Archivos relacionados:

- `src/GUI/Views/voice-view.fxml`
- `src/GUI/controllers/VoiceController.java`
- `src/Resources/Voice/VoiceDetector.java`
- `src/Resources/Voice/VoiceDetectionState.java`
- `src/Resources/Voice/VoiceConfig.java`

### 6. Centros cercanos

Muestra los centros de emergencia disponibles cargados desde recursos locales y permite filtrarlos por proximidad.

Funciones principales:

- Consulta de todos los centros cargados.
- Busqueda por radio en kilometros.
- Uso de coordenadas automaticas o manuales.
- Calculo de distancia aproximada a cada centro.

Archivos relacionados:

- `src/GUI/Views/Centers-view.fxml`
- `src/GUI/controllers/CentersController.java`
- `src/Resources/Location/FacilityLocator.java`
- `src/Resources/Location/Centers.json`

### 7. Historial medico y contactos

Gestiona informacion medica basica del usuario y contactos de emergencia asociados.

Funciones principales:

- Edicion de alergias, condiciones y medicacion.
- Alta de contactos de emergencia.
- Formulario de contacto oculto hasta que el usuario decide mostrarlo.
- Persistencia mediante la capa de servicios y acceso a datos.

Archivos relacionados:

- `src/GUI/Views/Medical-form-view.fxml`
- `src/GUI/controllers/MedicalFormController.java`
- `src/Services/MedicalService.java`

### 8. Configuracion

Escena base preparada para futuras opciones de personalizacion y comportamiento del sistema.

Funciones actuales:

- Pantalla funcional accesible desde el menu lateral.
- Espacio reservado para futuras preferencias visuales, notificaciones y parametros internos.

Archivos relacionados:

- `src/GUI/Views/Settings-view.fxml`
- `src/GUI/controllers/SettingsController.java`

## Interfaz actual

- Menu lateral integrado en la vista principal.
- Barra visual del scroll lateral oculta para mantener una navegacion mas limpia.
- Pantalla de login redisenada como tarjeta flotante sobre el fondo.
- El fondo del login ya ocupa toda la pantalla sin dejar un bloque blanco inferior.
- Espacio reservado para insertar una imagen superior en el formulario de acceso.
- Popup modal centrado para la alerta de peligro.
- Confirmacion modal antes de cerrar sesion.
- En historial medico, el alta de contactos queda oculta hasta que el usuario decide mostrar el formulario.
- El menu lateral ya no muestra el acceso muerto de perfil y la opcion de configuracion abre una escena base funcional.
- La portada ya funciona como dashboard con accesos rapidos a los modulos principales.
- Se ha aplicado una pasada de maquetacion para unificar el estilo visual entre login, registro, dashboard, historial medico y configuracion.
- Las escenas y transiciones principales ya se han ajustado a un formato movil vertical en lugar de una ventana horizontal.

## Estructura general del proyecto

```text
src/
  App/
    Main/
  GUI/
    Views/
    controllers/
    Styles/
  Resources/
    Danger/
    Emergency/
    Heart/
    Location/
    Voice/
    Database/
    Model/
    Session/
  Services/
Docs/
  README.md
```

## Flujo general de uso

1. El usuario inicia sesion.
2. Accede a la vista principal.
3. Puede entrar directamente a un modulo desde el dashboard o usar el menu lateral.
4. Cada modulo expone su funcionalidad directamente en la interfaz.
5. Los eventos relevantes pueden generar alertas, prealertas o consultas de datos segun el caso.

## Arquitectura resumida

La aplicacion sigue una organizacion basada en capas sencillas:

- `GUI/Views`: vistas FXML.
- `GUI/controllers`: controladores JavaFX.
- `Resources`: logica de dominio, simuladores, modelos y acceso a datos.
- `Services`: servicios de aplicacion que coordinan ciertas operaciones.

En el estado actual del proyecto se ha migrado gran parte de la interaccion de consola a flujos visibles dentro de la interfaz grafica.

## Requisitos para ejecutar

Requisitos generales:

- JDK compatible con JavaFX
- Librerias JavaFX configuradas en el entorno
- Dependencias del proyecto disponibles en el IDE o en el sistema de compilacion utilizado
- Acceso a la base de datos si se quiere usar la parte persistente completa

El proyecto se ha trabajado en IntelliJ IDEA con estructura basada en `src` y recursos locales.

## Recursos de datos

El proyecto incluye recursos locales relevantes, por ejemplo:

- `src/Resources/Location/Centers.json` para centros de emergencia.
- Archivos de log generados por algunas operaciones.
- Clases de acceso a base de datos para usuarios, contactos, emergencias e informacion medica.

## Estado actual

Actualmente la aplicacion dispone de:

- Vista principal con menu lateral y dashboard de inicio.
- Modulos de emergencia, peligro, salud, voz y centros integrados en UI.
- Popup modal global para la alerta de peligro.
- Flujo de historial medico con formulario plegable para alta de contactos.
- Escena base de configuracion accesible desde el menu.

Pendientes habituales de evolucion:

- Compilacion y empaquetado automatizados con una herramienta de build unificada.
- Integrar sensores o servicios externos reales en lugar de simulaciones.
- Añadir pruebas automatizadas de interfaz y logica.
- Profundizar la escena de configuracion con opciones persistentes.

## Autor

Proyecto de final de grado de DAM de Alejandro Mora.
