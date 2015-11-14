# Supplementary Requirements

**Author**: Team 30

## Purpose

This document lists requirements that are not easily captured in the use case model.  This document paired with the use case model show the complete requirements of the system.

## Performance

* The application is capable of processing one request at a time.  
* The application will run as long as there is power available and the Android OS has enough resources to provide to it.
* Transactions will complete within 5 minutes.

## Environment

* The application runs on a smartphone or tablet running Android version 4.4 (KitKat) or greater with minimum screen resolution of 1280Lx720W. All screens are locked for portrait orientation-mode.
* The Android device will need a camera to support QR code reading.
* Data stored by the application will reside locally in an SQLite database on the Android system.

## Usability

* The application will be easy to learn and operate by any Smoothie Cart vendor.
* A user manual will be provided to the end-user.

## Utility Software

* The customer will provide Android utility software that provides (1) credit-card scanning, (2) payment processing, (3) email management, and (4) QR code scanning capabilities

## Providers

* All QR cards are to be created and distributed by a third-party.

## Constraints

* The application only tracks payments performed using a credit card, or those covered by existing credits.  No cash payments are allowed.
* The email provider will handle all email functionalities and error handling.
* Data storage capacity is limited to what is available from the Android device.
* Purchases are limited to 99 smoothies of each type per order
* Customers must provide an email address to be added to the system.
