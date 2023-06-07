import React, { useState } from 'react';
import {
    View,
    Text,
    TextInput,
    TouchableOpacity,
    StyleSheet,
} from 'react-native';

import JitsiMeetModule, { JitsiMeetView } from './modulo';

const App = () => {

    const [conferenceUrl, onChangeconferenceUrl] = useState('');
    const [conferenceName, onChangeConferenceName] = useState('');

    const onPressOpen = () => {
        let url = '';

        setTimeout(() => {
            const url = 'https://meet.jit.si/exemple';
            const userInfo = {
                displayName: 'User',
                email: 'user@example.com',
                avatar: 'https:/gravatar.com/avatar/abc123',
            };
            JitsiMeetModule.activityMode({ userInfo, serverUrl: url });
        }, 1000);
    }

    return (
        <View style={styles.container}>
            <TextInput
                style={[styles.input, { marginBottom: 20 }]}
                placeholder='conference url'
                onChangeText={onChangeconferenceUrl}
                value={conferenceUrl}
            />
            <TextInput
                style={styles.input}
                placeholder='conference name'
                onChangeText={onChangeConferenceName}
                value={conferenceName}
            />
            <TouchableOpacity
                onPress={() => onPressOpen()}
                style={styles.button}>
                <Text style={{ color: 'white' }}>
                    open conference
                </Text>
            </TouchableOpacity>
            <JitsiMeetView
                onConferenceWillJoin={() => { }}
                onConferenceTerminated={() => { }}
                onConferenceJoined={() => { }}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
    },
    button: {
        height: 40,
        width: 160,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#000000'
    },
    input: {
        width: '80%',
        height: 40,
        marginBottom: 100,
        borderWidth: 1,
        padding: 10,
    },
});

export default App;