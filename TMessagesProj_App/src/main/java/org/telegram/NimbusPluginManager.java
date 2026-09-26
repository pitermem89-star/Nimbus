package org.telegram.plugins;

import android.content.Context;
import com.chaquo.python.Python;
import com.chaquo.python.PyObject;
import com.chaquo.python.android.AndroidPlatform;

import java.io.File;

public class NimbusPluginManager {

    private static final String PLUGIN_EXTENSION = ".nimp";

    public static void init(Context context) {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
    }

    public static File getPluginsDir(Context context) {
        File dir = new File(context.getExternalFilesDir(null), "NimbusPlugins");
        if (!dir.exists()) dir.mkdirs();
        return dir;
    }

    public static void loadAllPlugins(Context context) {
        init(context);
        File dir = getPluginsDir(context);
        File[] files = dir.listFiles((d, name) -> name.endsWith(PLUGIN_EXTENSION));
        if (files == null) return;

        Python py = Python.getInstance();
        PyObject loader = py.getModule("nimbus_loader");

        for (File file : files) {
            try {
                loader.callAttr("load_plugin", file.getAbsolutePath(), null);
            } catch (Exception e) {
                android.util.Log.e("NimbusPlugins", "Не загрузился: " + file.getName(), e);
            }
        }
    }
}
