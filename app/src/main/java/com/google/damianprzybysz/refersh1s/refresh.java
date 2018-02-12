package com.google.damianprzybysz.refersh1s;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class refresh extends AppCompatActivity {

    void DowywolaniaWPetli() {

        TextView outputView = (TextView) findViewById(R.id.postOutput);
        //Some url endpoint that you may have
        //String myUrl = "http://85.14.79.181/damian/getNOCQueuesForCallcenter.php";
        String myUrl = "http://85.14.79.181/damian/getQueuesForCallcenter.php";
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
        String pobranedane = result;


        String[] tablicaAgentow = pobranedane.split(":#");
        //System.out.println(tablicaAgentow[0]);
        //  System.out.println("###############################################");
        //System.out.println(tablicaAgentow[1]);

        String tymczas1 = tablicaAgentow[0];
        String[] tablicaAgentow1 = tymczas1.split(";%");

        int iloscagentow = tablicaAgentow1.length;

        // System.out.println(iloscagentow);
        String[][] tabagentow = new String[iloscagentow][5];
        String[] TablicaOpisowPrzyciskow = new String[iloscagentow];


        for (int i = 0; i < iloscagentow; i++) {

            //System.out.println(tablicaAgentow1[i]);


            String tymczas2 = tablicaAgentow1[i];
            String[] tablicaAgentow3 = tymczas2.split(",%");

            for (int j = 0; j < tablicaAgentow3.length; j++) {

                // System.out.println(tablicaAgentow3[j]);


                tabagentow[i][j] = tablicaAgentow3[j];

                // System.out.printf("tab["+i+"]["+j+"] = "+ tabagentow[i][j]+"\

                //usuwanie niechcianych znaków w nazwie


                //usuwanie niechcianych znaków w statusie agenta

                if (j == 1) {

                    tabagentow[i][j] = tabagentow[i][j].replace(",", " ");
                    tabagentow[i][j] = tabagentow[i][j].replace("Unavailable", "niedostępny");
                    tabagentow[i][j] = tabagentow[i][j].replace("Unknown", "niedostępny");
                    tabagentow[i][j] = tabagentow[i][j].replace("Ringing", "Odbierz dzwoni numer ");
                    tabagentow[i][j] = tabagentow[i][j].replace("Not in use", "dostępny");
                    tabagentow[i][j] = tabagentow[i][j].replace("Outgoing", "");
                    tabagentow[i][j] = tabagentow[i][j].replace("Idle", "");
                    tabagentow[i][j] = tabagentow[i][j].replace("In use", "prowadzi rozmowę"+ System.getProperty("line.separator")+ "z numerem   ");
                    tabagentow[i][j] = tabagentow[i][j].replace(" 48", "");
                    if(tabagentow[i][j].contains("niedostępny")){

                    }else{
                        tabagentow[i][j] += " s";
                    }




                }

                if (j == 2) {

                    tabagentow[i][j] = tabagentow[i][j].replace("1", "");
                    tabagentow[i][j] = tabagentow[i][j].replace("2", "DND");


                }

                if (j == 3) {


                    // tabagentow[i][j] = tabagentow[i][j].replace("0", "");

                    tabagentow[i][j] = "";

                }

                if (j == 4) {

                    tabagentow[i][j] = tabagentow[i][j].concat(" odebranych");

                }


                if (j == 0) {
                    TablicaOpisowPrzyciskow[i] = tabagentow[i][j];

                    TablicaOpisowPrzyciskow[i] = TablicaOpisowPrzyciskow[i].replace("SIP/48", "KONTO SIP" + System.getProperty("line.separator"));
                    TablicaOpisowPrzyciskow[i] = TablicaOpisowPrzyciskow[i].replace("Agent", "AGENT" + System.getProperty("line.separator"));
                    TablicaOpisowPrzyciskow[i] = TablicaOpisowPrzyciskow[i].replace("Local", "NR ZEW" + System.getProperty("line.separator"));
                    TablicaOpisowPrzyciskow[i] = TablicaOpisowPrzyciskow[i].replace("i", "");
                    TablicaOpisowPrzyciskow[i] = TablicaOpisowPrzyciskow[i].replace("/", "");
                } else {
                    TablicaOpisowPrzyciskow[i] += System.getProperty("line.separator") + tabagentow[i][j];

                }


            }


        }

        Button buttons[] = new Button[iloscagentow];

        for (int i = 0; i < iloscagentow; i++) {
            if(i==0)
                buttons[i] = (Button) findViewById(R.id.button1);
            if(i==1)
                buttons[i] = (Button) findViewById(R.id.button2);

            if(i==2)
                buttons[i] = (Button) findViewById(R.id.button3);

            if(i==3)
                buttons[i] = (Button) findViewById(R.id.button4);

            if(i==4)
                buttons[i] = (Button) findViewById(R.id.button5);

            if(i==5)
                buttons[i] = (Button) findViewById(R.id.button6);

            if(i==6)
                buttons[i] = (Button) findViewById(R.id.button7);

            if(i==7)
                buttons[i] = (Button) findViewById(R.id.button8);

            if(i==8)
                buttons[i] = (Button) findViewById(R.id.button9);

            if(i==9)
                buttons[i] = (Button) findViewById(R.id.button10);

        }


        for (int i = 0; i < iloscagentow; i++) {
            buttons[i].setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < iloscagentow; i++) {

            if (TablicaOpisowPrzyciskow[i].contains("dostępny")) {
                buttons[i].setBackgroundColor(Color.GREEN);
            }
            if (TablicaOpisowPrzyciskow[i].contains("prowadzi rozmowę")) {
                buttons[i].setBackgroundColor(Color.YELLOW);
            }
            if (TablicaOpisowPrzyciskow[i].contains("niedostępny")) {
                buttons[i].setBackgroundColor(Color.GRAY);
            }
            if (TablicaOpisowPrzyciskow[i].contains("nieznany")) {
                buttons[i].setBackgroundColor(Color.GRAY);
            }
            if (TablicaOpisowPrzyciskow[i].contains("Odbierz dzwoni")) {
                buttons[i].setBackgroundColor(Color.RED);
            }


        }
        for (int i = 0; i < iloscagentow; i++) {
            buttons[i].setText(TablicaOpisowPrzyciskow[i]);
        }





        String[][] tabdzwoniacych = new String[iloscagentow][5];
        String[] tabdzwoniacychwyswietlenie = new String[iloscagentow];
        String wyswietleniestanudzwoniacych = null;
        int czasoczekiwania = 0;
        String tymczas3 = tablicaAgentow[1];


        //System.out.println(tablicaAgentow[2]);

        String[] tablicaAgentow4 = tymczas3.split(";#");
        int iloscdzwoniacych = tablicaAgentow4.length;
        //System.out.println(iloscdzwoniacych);
        // System.out.println(tablicaAgentow4[0]);

        for (int i = 0; i < iloscdzwoniacych; i++) {

            String tymczas4 = tablicaAgentow4[i];
            String[] tablicaAgentow5 = tymczas4.split(",#");


            for (int j = 0; j < tablicaAgentow5.length; j++) {

                // System.out.println(tablicaAgentow3[j]);
                tabdzwoniacych[i][j] = tablicaAgentow5[j];

                if (j == 1) {

                    String czasoczekiwania1 = tabdzwoniacych[i][j];



                    int czasoczekiwaniawkolejce = 0;

                    try {
                        czasoczekiwaniawkolejce = Integer.parseInt(czasoczekiwania1);
                    } catch(NumberFormatException nfe) {
                        System.out.println("Could not parse " + nfe);
                    }


                    if (czasoczekiwaniawkolejce>60){


                        if(czasoczekiwaniawkolejce/60 == 1){
                            tabdzwoniacych[i][j] = "Czeka w kolejce " + czasoczekiwaniawkolejce/60 + " minutę " + czasoczekiwaniawkolejce%60 +" sekund " ;
                        }else{
                            tabdzwoniacych[i][j] = "Czeka w kolejce " + czasoczekiwaniawkolejce/60 + " minut " + czasoczekiwaniawkolejce%60 + " sekund";
                        }
                    }else{
                        tabdzwoniacych[i][j] = "Czeka w kolejce " + czasoczekiwaniawkolejce + " sekund ";
                    }


                    //tabdzwoniacych[i][j] = "Czeka w kolejce " + tabdzwoniacych[i][j] + " s ";




                }


                if (j == 0) {
                    tabdzwoniacychwyswietlenie[i] = tabdzwoniacych[i][j] + " ";
                } else {
                    tabdzwoniacychwyswietlenie[i] += tabdzwoniacych[i][j] + " ";
                }


                //System.out.printf("tab2["+i+"]["+j+"] = "+ tabdzwoniacychwyswietlenie[i]+"\n");

            }

            if (i == 0) {
                wyswietleniestanudzwoniacych = tabdzwoniacychwyswietlenie[i] + "\n";
            } else {
                wyswietleniestanudzwoniacych += tabdzwoniacychwyswietlenie[i] + "\n";
            }


        }


        if (wyswietleniestanudzwoniacych.length()<=3){
           // String nrtestowy = " Witamy w kolejce Demo NOC 3S...\n Obecnie nikt nie dzwoni. Oby tak zostało ...";
            String nrtestowy = " Witamy w kolejce testowej 3S...\n Obecnie nikt nie dzwoni. Zadzwoń pod numer 126126118 ";
            outputView.setText(nrtestowy);
        }else{
            outputView.setText(wyswietleniestanudzwoniacych);
        }




    }

    int countloop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);


        final TextView calltekst = (TextView) findViewById(R.id.callbutton);

        //-------------funkcja sprawdzająca czas------------------------------
        // android.os.Process.killProcess(android.os.Process.myPid());
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);

        if(year < 2019){
            //calltekst.setText("jest przed 2017");

        }else {
            android.os.Process.killProcess(android.os.Process.myPid());
        }




        //----------------------------------------------------------------------------

        //--------dzwonienie do 3s------------------------------------------------
        //final TextView calltekst = (TextView) findViewById(R.id.callbutton);

        calltekst.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {


                    // String str=calltekst.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:324203333"));
                    startActivity(intent);
                }
                catch (android.content.ActivityNotFoundException e){

                    Toast.makeText(getApplicationContext(),"App failed",Toast.LENGTH_LONG).show();
                }

            }
        });





        //-------------------------------------------------------------------------------



        final TextView outputView = (TextView) findViewById(R.id.postOutput);
        Button buttons[] = new Button[4];
        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);


        DowywolaniaWPetli();































        /*







        */













        Thread t = new Thread(){
            @Override
            public void run() {
                while(!isInterrupted()){

                    try {
                        Thread.sleep(2000);


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               // CallersCount callersCount = new CallersCount();
                               // String mCallersCounts = callersCount.getCallersCount();


                                DowywolaniaWPetli();
                                /*
                                TextView textView = (TextView) findViewById(R.id.postOutput);
                                String myUrl = "http://85.14.79.181/damian/countCallers.php";
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

                                textView.setText(result + " "+ countloop);
                                countloop++;

                                */




                            }
                        });




                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        };
        t.start();
    }
}
