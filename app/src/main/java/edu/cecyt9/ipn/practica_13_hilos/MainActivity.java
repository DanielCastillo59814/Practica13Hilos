package edu.cecyt9.ipn.practica_13_hilos;

/*
AUTOR: Fernando Daniel Castillo Barrón (C) Abril 2017
VERSIÓN: 1.0

DESCRIPCIÓN: Programa que usa hilos para calcular factoriales y la serie de fibonnacci

OBSERVACIONES:
El programa muestra el resultado en el Textview salida1

COMPILACIÓN: se compila en tiempo de ejecucion.
EJECUCIÓN: se ejecuta desde el IDE de Android Studio con las teclas shift + F10.  (En Windows) - ./programa (En Linux)
*/

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.*;


public class MainActivity extends Activity {
    private EditText entrada;
    private TextView salida1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada = (EditText) findViewById(R.id.entrada);
        salida1 = (TextView) findViewById(R.id.salida1);
    }

    public void calcularOperacion(View view) {
        int n = Integer.parseInt(entrada.getText().toString());
        new MiThread(n).start();
    }

    public int factorial(int n) {
        int res=1;
        for (int i=1; i<=n; i++) {
            res *= i;
            SystemClock.sleep(1000);
        }
        return res;
    }


    //Metodo agregado, devuelve un String
    //Con la secuencia de fibonnacci hasta la posicion dada
    public String fibonnacci(int n){
        int numa = 0;
        int numb = 1;
        String secuencia = "0, 1";
        if(n == 1){
            secuencia = "0";
        }else if(n == 0){
            secuencia = "no hay secuencia";
        }else{
            for (int i = 0; i < n - 2; i++) {
                int res = numa + numb;
                numa = numb;
                numb = res;
                secuencia += ", " + res;
            }
        }
        return secuencia;
    }

    class MiThread extends Thread {
        private int n;
        private int res1;
        private String res2;

        public MiThread(int n){
            this.n = n;
        }

        @Override
        public void run() {
            res1 = factorial(n);
            res2 = fibonnacci(n);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    salida1.append(n+"! = " + res1 + "\n");
                    salida1.append("Fibonnacci posicion " + n + " = " + res2 + "\n\n");
                }
            });
        }
    }

//    public void calcularOperacion(View view) {
//        int n = Integer.parseInt(entrada.getText().toString());
//        salida.append(n + "! = ");
//        MiThread thread = new MiThread(n);
//        thread.start();
//    }


//    ejemplo AsyncTask
//    class MiTarea extends AsyncTask<Integer, Void, Integer> {
//
//        @Override
//
//        protected Integer doInBackground(Integer... n) {
//
//            return factorial(n[0]);
//
//        }
//
//        @Override
//
//        protected void onPostExecute(Integer res) {
//
//            salida.append(res + "\n");
//
//        }
//
//    }

//        public void calcularOperacion(View view) {
//        int n = Integer.parseInt(entrada.getText().toString());
//        salida.append(n + "! = ");
//        MiTarea tarea = new MiTarea();
//        tarea.execute(n);
//
//        }

    //    ejemplo AsyncTask whit progressdialog
//    class MiTarea extends AsyncTask<Integer, Integer, Integer> {
//
//        private ProgressDialog progreso;
//
//        @Override protected void onPreExecute() {
//
//            progreso = new ProgressDialog(MainActivity.this);
//
//            progreso.setProgressStyle(ProgressDialog.
//                    STYLE_HORIZONTAL);
//
//            progreso.setMessage("Calculando...");
//
//            progreso.setCancelable(false);
//
//            progreso.setMax(100);
//
//            progreso.setProgress(0);
//
//            progreso.show();
//
//        }
//
//        @Override protected Integer doInBackground(Integer... n) {
//
//            int res = 1;
//
//            for (int i = 1; i <= n[0]; i++) {
//
//                res *= i;
//
//                SystemClock.sleep(1000);
//
//                publishProgress(i*100 / n[0]);
//
//            }
//
//            return res;
//
//        }
//
//        @Override protected void onProgressUpdate(Integer... porc) {
//
//            progreso.setProgress(porc[0]);
//
//        }
//
//        @Override protected void onPostExecute(Integer res) {
//
//            progreso.dismiss();
//
//            salida.append(res + "\n");
//
//        }
//
//    }

    //    ejemplo AsyncTask whit progressdialog cancel
//    class MiTarea extends AsyncTask<Integer, Integer, Integer> {
//
//        private ProgressDialog progreso;
//
//        @Override protected void onPreExecute() {
//
//            progreso = new ProgressDialog(MainActivity.this);
//
//            progreso.setProgressStyle(ProgressDialog.
//                    STYLE_HORIZONTAL);
//
//            progreso.setMessage("Calculando...");
//
//            progreso.setCancelable(true);
//
//            progreso.setOnCancelListener(new OnCancelListener() {
//
//                @Override
//                public void onCancel(DialogInterface dialog) {
//
//                    MiTarea.this.cancel(true);
//
//                }
//
//            });
//
//            progreso.setMax(100);
//
//            progreso.setProgress(0);
//
//            progreso.show();
//
//        }
//
//        @Override protected Integer doInBackground(Integer... n) {
//
//            int res = 1;
//
//            for (int i = 1; i <= n[0] && !isCancelled(); i++) {
//                res *= i;
//
//                SystemClock.sleep(1000);
//
//                publishProgress(i*100 / n[0]);
//
//            }
//
//            return res;
//
//        }
//
//        @Override protected void onProgressUpdate(Integer... porc) {
//
//            progreso.setProgress(porc[0]);
//
//        }
//
//        @Override protected void onPostExecute(Integer res) {
//
//            progreso.dismiss();
//
//            salida.append(res + "\n");
//
//        }
//
//        @Override protected void onCancelled() {
//
//            salida.append("cancelado\n");
//
//        }
//
//    }

}