package sample.change.me.customvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import sample.change.me.customvolley.bean.User;
import sample.change.me.customvolley.bean.Weather;
import sample.change.me.customvolley.bean.requestUser;
import sample.change.me.customvolley.netcode.IJsonListener;
import sample.change.me.customvolley.netcode.Volley;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       for (int i=0;i<30;i++){
           requestUser user=new requestUser("admin","123123123");
//           Volley.sendRequest(user, "http://www.weather.com.cn/data/sk/101010100.html", Weather.class, new IJsonListener<Weather>() {
           Volley.sendRequest(user, "http://wjb820728252.6655.la:41501/Itweb/loginServlet", User.class, new IJsonListener<User>() {
               @Override
               public void onSuccess(User user) {
                   Log.d(TAG, "onSuccess: " +user);
               }
               @Override
               public void onError() {

               }
           });
       }
    }
}
