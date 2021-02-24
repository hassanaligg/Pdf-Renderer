package com.example.quickstartappstask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.firebase.auth.FirebaseAuth;
import com.shockwave.pdfium.PdfDocument;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PDFView pdfView=findViewById(R.id.pdfView);
        ProgressBar progressBar=findViewById(R.id.progess_bar);


        pdfView.fromAsset("Holy-Quran.pdf")
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        pdfView.fromAsset("Holy-Quran.pdf")
                                .pages(getPages(nbPages))
                                .defaultPage(nbPages)
                                .enableSwipe(true)
                                .enableDoubletap(true)
                                .swipeHorizontal(true)
                                .pageSnap(true)
                                .autoSpacing(true)
                                .pageFling(true)
                                .enableDoubletap(true)
                                .enableAnnotationRendering(true)
                                .password(null)
                                .enableAntialiasing(true)
                                .pageFitPolicy(FitPolicy.WIDTH)
                                .spacing(0)
                                .fitEachPage(false)
                                .load();
                    }
                })
                .load();
//        pdfView.fromAsset("Holy-Quran.pdf")
//                .enableSwipe(true) // allows to block changing pages using swipe
//                .swipeHorizontal(true)
//                .pageSnap(true)
//                .autoSpacing(true)
//                .pageFling(true)
//                .enableDoubletap(true)
//                .defaultPage(0)
//                .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
//                .password(null)
//                .scrollHandle(null)
//                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
//                // spacing between pages in dp. To define spacing color, set view background
//                .spacing(0)
//                .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
//                .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
//                .nightMode(false) // toggle night mode
//                .load();
        progressBar.setVisibility(View.GONE);


    }

    private int [] getPages(int pagesNum){
        int [] pages = new int[pagesNum];
        int j = 0;
        for (int i = pages.length-1; i >= 0 ; i--){
            pages[j] = i;
            j++;
        }
        return pages;
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(MainActivity.this,FacebookLogin.class));
    }

}