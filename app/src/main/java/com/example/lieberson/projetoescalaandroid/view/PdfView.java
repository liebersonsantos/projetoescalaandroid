package com.example.lieberson.projetoescalaandroid.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lieberson.projetoescalaandroid.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PdfView extends AppCompatActivity {

    private PDFView pdfView;
    private static ProgressDialog progressDialog;
    private String URL_BASE = ("https://static.scielo.org/scielobooks/38m/pdf/santos-9788523209087.pdf");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        pdfView = findViewById(R.id.pdfview_id);

        new RetrievePDFByte().execute(URL_BASE);

    }

    class RetrievePDFByte extends AsyncTask<String, Void, byte[]> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(PdfView.this);
            progressDialog.setTitle("Carregando...");
            progressDialog.setMessage("Baixando arquivo...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected byte[] doInBackground(String... strings) {

            InputStream inputStream = null;
            String PDFs = "data/data/teste.pdf";

            try {

                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                if (urlConnection.getResponseCode() == 200) {

                    inputStream = new BufferedInputStream(urlConnection.getInputStream());

                    Log.i("INPUT", inputStream.toString());

                }

            } catch (IOException e) {

                return null;
            }

            try {
                assert inputStream != null;
                return IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);


        }

        @Override
        protected void onPostExecute(byte[] bytes) {

            pdfView.fromBytes(bytes)
                    .swipeHorizontal(true)
                    .defaultPage(0)
                    .pages(0,1,2,3,4)
                    .onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {


                        }
                    })
                    .load();
            progressDialog.dismiss();
        }
    }

}

