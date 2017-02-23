var Piwik = require('react-native').NativeModules.Piwik;
module.exports = {
  initTracker: Piwik.initTracker,
  setUserId: Piwik.setUserId,
  trackAppDownload: Piwik.trackAppDownload,
  trackEvent: function(category, action, name, value) {
    //
    Piwik.trackEvent(category, action, name, value);
  },
  trackGoal: function(goalId, revenue) {
    //
    Piwik.trackGoal(goalId, revenue);
  },
  trackScreen: function(screen, title) {
    //
    Piwik.trackScreen(screen, title);
  }
};
