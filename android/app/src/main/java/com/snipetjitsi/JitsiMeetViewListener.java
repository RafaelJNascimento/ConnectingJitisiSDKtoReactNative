package com.snipetjitsi;

import java.util.Map;
/**
 * Interface for listening to events coming from Jitsi Meet.
 */
public interface JitsiMeetViewListener {
    /**
     * Called when a conference was joined.
     *
     * @param data Map with a "url" key with the conference URL.
     */
    void onConferenceJoined(Map<String, Object> data);

    /**
     * Called when the active conference ends, be it because of user choice or
     * because of a failure.
     *
     * @param data Map with an "error" key with the error and a "url" key with
     * the conference URL. If the conference finished gracefully no `error`
     * key will be present. The possible values for "error" are described here:
     * https://github.com/jitsi/lib-jitsi-meet/blob/master/JitsiConnectionErrors.js
     * https://github.com/jitsi/lib-jitsi-meet/blob/master/JitsiConferenceErrors.js
     */
    void onConferenceTerminated(Map<String, Object> data);

    /**
     * Called before the conference is joined.
     *
     * @param data Map with a "url" key with the conference URL.
     */
    void onConferenceWillJoin(Map<String, Object> data);
}