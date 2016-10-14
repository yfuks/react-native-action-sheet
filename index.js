'use strict'

import {
  NativeModules,
  Platform,
  ActionSheetIOS
} from 'react-native';
import {
  ActionSheetAndroid
} from 'NativeModules';

module.exports = (Platform.OS == 'ios') ? ActionSheetIOS : ActionSheetAndroid;
