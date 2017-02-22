package de.bonify.reactnativepiwik;


import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import org.piwik.sdk.Tracker;
import org.piwik.sdk.TrackHelper;
import android.support.annotation.NonNull;


public class PiwikModule extends ReactContextBaseJavaModule {

    public PiwikModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    private Tracker mPiwikTracker;

    @ReactMethod
    public void initTracker(String url, int id) {
        try {
            mPiwikTracker = Piwik.getInstance(this).newTracker(url, id);
        } catch (MalformedURLException e) {
            Log.w(Tracker.LOGGER_TAG, "url is malformed", e);
            return null;
        }
        return mPiwikTracker;
    }

    @ReactMethod
    public void setUserId(String userId) {
        mPiwikTracker.setUserId(userId);
    }

    @ReactMethod
    public void trackEvent(@NonNull String category, @NonNull String action, String name, Float value) {
        if (mPiwikTracker == null) {
            throw new RuntimeException('Tracker must be initialized before usage');
        }
        TrackHelper.track().event(category, action).name(name).value(value).with(mPiwikTracker);
    }

    @ReactMethod
    public void trackGoal(@NonNull int goalId, Float revenue) {
        if (mPiwikTracker == null) {
            throw new RuntimeException('Tracker must be initialized before usage');
        }
        TrackHelper.track().goal(goalId).revenue(revenue).with(mPiwikTracker);
    }


    @ReactMethod
    public void trackGoal(@NonNull int goalId, Float revenue) {
        if (mPiwikTracker == null) {
            throw new RuntimeException('Tracker must be initialized before usage');
        }
        TrackHelper.track().goal(goalId).revenue(revenue).with(mPiwikTracker);
    }


    @ReactMethod
    public void trackAppDownload() {
        if (mPiwikTracker == null) {
            throw new RuntimeException('Tracker must be initialized before usage');
        }
        TrackHelper.track().download().with(mPiwikTracker);
    }

    @Override
    public String getName() {
        return "Piwik";
    }


}