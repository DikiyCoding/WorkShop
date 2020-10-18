package com.dikiy.workshop.code.sdks.facebook.samples;

import android.os.Parcel;
import android.os.Parcelable;

import com.facebook.AccessToken;

class UserInfo implements Parcelable {

    private String userName;
    private AccessToken accessToken;

    UserInfo(String userName, AccessToken accessToken) {
        this.userName = userName;
        this.accessToken = accessToken;
    }

    String getUserName() {
        return userName;
    }

    AccessToken getAccessToken() {
        return accessToken;
    }

    private UserInfo(Parcel parcel) {
        this.userName = parcel.readString();
        this.accessToken = parcel.readParcelable(UserInfo.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeParcelable(accessToken, 0);
    }

    public static final Parcelable.Creator<UserInfo> CREATOR =
            new Parcelable.Creator() {

                @Override
                public UserInfo createFromParcel(Parcel source) {
                    return new UserInfo(source);
                }

                @Override
                public UserInfo[] newArray(int size) {
                    return new UserInfo[size];
                }
            };
}
