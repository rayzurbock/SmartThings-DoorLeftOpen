/**
 *  Notify When Left Open and take photos
 *  Based on original code by olson.lukas@gmail.com (2013-06-24)
 *  Photo code added: 8/14/2014 - Brian Lowrance - brian@rayzurbock.com
 *  Code to prevent false alarms added: 11/8/2014 - Brian Lowrance - brian@rayzurbock.com
 */

// Automatically generated. Make future change here.
definition(
    name: "Door left open (with photos)",
    namespace: "",
    author: "Brian Lowrance",
    description: "Notify when something is left open and take some photos",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")

preferences {
	section("When . . .") {
		input "contactSensor", "capability.contactSensor", title: "This is left open"
        input "numMinutes", "number", title: "For how many minutes", required: false
        input "messageText", "text", title: "Send notification that says", required: false
        input "phoneNumber", "phone", title: "Send SMS message to", required: false
	}
    section("Take photos with"){
    	input "camera", "capability.imageCapture", required: false
		input "burstCount", "number", title: "How many? (default 5)", defaultValue:5, required: false
    }
}

def installed() {
	subscribe(contactSensor, "contact", onContactChange);
}

def updated() {
	unsubscribe()
   	subscribe(contactSensor, "contact", onContactChange);
}

def onContactChange(evt) {
	log.debug "onContactChange";
	if (evt.value == "open") {
    	runIn(numMinutes * 60, onContactLeftOpenHandler);
    } else {
    	unschedule(onContactLeftOpenHandler);
    }
}

def onContactLeftOpenHandler() {
	log.debug "onContactLeftOpenHandler";
    if (contactSensor.latestValue == "open") {
       log.debug "Door still open, alert"
	   sendPush(messageText);
       sendSms(phoneNumber, messageText);
       if (camera) {
		   camera.take()
           log.debug "Camera: Snap"
		   (1..((burstCount ?: 10) - 1)).each {
			   log.debug "Camera: Snap"
			   camera.take(delay: (500 * it))
			   pause(1500)
		   }
	   }
    } else {
       log.debug "Door closed, cancel alert"
    }
}
