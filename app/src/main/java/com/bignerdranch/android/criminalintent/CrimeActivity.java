package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

public class CrimeActivity extends FragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.bignerbranch.android.criminalintent.crime_id";
    public static final String RETURN_DATA_TO_CRIMELISTFRAGMENT = "returnData";
    private int catchExtra = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        catchExtra = getIntent().getExtras().getInt(CrimeListFragment.PUT_EXTRA_TO_CRIME_ACTIVITY);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if(fragment == null){
           fragment =  createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer,fragment).commit();

        }
        setAnswerShown();
    }
    protected Fragment createFragment(){
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
        }


    public static Intent newIntent(Context packageContext, UUID crimeId){// FIXME для вызова самой Activity!
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
        }

private void setAnswerShown(){
        Intent result = new Intent();
        result.putExtra(RETURN_DATA_TO_CRIMELISTFRAGMENT, catchExtra);
        setResult(RESULT_OK,result);
    }
}


