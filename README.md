Description
This project is about realtime database chat application by using Firebase

Module/functionalities 
1. can save into SQLite Database for offline reading
2. can delete history to free up storage
3. infinity chat with anyone anywhere as long as you have got the app and Internet.


Libraries/external API's used

1. FirebaseAPI
2. SQLiteOpenHelper 


How to setup environment development?

a)	Add Firebase to app

i.	By using the Firebase Assistant 
1.	Click Tools > Firebase to open the Assistant window.
2.	Click to expand one of the listed features (for example, Authentication), then click the provided tutorial link (for example, Log an Authentication event).
3.	Click the Connect to Firebase button to connect to Firebase and add the necessary code to your app.

ii.	Manually add Firebase
1.	Create a Firebase project in the Firebase console, if you don't already have one. If you already have an existing Google project associated with your mobile app, click Import Google Project. Otherwise, click Create New Project.
2.	Click Add Firebase to your Android app and follow the setup steps. If you're importing an existing Google project, this may happen automatically and you can just download the config file.
3.	When prompted, enter your app's package name.
4.	At the end, you'll download a google-services.json file and copy this into your project's module folder, typically app/.

b)	Add the SDK
    add SDK to root-level bulid.gradle file, to include the google-service plugin:
    
        buildscript {
        // ...
            dependencies {
            // ...
            classpath 'com.google.gms:google-services:3.1.0'
            }
        }

add the apply plugin line at the bottom of the file to enable the Gradle plugin in Gradle file ( app/build.gradle):

        apply plugin: 'com.android.application'

        android {
            // ...
        }
        dependencies {
  	    // ...
  	        compile 'com.google.firebase:firebase-core:10.2.6'

        //realtime database  
        compile 'com.google.firebase:firebase-database:10.2.6'

        //firebase authentication 
        compile 'com.google.firebase:firebase-auth:10.2.6'

        compile 'com.google.firebase:firebase-ui:1.1.1'

       //Google play service
        compile 'com.google.android.gms:play-services:10.2.6’
        
          // Getting a "Could not find" error? Make sure you have
          // the latest Google Repository in the Android SDK manager
        }

        // ADD THIS AT THE BOTTOM
        apply plugin: 'com.google.gms.google-services'

