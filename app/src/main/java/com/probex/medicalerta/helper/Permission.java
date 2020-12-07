package com.probex.medicalerta.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permission {

    public static boolean validatePermissions(String[] permissions, Activity activity, int requestCode){

        if (Build.VERSION.SDK_INT >= 23 ){

            List<String> permissionsList = new ArrayList<>();

            for ( String permission : permissions ){
                Boolean hasPermission = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
                if ( !hasPermission ) permissionsList.add(permission);
            }

            if ( permissionsList.isEmpty() ) return true;
            String[] newPermissions = new String[ permissionsList.size() ];
            permissionsList.toArray( newPermissions );

            ActivityCompat.requestPermissions(activity, newPermissions, requestCode );
        }

        return true;

    }

}
