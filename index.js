'use strict'

import {
  NativeModules,
  Platform,
  ActionSheetIOS
} from 'react-native';

module.exports = (Platform.OS == 'ios') ? ActionSheetIOS : NativeModules.ActionSheetAndroid;
