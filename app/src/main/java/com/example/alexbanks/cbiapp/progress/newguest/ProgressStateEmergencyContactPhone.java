package com.example.alexbanks.cbiapp.progress.newguest;

import android.app.Activity;

import com.example.alexbanks.cbiapp.activity.newguest.EmergencyContactPhoneActivity;
import com.example.alexbanks.cbiapp.progress.ProgressState;

public class ProgressStateEmergencyContactPhone extends ProgressState {

    public ProgressStateEmergencyContactPhone(){

    }

    @Override
    public Class<? extends Activity> getActivityClass(){
        return EmergencyContactPhoneActivity.class;
    }

    @Override
    public ProgressState createNextProgressState(){
        return new ProgressStateNewGuestWaiver();
    }

    @Override
    public boolean isProgressStateComplete(){
        return true;
    }

}
