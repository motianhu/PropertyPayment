package com.smona.app.propertypayment.db.table;

import java.util.ArrayList;
import java.util.HashMap;

import com.smona.app.propertypayment.common.util.LogUtil;

import com.smona.app.propertypayment.PaymentApplication;
import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

@SuppressLint("Registered")
public class PaymentProvider extends ContentProvider {
    private static final String TAG = "PaymentProvider";
    private DatabaseHelper mDataHelper;

    private static final UriMatcher URI_MATCH = new UriMatcher(
            UriMatcher.NO_MATCH);

    @SuppressLint("UseSparseArrays")
    private static HashMap<Integer, String> TABLE_MATCH = new HashMap<Integer, String>();

    static {

    }

    @Override
    public boolean onCreate() {
        LogUtil.d(TAG, "onCreate");
        mDataHelper = new DatabaseHelper(getContext());
        ((PaymentApplication) (getContext().getApplicationContext()))
                .setPropertyProvider(this);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        int match = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(match);
        qb.setTables(tableName);

        SQLiteDatabase db = mDataHelper.getReadableDatabase();
        return qb.query(db, projection, selection, selectionArgs, null, null,
                sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(match);
        LogUtil.d(TAG, "getType uri: " + uri + ";tableName: " + tableName);
        if (tableName != null) {
            return "vnd.android.cursor.dir/wallpaper";
        } else {
            throw new IllegalArgumentException("Unknown URL");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = URI_MATCH.match(uri);
        SQLiteDatabase db = mDataHelper.getWritableDatabase();
        String tableName = TABLE_MATCH.get(match);
        long rowId = db.insert(tableName, null, values);
        Uri newUrl = null;
        if (rowId != -1) {
            newUrl = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(newUrl, null);
        }
        return newUrl;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDataHelper.getWritableDatabase();

        int count = -1;
        int matchValue = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(matchValue);
        count = db.delete(tableName, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    public void batchInserts(Uri uri, ArrayList<ContentValues> list) {
        int matchValue = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(matchValue);
        SQLiteDatabase db = mDataHelper.getWritableDatabase();
        db.beginTransaction();
        for (ContentValues v : list) {
            db.insert(tableName, null, v);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void batchUpdatesOrInserts(Uri uri, ArrayList<ContentValues> list,
            String whereColumn) {
        int matchValue = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(matchValue);
        SQLiteDatabase db = mDataHelper.getWritableDatabase();
        db.beginTransaction();
        for (ContentValues v : list) {
            String whereClause = whereColumn + "=?";
            String[] whereArgs = new String[] { ""
                    + v.getAsInteger(whereColumn) };
            int count = db.update(tableName, v, whereClause, whereArgs);
            if (count <= 0) {
                db.insert(tableName, null, v);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void batchUpdateLocals(Uri uri, ArrayList<ContentValues> list,
            String whereColumn) {
        int matchValue = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(matchValue);
        SQLiteDatabase db = mDataHelper.getWritableDatabase();
        db.beginTransaction();
        String[] whereArgs;
        for (ContentValues v : list) {
            String whereClause = whereColumn + "=?";
            String where = v.getAsString(whereColumn);
            whereArgs = new String[] { where };
            int count = db.update(tableName, v, whereClause, whereArgs);
            if (count <= 0) {
                db.insert(tableName, null, v);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        SQLiteDatabase db = mDataHelper.getWritableDatabase();

        int count = -1;
        int match = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(match);
        count = db.update(tableName, values, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    public void updateOrInsert(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        int count = -1;
        count = update(uri, values, selection, selectionArgs);
        LogUtil.d(TAG, "count = " + count);
        if (count <= 0) {
            insert(uri, values);
        }
    }

    static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "payment.db";
        private static final int DATABASE_VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            LogUtil.d(TAG, "DatabaseHelper onCreate");
            ArrayList<String> sqlList = getCreateTableSqlList();
            for (String sql : sqlList) {
                db.execSQL(sql);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion >= newVersion) {
                return;
            }
            LogUtil.d(TAG, "DatabaseHelper onUpgrade oldVersion: " + oldVersion
                    + ", newVersion: " + newVersion);

            ArrayList<String> dropList = getDropTableSqlList();
            for (String sql : dropList) {
                db.execSQL(sql);
            }

            onCreate(db);
        }

        private ArrayList<String> getCreateTableSqlList() {
            ArrayList<String> sqlList = new ArrayList<String>();
            return sqlList;
        }

        private ArrayList<String> getDropTableSqlList() {
            ArrayList<String> sqlList = new ArrayList<String>();
            return sqlList;
        }
    }

}
