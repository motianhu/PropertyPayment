package com.smona.app.propertypayment.common.ui;

import java.util.ArrayList;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.source.xlistview.XListView.IXListViewListener;

public abstract class PaymentFetchListActivity extends PaymentBaseActivity
        implements IXListViewListener {

    protected static final int PAGE_SIZE = 10;
    private int mCurrPage = 1;
    private boolean mIsDataOver = false;

    protected ListView mList;
    protected PaymentBaseDataAdapter mAdapter;

    @Override
    public void onRefresh() {
    }

    @Override
    public void onLoadMore() {
        if (mIsDataOver) {
            showMessage("数据到达终点");
            return;
        }
        loadMore();
    }

    protected abstract void loadMore();

    protected void setDataPos(int size) {
        mCurrPage += 1;
        mIsDataOver = PAGE_SIZE > size;
    }

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
