package com.andrukhiv.mynavigationdrawer.database;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;

import com.andrukhiv.mynavigationdrawer.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import static com.andrukhiv.mynavigationdrawer.tables.BugTable.BUG_NAME_TABLE;
import static com.andrukhiv.mynavigationdrawer.tables.FormationTable.STRUCTURE_FORMATION_TABLE;
import static com.andrukhiv.mynavigationdrawer.tables.KitchenTable.KITCHEN_TABLE;
import static com.andrukhiv.mynavigationdrawer.tables.LaboratoryInstruction.LABORATORY_INSTRUCTION_NAME_TABLE;
import static com.andrukhiv.mynavigationdrawer.tables.LaboratoryTable.LABORATORY_NAME_TABLE;
import static com.andrukhiv.mynavigationdrawer.tables.LibraryTable.LIBRARY_NAME_TABLE;
import static com.andrukhiv.mynavigationdrawer.tables.MapsTable.MAPS_NAME_TABLE;
import static com.andrukhiv.mynavigationdrawer.tables.PreparatyTable.PREPARATY_TABLE;
import static com.andrukhiv.mynavigationdrawer.tables.ReproductionTable.REPRODUCTION_TABLE;
import static com.andrukhiv.mynavigationdrawer.tables.SpecificationsTable.SPECIFICATIONS_TABLE;

public class DbHelper extends SQLiteOpenHelper {

    private static String TAG = "DbHelper"; // Тег только для окна LogCat
    protected static String DB_PATH = ""; //путь назначения (местоположения) нашей базы данных на устройстве
    protected static String DB_NAME = "som_v5.db";// Название базы данных
    private static final int DB_VERSION = 1;// Версия базы данных
    private SQLiteDatabase mDataBase;
    private final Context mContext;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }


    public void createDataBase() throws IOException {
        // Если база данных не существует, скопируйте ее из assests

        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                // Скопировать базу данных из assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
   }


    // Убедитесь, что база данных существует здесь: /data/data/your package/databases/Da Name
    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }


    //Скопировать базу данных из assests
    protected void copyDataBase() throws IOException {
        // http://blog.harrix.org/article/6784
        // InputStream mInput = mContext.getResources().openRawResource(R.raw.grapes);
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }


    // Откройте базу данных, чтобы мы могли ее запросить.
    public void openDataBase() throws SQLException {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    }


    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }


//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }

    @Override
    public final void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//                db.execSQL(  "DROP TABLE IF EXISTS " + SPECIFICATIONS_TABLE);
//        //db.execSQL(  "DROP TABLE IF EXISTS " + MATCH_TABLE);
//        onCreate(db);
    }
}