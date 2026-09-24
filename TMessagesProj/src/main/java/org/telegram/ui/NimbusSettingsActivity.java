package org.telegram.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;

public class NimbusSettingsActivity extends BaseFragment {

    // Поменяй на ссылку своего канала, когда создашь его
    private static final String PLUGINS_CHANNEL_URL = "https://t.me/nimbus_plugins";

    private SharedPreferences prefs(Context context) {
        return context.getSharedPreferences("nimbus_plugins", Context.MODE_PRIVATE);
    }

    @Override
    public View createView(Context context) {
        actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        actionBar.setAllowOverlayTitle(true);
        actionBar.setTitle("Настройки Nimbus");

        ScrollView scrollView = new ScrollView(context);
        scrollView.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));

        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        int pad = AndroidUtilities.dp(16);
        container.setPadding(pad, pad, pad, pad);

        TextView header = new TextView(context);
        header.setText("Плагины — свои");
        header.setTextSize(14);
        header.setTextColor(0xFFE65100);
        header.setPadding(0, 0, 0, AndroidUtilities.dp(8));
        container.addView(header);

        container.addView(createToggleRow(context, "Анимированный баннер профиля", "GIF или видео в профиле", "plugin_banner"));
        container.addView(createToggleRow(context, "Значки профиля", "Владелец, Кодер и другие", "plugin_badges"));

        TextView storeHeader = new TextView(context);
        storeHeader.setText("Ещё больше плагинов");
        storeHeader.setTextSize(14);
        storeHeader.setTextColor(0xFFE65100);
        storeHeader.setPadding(0, AndroidUtilities.dp(20), 0, AndroidUtilities.dp(8));
        container.addView(storeHeader);

        container.addView(createLinkRow(context, "Магазин плагинов", "Открыть канал с плагинами от людей"));

        scrollView.addView(container, new android.view.ViewGroup.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

        fragmentView = scrollView;
        return fragmentView;
    }

    private LinearLayout createToggleRow(Context context, String title, String subtitle, String prefKey) {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        int vpad = AndroidUtilities.dp(12);
        row.setPadding(0, vpad, 0, vpad);

        LinearLayout textContainer = new LinearLayout(context);
        textContainer.setOrientation(LinearLayout.VERTICAL);

        TextView titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextSize(16);
        titleView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));

        TextView subtitleView = new TextView(context);
        subtitleView.setText(subtitle);
        subtitleView.setTextSize(13);
        subtitleView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));

        textContainer.addView(titleView);
        textContainer.addView(subtitleView);

        Switch toggle = new Switch(context);
        toggle.setChecked(prefs(context).getBoolean(prefKey, false));
        toggle.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            prefs(context).edit().putBoolean(prefKey, isChecked).apply();
        });

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        LinearLayout.LayoutParams toggleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        row.addView(textContainer, textParams);
        row.addView(toggle, toggleParams);

        return row;
    }

    private LinearLayout createLinkRow(Context context, String title, String subtitle) {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.VERTICAL);
        int vpad = AndroidUtilities.dp(12);
        row.setPadding(0, vpad, 0, vpad);
        row.setClickable(true);
        row.setFocusable(true);

        TextView titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextSize(16);
        titleView.setTextColor(0xFFE65100);

        TextView subtitleView = new TextView(context);
        subtitleView.setText(subtitle);
        subtitleView.setTextSize(13);
        subtitleView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));

        row.addView(titleView);
        row.addView(subtitleView);

        row.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(PLUGINS_CHANNEL_URL));
            context.startActivity(intent);
        });

        return row;
    }
    }
