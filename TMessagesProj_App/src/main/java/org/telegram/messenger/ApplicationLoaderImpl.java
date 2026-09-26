package org.telegram.messenger;

import org.telegram.messenger.regular.BuildConfig;

public class ApplicationLoaderImpl extends ApplicationLoader {
    @Override
    protected String onGetApplicationId() {
        return BuildConfig.APPLICATION_ID;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            org.telegram.plugins.NimbusPluginManager.loadAllPlugins(applicationContext);
        } catch (Throwable e) {
            FileLog.e(e);
        }
    }
}
