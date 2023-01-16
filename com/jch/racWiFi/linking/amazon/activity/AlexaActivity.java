package com.jch.racWiFi.linking.amazon.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;
import dagger.android.AndroidInjection;

public class AlexaActivity extends CoreActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Uri data;
        AndroidInjection.inject((Activity) this);
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_alexa);
        NavController findNavController = Navigation.findNavController(this, R.id.alexaNavHostFragment);
        Intent intent = getIntent();
        if (intent != null && (data = intent.getData()) != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable(Constants.Alexa.CALLBACK, data);
            findNavController.navigate((int) R.id.alexaLinkedFragment, bundle2);
        }
    }
}
