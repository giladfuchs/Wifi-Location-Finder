OOP Project

The project takes Csv file produced by Wigle-Wifi, filterd the data and export to Kml file.
System:

Convert: 
In this part we read a Csv-file (export from App) and filterd it according to same time and loction of any wifi
and take the 10 with the strongest signal, eventually we export all the wifi's to a new csv file in our format.

Filter:
We take the file we produced in "Convert" and let the user to choose how he want to filter by location,ID,time.
Then, we check duplicate mac and take only the one with the strongest signal and remove the others.
Afterwards, we create a Kml file with all the rest of spots after the filter.

Main:
Put the path for the directory that contains Wigle-Wifi's files and the path for csv file you want to create.
Run "Convert".
Put the path for the Csv file (from "Convert"), the path for csv file you want to create
and the path for a kml you want to create.
Run "Filter".
The program ask you how you want to filter it, finaily it's create the kml file and csv file.
You can use Google-Earth to see the kml.
