package com.example.alexbanks.cbiapp.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothClass;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.alexbanks.cbiapp.R;
import com.example.alexbanks.cbiapp.admin.CBIDeviceAdmin;
import com.example.alexbanks.cbiapp.admin.CBIKioskLauncherActivity;
import com.example.alexbanks.cbiapp.input.CustomInputManager;
import com.example.alexbanks.cbiapp.progress.Progress;
import com.example.alexbanks.cbiapp.progress.ProgressState;

import java.io.IOException;

/* This class contains general methods shared by most activities in this app */
public class BaseActivity<ps extends ProgressState> extends FragmentActivity {

    public static final String PROGRESS_EXTRA_KEY = "progress_extra";

    public Progress progress;

    //public DevicePolicyManager dpm;
    //public ComponentName cbiAdminDeviceSample;

    //public static final int REQUEST_CODE_ENABLE_ADMIN = 1;
    //public static final int REQUEST_CODE_ENABLE_DPC = 2;

    /*private void enableAdmin(){
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, this.cbiAdminDeviceSample);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, this.getString(R.string.cbi_admin_add_admin_extra_text));
        startActivityForResult(intent, BaseActivity.REQUEST_CODE_ENABLE_ADMIN);
    }

    public boolean isDPCEnabled(){
        dpm=(DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        Log.d("llll", "aaaa" + getApplicationContext().getPackageName() + ":" + dpm.isDeviceOwnerApp(getApplicationContext().getPackageName()));
        return dpm.isProfileOwnerApp(getApplicationContext().getPackageName());
    }

    private void enableDPC(){
        PackageManager pm = getPackageManager();
        if(!pm.hasSystemFeature(PackageManager.FEATURE_MANAGED_USERS)){
            Log.e("error", "device does not support work profiles");
        }
        Intent intent = new Intent(DevicePolicyManager.ACTION_PROVISION_MANAGED_PROFILE);
        intent.putExtra(DevicePolicyManager.EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME, this.getApplicationContext().getPackageName());
        if(intent.resolveActivity(this.getPackageManager()) == null){
            Log.e("error", "no intent handler");
        }else{
            Log.d("h", "ffff: what?");
            startActivityForResult(intent, BaseActivity.REQUEST_CODE_ENABLE_DPC);
            this.finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data){
        if(requestCode == BaseActivity.REQUEST_CODE_ENABLE_ADMIN){
            Log.d("h", "aaaa: admin stuff done");
        }
        else if(requestCode == BaseActivity.REQUEST_CODE_ENABLE_DPC){
            if(responseCode == Activity.RESULT_OK){
                Log.i("info", "profile created");
            }else{
                Log.e("error", "profile creation failed");
            }
        }
        else{
            super.onActivityResult(requestCode, responseCode, data);
        }
    }*/

    public void handleNavButtonClickHelp(){
        Log.d("ffff", "help clicked");
    }

    public void handleNavButtonClickBack(){
        this.progress.previousState();
        this.runActivityFromProgress(this.progress);
    }

    public void handleNavButtonClickCancel(){
        Log.d("ffff", "cancel clicked");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackageManager pm = getPackageManager();
        Log.d("h", "cccc: " + pm.hasSystemFeature(PackageManager.FEATURE_MANAGED_USERS));
        //this.dpm = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        //Log.d("bbbb", ""  + dpm.isProfileOwnerApp(getApplicationContext().getPackageName()));
        eventConfiguration();
        //hideStatusNavBar();
        this.checkProgress();
        CustomInputManager.activeProgressState=progress.getCurrentProgressState();
        CustomInputManager.clearCustomInputs();
        Log.d("nullupdate", "has nav fragment " + this.hasNavFragment());
        //this.cbiAdminDeviceSample = CBIDeviceAdmin.getComponentName(this);
        //Log.d("h", "xxxx: " + dpm.isAdminActive(this.cbiAdminDeviceSample));
        //enableAdmin();
        //dpm.setStatusBarDisabled(cbiAdminDeviceSample, false);
        //if(!isDPCEnabled()) {
            //enableDPC();
        //    Log.d("h", "xxxx: what now???");
        //}else {
        //    Log.d("h", "xxxx: dpc now done");
        //}
        //Log.d("h", "xxxx: " + dpm.isAdminActive(this.cbiAdminDeviceSample));
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d("bbbb", "a"  + dpm.isProfileOwnerApp(getApplicationContext().getPackageName()));
        /* Re-hide the status/nav bar after being away for a while */
        //Log.d("derp", "dd: " + dpm.isLockTaskPermitted("com.example.alexbanks.cbiapp"));
        //dpm.setLockTaskPackages(cbiAdminDeviceSample, new String[]{"com.example.alexbanks.cbiapp"});
        //Log.d("bbbb", "aff" + dpm.isDeviceOwnerApp(getApplicationContext().getPackageName()));
        hideStatusNavBar();
        //dpm.setLockTaskFeatures(cbiAdminDeviceSample, DevicePolicyManager.LOCK_TASK_FEATURE_KEYGUARD);
        this.checkProgress();
        /*if(CBIKioskLauncherActivity.getDPM(this).isLockTaskPermitted(getApplicationContext().getPackageName())){
            this.startLockTask();
            Log.d("bad", "no issues here");
        }else{
            Log.d("bad", "we have an issue here, no dpm");
        }*/
        if(this.hasNavFragment())
            this.startNavFragment();
        //dpm.setStatusBarDisabled(cbiAdminDeviceSample, true);
        //IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MAIN);
        //intentFilter.addCategory(Intent.CATEGORY_HOME);
        //intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        //dpm.addPersistentPreferredActivity(cbiAdminDeviceSample, intentFilter, new ComponentName(getPackageName(), BaseActivity.class.getName()));
        //Log.d("h", "xxxx: " + dpm.isAdminActive(this.cbiAdminDeviceSample));
    }

    private static final String MESSAGE_PROGRESS = "cbiapp.progress.current";

    /*
    This one checks to see if this is the right activity for the given progress state
     */
    public boolean nextProgress(){
        //TODO remove true
        if(this.progress.checkPreviousProgressStates() == -1 || true){
            this.progress.nextState();
            this.runActivityFromProgress(this.progress);
            Log.d("something", "good happened");
            return true;
        }
        else{
            Log.d("h", "well this is unfortunate");
            return false;
        }
    }

    public boolean isActivityCorrect(Progress progress){
        return progress.getCurrentProgressState().getActivityClass().equals(this.getClass());
    }

    protected void checkProgress(){
        if(this.loadProgress()){
            if(!this.isActivityCorrect(this.progress)){
                this.runActivityFromProgress(this.progress);
            }
        }else{
            this.runActivityFromProgress(Progress.createNewGuestProgress());
        }
    }

    public boolean loadProgress(){
        Intent i = this.getIntent();
        if(i.hasExtra(BaseActivity.PROGRESS_EXTRA_KEY)){
            this.progress = i.getParcelableExtra(BaseActivity.PROGRESS_EXTRA_KEY);
            return true;
        }else{
            return false;
        }
    }

    public void saveProgress(){
        Intent intent = this.getIntent();
        intent.putExtra(BaseActivity.PROGRESS_EXTRA_KEY, this.progress);
        this.setIntent(intent);
    }

    public void runActivityFromProgress(Progress progress){
        runActivityFromProgress(progress, this);
    }

    //Run activity from progress context, and optionally a supplied base activity (null if no previous base activity exists)
    public static void runActivityFromProgress(Progress progress, Activity activity){
        ProgressState currentState = progress.getCurrentProgressState();
        Intent intent;
        BaseActivity baseActivity = null;
        if(activity instanceof BaseActivity)baseActivity=(BaseActivity)activity;

        if(baseActivity != null && baseActivity.isActivityCorrect(progress)){
            //Currently running activity matches current progress state, so just save and continue
            intent = baseActivity.getIntent();
        }
        else {
            //Create new intent for the correct activity
            intent = new Intent(activity, currentState.getActivityClass());
        }
        intent.putExtra(BaseActivity.PROGRESS_EXTRA_KEY, progress);
        activity.startActivity(intent);
        if(baseActivity != null && !baseActivity.isActivityCorrect(progress)){
            Log.d("finished", "finished a progress " + (activity.getClass().getName()));
            baseActivity.finish();
            //baseActivity.removeNavbarFragment();
        }
    }

    private boolean hasNavFragment(){
        return findViewById(R.id.layout_newguest) != null;
    }

    private void startNavFragment(){
        NavButtonGroupFragment navFragment = new NavButtonGroupFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_newguest, navFragment).commit();
    }

    /* Setup some event callbacks here */
    private void eventConfiguration(){
        View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                hideStatusNavBar();
            }
        });
    }

    /* Hides the status and nav bar, must be called whenever UI options change */
    private void hideStatusNavBar(){
        View v = this.getWindow().getDecorView();
        v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN |
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void changeFragment(Fragment targetFragment){
        //getSupportFragmentManager().beginTransaction().replace(R.id.layout_newguest_name
    }

    /*private void removeNavbarFragment(){
        Fragment f = this.getSupportFragmentManager().findFragmentByTag("nav_fragment");
        if(f != null){
            Log.d("nullupdate", "f located, now removing");
            this.getSupportFragmentManager().beginTransaction().remove(f).commitNowAllowingStateLoss();
        }else{
            Log.d("nullupdate", "f not located, removal failure (bad)");
        }
    }*/

    public ps getProgressState(){
        Log.d("aaaa", "activityType " + this.getClass().getName());
        return (ps)this.progress.getCurrentProgressState();

    }

}
