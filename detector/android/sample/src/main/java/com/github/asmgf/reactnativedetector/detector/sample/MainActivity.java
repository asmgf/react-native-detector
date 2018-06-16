package com.github.asmgf.reactnativedetector.detector.sample;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.github.asmgf.reactnativedetector.detector.DetectorView;

public class MainActivity extends AppCompatActivity {
    private static final String[] PERMISSIONS = new String[]{Manifest.permission.CAMERA};

    private DetectorView detectorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        View cueView = findViewById(R.id.view_cue);
        detectorView = findViewById(R.id.view_detector);
        detectorView.setListener(detected -> runOnUiThread(() ->
                cueView.setVisibility(detected ? View.VISIBLE : View.GONE)
        ));
        ActivityCompat.requestPermissions(this, PERMISSIONS, 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length == 0) return;
        if (!checkPermissions()) {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.permissions_rationale)
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok, (DialogInterface dialog, int which) -> finish())
                    .show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkPermissions()) {
            detectorView.enableView();
        }
    }

    @Override
    protected void onPause() {
        detectorView.disableView();
        super.onPause();
    }

    private boolean checkPermissions() {
        for (String permission : PERMISSIONS) {
            int status = ActivityCompat.checkSelfPermission(this, permission);
            if (status != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
