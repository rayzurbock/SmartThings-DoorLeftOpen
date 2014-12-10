SmartThings-DoorLeftOpen
========================
#License
Copyright (c) 2014 Brian S. Lowrance (brian@rayzurbock.com)

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at:

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

#Description
SmartThings Door Left Open Alert with optional Photo Capture & Hello Home Actions <br />
Version 1.3.1

An app for SmartThings that will send a notification (and optionally capture photo's / run a hello home action) in the event that a door is left open for longer than your desired threshold time.

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
*  11/8/2014 Prevent false alarms.  Added option to repeat alert if door remains open. Added versioning.
*  11/9/2014 Prevent repeat forever.  Limit repeat messages to 10.
*  12/4/2014 - (1.3.2-Beta1) - Added Dynamic Menus (Status Page, Configure Page). Added Hello Home action option on first alert and optional Hello Home on clear.
*  12/9/2014 - (1.3.3) - Adding Dynamic Menus from 1.3.2-Beta + SpeechSynthesis (audio announcements) for Sonos/VLC Thing devices. Added to category: "Safety & Security"
