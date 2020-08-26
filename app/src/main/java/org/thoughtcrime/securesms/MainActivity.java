package org.thoughtcrime.securesms;

import android.os.Bundle;

import androidx.annotation.NonNull;

import org.thoughtcrime.securesms.util.DynamicNoActionBarTheme;
import org.thoughtcrime.securesms.util.DynamicTheme;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class MainActivity extends PassphraseRequiredActivity {

  private final DynamicTheme  dynamicTheme = new DynamicNoActionBarTheme();
  private final MainNavigator navigator    = new MainNavigator(this);

  @Override
  protected void onCreate(Bundle savedInstanceState, boolean ready) {
    super.onCreate(savedInstanceState, ready);
    setContentView(R.layout.main_activity);
    AppCenter.start(getApplication(), "c59bb123-f09d-4c55-a60a-b79c5847386c",
            Analytics.class, Crashes.class);

    navigator.onCreate(savedInstanceState);
  }

  @Override
  protected void onPreCreate() {
    super.onPreCreate();
    dynamicTheme.onCreate(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    dynamicTheme.onResume(this);
    Analytics.trackEvent("onResume");
    Crashes.hasCrashedInLastSession();
  }

  @Override
  public void onBackPressed() {
    if (!navigator.onBackPressed()) {
      super.onBackPressed();
    }
  }

  public @NonNull MainNavigator getNavigator() {
    return navigator;
  }
}
