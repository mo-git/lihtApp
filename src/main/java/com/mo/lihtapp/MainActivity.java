package com.mo.lihtapp;

import android.hardware.Camera;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;
import android.view.WindowManager;
public class MainActivity extends Activity implements View.OnClickListener{
    private ToggleButton toggleButton;

    private Camera camera ;
    Camera.Parameters param;
    private boolean isOpen = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("11111","111111");
        try {
            camera = Camera.open();
        } catch (Exception e) {
            camera = Camera.open(0);

        }
        toggleButton = (ToggleButton) this.findViewById(R.id.toggleButton1);
        toggleButton.setOnClickListener(this);
        param = camera.getParameters();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        toggleButton.setChecked(false);
        param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        toggleButton.setBackgroundColor(0x30ffffff);
        toggleButton.setBackgroundResource(R.drawable.red_shape);
        camera.setParameters(param);
        camera.startPreview();
    }

    @Override
    public void onClick(View v) {
        Log.i("3333","333");
        ToggleButton tb = (ToggleButton) v;

        if(!tb.isChecked()){
            isOpen = true;
            param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            toggleButton.setBackgroundColor(0x30ffffff);
            toggleButton.setBackgroundResource(R.drawable.red_shape);
            camera.setParameters(param);
            camera.startPreview();
        }else{
            isOpen = false;
            param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            toggleButton.setBackgroundColor(0xffffffff);
            toggleButton.setBackgroundResource(R.drawable.green_shape);
            camera.setParameters(param);
            camera.stopPreview();
        }

    }

    @Override
    public void finish() {
        super.finish();
        param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(param);
        camera.stopPreview();
        camera.release();
        camera = null;

    }
}
