# RCOEM Club Finder - Android App

![Club Finder Logo](https://i.imgur.com/g8S5DAp.jpeg)

Welcome to the official repository for the RCOEM Club Finder, a native Android application designed to enhance student engagement at Shri Ramdeobaba College of Engineering and Management, Nagpur. This app serves as a central hub for students to discover clubs, stay updated on campus events, and connect with the college community in real-time.

---

## ✨ Features

This app is packed with features designed for both regular students and club administrators:

* **📱 Modern & Intuitive UI:** A clean, visually appealing interface with both light and dark modes.
* **🔍 Club Directory:** A comprehensive and searchable list of all official RCOEM clubs with detailed descriptions, faculty coordinator information, and contact details.
* **📢 Real-Time Announcements:** A live feed where club admins can post announcements for interviews, meetings, and other events. The feed updates instantly for all users.
* **💬 Anonymous Public Chat:** An open chat room where all students can communicate and discuss topics anonymously. The chat features real-time messaging and the ability for users to delete their own messages.
* **🔐 Secure Admin Panel:** A password-protected section for authorized club members to manage and post announcements.
* **🎨 Dynamic Theming:** The app features a dynamic color system, with club detail pages adapting to the color of the specific club's category.
* **⚙️ User-Friendly Navigation:** A clean slide-out navigation drawer and a bottom navigation bar for easy access to all features.

---

## 🛠️ Tech Stack & Architecture

This project is built using modern Android development tools and practices.

* **Language:** [Kotlin](https://kotlinlang.org/)
* **IDE:** [Android Studio](https://developer.android.com/studio)
* **Architecture:** Single-Activity, multiple Fragments
* **UI Toolkit:** XML with Material Design 3 components
* **Backend & Database:** [Google Firebase](https://firebase.google.com/)
    * **Cloud Firestore:** For real-time data storage (Announcements, Chat Messages).
    * **Firebase Authentication:** For both standard Email/Password (Admin) and Anonymous (Chat) sign-in.

---

## 📸 Screenshots

| Light Mode                                           | Dark Mode                                            | Club Details                                             |
| :--------------------------------------------------- | :--------------------------------------------------- | :------------------------------------------------------- |
| ![Light Mode Screenshot](https://i.imgur.com/Y1g4v6n.jpeg) | ![Dark Mode Screenshot](https://i.imgur.com/j1fF2eL.jpeg) | ![Club Details Screenshot](https://i.imgur.com/O6L7h8p.jpeg) |

| Admin Login                                          | Admin Panel                                            | Anonymous Chat                                         |
| :--------------------------------------------------- | :----------------------------------------------------- | :----------------------------------------------------- |
| ![Admin Login Screenshot](https://i.imgur.com/p9f7g8h.jpeg) | ![Admin Panel Screenshot](https://i.imgur.com/k9h8g7i.jpeg) | ![Chat Screenshot](https://i.imgur.com/f7h6g5j.jpeg)       |

---

## 🚀 Setup and Installation

To get this project running on your own machine, follow these steps:

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Pranav0931/rcoem-club-finder-app.git](https://github.com/Pranav0931/rcoem-club-finder-app.git)
    ```

2.  **Open in Android Studio:** Open the cloned folder in the latest version of Android Studio.

3.  **Set up Firebase:**
    * Go to the [Firebase Console](https://console.firebase.google.com/) and create a new project.
    * Add an Android app to your Firebase project with the package name `com.hdaf.clubfinder`.
    * Follow the setup instructions to download the `google-services.json` file.
    * **IMPORTANT:** Place the downloaded `google-services.json` file in the `app/` directory of the project. This file is not included in the repository for security reasons.

4.  **Enable Firebase Services:**
    * In the Firebase console, go to **Authentication** and enable the **Email/Password** and **Anonymous** sign-in providers.
    * Go to **Cloud Firestore**, create a database, and start it in **test mode**.

5.  **Build and Run:** Sync the project with Gradle files and run the app on an emulator or a physical device.

---

## 🤝 Acknowledgements

This project was developed in a collaborative effort. Thanks for the great ideas and continuous feedback that shaped this application!
