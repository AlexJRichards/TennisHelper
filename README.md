
# Android Tennis Application (TenHelper)

This is an android application for my dissertation project. These installation instructions are for a windows machine.


## Clone the git repository to local device
- type "git clone https://github.com/AlexJRichards/TennisHelper.git" in console.

## Install latest version of Android Studio(2020.3.1) Arctic Fox 

- download file from https://developer.android.com/studio
- Click do not import settings during installation
- if a kotlin plugin update pops up then click update and restart android studio to update
- choose standard installation

## Open the project

- click file, open, navigate to the directory where the code repository is downloaded and click on tenhelper, then hit okay.
- Click trust project at the top of the page or on popup banner.
- wait for the project to index (you will see progress in bottom right corner)
- wait for the project gradle to build (you can monitor progress in background tasks in bottom right corner).


Load any google pixel APK along with Android 11.0 SDK on the emulator and run the application to view it on computer, it is possible to also run it on an android phone if one is in developer mode and plugged into the computer.
Alternatively, run the application APK on an android device to install.


## Run project on Emulator in Android Studio

Step 1 - Install SDK :
 - Open SDK Manager - Go to Top toolbar, under Tools then click SDK Manager
 - Choose to Install Android 11.0 SDK by selecting is from the list, clicking apply and accepting the license agreement
 - In the SDK Manager -> click on SDK Tools tab
 - Make sure Android SDK Build Tools, Android emulator, Android SDK Platform Tools and USB driver installed in the SDK tools tab
 - If they are not installed, click them and apply. Click ok to exit


Step 2 - Install AVD-Android Virtual Device:
  (Top toolbar) Tools -> AVD Manager
  Click Create Virtual device at the bottom
  click the pixel 3 XL download button, select the downloaded device and then click next
  Then click through and finish

Step 3- To Run App :
  (Top Toolbar) Run -> Run
  This will launch on the emulator after a wait
  Project may need to be compiled first (Build Menu then Make Project)
  NOTE - If the emulator loads but the application does not appear then it may be due to windows defender firewall blocking
	 some of the features. To rectify this, click the square button at the bottom of the screen after the application has compiled. This will bring
	 up a security allert on which you can allow access to the app. If this message does not appear then please wait a few minutes and try this process again.
  - When the application launches, click the insert player stats button. You can enter the following details to get a demo user working. To clear the onscreen keyboard press
    down arrow in the bottom left corner. 
      - name: Demo
      - gender: M (this is capitalised)
      - height: 180
      - weight: 80
      - tennis level: 6.2
      - how many times a week do you play tennis: 3
      - fitness score: 11
  - At this stage setup is complete and you can use the application. If you run into an error here it may be due to the inputs being invalid. In this case, re run the app
    in android studio and try again.

Additional notes:
If you run into any undocumented errors then try:
- Sync the gradle by going to the left project menu and clicking project files then opening build.gradle


To run project on android phone (App has only been tested on Android 10 and 11 devices):

# Option 1 (run on phone directly not through android studio):
    Make sure third party apk's are allowed to be installed under security settings
    Install the APK Provided in package on the phone by clicking on the file (fails if stored in the downloads folder) (If allowed in privacy settings)

# Option 2 (run on phone through android studio):
Step 1 (turn on development mode on phone)
  Turn on developer mode in phone options (Different depending on phone)
  Turn on USB Debugging on phone
  Attach to PC with USB
  Select USB Transfer mode to file transfer
Step 2 (On computer in android studio)
  Make sure Android Studio recognises device under devices in top right
  Click run button or alt f10
  Android studio needs phone to be on usb file transfer mode instead of just charging



    