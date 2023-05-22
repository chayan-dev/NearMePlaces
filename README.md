# NearMePlaces

## Build and run the app:
- Open terminal in the directory you want to clone the repo and run command : git clone https://github.com/chayan-dev/NearMePlaces.git
- Open the the cloned directory using Android studio.
- Navigate to local.properties and add a new field to add your api key: GOOGLE_MAPS_API_KEY= **api-key**
- Build and run the app.
- Enable location from settings/notification panel in the device.

## Walkthrough video:  [VIDEO](https://drive.google.com/file/d/1chytYZspP5VlJgVXqdC_mlllw19cZGHl/view?usp=share_link)

## Features:
- Locate your location in the map.
- See nearby point of interests within a default radius of 5km.
- Click on a point of interest to see it's name, address, rating in a bottom-sheet.
- Click "Navigate" button in bottom-sheet to get directions in Google Maps to the point of interest from your location.
- Filter point of interests from bottom navigation bar based on restaurant, atm, hospital, school.

## Design:
- MVVM architechture with retrofit and coroutines
- Google Maps SDK and Places API

## APK:  [Download](https://drive.google.com/file/d/1KhKsGZ-gVUmkbLVK-bgTpFfQoNLZCXRD/view?usp=share_link)
