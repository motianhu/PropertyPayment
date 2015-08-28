package com.smona.app.propertypayment.phone;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;
import com.smona.app.propertypayment.common.ui.PaymentSimpleFeeDetailListActivity;
import com.smona.app.propertypayment.common.ui.PaymentSimpleFeePayActivity;
import com.smona.app.propertypayment.common.util.LogUtil;

public class PhoneActivity extends PaymentBaseActivity {

    private static final String TAG = "PhoneActivity";
    private static final int ACTION_START_CONTACTS = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_phone);
        initViews();
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_phone);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.input_phone);
        initView(R.id.input_phone);

        parent = mRoot.findViewById(R.id.phone_guishu);
        initText(parent, R.id.name, "河南电信");

        initText(R.id.next_step, R.string.payment_phone_liji_pay);
        initView(R.id.next_step);

        parent = mRoot.findViewById(R.id.dazhe_info);
        initTextHint(parent, R.id.select_type,
                R.string.payment_phone_select_jine);
        initView(R.id.dazhe_info);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.dazhe_info:
            startActivityForResult(new Intent(Intent.ACTION_PICK,
                    ContactsContract.Contacts.CONTENT_URI),
                    ACTION_START_CONTACTS);
            break;
        case R.id.detail:
            gotoSubActivity(PaymentSimpleFeeDetailListActivity.class);
            break;
        case R.id.input_phone:
            gotoSubActivity(PhoneHistoryListActivity.class);
            break;
        case R.id.next_step:
            gotoSubActivity(PaymentSimpleFeePayActivity.class);
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (ACTION_START_CONTACTS == requestCode) {
                getPhoneAndName(data);
            }
        }
    }

    private void getPhoneAndName(Intent data) {
        ContentResolver reContentResolverol = getContentResolver();
        Uri contactData = data.getData();
        Cursor cursor = managedQuery(contactData, null, null, null, null);
        cursor.moveToFirst();
        String username = cursor.getString(cursor
                .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        String contactId = cursor.getString(cursor
                .getColumnIndex(ContactsContract.Contacts._ID));
        Cursor phone = reContentResolverol.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                        + contactId, null, null);
        String usernumber = null;
        while (phone.moveToNext()) {
            usernumber = phone
                    .getString(phone
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
        cursor.close();
        LogUtil.d(TAG, "username: " + username + ", usernumber: " + usernumber);
        querySim();
    }

    private void querySim() {
        Uri uri = Uri.parse("content://icc/adn");
        Cursor cursor = getContentResolver().query(uri, null, null,
        null, null);
        LogUtil.d(TAG, ">>>>>>" + cursor.getCount());
        while (cursor.moveToNext()) {
            String id = cursor
                    .getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
            String name = cursor
                    .getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Relation.NAME));
            String phoneNumber = cursor.getString(cursor
            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            LogUtil.d("1023", ">>>>>>" + "_id, " + id);
            LogUtil.d("1023", ">>>>>>" + "name, " + name);
            LogUtil.d("1023", ">>>>>>" + "phone number, " + phoneNumber);

        }
    }

}
