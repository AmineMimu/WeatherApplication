# WeatherApplication
Weather application on Android Studio for school

# To use the app:
  - Need to clone the project "git clone https://github.com/AmineMimu/WeatherApplication.git"
  - Run the project localy on an emulator or on your android device
  - buildToolsVersion "26.0.1"
  - Home page : where find the added cities or blank activity if no city added yet
    - Can go to the addCity Activity by clicking on the button "Add City"
  - Page Add City : where to add new city using 2 correct IDs (City & Country)
    - Use the button "Enregistrer" to save the added City.
  - need to go back to the Home page to show details about the added City
    - Need to click on a city to show more details about it
    
# Part 02 - Adding a database:
  - When cities added --> saved to the DB
  - When deleting cities --> from the DB
  - When executing the app --> fetch cities name saved on the DB, then the API takes 2s to fetch other data from Yahoo server
  - Refresh function : working --> executing the request of synchronization data from Yahoo API with an Asynk task.
  
