package com.jch.racWiFi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jch_hitachi.aircloudglobal.R;

public class TestActivity extends CoreActivity implements AdapterView.OnItemSelectedListener {
    @BindView(2131363172)
    ConstraintLayout mTextViewRefreshing;
    String[] users = {"Living Room", "Bed Room", "Living Room 2", "Kitchen", "Living Room", "Bed Room", "Living Room 29948868877877777hhhhhhhhhhhhhh", "Kitchen1234", "abagcmmc"};

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.weekly_timer_copy_frame);
        ButterKnife.bind((Activity) this);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_device_name);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, this.users);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_drop_down_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
        this.mTextViewRefreshing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                spinner.performClick();
            }
        });
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        Context applicationContext = getApplicationContext();
        Toast.makeText(applicationContext, "Selected User: " + this.users[i], 0).show();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
