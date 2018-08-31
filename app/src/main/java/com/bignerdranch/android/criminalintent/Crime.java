package com.bignerdranch.android.criminalintent;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private String mSuspect;
    private Calendar mDate;
    private boolean mSolved;


    public Crime(){
        // Генерирование уникального идентификатора
        mId = UUID.randomUUID();
        mDate = Calendar.getInstance();
     }

    public Crime(UUID id){
        mId = id;
        mDate = Calendar.getInstance();
    }

    public UUID getId() {
        return mId;
    }


    public String getTitle() {
        return mTitle;
    }

    public Calendar getDate() {
        return mDate;
    }

    public void setDate(Calendar date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }
}
