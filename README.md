<H1>CG-Challenge</H1>

<H2>Overall Goal</H2>

Develop a mobile application that consumes the CampGladiator API to display CampGladiator locations on the map. 
The app may be written for iOS or Android (or both), and ideally should be written in Swift or Kotlin. 
Please keep in mind the user experience and best practices for your respective platform.


<H2>Requirements</H2>

<p>
1. The app should launch to a map view, initially focused on the current location of the user.<br>
2. Data should be fetched from the CampGladiator API to fetch locations near the user’s current position.<br>
3. Display each location returned on the map based on its placeLatitude and placeLongitude, and display the placeName for each pin.<br>
4. A search bar should allow users to search (utilizing the Google Places API or a geocoding service of your choice) a different area on the map, which should trigger a CampGladiator locations API search at that location.<br>
</p>

<H2>Things to note</H2>
<p>
● Clone Repo and use Android Studio to build. Note this sample App will only work on Android Devices 7.0+ <br>
● Make sure Map Api Support is enabled for the project. Visit here: https://developers.google.com/maps/documentation/android-sdk/start <br>

Choose one of the following ways to get your API key from Android Studio:<br>

<Strong>The fast, easy way:</Strong><br>
Use the link provided in the <Strong>google_maps_api.xml</Strong> file that Android Studio created for you:<br>
Copy the link provided in the <Strong>google_maps_api.xml</Strong> file and paste it into your browser. 
<br>The link takes you to the Google Cloud Platform Console and supplies the required information to the Google Cloud Platform Console via URL parameters, thus reducing the manual input required from you.
<br>Follow the instructions to create a new project on the Google Cloud Platform Console or select an existing project.
Create an Android-restricted API key for your project.
Copy the resulting API key, go back to Android Studio, and paste the API key into the <string> element in the google_maps_api.xml file.

<Strong>A slightly less fast way:</Strong> <br>
Use the credentials provided in the <Strong>google_maps_api.xml</Strong> file that Android Studio created for you:
<br>Copy the credentials provided in the <Strong>google_maps_api.xml</Strong> file.
Go to the Google Cloud Platform Console in your browser.
Use the copied credentials to add your app to an existing API key or to create a new API key.
The full process for getting an API key: If neither of the above options works for your situation, see https://developers.google.com/maps/documentation/android-sdk/get-api-key
<br>
<br>
● Make sure Places Api is enabled for the project <br>Visit Here: https://console.cloud.google.com/apis/library/places-backend.googleapis.com?project=camp-gladiator-challenge&folder&organizationId <br>
● Enable billing on your project, this is required for Places lib to work. Otherwise searches will not work. <br>
● If you want to include crashlytics Go to Firebase.com and follow prompts to add google-services.json file to the project.
  <br> Please note that you must use a Pay as you go plan or preimum plan with Firebase otherwise it will disable the Places Api. 
  

</p>

