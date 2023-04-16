package com.snipetjitsi;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

//import org.jitsi.meet.sdk.JitsiMeetActivity;
//import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

public class OpenActivityModule extends ReactContextBaseJavaModule {
    public OpenActivityModule (ReactApplicationContext reactContext){
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "OpenActivity";
    }

    @ReactMethod
    public void open(){
        Log.d("test Module", "Open click");
        /*String conferenceName = "https://meet.jit.si/teste";
        if (conferenceName.length() > 0) {
            JitsiMeetConferenceOptions options
                    = new JitsiMeetConferenceOptions.Builder()
                    .setFeatureFlag("notifications.enabled", false)
                    .setFeatureFlag("filmstrip.enabled", false)
                    .setFeatureFlag("welcomepage.enabled", false)
                    .setRoom(conferenceName)
                    .build();
            JitsiMeetActivity.launch(this, options);
        }

         */
    }
}

