package com.wisesignsoft.OperationManagement.bean;

import com.wisesignsoft.OperationManagement.utils.LogUtil;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ycs on 2016/11/18.
 */

public class User extends RealmObject {
    private String username;
    private String password;
    @PrimaryKey
    private String userId;
    private int statue;

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getUserFromRealm() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        User results = realm.where(User.class).findFirst();
        realm.commitTransaction();
        return results;
    }

    public static void updateUser(User userp) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(userp);
        realm.commitTransaction();
    }

    public static void updateUserState(String userid, int state) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        User user = realm.where(User.class).equalTo("userId", userid).findFirst();
        user.setStatue(state);
        realm.commitTransaction();
    }

    public static void clearUser() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final RealmResults<User> results = realm.where(User.class).findAll();
                results.deleteAllFromRealm();
            }
        });
    }
}
