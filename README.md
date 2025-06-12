# Fitness Microservices Project

A cloud-native fitness tracking application built with a microservices architecture using Spring Boot, React, and Vite.

## Project Structure

```
fitness-microservices/
│
├── activityservice/         # Microservice for activity tracking (Spring Boot, MongoDB)
├── aiservice/               # AI microservice for fitness recommendations (Spring Boot)
├── configserver/            # Centralized configuration server (Spring Cloud Config)
├── eureka/                  # Service discovery (Netflix Eureka)
├── gateway/                 # API Gateway (Spring Cloud Gateway)
├── userservice/             # User management microservice (Spring Boot, JPA)
└── fitness-app-frontend/    # Frontend (React + Vite)
```

## Technologies Used

- **Backend:** Java 17, Spring Boot, Spring Cloud, Netflix Eureka, MongoDB, JPA
- **Frontend:** React 19, Vite
- **DevOps:** Maven, Docker (optional for deployment)
- **Other:** Spring Cloud Config, Spring Cloud Gateway

## Getting Started

### Prerequisites

- Java 17+
- Node.js (for frontend)
- Maven
- MongoDB (for activityservice)
- Docker (optional, for containerization)

### Running the Microservices

1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/fitness-microservices.git
   cd fitness-microservices
   ```

2. **Start Config Server:**
   ```sh
   cd configserver
   ./mvnw spring-boot:run
   ```

3. **Start Eureka Server:**
   ```sh
   cd ../eureka
   ./mvnw spring-boot:run
   ```

4. **Start Other Services:**
   - In separate terminals, run the following for each service:
     ```sh
     cd ../activityservice
     ./mvnw spring-boot:run

     cd ../aiservice
     ./mvnw spring-boot:run

     cd ../userservice
     ./mvnw spring-boot:run

     cd ../gateway
     ./mvnw spring-boot:run
     ```

5. **Start the Frontend:**
   ```sh
   cd ../fitness-app-frontend
   npm install
   npm run dev
   ```

6. **Access the Application:**
   - Frontend: [http://localhost:5173](http://localhost:5173) (default Vite port)
   - Eureka Dashboard: [http://localhost:8761](http://localhost:8761)
   - API Gateway: [http://localhost:8080](http://localhost:8080) (default)

## Development

- Each microservice is a standalone Spring Boot application.
- The frontend is a React app bootstrapped with Vite.
- Configuration is managed centrally via the config server.
- Service discovery is handled by Eureka.
- API Gateway routes requests to the appropriate microservice.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a pull request

## License

This project is licensed under the MIT License.

---

**Note:** Update service ports and URLs as per your configuration. For production, consider using Docker Compose or Kubernetes for orchestration.
