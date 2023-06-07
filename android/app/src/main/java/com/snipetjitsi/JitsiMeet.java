package com.snipetjitsi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetUserInfo;

import java.net.URL;
import java.util.Map;

public class JitsiMeet  {
    public static final String TAG = "JitsiMeet";
    public static boolean meetingActive = false;
    private static JitsiMeet instance = null;


    public static JitsiMeet getInstance() { return instance; }


    public static boolean startConference(Context context, JSONObject JSONoptions) {
        try {
            JitsiMeetUserInfo userInfo = new JitsiMeetUserInfo();

            if(JSONoptions.optString("room", "").isEmpty()){
                //va definita una stanza!
                return false;
            }

            // Configurazione UserInfo

            if(!JSONoptions.optString("avatar", "").isEmpty()) {
                userInfo.setAvatar(new URL(JSONoptions.getString("avatar")));
            }
            if(!JSONoptions.optString("displayName", "").isEmpty()) {
                userInfo.setDisplayName(JSONoptions.getString("displayName"));
            }
            if(!JSONoptions.optString("email", "").isEmpty()) {
                userInfo.setEmail(JSONoptions.getString("email"));
            }

            // Configurazione options

            JitsiMeetConferenceOptions.Builder builder = new JitsiMeetConferenceOptions.Builder();

            builder.setRoom(JSONoptions.getString("room"));
            builder.setServerURL(new URL(JSONoptions.optString("serverURL", "https://meet.jit.si")));

            builder.setUserInfo(userInfo);

            if(!JSONoptions.optString("subject", "").isEmpty()) {
                builder.setSubject(JSONoptions.getString("subject"));
            }
            if(!JSONoptions.optString("token", "").isEmpty()) {
                builder.setToken(JSONoptions.getString("token"));
            }
            builder.setAudioMuted(JSONoptions.optBoolean("audioMuted",false));
            builder.setVideoMuted(JSONoptions.optBoolean("videoMuted",false));
            builder.setAudioOnly(JSONoptions.optBoolean("audioOnly",false));

            // Required to bypass call dealer issue
            builder.setFeatureFlag("call-integration.enabled", false);

            // Configurazione flags di options
            try {
                JSONObject flags = JSONoptions.getJSONObject("flags");
                JSONArray flagNames = flags.names();

                for (int i = 0; i < flagNames.length(); i++) {

                    String key = flags.names().get(i).toString();

                    if (flags.get(key) instanceof Integer) {
                        builder.setFeatureFlag(key, flags.getInt(key));
                    } else if (flags.get(key) instanceof String) {
                        builder.setFeatureFlag(key, flags.getString(key));
                    } else if (flags.get(key) instanceof Boolean) {
                        builder.setFeatureFlag(key, flags.getBoolean(key));
                    }

                }
            }
            catch (Exception e) {}

            JitsiMeetConferenceOptions options = builder.build();
            JitsiMeetActivity.launch(context,options);

            return true;

        } catch (Exception e) {
            return false;
        }
    }


    private Intent getForegroundIntent(Context app) {
        Intent intent = app.getPackageManager().getLaunchIntentForPackage(app.getPackageName());

        intent.addFlags(
                Intent.FLAG_ACTIVITY_REORDER_TO_FRONT |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);

        return intent;
    }

}