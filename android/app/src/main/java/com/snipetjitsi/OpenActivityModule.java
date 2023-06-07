package com.snipetjitsi;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetUserInfo;

import java.net.MalformedURLException;
import java.net.URL;

public class OpenActivityModule extends ReactContextBaseJavaModule {
    Context context;

    public OpenActivityModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext.getApplicationContext(); // This is where you get the context
    }

    @NonNull
    @Override
    public String getName() {
        return "OpenActivity";
    }

    @ReactMethod
    public void call(String url, ReadableMap userInfo, ReadableMap meetOptions, ReadableMap meetFeatureFlags) {

        Log.d("test Module", url);
        JitsiMeetUserInfo _userInfo = new JitsiMeetUserInfo();
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (userInfo != null) {
                    if (userInfo.hasKey("displayName")) {
                        _userInfo.setDisplayName(userInfo.getString("displayName"));
                    }
                    if (userInfo.hasKey("email")) {
                        _userInfo.setEmail(userInfo.getString("email"));
                    }
                    if (userInfo.hasKey("avatar")) {
                        String avatarURL = userInfo.getString("avatar");
                        try {
                            _userInfo.setAvatar(new URL(avatarURL));
                        } catch (MalformedURLException e) {
                        }
                    }
                }

                JitsiMeetConferenceOptions options
                        = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(url)
                        .setUserInfo(_userInfo)
                        .setToken(meetOptions.hasKey("token") ? meetOptions.getString("token") : "")
                        .setSubject(meetOptions.hasKey("subject") ? meetOptions.getString("subject") : "")
                        .setAudioMuted(meetOptions.hasKey("audioMuted") ? meetOptions.getBoolean("audioMuted") : false)
                        .setAudioOnly(meetOptions.hasKey("audioOnly") ? meetOptions.getBoolean("audioOnly") : false)
                        .setVideoMuted(meetOptions.hasKey("videoMuted") ? meetOptions.getBoolean("videoMuted") : false)
                        .setFeatureFlag("add-people.enabled", meetFeatureFlags.hasKey("addPeopleEnabled") ? meetFeatureFlags.getBoolean("addPeopleEnabled") : true)
                        .setFeatureFlag("calendar.enabled", meetFeatureFlags.hasKey("calendarEnabled") ? meetFeatureFlags.getBoolean("calendarEnabled") : true)
                        .setFeatureFlag("call-integration.enabled", meetFeatureFlags.hasKey("callIntegrationEnabled") ? meetFeatureFlags.getBoolean("callIntegrationEnabled") : true)
                        .setFeatureFlag("chat.enabled", meetFeatureFlags.hasKey("chatEnabled") ? meetFeatureFlags.getBoolean("chatEnabled") : true)
                        .setFeatureFlag("close-captions.enabled", meetFeatureFlags.hasKey("closeCaptionsEnabled") ? meetFeatureFlags.getBoolean("closeCaptionsEnabled") : true)
                        .setFeatureFlag("invite.enabled", meetFeatureFlags.hasKey("inviteEnabled") ? meetFeatureFlags.getBoolean("inviteEnabled") : true)
                        .setFeatureFlag("android.screensharing.enabled", meetFeatureFlags.hasKey("androidScreenSharingEnabled") ? meetFeatureFlags.getBoolean("androidScreenSharingEnabled") : true)
                        .setFeatureFlag("live-streaming.enabled", meetFeatureFlags.hasKey("liveStreamingEnabled") ? meetFeatureFlags.getBoolean("liveStreamingEnabled") : true)
                        .setFeatureFlag("meeting-name.enabled", meetFeatureFlags.hasKey("meetingNameEnabled") ? meetFeatureFlags.getBoolean("meetingNameEnabled") : true)
                        .setFeatureFlag("meeting-password.enabled", meetFeatureFlags.hasKey("meetingPasswordEnabled") ? meetFeatureFlags.getBoolean("meetingPasswordEnabled") : true)
                        .setFeatureFlag("pip.enabled", meetFeatureFlags.hasKey("pipEnabled") ? meetFeatureFlags.getBoolean("pipEnabled") : true)
                        .setFeatureFlag("kick-out.enabled", meetFeatureFlags.hasKey("kickOutEnabled") ? meetFeatureFlags.getBoolean("kickOutEnabled") : true)
                        .setFeatureFlag("conference-timer.enabled", meetFeatureFlags.hasKey("conferenceTimerEnabled") ? meetFeatureFlags.getBoolean("conferenceTimerEnabled") : true)
                        .setFeatureFlag("video-share.enabled", meetFeatureFlags.hasKey("videoShareEnabled") ? meetFeatureFlags.getBoolean("videoShareEnabled") : true)
                        .setFeatureFlag("recording.enabled", meetFeatureFlags.hasKey("recordingEnabled") ? meetFeatureFlags.getBoolean("recordingEnabled") : true)
                        .setFeatureFlag("reactions.enabled", meetFeatureFlags.hasKey("reactionsEnabled") ? meetFeatureFlags.getBoolean("reactionsEnabled") : true)
                        .setFeatureFlag("raise-hand.enabled", meetFeatureFlags.hasKey("raiseHandEnabled") ? meetFeatureFlags.getBoolean("raiseHandEnabled") : true)
                        .setFeatureFlag("tile-view.enabled", meetFeatureFlags.hasKey("tileViewEnabled") ? meetFeatureFlags.getBoolean("tileViewEnabled") : true)
                        .setFeatureFlag("toolbox.alwaysVisible", meetFeatureFlags.hasKey("toolboxAlwaysVisible") ? meetFeatureFlags.getBoolean("toolboxAlwaysVisible") : false)
                        .setFeatureFlag("toolbox.enabled", meetFeatureFlags.hasKey("toolboxEnabled") ? meetFeatureFlags.getBoolean("toolboxEnabled") : true)
                        .setFeatureFlag("welcomepage.enabled", meetFeatureFlags.hasKey("welcomePageEnabled") ? meetFeatureFlags.getBoolean("welcomePageEnabled") : false)
                        .setFeatureFlag("prejoinpage.enabled", meetFeatureFlags.hasKey("prejoinPageEnabled") ? meetFeatureFlags.getBoolean("prejoinPageEnabled") : false)
                        .build();

                JitsiMeetActivity.launch(getReactApplicationContext(), options);
            }
        });
    }
}

