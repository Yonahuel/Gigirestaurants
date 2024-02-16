# Gigirestaurants App

Gigirestaurants App es una aplicación que permite visualizar y guardar restaurantes favoritos utilizando el API público de TripAdvisor.

## Características

- Lista de restaurantes cercanos a la ubicación del usuario.
- Búsqueda personalizada de restaurantes.
- Marcado de restaurantes como favoritos.
- Visualización de detalles de restaurantes.
- Listo para producción.

## Tecnologías utilizadas

- Kotlin
- Android Jetpack (ViewModel, LiveData, Room)
- Kotlin Coroutines
- Ktor para llamadas a la API de TripAdvisor
- Mockito y Mockk para pruebas
- Compose para las vistas
- Room para almacenamiento local
- Flow 

## Estructura del proyecto

- La app usa la aquitectura MVVM

## Configuración

1. Clona el repositorio
2. Abre el proyecto en Android Studio.
3. En el archivo `local.properties`, asegúrate de cambiar la clave API por tu propia clave proporcionada por TripAdvisor