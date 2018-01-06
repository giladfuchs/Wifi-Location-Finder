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

GUI:
Graphical interface for the user to choose, user can choose to read Csv file from directory contained Wigle-Wifi files or Csv file that has been filtered into Data-Base. Afterwards user can filter the Data-Base according to Time/Name/Location and save it as Csv File or KML file.
This filter has 2 option, user can AND or OR filter with filter and fix the duplication lists, also there is option for NOT filter. 
In addition, user can run Algorithms, in Algorithm 1 user can enter a MAC address and get the revaluated location of this MAC. In Algorithm 2 user has 2 options, option 1 - choose a file and write a row number then this Algorithm will take the row number user enter from the file and user will get the revaluated location. option 2 - enter 3 MAC addresses and 3 signals and user will get the revaluated location. 
This interface contains also information field which describes the filter characteristics the user entered, the amount of lists and the amount of MAC in the Data-Base. There is also an Undo button which allows the user return 1 step backward in the filters he make.
User can also delete the Data-Base and exit from button.
