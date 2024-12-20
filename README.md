# Cryptik - Cryptocurrency Portfolio Tracker

A robust Spring Boot application for tracking and managing cryptocurrency portfolios with real-time market data integration.

## Features

- 🔐 User authentication and authorization with JWT
- 📊 Real-time cryptocurrency price tracking
- 💼 Multiple portfolio management
- 📈 Transaction tracking and portfolio performance analysis
- ⚡ Real-time price alerts
- 📱 RESTful API with rate limiting
- 📄 Pagination and efficient data fetching
- 🔄 Integration with external cryptocurrency APIs

## Technology Stack

- Java 21
- Spring Boot 3.x
- PostgreSQL
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- Lombok
- WebClient
- Bucket4j (Rate Limiting)
- Caffeine Cache

## Database Schema

```mermaid
erDiagram
    USERS ||--o{ PORTFOLIOS : owns
    USERS ||--o{ ALERTS : sets
    USERS {
        bigint user_id PK
        varchar email UK
        varchar password_hash
        timestamp created_at
        timestamp last_login
    }
    PORTFOLIOS ||--o{ TRANSACTIONS : contains
    PORTFOLIOS {
        bigint portfolio_id PK
        bigint user_id FK
        varchar name
        timestamp created_at
    }
    TRANSACTIONS {
        bigint transaction_id PK
        bigint portfolio_id FK
        bigint cryptocurrency_id FK
        decimal amount
        decimal price_usd
        timestamp transaction_date
        varchar transaction_type
    }
    CRYPTOCURRENCIES ||--o{ TRANSACTIONS : involves
    CRYPTOCURRENCIES ||--o{ PRICE_HISTORY : has
    CRYPTOCURRENCIES ||--o{ ALERTS : triggers
    CRYPTOCURRENCIES {
        bigint cryptocurrency_id PK
        varchar symbol UK
        varchar name
        decimal current_price_usd
        bigint market_cap_usd
        decimal volume_24h_usd
        timestamp last_updated
    }
    PRICE_HISTORY {
        bigint price_history_id PK
        bigint cryptocurrency_id FK
        decimal price_usd
        timestamp recorded_at
    }
    ALERTS {
        bigint alert_id PK
        bigint user_id FK
        bigint cryptocurrency_id FK
        decimal target_price
        varchar condition
        boolean is_triggered
        timestamp created_at
    }
```

### Entity Relationships:
- Users can have multiple portfolios and alerts
- Portfolios contain multiple transactions
- Transactions are linked to specific cryptocurrencies
- Cryptocurrencies have price history and can trigger alerts

## API Endpoints

### User Management
```
POST /api/users/register - Register a new user
POST /api/users/login - User login
GET /api/users/profile - Get user profile
PUT /api/users/profile - Update user profile
```

### Portfolio Management
```
POST /api/portfolios - Create a new portfolio
GET /api/portfolios - Get all portfolios (paginated)
GET /api/portfolios/{id} - Get specific portfolio
PUT /api/portfolios/{id} - Update portfolio
DELETE /api/portfolios/{id} - Delete portfolio
GET /api/portfolios/{id}/performance - Get portfolio performance metrics
```

### Transaction Management
```
POST /api/transactions - Add new transaction
GET /api/transactions - Get all transactions (paginated)
GET /api/transactions/{id} - Get specific transaction
PUT /api/transactions/{id} - Update transaction
DELETE /api/transactions/{id} - Delete transaction
```

### Cryptocurrency Data
```
GET /api/cryptocurrencies - Get all cryptocurrencies (paginated)
GET /api/cryptocurrencies/{id} - Get specific cryptocurrency
GET /api/cryptocurrencies/{id}/price-history - Get price history (paginated)
```

### Alert Management
```
POST /api/alerts - Create new price alert
GET /api/alerts - Get all alerts (paginated)
GET /api/alerts/{id} - Get specific alert
PUT /api/alerts/{id} - Update alert
DELETE /api/alerts/{id} - Delete alert
```

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven
- PostgreSQL
- Git

### Installation Steps

1. Clone the repository:
```bash
git clone https://github.com/yourusername/cryptik.git
cd cryptik
```

2. Configure PostgreSQL database:
```sql
CREATE DATABASE cryptik;
```

3. Update application.properties:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cryptik
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

4. Build the project:
```bash
mvn clean install
```

5. Run the application:
```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## Configuration

### Rate Limiting
Rate limiting is configured per endpoint:
- Authentication endpoints: 3 requests per minute
- API endpoints: 100 requests per minute per user

### External API Integration
The application integrates with CoinGecko API for real-time cryptocurrency data. Configure the API key in application.properties:
```properties
coingecko.api.key=your_api_key
coingecko.api.base-url=https://api.coingecko.com/api/v3
```

## Security

- JWT-based authentication
- Password encryption using BCrypt
- Rate limiting to prevent abuse
- Input validation on all endpoints
- CORS configuration for frontend integration

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
