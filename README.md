# GraphQL Example – Android (Jetpack Compose + Ktor)

This project is a **simple, clean Android sample** demonstrating how to consume a **GraphQL API** using **Ktor** in a **Jetpack Compose** application, following **Clean Architecture** and **MVVM** principles.

The goal of this repository is to show **idiomatic GraphQL handling** in Android without REST-style abstractions like fake HTTP status codes or generic response wrappers.

---

## ✨ Features

* ✅ GraphQL API integration using **Ktor**
* ✅ Jetpack Compose UI
* ✅ Clean Architecture (Data → Domain → UI)
* ✅ MVVM with unidirectional data flow
* ✅ Kotlin Coroutines
* ✅ Koin for dependency injection
* ✅ Proper GraphQL error handling (`data` vs `errors`)
* ✅ No Retrofit, no REST-style `CommonResponse`

---

## 🔗 API Used (Free & Public)

**Countries GraphQL API**

```
https://countries.trevorblades.com/graphql
```

Sample query:

```graphql
query {
  countries {
    code
    name
    capital
  }
}
```

This API is:

* Completely free
* No authentication required
* Ideal for demos and learning

---

## 🏗️ Architecture Overview

```
UI (Jetpack Compose)
   ↓
ViewModel (State + Events)
   ↓
Repository
   ↓
GraphQL API (Ktor Client)
```

### Key Design Decisions

* GraphQL responses are handled via `GraphQLResponse<T>`
* Business logic uses a sealed
