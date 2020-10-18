package com.dikiy.workshop.code.sdks.facebook.samples;

import com.facebook.AccessToken;
import com.facebook.login.LoginBehavior;

class Slot {
    private UserInfo userInfo;
    private final UserInfoCache userInfoCache;
    private LoginBehavior loginBehavior;

    Slot(int slotNumber, LoginBehavior loginBehavior) {
        this.loginBehavior = loginBehavior;
        this.userInfoCache = new UserInfoCache(slotNumber);
        this.userInfo = userInfoCache.get();
    }

    LoginBehavior getLoginBehavior() {
        return loginBehavior;
    }

    String getUserName() {
        return (userInfo != null) ? userInfo.getUserName() : null;
    }

    AccessToken getAccessToken() {
        return (userInfo != null) ? userInfo.getAccessToken() : null;
    }

    String getUserId() {
        return (userInfo != null) ? userInfo.getAccessToken().getUserId() : null;
    }

    UserInfo getUserInfo() {
        return userInfo;
    }

    void setUserInfo(UserInfo user) {
        userInfo = user;
        if (user == null) return;
        userInfoCache.put(user);
    }

    void clear() {
        userInfo = null;
        userInfoCache.clear();
    }
}