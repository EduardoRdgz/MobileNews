# MobileNews

## About

MobileNews is an Android application that fetches and displays news articles from an API (https://hn.algolia.com/api/v1). It uses modern Android development practices, including MVVM architecture, Jetpack libraries, data persistance and coroutines.

## Features

* Fetches news articles from an API.
* Displays news articles in a RecyclerView.
* Allows users to delete news articles.
* Handles network connectivity.
* Persists deleted articles to avoid re-fetching them.

## Getting Started

### Prerequisites

* Android Studio installed.
* An Android device or emulator (8.1 or higher).

### Building and Running

1. Clone the repository: `git clone <repository_url>`
2. Open the project in Android Studio.
3. Build and run the app on your device or emulator.

## Dependencies

* Jetpack libraries (e.g., ViewModel, LiveData, Room)
* Retrofit for API communication
* Coroutines for asynchronous operations
* Dagger Hilt for dependencies injection

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern.

* **Model:** Represents the data (news articles).
* **View:** Displays the data to the user (Activity/Fragment).
* **ViewModel:** Manages the data and UI logic.

