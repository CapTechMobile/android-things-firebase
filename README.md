# android-things-firebase
A sample project that creates a "Home Lighting System" using Android Things and Firebase integration to control turning three LEDs on/off. To use, a Firebase instance must be created and two apps adds for the Android Things app and the companion phone app and add the google-services.json files to their respective modules in the project. The related blog post can be found [here](https://www.captechconsulting.com/blogs/creating-a-home-lighting-system-with-android-things-and-firebase). JSON structure for the Firebase database is as follows:
```
{
  "lighting_system" : {
    "blue_on" : false,
    "green_on" : false,
    "red_on" : false
  }
}
```

Expected wiring of the Raspberry Pi and LEDs is below -- if you choose to use a different device or different GPIO pins, be sure to update the pin values in the [MainActivity](/app/src/main/java/atownsend/androidthingsfirebase/MainActivity.java) for the Android Things app.

![Wiring image](/android_things_sketch.png)

# Demo
![Demo gif](/demo.gif)
