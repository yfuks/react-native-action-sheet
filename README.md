# react-native-action-sheet [![npm version](https://badge.fury.io/js/%40yfuks%2Freact-native-action-sheet.svg)](https://badge.fury.io/js/%40yfuks%2Freact-native-action-sheet) ![MIT](https://img.shields.io/dub/l/vibe-d.svg) ![Platform - Android and iOS](https://img.shields.io/badge/platform-Android%20%7C%20iOS-yellow.svg)
React native action sheet with native android (using the built-in [AlertDialog](https://developer.android.com/reference/android/app/AlertDialog.html))

This module simply return the [ActionSheetIOS](https://facebook.github.io/react-native/docs/actionsheetios.html) if the device on iOS

iOS | Android
------- | ----
<img title="iOS" src="http://i.imgur.com/Y9n9jkb.png" height=550> | <img title="Android" src="http://i.imgur.com/oRXTG7g.png" height=550>

## Table of contents
- [Install](#install)
- [Usage](#usage)
- [Methods](#methods)

## Install

`npm install @yfuks/react-native-action-sheet@latest --save`
`react-native link @yfuks/react-native-action-sheet`

#### Android

The `react-native link` command above should do everything you need, but if for some reason it does not work, you can replicate its effects manually by making the following changes.

```gradle
// file: android/settings.gradle
...

include ':react-native-action-sheet'
project(':react-native-action-sheet').projectDir = new File(settingsDir, '../node_modules/@yfuks/react-native-action-sheet/android')
```
```gradle
// file: android/app/build.gradle
...

dependencies {
    ...
    compile project(':react-native-action-sheet')
}
```

```java
// file: android/app/src/main/java/com/<...>/MainApplication.java
...

import com.actionsheet.ActionSheetPackage; // <-- add this import

public class MainApplication extends Application implements ReactApplication {
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
            new MainReactPackage(),
            new ActionSheetPackage() // <-- add this line
        );
    }
...
}

```

## Usage

```javascript
import ActionSheet from '@yfuks/react-native-action-sheet';
import { Platform } from 'react-native';

var BUTTONSiOS = [
  'Option 0',
  'Option 1',
  'Option 2',
  'Delete',
  'Cancel'
];

var BUTTONSandroid = [
  'Option 0',
  'Option 1',
  'Option 2'
];

var DESTRUCTIVE_INDEX = 3;
var CANCEL_INDEX = 4;

ActionSheet.showActionSheetWithOptions({
  options: (Platform.OS == 'ios') ? BUTTONSiOS : BUTTONSandroid,
  cancelButtonIndex: CANCEL_INDEX,
  destructiveButtonIndex: DESTRUCTIVE_INDEX,
  tintColor: 'blue'
},
(buttonIndex) => {
  console.log('button clicked :', buttonIndex);
});
```

## Methods

For the iOS implementation see [ActionSheetIOS](https://facebook.github.io/react-native/docs/actionsheetios.html)

#### showActionSheetWithOptions (Android)
```javascript
/**
 * Display the native action sheet
 */

static showActionSheetWithOptions(options, callback);
```

@note: on Android in case of a touch outside the ActionSheet or the button *back* is pressed the buttonIndex value is ```'undefined'```

#### options

option | iOS  | Android | Info
------ | ---- | ------- | ----
options | OK | OK | (array of strings) - a list of button titles (required on iOS)
cancelButtonIndex | OK | - | (int) - index of cancel button in options (useless in android since we have back button)
destructiveButtonIndex | OK | - | (int) - index of destructive button in options (same as above)
title | OK | OK | (string) - a title to show above the action sheet
message | OK | - | (string) - a message to show below the title
tintColor | OK | - | (string) - a color to set to the text (defined by processColor)
