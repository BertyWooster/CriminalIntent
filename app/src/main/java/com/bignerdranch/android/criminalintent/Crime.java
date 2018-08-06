package com.bignerdranch.android.criminalintent;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private DateFormat mDate;// FIXME Остается под вопросом!!
    private boolean mSolved;


    public Crime(){
        // Генерирование уникального идентификатора
        mId = UUID.randomUUID();
        mDate = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);
     }

    public UUID getId() {
        return mId;
    }


    public String getTitle() {
        return mTitle;
    }

    public DateFormat getDate() {
        return mDate;
    }

    public void setDate(DateFormat date) {
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
}
