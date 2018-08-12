
package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
;

public class TimePickerFragment extends DialogFragment {
    private static final String ARG_TIME = "time";
    private TimePicker mTimePicker;
    public static final String EXTRA_TIME = "com.bignerdranch.android.criminalintent.time";

    private void sendResult(int  resultCode, Calendar date){
        if(getTargetFragment() == null){return;}

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode, intent);
    }


    public static TimePickerFragment newInstanse(Calendar date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar calendar = (Calendar) getArguments().getSerializable(ARG_TIME);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time,null);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);

        return new AlertDialog.Builder(getActivity()).setView(v)
                .setTitle(R.string.time_picker_title).
                        setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int hour = mTimePicker.getCurrentHour();
                                int minute = mTimePicker.getCurrentMinute();
                                Calendar res = Calendar.getInstance();
                                res.set(year,month,day,hour,minute);
                                int test = res.get(Calendar.HOUR_OF_DAY);
                                sendResult(Activity.RESULT_OK,res);
                            }
                        }).create();
    }



}
