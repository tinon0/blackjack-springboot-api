# API en Spring Boot: Blackjack

## 🎯 Objetivo del Proyecto

Desarrollar una **API REST** en **Spring Boot** que permita gestionar un juego de **Blackjack**. La API proporciona los endpoints necesarios para iniciar una partida, pedir o aguantar turno.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje de Programación**: Java
- **Framework**: Spring Boot
- **Base de Datos**: H2 (para almacenamiento en memoria, puede ser reemplazada por una base de datos persistente)
- **Gestión de Dependencias**: Maven
- **Formatos de Datos**: JSON

## 📂 Estructura del Proyecto

- **Controladores**: Manejan las solicitudes HTTP y dirigen la lógica del juego.
- **Servicios**: Implementan la lógica del negocio del Blackjack.
- **Modelos**: Representan las entidades del juego.
- **Repositorios**: Interactúan con la base de datos para almacenar el estado del juego.
- **Configuración**: Configuraciones de Spring Boot y dependencias.

## 🔄 Funcionamiento

### Endpoints:

1. **Crear Match**
   - **Endpoint**: `GET /blackjack/matches/crearMatch`
   - **Descripción**: Inicia una nueva partida de Blackjack y retorna ya la primera jugada.

2. **Realizar Jugada**
   - **Endpoint**: `POST /blackjack/matches/{idMatch}/round/{idRound}/play`
   - **Descripción**: Permite a un jugador realizar una jugada (Hit) durante una partida activa elegida.

3. **Aguantar turno**
   - **Endpoint**: `POST /blackjack/matches/{idMatch}/round/{idRound}/hold`
   - **Descripción**: Al usarlo, aguantas tu turno y la CPU sigue hasta dar con el ganador.

## 📝 Cómo Usar

1. **Iniciar una Nueva Partida**: Envía una solicitud `GET` al endpoint `/blackjack/matches/crearMatch` para comenzar una nueva partida. La respuesta incluirá ya tu mano con cartas.

2. **Pedir carta**: Con la partida guardada y seteando su ID (comienza de 1 en 1), realiza la jugada enviando al `POST` del endpoint `/blackjack/matches/{idMatch}/round/{idRound}/play`.

3. **Aguantar**: Usar el endpoint `/blackjack/matches/{idMatch}/round/{idRound}/hold` para aguantar tu turno. Esto hace que la CPU pida cartas hasta no llegar a su limite. Despues de ello, muestra el ganador.
---
# API in Spring Boot: Blackjack

## 🎯 Project Objective

Develop a **REST** API on **Spring Boot** to manage a game of **Blackjack**. The API provides the necessary endpoints to start a game, ask or hold turn.

## 🛠️ Technologies Used

- **Programming language**: Java
- **Framework**: Spring Boot
- **Database**: H2 (for in-memory storage, can be replaced by a persistent database)
- **Dependency Management**: Maven
- **Data formats**: JSON

## 📂 Project Structure

- **Controllers**: Handle HTTP requests and direct game logic.
- **Services**: Implement the business logic of Blackjack.
- **Models**: Represent game entities.
- **Repositories**: They interact with the database to store the game status.
- **Configuration**: Spring Boot configurations and dependencies.

## 🔄 Operation

### Endpoints:

1. **Create Match**
   - **Endpoint**: ‘GET /blackjack/matches/crearMatch’
   - **Description**: Starts a new game of Blackjack and returns the first move.

2. **Hit**
   - **Endpoint** : ‘POST /blackjack/matches/{idMatch}/round/{idRound}/play’
   - **Description**: Allows a player to make a move (Hit) during an active game of their choice.

3. **Hold**
   - **Endpoint** : ‘POST /blackjack/matches/{idMatch}/round/{idRound}/hold’
   - **Description**: When you use it, you hold your turn and the CPU goes on until you get the winner.

## 📝 How to use

1. **Start a New Game**: Send a `GET` request to the endpoint `blackjack/matches/crearMatch. The answer will include your hand with cards.

2. **Draw card**: With the game saved and setting your ID (starts 1 on 1), make the move by sending to the endpoint’s POST `/blackjack/matches/{idMatch}/round/{idRound}/play`.

3. **Hold**: Use endpoint `/blackjack/matches/{idMatch}/round/{idRound}/hold`to hold your turn. This causes the CPU to call cards until it reaches its limit. After that, it shows the winner.
