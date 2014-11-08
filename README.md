SmartThings-DoorLeftOpen
========================

SmartThings Door Left Open Alert with Photo Capture

An app for SmartThings that will send a notification (and optionally capture photo's) in the event that a door is left open for longer than your desired threshold time.

To view captured photos, Open the SmartThings app on your mobile device, navigate to your camera and review the carousel at the top *(Note: I have had to force quit the SmartThings app and relaunch it before new photos show up in the carousel)*.

#Installation
1. Login at <a href=http://graph.api.smartthings.com>http://graph.api.smartthings.com</a>
2. Go to "My SmartApps" section and click on the "+ New SmartApp" button on the right.
3. On the "New SmartApp" page, fill out mandatory "Name" and "Description" fields (it does not matter what you put there).
4. Click the "Create" button at the bottom.
5. When a new app template opens in the IDE, replace the contents with that in the .groovy file here
6. Click the blue "Save" button above the editor window.
7. Click the "Publish" button next to it and select "For Me". You have now self-published your SmartApp.
8. Open SmartThings mobile app on iPhone or Android and go to the Dashboard.
9. Tap on the round "+" button and navigate to "My Apps" section by swiping the menu ribbon all the way to the left.
10. "Door Left Open (with photos)" app should be available in the list of SmartApps that appears below the menu ribbon. Tap it and follow setup instructions.

# Revision History
*  8/14/2014 Option to take a photo with a ST compatible camera added.
*  11/8/2014 Prevent false alarms.  Added option to repeat alert if door remains open.
