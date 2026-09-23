package org.telegram.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class NimbusSettingsActivity extends BaseFragment {

    @Override
    public View createView(Context context) {
        actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        actionBar.setAllowOverlayTitle(true);
        actionBar.setTitle("Настройки Nimbus");

        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));

        TextView textView = new TextView(context);
        textView.setText("Скоро здесь появятся настройки");
        textView.setTextSize(16);
        textView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        textView.setGravity(Gravity.CENTER);
        frameLayout.addView(textView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));

        fragmentView = frameLayout;
        return fragmentView;
    }
}
