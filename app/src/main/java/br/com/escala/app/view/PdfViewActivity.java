package br.com.escala.app.view;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import br.com.escala.app.R;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.model.Revista;
import br.com.escala.app.network.RestClient;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class PdfViewActivity extends BaseActivity{

    private static int REQUEST_WRITE_PERMISSIONS = 0;
    private PDFView pdfView;
    private ProgressBar progressBar;
    private String path;
    private CompositeDisposable disposable = new CompositeDisposable();
    private String fileName = "";
    private String url;
    private AlertDialog.Builder builder;
    private Dialog dialog;
    private Revista revista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.activity_pdf_view);

        revista = LerEdicaoMesActivity.revistaIntent;

        Bundle extra = getIntent().getExtras();

        if( extra != null ){
            url = getIntent().getStringExtra("URL");
            fileName = getIntent().getStringExtra("FILE_NAME");
            path = getIntent().getStringExtra("PATH");
        }

        progressBar = findViewById(R.id.progressBar);

        pdfView = findViewById(R.id.pdfview_id);

        if (getPermissions()) {
            getPdf(url);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (setDrawerVisibility(true)){
            setMenuDrawer(revista);
            setImageDetail(revista);
        }
    }

    private void getPdf(String url) {

        disposable.add(RestClient.getInstancePDF(url).downloadFile(path)
                .flatMap(this::saveFile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> {
                    // colocar o loading para a tela
                    progressBar.setVisibility(View.VISIBLE);
                })
                .doAfterTerminate(() -> {
                    // tirar o loading pra ma tela
                    progressBar.setVisibility(View.GONE);
                })
                .doOnError(throwable -> {
                    Log.i("TAG", "onCreate: " + throwable.getMessage());
                    finishActivityPdf(url);
                })
                .subscribe(file -> {

                    finishActivityPdf(url);

                    pdfView.fromFile(file)
                            .enableSwipe(true) // allows to block changing pages using swipe
                            .swipeHorizontal(true)
                            .enableDoubletap(true)
                            .defaultPage(0)
                            .pages(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                            .onPageChange(new OnPageChangeListener() {

                                @Override
                                public void onPageChanged(int page, int pageCount) {

                                    Log.i("TAG onPageChanged", "page " + page + "pageCount " + pageCount);

                                    if (page == 10){

                                        goNotify();
                                    }
                                }
                            })
                            .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                            .password(null)
                            .scrollHandle(null)
                            .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                            .load();

                }, throwable -> {
                    Log.i("TAG", "throwable: " + throwable.getMessage());
                    // mostrar a mensagem pro usuario, de error
                    finishActivityPdf(url);
                })
        );
    }


    private void goNotify() {

        View view = LayoutInflater.from(PdfViewActivity.this).inflate(R.layout.activity_notificacao, null);

        ImageView imgFecharView = view.findViewById(R.id.btn_fechar_notificacao_id);
        Button btnAssineJa = view.findViewById(R.id.btn_assine_ja_id);
        Button btnCompreApp = view.findViewById(R.id.btn_compre_app_id);
        TextView txtVolteLogin = view.findViewById(R.id.btn_tela_login_id);

        builder = new AlertDialog.Builder(PdfViewActivity.this);

        imgFecharView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        btnAssineJa.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), AssineJaActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            dialog.dismiss();
            startActivity(intent);
        });

        btnCompreApp.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), CompreAppActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            dialog.dismiss();
            startActivity(intent);
        });

        txtVolteLogin.setOnClickListener(v -> {

            dialog.dismiss();

            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            dialog.dismiss();
            startActivity(intent);
        });

        builder.setView(view).setCancelable(false);
        dialog = builder.create();
        dialog.show();

    }

    private Single<File> saveFile(ResponseBody responseBody){
        return Single.create(emmiter -> {
            try {
                File file = new File(getStorageDir(PdfViewActivity.this).toString() + File.separator + fileName);
                BufferedSink sink = Okio.buffer(Okio.sink(file));
                // you can access body of response
                sink.writeAll(responseBody.source());
                sink.close();
                emmiter.onSuccess(file);
            }catch (IOException e) {
                e.printStackTrace();
                emmiter.onError(e);
            }
        });
    }

    private void finishActivityPdf(String url) {
        if (url == null) {
            Toast.makeText(this, "Pdf não encontrado", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private File getStorageDir(Context context) { //criando um diretorio dentro pasta SDCard
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(Environment.getExternalStorageState()), "Escala");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("TAG", "Directory not created");
            }
        }
        return file;
    }

    private boolean getPermissions() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            return true;
        }
        if (checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Atenção");
            builder.setMessage("Permissão necessária para visualização de PDF");
            builder.setNegativeButton("Não", (dialog, which) -> dialog.dismiss());
            builder.setPositiveButton("Sim", (dialog, which) -> {

//                dialog.dismiss();

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSIONS);
            });
            builder.create();
            builder.show();

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSIONS);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSIONS && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getPdf(url);
        } else {
            Toast.makeText(this, "Permissões são necessárias para visualizar revista", Toast.LENGTH_SHORT).show();
            finish();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        File file = new File(getStorageDir(PdfViewActivity.this).toString() + File.separator + fileName);
        if (file.exists()){
            file.delete();
        }

        super.onDestroy();
    }

}

//    private void getPdf(String url) {
//
//        disposable.add(RestClient.getInstancePDF(url).downloadFile(path)
//                .flatMap(this::saveFile)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(disposable1 -> {
//                    // colocar o loading para a tela
//                    progressBar.setVisibility(View.VISIBLE);
//                })
//                .doAfterTerminate(() -> {
//                    // tirar o loading pra ma tela
//                    progressBar.setVisibility(View.GONE);
//                })
//                .doOnError(throwable -> {
//                    Log.i("TAG", "onCreate: " + throwable.getMessage());
//                    finishActivityPdf(url);
//                })
//                .subscribe(file -> {
//
//                    finishActivityPdf(url);
//
//                    pdfView.fromFile(file)
//                            .enableSwipe(true) // allows to block changing pages using swipe
//                            .swipeHorizontal(true)
//                            .enableDoubletap(true)
//                            .defaultPage(0)
//                            .pages(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
//                            .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
//                            .password(null)
//                            .scrollHandle(null)
//                            .enableAntialiasing(true) // improve rendering a little bit on low-res screens
//                            .load();
//
//                }, throwable -> {
//                    Log.i("TAG", "throwable: " + throwable.getMessage());
//                    // mostrar a mensagem pro usuario, de error
//                    finishActivityPdf(url);
//                })
//        );
//    }
