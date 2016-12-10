package com.example.testretrofit;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable {


    private String userPassword;
    private String userName;
    private String passSynNo;
    private String orgName;
    private String mobile;


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassSynNo() {
        return passSynNo;
    }

    public void setPassSynNo(String passSynNo) {
        this.passSynNo = passSynNo;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userPassword);
        dest.writeString(this.userName);
        dest.writeString(this.passSynNo);
        dest.writeString(this.orgName);
        dest.writeString(this.mobile);
    }

    public Steps() {
    }

    protected Steps(Parcel in) {
        this.userPassword = in.readString();
        this.userName = in.readString();
        this.passSynNo = in.readString();
        this.orgName = in.readString();
        this.mobile = in.readString();
    }

    public static final Parcelable.Creator<Steps> CREATOR = new Parcelable.Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel source) {
            return new Steps(source);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };
}
