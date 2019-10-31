package com.didapingche.flutter_hot_fix;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.didapingche.flutter_hot_fix.hotfix.HotFixFlutterActivity;
import com.didapingche.flutter_hot_fix.utils.FlutterFileUtils;

import java.util.List;

import io.flutter.plugins.GeneratedPluginRegistrant;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends HotFixFlutterActivity implements EasyPermissions.PermissionCallbacks,
        EasyPermissions.RationaleCallbacks{

  private static final String[] LOCATION_AND_CONTACTS =
          {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Ask for both permissions
    EasyPermissions.requestPermissions(
            this,
           "读写权限",
            123,
            LOCATION_AND_CONTACTS);


    String path = FlutterFileUtils.copyLibAndWrite(MainActivity.this,"libapp_fix.so");
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
                                         @NonNull String[] permissions,
                                         @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
  }

  @Override
  public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
  }

  @Override
  public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
    // This will display a dialog directing them to enable the permission in app settings.
    if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
      new AppSettingsDialog.Builder(this).build().show();
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {


      // Do something after user returned from app settings screen, like showing a Toast.

    }
  }

  @Override
  public void onRationaleAccepted(int requestCode) {
  }

  @Override
  public void onRationaleDenied(int requestCode) {
  }



}
