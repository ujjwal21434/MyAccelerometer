Accelerometer Data Logger Android App

Description:

The Accelerometer Data Logger Android App records and displays real-time data from the device's accelerometer sensor. It allows users to view the recorded data in graphical form to analyze the movement patterns over time.


How to Use:

Launch the app on your Android device.
The app automatically starts recording accelerometer data.
The X, Y, and Z-axis readings are displayed in real-time on the main screen.
Click on the "View History" button to see the historical data in graphical form.
In the history view, graphs for each axis (X, Y, Z) display the recorded data over time.


Implementation Overview:

The app is developed using Kotlin programming language.
It utilizes the device's accelerometer sensor to record data.
Data is stored locally in an SQLite database using the DBHelper class.
The main activity continuously listens to sensor events and updates the UI with real-time readings.
The HistoryActivity retrieves data from the database and displays it using GraphView library.


Files Overview:

MainActivity.kt: Contains the main implementation of the app, including sensor setup, data recording, and UI updates.
HistoryActivity.kt: Displays historical accelerometer data using graphs for each axis.
DBHelper.kt: Manages SQLite database operations for storing and retrieving accelerometer data.


Dependencies:

GraphView Library: Used to display accelerometer data in graphical form.
AndroidX Appcompat Library


Additional Notes:

The app currently records accelerometer data continuously. Adjustments can be made to control the recording frequency or to start/stop recording manually.
Ensure proper permissions are granted for accessing the device's accelerometer sensor.
