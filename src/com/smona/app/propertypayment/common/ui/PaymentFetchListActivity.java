package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;

public abstract class PaymentFetchListActivity extends PaymentBaseActivity {

    protected static final int PAGE_SIZE = 10;
    private int mCurrPage = 1;

    protected ListView mList;
    protected PaymentBaseDataAdapter mAdapter;

    protected abstract void loadMore();

    protected int getCurrentPage() {
        return mCurrPage;
    }

    protected void setFetchListener(ArrayList<PaymentItemInfo> data) {
        mList = (ListView) mRoot.findViewById(R.id.list_content);
        mAdapter = createAdapter(data);
        mList.setAdapter(mAdapter);
    }

    protected void setOnItemClickListener(OnItemClickListener listener) {
        mList.setOnItemClickListener(listener);
    }

    public abstract PaymentBaseDataAdapter createAdapter(
            ArrayList<PaymentItemInfo> data);

    protected void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    protected void finishDialogAndRefresh() {
        runOnUiThread(new Runnable() {
            public void run() {
                hideCustomProgressDialog();
            }
        });
    }

}
