import React from 'react';
import {
    View,
    Text,
    TouchableOpacity,
    StyleSheet,
    NativeModules,
} from 'react-native';

const App = () => {


    const {OpenActivity} = NativeModules;

    const onPressOpen = () => {
        OpenActivity.open();
    }

    return (
        <View style={styles.container}>
            <TouchableOpacity
                onPress={() => onPressOpen()}
                style={styles.button}>
                <Text style={{ color: 'white' }}>
                    Abrir atividade
                </Text>
            </TouchableOpacity>
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
    }
});

export default App;