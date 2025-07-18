# Projeto Agregador de Investimentos
Repositório para meu projeto Springboot

## Diagrama de classes

```mermaid
erDiagram
    user ||--o{ account : "1"
    account ||--o{ billing_address : "1"
    account ||--o{ account_stock : "1"
    stock ||--o{ account_stock : "1"

    user {
        int user_id PK
        string username
        string email
        string password
    }

    account {
        int account_id PK
        string description
    }

    billing_address {
        int account_id PK
        string street
        string number
    }

    stock {
        int stock_id PK
        string description
        string ticker
    }

    account_stock {
        int account_id PK
        int stock_id PK
    }