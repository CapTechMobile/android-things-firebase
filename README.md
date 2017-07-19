# android-things-firebase
A sample project that creates a "Home Lighting System" using Android Things and Firebase integration to control turning three LEDs on/off. To use, a Firebase instance must be created and two apps adds for the Android Things app and the companion phone app and add the google-services.json files to their respective modules in the project. JSON structure for the Firebase database is as follows:
```
{
  "lighting_system" : {
    "blue_on" : false,
    "green_on" : false,
    "red_on" : false
  }
}
```
