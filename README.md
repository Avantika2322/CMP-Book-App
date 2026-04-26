# 📚 Book Library App (Kotlin Multiplatform)

This is a **Kotlin Multiplatform (KMP)** project targeting **Android** and **iOS**, built using **Compose Multiplatform (CMP)** for shared UI.

The app allows users to explore books, view details, search for books, and manage favorites seamlessly across platforms.

---

## 🚀 Features

- 📖 Book Listing – View a list of available books  
- 🔍 Search Books – Search books by title or keyword  
- 📘 Book Details – View detailed information about each book  
- ❤️ Favorites – Add/remove books from favorites  
- 🔄 Cross-platform UI using Compose Multiplatform  

---

## 🧱 Tech Stack

- Kotlin Multiplatform (KMP)  
- Compose Multiplatform (CMP) – Shared UI  
- Ktor – Network layer  
- Koin – Dependency Injection  
- Room – Local database (for caching & favorites)  
- Coil – Image loading  

### Architecture
- MVVM / MVI  
- Clean Architecture (Domain, Data, Presentation)

---

## 📂 Project Structure

### /composeApp

Contains shared code for all platforms.

- commonMain  
  Shared business logic, UI, and core functionality  

- androidMain  
  Android-specific implementations  

- iosMain  
  iOS-specific implementations  

- jvmMain (optional)  
  Desktop-specific code  

---

### /iosApp

- Entry point for the iOS application  
- Can include SwiftUI code  
- Launches shared Compose UI  

---

## 🏗️ Architecture Overview

This project follows **Clean Architecture**:

### Presentation Layer
- Compose UI  
- ViewModels (MVVM/MVI)

### Domain Layer
- Use cases  
- Business logic  

### Data Layer
- Repository pattern  
- Remote (Ktor API)  
- Local (Room DB)  

---

## ▶️ How to Run

### Android

Run using IDE or terminal:

#### macOS/Linux
```bash
./gradlew :composeApp:assembleDebug
```

#### Windows
```bash
.\gradlew.bat :composeApp:assembleDebug
```

---

### iOS

- Open `/iosApp` in Xcode  
- Select a simulator or device  
- Click Run  

---

## 🔧 Key Modules

### Book Module
- Fetch book list from API  
- Display in UI  

### Search Module
- Search books using API  
- Built with Ktor  

### Favorites Module
- Save/remove favorites locally  
- Uses Room database  

---

## 📌 Future Improvements

- Pagination support  
- Offline-first capability  
- Dark mode  
- Unit & UI testing  
- Push notifications  

---

## 📖 Learn More

- Kotlin Multiplatform  
- Compose Multiplatform  
- Ktor  
- Koin  
