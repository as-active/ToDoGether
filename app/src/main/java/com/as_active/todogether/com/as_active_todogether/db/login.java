package com.as_active.todogether.com.as_active_todogether.db;

import com.google.firebase.database.IgnoreExtraProperties;
import java.security.Timestamp;
import java.util.Date;

/**
 * Created by Alex on 10.01.17.
 */

@IgnoreExtraProperties
public class login {

    public String logon_time;
    public long logon_timestamp;

    public login() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public login(Date login_date) {
        this.logon_time = login_date.toString();
        this.logon_timestamp = login_date.getTime();
    }

}

