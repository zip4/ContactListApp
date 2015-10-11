package com.example.pashulya.contactlistapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pashulya on 01.09.2015.
 */
public class ContactItem implements Parcelable {
    public String Lastname;
    public String Firstname;
    public String Number;

    public ContactItem(String firstname, String lastname, String number) {
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.Number = number;
    }

    protected ContactItem(Parcel in) {
        Lastname = in.readString();
        Firstname = in.readString();
        Number = in.readString();
    }

    public static final Creator<ContactItem> CREATOR = new Creator<ContactItem>() {
        @Override
        public ContactItem createFromParcel(Parcel in) {
            return new ContactItem(in);
        }

        @Override
        public ContactItem[] newArray(int size) {
            return new ContactItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Lastname);
        dest.writeString(Firstname);
        dest.writeString(Number);
    }
}
