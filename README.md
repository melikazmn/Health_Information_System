# Health Information System

## Overview

The Health Information System is a Java-based application designed to manage and streamline the interactions between patients, doctors, nurses, and administrators in a healthcare environment. The system utilizes Object-Oriented Programming (OOP) principles to provide a structured and efficient way to handle user data and healthcare operations.

## Features

User Management: Administrators can add, delete, and manage users (Doctors, Nurses, and Patients).
Login System: Secure login for different user roles with password protection.
Patient Management: Doctors can pick patients, view their information, write prescriptions, and discharge them.
Nurse Operations: Nurses can check patient states, get prescriptions, and manage patients' check-ins.
Admin Functions: Admins have access to all users, can change passwords, and manage healthcare professionals.
File Handling: Configuration settings are read from an external file (config.txt) for flexibility.

## Requirements

- Java Development Kit (JDK) 8 or higher
- An IDE (e.g., IntelliJ IDEA, Eclipse) or command-line tools to compile and run the program

## Configuration

The application reads from config.txt located in the specified path. Ensure this file exists and contains the necessary data for doctors and patients.
The format for each line in config.txt is as follows:
- Doctor fields (even lines)
- Patient fields (odd lines)

## Usage

Upon running the application, you will be greeted with a welcome message and prompted to log in or exit.
Choose the appropriate user role based on your credentials (Admin, Doctor, Nurse, Patient).
Follow the menu prompts to perform various operations according to your role.

## User Roles

- Admin: Can manage all users and oversee the healthcare operations.
- Doctor: Responsible for patient care and management.
- Nurse: Assists in patient care and coordination between doctors and patients.
- Patient: Can check in/out and view their personal information.

## Contributions

Contributions are welcome! Please feel free to submit a pull request or open an issue for discussion.
