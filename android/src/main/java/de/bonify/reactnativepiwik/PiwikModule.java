package de.bonify.reactnativepiwik;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import org.piwik.sdk.Piwik;
import org.piwik.sdk.Tracker;
import org.piwik.sdk.TrackHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.net.MalformedURLException;


public class PiwikModule extends ReactContextBaseJavaModule implements LifecycleEventListener {

    private static final String LOGGER_TAG = "PiwikModule";

    public PiwikModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addLifecycleEventListener(this);
    }

    private Tracker mPiwikTracker;

    @ReactMethod
    public void initTracker(String url, int id) {
        try {
            mPiwikTracker = Piwik.getInstance(getReactApplicationContext()).newTracker(url, id);
        } catch (MalformedURLException e) {
            Log.w(LOGGER_TAG, "url is malformed", e);
        }
    }

    @ReactMethod
    public void setAppOptOut(Boolean isOptedOut) {
        Piwik.getInstance(getReactApplicationContext()).setOptOut(isOptedOut);
    }

    @ReactMethod
    public void setUserId(String userId) {
        mPiwikTracker.setUserId(userId);
    }

    @ReactMethod
    public void trackScreen(@NonNull String screen, String title) {
        if (mPiwikTracker == null) {
            throw new RuntimeException("Tracker must be initialized before usage");
        }
        TrackHelper.track().screen(screen).title(title).with(mPiwikTracker);
    }

    @ReactMethod
    public void trackEvent(@NonNull String category, @NonNull String action, ReadableMap values) {
        if (mPiwikTracker == null) {
            throw new RuntimeException("Tracker must be initialized before usage");
        }
        String name = null;
        Float value = null;
        if (values.hasKey("name") && !values.isNull("name")) {
            name = values.getString("name");
        }
        if (values.hasKey("value") && !values.isNull("value")) {
            value = (float)values.getDouble("value");
        }
        TrackHelper.track().event(category, action).name(name).value(value).with(mPiwikTracker);
    }

    @ReactMethod
    public void trackGoal(int goalId, ReadableMap values) {
        if (mPiwikTracker == null) {
            throw new RuntimeException("Tracker must be initialized before usage");
        }
        Float revenue = null;
        if (values.hasKey("revenue") && !values.isNull("revenue")) {
            revenue = (float)values.getDouble("revenue");
        }
        TrackHelper.track().goal(goalId).revenue(revenue).with(mPiwikTracker);
    }


    @ReactMethod
    public void trackAppDownload() {
        if (mPiwikTracker == null) {
            throw new RuntimeException("Tracker must be initialized before usage");
        }
        TrackHelper.track().download().with(mPiwikTracker);
    }

    @Override
    public String getName() {
        return "Piwik";
    }

    @Override
    public void onHostResume() {
    }

    @Override
    public void onHostPause() {
        if (mPiwikTracker != null) {
            mPiwikTracker.dispatch();
        }
    }

    @Override
    public void onHostDestroy() {
    }

}
