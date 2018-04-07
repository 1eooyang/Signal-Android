package org.thoughtcrime.securesms.database.helpers;

import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;

import net.sqlcipher.database.SQLiteDatabase;

import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.database.MmsDatabase;
import org.thoughtcrime.securesms.database.SearchDatabase;
import org.thoughtcrime.securesms.database.SmsDatabase;
import org.thoughtcrime.securesms.service.GenericForegroundService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FullTextSearchMigrationHelper {

  private static final String   TAG      = FullTextSearchMigrationHelper.class.getSimpleName();

  public static void migrateToFullTextSearch(@NonNull SQLiteDatabase db) {
    for (String sql : SearchDatabase.CREATE_TABLE) {
      db.execSQL(sql);
    }

    Log.i(TAG, "Beginning to build search index.");
    long start = SystemClock.elapsedRealtime();

    db.execSQL("INSERT INTO " + SearchDatabase.SMS_FTS_TABLE_NAME + " (rowid, " + SearchDatabase.BODY + ") " +
        "SELECT " + SmsDatabase.ID + " , " + SmsDatabase.BODY + " FROM " + SmsDatabase.TABLE_NAME);

    long smsFinished = SystemClock.elapsedRealtime();
    Log.i(TAG, "Indexing SMS completed in " + (smsFinished - start) + " ms");

    db.execSQL("INSERT INTO " + SearchDatabase.MMS_FTS_TABLE_NAME + " (rowid, " + SearchDatabase.BODY + ") " +
        "SELECT " + MmsDatabase.ID + " , " + MmsDatabase.BODY + " FROM " + MmsDatabase.TABLE_NAME);

    long mmsFinished = SystemClock.elapsedRealtime();
    Log.i(TAG, "Indexing MMS completed in " + (mmsFinished - smsFinished) + " ms");
    Log.i(TAG, "Indexing finished. Total time: " + (mmsFinished - start) + " ms");

  }
}
