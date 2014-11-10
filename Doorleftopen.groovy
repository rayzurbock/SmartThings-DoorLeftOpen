/**
 *  Notify When Left Open and take photos
 *  Based on original code by olson.lukas@gmail.com (2013-06-24)
 *  8/14/2014 - Brian Lowrance - brian@rayzurbock.com - Photo code added
 *  11/8/2014 - Brian Lowrance - brian@rayzurbock.com - Prevent false alarms added
 *  11/8/2014 - Brian Lowrance - brian@rayzurbock.com - Option to repeat alert if door remains open
 *  11/9/2014 - Brian Lowrance - brian@rayzurbock.com - Modified repeat alert with a max of 10 per occurrance.
 * 
 * For the latest version visit: https://github.com/rayzurbock/SmartThings-DoorLeftOpen
 * Version: 1.3.1
 */

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
        input "numMinutes", "number", title: "For how many minutes", required: true
        input "messageText", "text", title: "Send notification that says", required: true
        input "phoneNumber", "phone", title: "Send SMS message to", required: false
        input "repeatpush", "bool", title: "Repeat notification until resolved?", required: true
    }
    section("Take photos with"){
        input "camera", "capability.imageCapture", required: false
        input "burstCount", "number", title: "How many? (default 5)", defaultValue:5, required: false
    }
}

def installed() {
    subscribe(contactSensor, "contact", onContactChange);
    state.count = 0;
    state.maxrepeat = 10;
    state.alertmsg = "";
}

def updated() {
    unsubscribe()
    subscribe(contactSensor, "contact", onContactChange);
    state.count = 0;
    state.maxrepeat = 10;
    state.alertmsg = "";
}

def onContactChange(evt) {
    log.debug "onContactChange";
    if (evt.value == "open") {
        state.count = 0;
        state.maxrepeat = 10;
        runIn(numMinutes * 60, onContactLeftOpenHandler);
    } else {
        unschedule(onContactLeftOpenHandler);
    }
}

def onContactLeftOpenHandler() {
    log.debug "onContactLeftOpenHandler";
    if (contactSensor.latestValue == "open") {
        state.count = state.count + 1
        log.debug "Door still open, alert! (Alert #${state.count})"
        if (state.count == 1) {state.alertmsg = messageText}
        if (state.count > 1 && state.count < state.maxrepeat) {state.alertmsg = "${messageText}. Repeat #${state.count}."}
        if (state.count == state.maxrepeat) {state.alertmsg = "${messageText}. Last notice."}
        sendPush(state.alertmsg);
        sendSms(phoneNumber, state.alertmsg);
        if (repeatpush) {
            if (state.count < state.maxrepeat) {
                log.debug "Rescheduling repeat alert";
                unschedule();
                runIn(numMinutes * 60, onContactLeftOpenHandler);
            }
        }
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
