package com.actionsheet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

import java.util.List;
import java.util.ArrayList;

public class ActionSheetModule extends ReactContextBaseJavaModule {
  WritableMap response;

  public ActionSheetModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "ActionSheetAndroid";
  }

  @ReactMethod
  public void showActionSheetWithOptions(final ReadableMap options, final Callback callback) {
    Activity currentActivity = getCurrentActivity();

    if (currentActivity == null) {
      response = Arguments.createMap();
      response.putString("error", "can't find current Activity");
      callback.invoke(response);
      return;
    }

    final List<String> titles = new ArrayList<String>();

    int _cancelInde = 99;
    if (options.hasKey("cancelButtonIndex")) {
      _cancelInde = options.getInt("cancelButtonIndex");
    }
    final int cancelIndex = _cancelInde;
    if (options.hasKey("options")) {
      ReadableArray customButtons = options.getArray("options");
      for (int i = 0; i < customButtons.size(); i++) {
        int currentIndex = titles.size();
        if (i != cancelIndex) {
          titles.add(currentIndex, customButtons.getString(i));
        }
      }
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(currentActivity,
            R.layout.dialog_item, titles);
    AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity, R.style.DialogStyle);
    if (options.hasKey("title") && options.getString("title") != null && !options.getString("title").isEmpty()) {
      builder.setTitle(options.getString("title"));
    }

    /* @TODO message currently disable the options
    if (options.hasKey("message") && options.getString("message") != null && !options.getString("message").isEmpty()) {
      builder.setMessage(options.getString("message"));
    }
    */

    builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int index) {
        if (index < cancelIndex) {
          callback.invoke(index);
        } else {
          callback.invoke(index + 1);
        }
      }
    });

    final AlertDialog dialog = builder.create();
    /**
     * override onCancel method to callback cancel in case of a touch outside of
     * the dialog or the BACK key pressed
     */
    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
      @Override
      public void onCancel(DialogInterface dialog) {
        dialog.dismiss();
      }
    });
    dialog.show();
  }
}
