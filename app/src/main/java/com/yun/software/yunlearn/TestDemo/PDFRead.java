package com.yun.software.yunlearn.TestDemo;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.shockwave.pdfium.PdfDocument;
import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import java.util.List;

import butterknife.Bind;

/**
 * Created by yanliang
 * on 2018/2/1 15:35
 */

public class PDFRead extends BaseActivity {
    @Bind(R.id.pdfView)
    PDFView pdfView;
    public static final String SAMPLE_FILE = "sample.pdf";
    Uri uri;

    Integer pageNumber = 0;

    String pdfFileName;
    @Override
    public int getLayoutId() {
        return R.layout.activity_pdf_red;
    }

    @Override
    public void setData() {
       displayFromAsset(SAMPLE_FILE);
    }

    @Override
    public void addLisener() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(pageNumber)
                .onPageChange(mylisener)
                .enableAnnotationRendering(true)
                .onLoad(myonLoadCompleteListener)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(myErrorlisener)
                .pageFitPolicy(FitPolicy.BOTH)
                .load();
    }
    /**
     *从文件件中加载
     */
    private void displayFromUri(Uri uri) {
        pdfFileName = getFileName(uri);

        pdfView.fromUri(uri)
                .defaultPage(pageNumber)
                .onPageChange(mylisener)
                .enableAnnotationRendering(true)
                .onLoad(myonLoadCompleteListener)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(myErrorlisener)
                .load();
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }

    private OnPageChangeListener mylisener=new OnPageChangeListener() {
        @Override
        public void onPageChanged(int page, int pageCount) {
            pageNumber = page;
            setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
        }




    };
    private OnLoadCompleteListener  myonLoadCompleteListener=new OnLoadCompleteListener() {
        @Override
        public void loadComplete(int nbPages) {
            PdfDocument.Meta meta = pdfView.getDocumentMeta();
            Log.e(TAG, "title = " + meta.getTitle());
            Log.e(TAG, "author = " + meta.getAuthor());
            Log.e(TAG, "subject = " + meta.getSubject());
            Log.e(TAG, "keywords = " + meta.getKeywords());
            Log.e(TAG, "creator = " + meta.getCreator());
            Log.e(TAG, "producer = " + meta.getProducer());
            Log.e(TAG, "creationDate = " + meta.getCreationDate());
            Log.e(TAG, "modDate = " + meta.getModDate());
            printBookmarksTree(pdfView.getTableOfContents(), "-");
        }
    };
    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    private OnPageErrorListener myErrorlisener=new OnPageErrorListener() {
        @Override
        public void onPageError(int page, Throwable t) {
            Log.e(TAG, "Cannot load page " + page);
        }
    };



}
