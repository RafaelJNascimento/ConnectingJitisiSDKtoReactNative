package com.snipetjitsi;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;


@ReactModule(name = RNJitsiViewManager.REACT_CLASS)
public class RNJitsiViewManager extends SimpleViewManager<RNJitsiMeetView> {
    public static final String REACT_CLASS = "RNJitsiMeetView";
    private JitsiMeetViewInterface jitsiMeetViewInterface;
    private ReactApplicationContext mReactContext;

    public RNJitsiViewManager(ReactApplicationContext reactContext, JitsiMeetViewInterface jitsiMeetViewReference) {
        jitsiMeetViewInterface = jitsiMeetViewReference;
        mReactContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public RNJitsiMeetView createViewInstance(ThemedReactContext context) {
        if (jitsiMeetViewInterface.getJitsiMeetView() == null) {
            RNJitsiMeetView view = new RNJitsiMeetView(context.getCurrentActivity());
            jitsiMeetViewInterface.setJitsiMeetView(view);
        }
        return jitsiMeetViewInterface.getJitsiMeetView();
    }
/*
    @ReactProp(name = "onConferenceJoined")
    public void onConferenceJoined(Map<String, Object> data) {
    }

    @ReactProp(name = "onConferenceTerminated")
    public void onConferenceTerminated(Map<String, Object> data) {
    }

    @ReactProp(name = "onConferenceWillJoin")
    public void onConferenceWillJoin(Map<String, Object> data) {
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().build();
    }
 */
}