📚 Book Library App (Kotlin Multiplatform)

This is a Kotlin Multiplatform (KMP) project targeting Android and iOS, built using Compose Multiplatform (CMP) for shared UI.

The app allows users to explore books, view details, search for books, and manage favorites seamlessly across platforms.

🚀 Features
📖 Book Listing – View a list of available books
🔍 Search Books – Search books by title or keyword
📘 Book Details – View detailed information about each book
❤️ Favorites – Add/remove books from favorites
🔄 Cross-platform UI using Compose Multiplatform
🧱 Tech Stack
Kotlin Multiplatform (KMP)
Compose Multiplatform (CMP) – Shared UI
Ktor – Network layer
Koin – Dependency Injection
Room – Local database (for caching & favorites)
Coil – Image loading
Architecture:
MVVM / MVI hybrid
Clean Architecture (Domain, Data, Presentation layers)
📂 Project Structure
/composeApp

Contains shared code for all platforms.

commonMain
→ Shared business logic, UI, and core functionality
androidMain
→ Android-specific implementations
iosMain
→ iOS-specific implementations (e.g., platform APIs like CoreCrypto if needed)
jvmMain (if used)
→ Desktop-specific code
/iosApp
Entry point for the iOS application
Can include SwiftUI code if needed
Responsible for launching the shared Compose UI
🏗️ Architecture Overview

The project follows Clean Architecture principles:

Presentation Layer
Compose UI
ViewModels (MVVM/MVI)
Domain Layer
Use cases
Business logic
Data Layer
Repository pattern
Remote (Ktor API)
Local (Room DB)
▶️ How to Run
🟢 Android

Run using IDE or terminal:

macOS/Linux

./gradlew :composeApp:assembleDebug

Windows

.\gradlew.bat :composeApp:assembleDebug
🍎 iOS
Open /iosApp in Xcode
Select a simulator/device
Click Run
🔧 Key Functional Modules
Book Module
Fetch book list from API
Display in UI
Search Module
Real-time or query-based search
API integration using Ktor
Favorites Module
Save/remove favorites locally using Room
Persist user selections
📌 Future Improvements (Optional)
Pagination for large book lists
Offline-first support
Dark mode support
Unit & UI testing
Push notifications
📖 Learn More
Kotlin Multiplatform documentation
Compose Multiplatform guides
Ktor & Koin official docs
