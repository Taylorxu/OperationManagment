package com.wisesignsoft.OperationManagement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.SelectorView;
import com.wisesignsoft.OperationManagement.ui.fragment.ContactFragment;
import com.wisesignsoft.OperationManagement.ui.fragment.MapFragment;
import com.wisesignsoft.OperationManagement.ui.fragment.MessageFragment;
import com.wisesignsoft.OperationManagement.ui.fragment.MyFragment;
import com.wisesignsoft.OperationManagement.ui.fragment.OrdinaryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SelectorView sv_home;
    private SelectorView sv_message;
    private SelectorView sv_contact;
    private SelectorView sv_my;

    long clickTime;
    Toast toast;
    private MyTitle mt_main;
    private FrameLayout fl_main;
    private OrdinaryFragment ordinaryFragment;
    private MapFragment mapFragment;
    private MessageFragment messageFragment;
    private ContactFragment contactFragment;
    private MyFragment myFragment;
    private FragmentManager manager;
    private List<Fragment> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mt_main = (MyTitle) findViewById(R.id.mt_main);
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        sv_home = (SelectorView) findViewById(R.id.sv_main_home);
        sv_message = (SelectorView) findViewById(R.id.sv_main_message);
        sv_contact = (SelectorView) findViewById(R.id.sv_main_contact);
        sv_my = (SelectorView) findViewById(R.id.sv_main_my);

        ordinaryFragment = new OrdinaryFragment();
        mapFragment = new MapFragment();
        messageFragment = new MessageFragment();
        contactFragment = new ContactFragment();
        myFragment = new MyFragment();
        list = new ArrayList<>();
        list.add(ordinaryFragment);
        list.add(mapFragment);
        list.add(messageFragment);
        list.add(contactFragment);
        list.add(myFragment);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment fragment : list) {
            transaction.add(R.id.fl_main, fragment);
        }
        transaction.commit();

        sv_home.setOnClickListener(this);
        sv_contact.setOnClickListener(this);
        sv_message.setOnClickListener(this);
        sv_my.setOnClickListener(this);

        sv_home.setInit(R.drawable.selector_home, getResources().getString(R.string.homed));
        sv_message.setInit(R.drawable.selector_message, getResources().getString(R.string.message));
        sv_contact.setInit(R.drawable.selector_contact, getResources().getString(R.string.contact));
        sv_my.setInit(R.drawable.selector_my, getResources().getString(R.string.my));
        /*默认设置*/
        selectHome();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sv_main_home:
                selectHome();
                break;
            case R.id.sv_main_message:
                selectMessage();
                break;
            case R.id.sv_main_contact:
                selectContact();
                break;
            case R.id.sv_main_my:
                selectMy();
                break;
        }
    }

    private void setSelect() {
        sv_home.setSelect(false);
        sv_home.setEnabled(true);
        sv_message.setSelect(false);
        sv_message.setEnabled(true);
        sv_contact.setSelect(false);
        sv_contact.setEnabled(true);
        sv_my.setSelect(false);
        sv_my.setEnabled(true);
        FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment fragment : list) {
            transaction.hide(fragment);
        }
        transaction.commit();
    }

    private void selectHome() {
        setSelect();
        sv_home.setSelect(true);
        sv_home.setEnabled(false);
        mt_main.setTitle(getResources().getString(R.string.homed));
        mt_main.setIvRight(true, R.mipmap.map, ordinaryListener);
        manager.beginTransaction().show(ordinaryFragment).commit();
    }

    private void selectMap() {
        sv_home.setSelect(true);
        sv_home.setEnabled(false);
        mt_main.setTitle(getResources().getString(R.string.map));
        mt_main.setIvRight(true, R.mipmap.menu, mapListener);
        manager.beginTransaction().show(mapFragment).commit();
        mapFragment.onStart();
    }

    private void selectMessage() {
        setSelect();
        sv_message.setSelect(true);
        sv_message.setEnabled(false);
        mt_main.setTitle(getResources().getString(R.string.message));
        mt_main.setIvRight(false, -1, null);
        manager.beginTransaction().show(messageFragment).commit();
    }

    private void selectContact() {
        setSelect();
        sv_contact.setSelect(true);
        sv_contact.setEnabled(false);
        mt_main.setTitle("联系人列表");
        mt_main.setIvRight(false, -1, null);
        manager.beginTransaction().show(contactFragment).commit();
    }

    private void selectMy() {
        setSelect();
        sv_my.setSelect(true);
        sv_my.setEnabled(false);
        mt_main.setTitle(getResources().getString(R.string.my));
        mt_main.setIvRight(false, -1, null);
        manager.beginTransaction().show(myFragment).commit();
    }

    private View.OnClickListener ordinaryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            selectMap();
        }
    };
    private View.OnClickListener mapListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            selectHome();
        }
    };


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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
