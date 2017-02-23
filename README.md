Piwik SDK for React Native
========================

This document describes how to get started using the Piwik Tracking SDK for React Native. 
[Piwik](http://piwik.org) is the leading open source web analytics platform 
that gives you valuable insights into your website's visitors, 
your marketing campaigns and much more, so you can optimize your strategy and experience of your visitors.
This relies on the native [Android SDK](https://github.com/piwik/piwik-sdk-android) for Piwik and this README page is heavily inspired by it.

## Getting started

Integrating Piwik into your React Native app
 
1. [Install Piwik](http://piwik.org/docs/installation/)
2. [Create a new website in the Piwik web interface](http://piwik.org/docs/manage-websites/). Copy the Website ID from "Settings > Websites".
3. [Include the library](#include-library)
4. [Initialize Tracker](#initialize-tracker).
5. [Track screen views, goals and more](#tracker-usage).


###Include the library

####Android
- Open `/android/settings.gradle`
- Below `include ':app'` add:

```
include ':react-native-piwik'
project(':react-native-piwik').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-piwik/android/') 
```
- Open `android/app/build.gradle`
- Add the following under `dependencies`:

```
compile project(':react-native-piwik')
```
- Open your `MainApplication.java` file under `android/src`
- Import the lib using `import de.bonify.reactnativepiwik.PiwikPackage;`
- Add the following `new PiwikPackage()` to the `getPackages` function.


### Tracker Usage

#### Track screen views

To send a screen view set the screen path and titles on the tracker.

```javascript
Piwik.trackScrenn("/your_activity", "Title")
```

#### Track events

To collect data about user's interaction with interactive components of your app, like button presses or the use of a particular item in a game 
use trackEvent.

```javascript

Piwik.trackEvent("category", "action", "label", 1000)
```

#### Track goals

If you want to trigger a conversion manually or track some user interaction simply call the method trackGoal. Read more about what is a [Goal in Piwik](http://piwik.org/docs/tracking-goals-web-analytics/).

```javascript

Piwik.trackGoal(1, revenue)
```


## Contribute

* Fork the project
* Create a feature branch based on the 'master' branch
* Create a PR and feel proud.



## License

react-native-piwik is released under the MIT license, see [LICENSE](https://github.com/BonifyByForteil/react-native-piwik/blob/master/LICENSE).

