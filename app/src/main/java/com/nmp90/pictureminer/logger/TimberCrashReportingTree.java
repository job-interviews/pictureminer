package com.nmp90.pictureminer.logger;

import android.util.Log;

import timber.log.Timber;

/**
 * Created by joro on 18.12.16.
 */

public class TimberCrashReportingTree extends Timber.Tree  {
    @Override protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return;
        }
        //TODO: Add fake crash library or Crashlytics
        //FakeCrashLibrary.log(priority, tag, message);

        if (t != null) {
            if (priority == Log.ERROR) {
                //FakeCrashLibrary.logError(t);
            } else if (priority == Log.WARN) {
                //FakeCrashLibrary.logWarning(t);
            }
        }
    }
}
