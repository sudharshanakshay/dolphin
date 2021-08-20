package com.example.dolphin;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LandingActivity extends AppCompatActivity {

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_settings)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        gestureDetector = new GestureDetector(this, new SwipeDetector());


    }

    public void makeToast(String message){
        Toast.makeText(LandingActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void onSwipeRight(){
        Intent intent = new Intent(this, LeftSlidePage.class);
        startActivity(intent);
    }

    public class SwipeDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //check the movement along the y-axis, if it exceeds SWIPE_MAX_OFF_PATH, then dismiss.
            if(Math.abs(e1.getY()-e2.getY())>SWIPE_MAX_OFF_PATH){
                return false;
            }

            //makeToast( "start = "+String.valueOf( e1.getX() )+" | end = "+String.valueOf( e2.getX() )  );

            // from left to right
            if(e2.getX() > e1.getX()){
                if(Math.abs(e2.getX()-e1.getX()) > SWIPE_MIN_DISTANCE && Math.abs(velocityX)>SWIPE_THRESHOLD_VELOCITY){
                    onSwipeRight();
                    return true;
                }
                return false;
            }

            // from right to left
            if(e1.getX() > e2.getX()){
                if(Math.abs(e1.getX()-e2.getX()) > SWIPE_MIN_DISTANCE && Math.abs(velocityX)>SWIPE_THRESHOLD_VELOCITY){
                    //onSwipeRight();
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //touch dispatcher
        if(gestureDetector!=null){
            if(gestureDetector.onTouchEvent(ev))
                // if the gestureDetector handles the event, a swipe has been executed & nothing
                // more has to be done .
                return  true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

}