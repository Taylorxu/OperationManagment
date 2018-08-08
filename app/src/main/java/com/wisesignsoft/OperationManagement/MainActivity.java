package com.wisesignsoft.OperationManagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    long clickTime;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            toast = Toast.makeText(MainActivity.this, getResources().getString(R.string.exit_main), Toast.LENGTH_SHORT);
            toast.show();
            clickTime = System.currentTimeMillis();
        } else {
            finish();
            if (toast != null) {
                toast.cancel();
                toast = null;
            }
        }
    }

}
