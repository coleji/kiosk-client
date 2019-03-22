package com.example.alexbanks.cbiapp.progress.newguest;

import android.app.Activity;

import com.example.alexbanks.cbiapp.activity.newguest.NewGuestBeginSignatureActivity;
import com.example.alexbanks.cbiapp.activity.newguest.NewGuestWaiverActivity;
import com.example.alexbanks.cbiapp.progress.ProgressState;

public class ProgressStateNewGuestSignature extends ProgressState {
    public ProgressStateNewGuestSignature(){
    }

    @Override
    public Class<? extends Activity> getActivityClass(){ return NewGuestBeginSignatureActivity.class; }

    @Override
    public ProgressState createNextProgressState() {
        return null;
    }

    @Override
    public boolean isProgressStateComplete(){
        return true;
    }
}
