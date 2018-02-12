package com.google.damianprzybysz.refersh1s;

import android.widget.TextView;

import java.util.concurrent.ExecutionException;

/**
 * Created by damian.przybysz on 24.01.2018.
 */

public class CallersCount {


    public String getCallersCount(){


  //Some url endpoint that you may have
    String myUrl = "http://85.14.79.181/damian/countCallers.php";

    //String to place our result in
    String result = null;

    //Instantiate new instance of our class
    HttpGetRequest getRequest = new HttpGetRequest();



        try {
        result = getRequest.execute(myUrl).get();

    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (ExecutionException e) {
        e.printStackTrace();
    }
    return result;
    }

}
