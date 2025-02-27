Check Permissions:
Since the app requests permissions for internet and location, ensure that you grant these permissions when prompted on the device.


               <uses-permission android:name="android.permission.INTERNET"/>
               <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

Make sure you have a valid Google Maps API key configured in your strings.xml file as referenced in the AndroidManifest.xml.

                eg. <string name="google_map_key">AIzaSyDtlWQbu_elEKQmt4CEsLYpXJmTqhZ5brU</string> in res/string
               and   android:value="@string/google_map_key"/> in manifest 


Make sure you are set these dependencies in build-gradle.
                  
               implementation 'com.google.android.gms:play-services-location:17.0.0'
               implementation 'com.google.android.gms:play-services-maps:17.0.0'