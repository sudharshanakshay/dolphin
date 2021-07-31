package com.example.dolphin.session;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class AlertDialogManager {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  void showAlertDialog(Context context, String title, String message, boolean status){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();

        }
    }
}
