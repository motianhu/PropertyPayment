package com.smona.app.propertypayment.db.table;

import android.net.Uri;
import android.provider.BaseColumns;

public abstract class PaymentAbstractTable implements BaseColumns {
    public static final String AUTHORITY = "com.smona.app.propertypayment.cache";
    protected static final String PARAMETER_NOTIFY = "notify";

    protected static final String TEXT = " TEXT";
    protected static final String DOUHAO = ",";

    public String mTableName;
    public Uri mContentUri;
    public Uri mContentUri_NoNotify;

    protected PaymentAbstractTable(String tableName) {
        mTableName = tableName;
        mContentUri = Uri.parse("content://" + AUTHORITY + "/" + tableName);
        mContentUri_NoNotify = Uri.parse("content://" + AUTHORITY + "/"
                + tableName + "?" + PARAMETER_NOTIFY + "=false");

    }

    public abstract String createTableSql();

    public String dropTableSql() {
        return "DROP TABLE IF EXISTS " + mTableName;
    };

    public String deleteTableSql() {
        return "DELETE TABLE " + mTableName;
    };

    public abstract String updateTableSql();
}
