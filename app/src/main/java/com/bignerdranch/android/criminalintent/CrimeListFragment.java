package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView; // FIXME RecyclerView класс для отображения списка.
    private CrimeAdapter mAdapter;
    private static final int REQUEST_EXEMPLE_ID = 0;
    public static final String PUT_EXTRA_TO_CRIME_ACTIVITY = "extra";
    private int current_index = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list, container,false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;

   }



    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if(mAdapter == null){
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter); }
            else{
            if(current_index != -1){
                mAdapter.notifyItemChanged(current_index);
                current_index = -1;
            } //FIXME разобраться с сохранением данных.
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }


    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Crime mCrime;

        @Override
        public void onClick(View v){// откуда View??
            Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getId());
            int index = mAdapter.giveIndexOfCrime(mCrime);
            intent.putExtra(PUT_EXTRA_TO_CRIME_ACTIVITY,index);
            startActivityForResult(intent, REQUEST_EXEMPLE_ID);
        }


        public CrimeHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindCrime(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().format(new Date()));
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

    }


    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){ // FIXME Конструктор CrimeAdapter!
            mCrimes = crimes;}

        @Override
                public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType){
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view = layoutInflater.inflate(R.layout.list_item_crime, parent,false);
                return new CrimeHolder(view);

        }

        @Override
                public void onBindViewHolder(CrimeHolder holder, int position){
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
            }

        @Override
                public int getItemCount(){
            return mCrimes.size();
        }

        int giveIndexOfCrime(Crime crime){
         return mCrimes.indexOf(crime);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result){

        if(resultCode != Activity.RESULT_OK){
            return;
        }if(requestCode == REQUEST_EXEMPLE_ID){
            if (result == null){return;}
            int index = result.getExtras().getInt(CrimeActivity.RETURN_DATA_TO_CRIMELISTFRAGMENT);
            if(index == -1){return;}
            else {
                current_index = index;}
        }
    }

}
