package com.sz.testokhttp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sz.testokhttp.bean.Notify;
import com.sz.testokhttp.bean.User;
import com.sz.testokhttp.func.AppRequestRouter;
import com.sz.testokhttp.func.RequestDataCallback;
import com.sz.testokhttp.utils.Util;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button txt_getrequest;
    private Button txt_postrequest;
    private Button txt_getPhoneNumber;
    private TextView txt_showresponse;
    private TextView txt_times;
    private int times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_getrequest = findViewById(R.id.txt_getrequest);
        txt_postrequest = findViewById(R.id.txt_postrequest);
        txt_showresponse = findViewById(R.id.txt_showresponse);
        txt_getPhoneNumber = findViewById(R.id.txt_getPhoneNumber);
        txt_times = findViewById(R.id.txt_times);

        txt_getrequest.setOnClickListener(this);
        txt_postrequest.setOnClickListener(this);
        txt_getPhoneNumber.setOnClickListener(this);
        txt_showresponse.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.txt_getrequest:
                AppRequestRouter.getInstance().getUsers(new RequestDataCallback<User>() {
                    @Override
                    public void dataCallback(User user) {
                        if(user != null){
                            txt_showresponse.setText( user.toString() );
                        }else{
                            times++;
                            txt_times.setText( times );
                        }

                    }
                });
                break;
            case R.id.txt_postrequest:
                AppRequestRouter.getInstance().getNofify(new RequestDataCallback<Notify>() {
                    @Override
                    public void dataCallback(Notify notify) {
                        if(notify != null){
                            txt_showresponse.setText( notify.toString() );
                        }else{
                            times++;
                            txt_times.setText( times );
                        }
                    }
                });
                break;
            case R.id.txt_showresponse:

                break;
            case R.id.txt_getPhoneNumber:
                txt_getPhoneNumber.setText( ""  + Util.getPhoneNumber(this.getBaseContext()) );
                break;
        }

    }

}
