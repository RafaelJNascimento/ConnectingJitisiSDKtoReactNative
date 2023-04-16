package com.snipetjitsi;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

public class OpenActivityModule extends ReactContextBaseJavaModule {
    Context context;

    public OpenActivityModule (ReactApplicationContext reactContext){
        super(reactContext);
        this.context = reactContext.getApplicationContext(); // This is where you get the context
    }

    @NonNull
    @Override
    public String getName() {
        return "OpenActivity";
    }

    @ReactMethod
    public void open(String conferenceURL){
        Log.d("test Module", conferenceURL);
        if (conferenceURL.length() > 0) {
            JitsiMeetConferenceOptions options
                    = new JitsiMeetConferenceOptions.Builder()
                    .setFeatureFlag("notifications.enabled", false)
                    .setFeatureFlag("filmstrip.enabled", false)
                    .setFeatureFlag("welcomepage.enabled", false)
                    .setRoom(conferenceURL)
                    .build();
            JitsiMeetActivity.launch(this.context, options);
        }
    }
}

