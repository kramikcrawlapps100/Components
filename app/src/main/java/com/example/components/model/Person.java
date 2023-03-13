package com.example.components.model;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Person implements Parcelable {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    PendingIntent pIntent = null;

    public Person(PendingIntent pi)
    {
        pIntent = pi;
    }

    public Person(Parcel parcel) {
        this.name = parcel.readString();
        this.age = parcel.readInt();
        pIntent = (PendingIntent)parcel.readValue(getClass().getClassLoader());
    }
    
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
