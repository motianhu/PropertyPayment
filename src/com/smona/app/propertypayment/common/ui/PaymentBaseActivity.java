package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public abstract class PaymentBaseActivity extends PaymentDialogActivity {

    private static final int MSG_LOAD_DATA = 1;
    private static final int MSG_NOTIFY_REFRESH_UI = 2;

    protected View mRoot = null;

    @SuppressLint("HandlerLeak")
    private Handler mLoadDataHandler = new Handler() {
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
            case MSG_LOAD_DATA:
                loadData();
                break;
            case MSG_NOTIFY_REFRESH_UI:
                refreshUI();
                break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initViews() {
        initRoot();
        initHeader();
        initBody();
    }

    private void initRoot() {
        mRoot = findViewById(R.id.root);
    }

    protected abstract void initHeader();

    protected abstract void initBody();

    protected void requestLoadData() {
        sendMessage(MSG_LOAD_DATA);
    }

    protected void requestRefreshUI() {
        sendMessage(MSG_NOTIFY_REFRESH_UI);
    }

    private void sendMessage(int msg) {
        mLoadDataHandler.sendEmptyMessage(msg);
    }

    protected void loadData() {

    }

    protected void refreshUI() {

    }

    protected void saveData(String content) {

    }

    protected void failedRequest() {

    }

    public void onResult(boolean success, String content) {
        if (success) {
            saveData(content);
        } else {
            failedRequest();
        }
    }

    private OnClickListener mClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            clickView(v);
        }
    };

    protected abstract void clickView(View v);

    // text
    protected void initText(int resId, int text) {
        TextView title = (TextView) mRoot.findViewById(resId);
        title.setText(text);
    }

    protected void initText(int resId, String text) {
        TextView title = (TextView) mRoot.findViewById(resId);
        title.setText(text);
    }

    // parent text
    protected void initText(View parent, int resId, int text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setText(text);
    }

    protected void initText(View parent, int resId, String text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setText(text);
    }

    // hint
    protected void initTextHint(int resId, int text) {
        TextView title = (TextView) mRoot.findViewById(resId);
        title.setHint(text);
    }

    protected void initTextHint(int resId, String text) {
        TextView title = (TextView) mRoot.findViewById(resId);
        title.setHint(text);
    }

    // parent hint
    protected void initTextHint(View parent, int resId, int text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setHint(text);
    }

    protected void initTextHint(View parent, int resId, String text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setHint(text);
    }

    // text
    protected String getTextContent(View parent, int resId) {
        TextView view = (TextView) parent.findViewById(resId);
        return view.getText().toString();
    }

    protected String getTextContent(int resId) {
        TextView view = (TextView) mRoot.findViewById(resId);
        return view.getText().toString();
    }

    // set tag
    protected void setTag(int resId, Object obj) {
        View view = mRoot.findViewById(resId);
        view.setTag(obj);
    }

    protected void setTag(View parent, int resId, Object obj) {
        View view = parent.findViewById(resId);
        view.setTag(obj);
    }

    protected Object getTag(int resId) {
        View view = mRoot.findViewById(resId);
        return view.getTag();
    }

    protected Object getTag(View parent, int resId) {
        View view = parent.findViewById(resId);
        return view.getTag();
    }

    protected void initView(int resId) {
        View view = mRoot.findViewById(resId);
        view.setOnClickListener(mClickListener);
    }
    
    protected void initView(View view) {
        view.setOnClickListener(mClickListener);
    }

    protected void gotoSubActivity(Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void showMessage(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(PaymentBaseActivity.this, text,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void showMessage(final int text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(PaymentBaseActivity.this, text,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void showSingleChoiceType(ArrayList<PaymentItemInfo> datas,
            final IChoiceCallback callback) {
        PaymentTypeAdapter dapter = new PaymentTypeAdapter(this, datas);

        PaymentSelectedDialog.Builder builder = new PaymentSelectedDialog.Builder(
                this);
        builder.setSingleChoiceItems(dapter, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onChoice(which);
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
    

    public interface IChoiceCallback {
        void onChoice(int which);
    }
}
