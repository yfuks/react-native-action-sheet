import {
  NativeModules,
  Platform,
  ActionSheetIOS
} from 'react-native';

export default (Platform.OS === 'ios') ? ActionSheetIOS : NativeModules.ActionSheetAndroid;
