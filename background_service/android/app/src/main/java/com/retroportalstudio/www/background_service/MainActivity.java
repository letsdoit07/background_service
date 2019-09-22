package com.retroportalstudio.www.background_service;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {

  private Intent forService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);

    forService = new Intent(MainActivity.this,MyService.class);

    new MethodChannel(getFlutterView(),"com.retroportalstudio.messages")
            .setMethodCallHandler(new MethodChannel.MethodCallHandler() {
      @Override
      public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if(methodCall.method.equals("startService")){
          startService();
          result.success("Service Started");
        }
      }
    });


  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    stopService(forService);
  }

  private void startService(){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
      startForegroundService(forService);
    } else {
      startService(forService);
    }
  }



}
