# Challenge-Conversor-de-Monedas

## Descripción
Aplicación de consola desarrollada en Java que permite convertir entre diferentes monedas utilizando tasas de cambio en tiempo real mediante la API de ExchangeRate-API.

## Características
Conversión entre múltiples monedas:
- Dólar Estadounidense (USD)
- Peso Argentino (ARS)
- Real Brasileño (BRL)
- Peso Colombiano (COP)

**Funcionalidades principales:**
- Interfaz de consola intuitiva
- Tasas de cambio actualizadas en tiempo real
- Resultados con precisión de dos decimales
- Manejo robusto de errores y excepciones personalizadas
- Validación de entradas de usuario
- Uso de BigDecimal para cálculos precisos

## Requisitos
- Java 17 o superior
- Biblioteca Gson 2.10.1 o superior para procesamiento JSON
- API Key de ExchangeRate-API (variable de entorno)

## Estructura del Proyecto

```
Challenge-Conversor-de-Monedas/
├── src/
│   └── com/
│       └── alura/
│           └── conversor/
│               ├── Main.java
│               ├── MenuController.java
│               ├── exception/
│               │   ├── ApiConfigurationException.java
│               │   ├── HttpErrorException.java
│               │   └── InvalidAmountException.java
│               ├── model/
│               │   ├── ConversionPair.java
│               │   ├── Currency.java
│               │   └── CurrencyConversion.java
│               └── service/
│                   ├── ApiClientService.java
│                   └── HttpClientService.java
├── lib/
│   └── gson-2.13.1.jar
├── .gitignore
└── README.md
```

## Arquitectura
El proyecto sigue una arquitectura en capas con separación de responsabilidades:

### Capa de Modelo (model)
- **Currency**: Enum que define las monedas disponibles (USD, ARS, BRL, COP)
- **CurrencyConversion**: Clase que representa el resultado de una conversión con tasa y monto
- **ConversionPair**: Clase que encapsula un par de monedas para conversión

### Capa de Servicio (service)
- **ApiClientService**: Servicio principal que gestiona las peticiones a la API y deserializa respuestas JSON usando Gson
- **HttpClientService**: Cliente HTTP que encapsula las peticiones usando HttpClient de Java

### Capa de Controlador
- **MenuController**: Controlador que maneja la lógica del menú y la interacción con el usuario

### Capa de Excepciones (exception)
- **ApiConfigurationException**: Error de configuración de API Key
- **HttpErrorException**: Error en peticiones HTTP
- **InvalidAmountException**: Error de validación de montos

### Punto de Entrada
- **Main**: Clase principal que inicia la aplicación

## Tecnologías Utilizadas
- **Java 21**: Lenguaje de programación principal
- **Gson 2.13.1**: Biblioteca para parsear respuestas JSON de la API
- **HttpClient**: Cliente HTTP nativo de Java para realizar peticiones
- **BigDecimal**: Para cálculos monetarios precisos
- **ExchangeRate-API**: API externa para obtener tasas de cambio en tiempo real

## Configuración

### 1. Descargar Gson
Descarga el archivo JAR de Gson desde:
[Maven Central - Gson 2.10.1](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/)

### 2. Agregar Gson al Proyecto
Crea una carpeta `lib` en la raíz del proyecto y coloca el archivo `gson-2.10.1.jar` dentro.

### 3. Configurar en IntelliJ IDEA
1. File → Project Structure → Modules
2. Dependencies → + → JARs or directories
3. Seleccionar el archivo `gson-2.10.1.jar` de la carpeta `lib`
4. Apply → OK

### 4. Configurar API Key
Obtén tu API Key gratuita en [ExchangeRate-API](https://www.exchangerate-api.com/) y configúrala como variable de entorno en IntelliJ:
1. Run → Edit Configurations
2. Environment variables → Add
3. Name: `API_KEY` | Value: `tu_api_key_aqui`

## Cómo Usar

1. Clonar el repositorio:
```bash
git clone https://github.com/nicolassbon/Challenge-Conversor-de-Monedas.git
```

2. Descargar y configurar Gson (ver sección Configuración)
3. Configurar la variable de entorno `API_KEY`
4. Compilar el proyecto con Java 17+
5. Ejecutar la clase `Main`
6. Seleccionar la opción de conversión deseada del menú (1-6)
7. Ingresar el monto a convertir
8. Visualizar el resultado de la conversión
9. Repetir o seleccionar opción 7 para salir

## Manejo de Errores
La aplicación maneja los siguientes tipos de errores:
- **Entrada inválida**: Notifica al usuario cuando ingresa texto en lugar de números
- **Monto inválido**: Valida que el monto sea mayor a cero
- **API Key no configurada**: Detecta si la variable de entorno no está configurada
- **Errores de conexión**: Maneja problemas de red o respuestas HTTP erróneas
- **Errores inesperados**: Captura cualquier excepción no prevista

## API Utilizada
[ExchangeRate-API](https://www.exchangerate-api.com/) - Proveedor de tasas de cambio en tiempo real

**Endpoint usado**: `/v6/{API_KEY}/pair/{base_code}/{target_code}/{amount}`

## Autor
[@nicolassbon](https://github.com/nicolassbon)

**Nota:**
Este proyecto fue desarrollado como parte de la especialización Back-end del programa ONE - Oracle Next Education de Alura Latam.
