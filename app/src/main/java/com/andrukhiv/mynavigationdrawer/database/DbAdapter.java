package com.andrukhiv.mynavigationdrawer.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.andrukhiv.mynavigationdrawer.models.BugModel;
import com.andrukhiv.mynavigationdrawer.models.FormationModel;
import com.andrukhiv.mynavigationdrawer.models.KitchenModel;
import com.andrukhiv.mynavigationdrawer.models.LaboratoryModel;
import com.andrukhiv.mynavigationdrawer.models.LaboratoryTableModel;
import com.andrukhiv.mynavigationdrawer.models.LibraryModel;
import com.andrukhiv.mynavigationdrawer.models.MapsModel;
import com.andrukhiv.mynavigationdrawer.models.PreparationDialogModel;
import com.andrukhiv.mynavigationdrawer.models.PreparatyModel;
import com.andrukhiv.mynavigationdrawer.models.ReproductionModel;
import com.andrukhiv.mynavigationdrawer.models.SpecificationsModel;
import com.andrukhiv.mynavigationdrawer.tables.BugTable;
import com.andrukhiv.mynavigationdrawer.tables.KitchenTable;
import com.andrukhiv.mynavigationdrawer.tables.LaboratoryInstruction;
import com.andrukhiv.mynavigationdrawer.tables.LaboratoryTable;
import com.andrukhiv.mynavigationdrawer.tables.LibraryTable;
import com.andrukhiv.mynavigationdrawer.tables.MapsTable;
import com.andrukhiv.mynavigationdrawer.tables.PreparationDialogTable;
import com.andrukhiv.mynavigationdrawer.tables.PreparatyTable;
import com.andrukhiv.mynavigationdrawer.tables.ReproductionTable;
import com.andrukhiv.mynavigationdrawer.tables.SpecificationsTable;
import com.andrukhiv.mynavigationdrawer.tables.FormationTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DbAdapter {

    private static final String TAG = "DbAdapter";

    private static SQLiteDatabase mDb;
    private DbHelper mDbHelper;

    @SuppressLint("StaticFieldLeak")
    private static DbAdapter sInstance;


    public static synchronized DbAdapter getInstance(Context context) {

        // Используйте контекст приложения, который гарантирует, что вы случайно
        // не протекаете контекст Activity.
        // См. Эту статью для получения дополнительной информации: http://bit.ly/6LRzfx

        if (sInstance == null) {
            sInstance = new DbAdapter(context.getApplicationContext());
        }
        return sInstance;
    }


    public DbAdapter(Context context) {
        mDbHelper = new DbHelper(context);
    }


    public void createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
    }


    public void open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException.toString());
            throw mSQLException;
        }
    }




    public void close() {
        mDbHelper.close();
    }


    public static ArrayList<SpecificationsModel> getAllGrapes() {

        ArrayList<SpecificationsModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " +
                SpecificationsTable.SPECIFICATIONS_TABLE;
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new SpecificationsModel(
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_SORT)),
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_SORT_INT)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_PHOTO_LARGE)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_PHOTO_SMALL)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_LINK)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_TERM)),
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_FROST)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_GROWTH)),
                        cursor.getDouble(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_WEIGHT)),
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_FAVORITE))
                ));
            }
            cursor.close();
        }
        return result;
    }

    public static ArrayList<SpecificationsModel> getTableGrapes() {

        ArrayList<SpecificationsModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " +
                SpecificationsTable.SPECIFICATIONS_TABLE +
                " WHERE " +
                SpecificationsTable.SPECIFICATIONS_COLUMN_SORT_INT + " =2";// столові сорти

        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new SpecificationsModel(
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_SORT)),
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_SORT_INT)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_PHOTO_LARGE)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_PHOTO_SMALL)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_LINK)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_TERM)),
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_FROST)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_GROWTH)),
                        cursor.getDouble(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_WEIGHT)),
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_FAVORITE))
                ));
            }
            cursor.close();
        }
        return result;
    }


    public static ArrayList<SpecificationsModel> getWineGrapes() {

        ArrayList<SpecificationsModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " +
                SpecificationsTable.SPECIFICATIONS_TABLE +
                " WHERE " +
                SpecificationsTable.SPECIFICATIONS_COLUMN_SORT_INT + " =1";// столові сорти

        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new SpecificationsModel(
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_SORT)),
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_SORT_INT)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_PHOTO_LARGE)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_PHOTO_SMALL)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_LINK)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_TERM)),
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_FROST)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_GROWTH)),
                        cursor.getDouble(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_WEIGHT)),
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_FAVORITE))
                ));
            }
            cursor.close();
        }
        return result;
    }

    public static ArrayList<SpecificationsModel> getSeedlessGrapes() {

        ArrayList<SpecificationsModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " +
                SpecificationsTable.SPECIFICATIONS_TABLE +
                " WHERE " +
                SpecificationsTable.SPECIFICATIONS_COLUMN_SORT_INT + " =3";// столові сорти

        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new SpecificationsModel(
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_SORT)),
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_SORT_INT)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_PHOTO_LARGE)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_PHOTO_SMALL)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_LINK)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_TERM)),
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_FROST)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_GROWTH)),
                        cursor.getDouble(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_WEIGHT)),
                        cursor.getInt(cursor.getColumnIndex(SpecificationsTable.SPECIFICATIONS_COLUMN_FAVORITE))
                ));
            }
            cursor.close();
        }
        return result;
    }


    public ArrayList<ReproductionModel> getReproduction() {

        ArrayList<ReproductionModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " + ReproductionTable.REPRODUCTION_TABLE;
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new ReproductionModel(
                        cursor.getLong(cursor.getColumnIndex(ReproductionTable.REPRODUCTION_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(ReproductionTable.REPRODUCTION_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(ReproductionTable.REPRODUCTION_COLUMN_DESCRIPTION))
                ));
            }
            cursor.close();
        }
        return result;
    }


    public ArrayList<FormationModel> getFormation() {

        ArrayList<FormationModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " + FormationTable.STRUCTURE_FORMATION_TABLE;
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new FormationModel(
                        cursor.getLong(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_PHOTO_1)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_PHOTO_2)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_PHOTO_3)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_PHOTO_4)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_PHOTO_5)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_PHOTO_6)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_DESCRIPTION_1)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_DESCRIPTION_2)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_DESCRIPTION_3)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_DESCRIPTION_4)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_DESCRIPTION_5)),
                        cursor.getString(cursor.getColumnIndex(FormationTable.STRUCTURE_FORMATION_COLUMN_DESCRIPTION_6))
                ));
            }
            cursor.close();
        }
        return result;
    }


    public static ArrayList<LibraryModel> getLibrary() {

        ArrayList<LibraryModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " +
                LibraryTable.LIBRARY_NAME_TABLE;
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new LibraryModel(
                        cursor.getInt(cursor.getColumnIndex(LibraryTable.LIBRARY_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(LibraryTable.LIBRARY_COLUMN_IMAGE)),
                        cursor.getString(cursor.getColumnIndex(LibraryTable.LIBRARY_COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(LibraryTable.LIBRARY_COLUMN_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(LibraryTable.LIBRARY_COLUMN_LINK))
                ));
            }
            cursor.close();
        }
        return result;
    }


    public static ArrayList<MapsModel> getMaps() {

        ArrayList<MapsModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " + MapsTable.MAPS_NAME_TABLE +
                " WHERE " +
                MapsTable.MAPS_COLUMN_REGION_INT + " =1";// столові сорти
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new MapsModel(
                        cursor.getLong(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_REGION)),
                        cursor.getInt(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_REGION_INT)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_WEB)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_PHONE)),
                        cursor.getDouble(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_LAT)),
                        cursor.getDouble(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_LNG)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_NAVIGATION_POSITION)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_IMAGE))
                ));
            }
            cursor.close();
        }
        return result;
    }


    public static ArrayList<MapsModel> getMapsOdesa() {

        ArrayList<MapsModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " + MapsTable.MAPS_NAME_TABLE +
                " WHERE " +
                MapsTable.MAPS_COLUMN_REGION_INT + " =2";// столові сорти
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new MapsModel(
                        cursor.getLong(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_REGION)),
                        cursor.getInt(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_REGION_INT)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_WEB)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_PHONE)),
                        cursor.getDouble(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_LAT)),
                        cursor.getDouble(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_LNG)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_NAVIGATION_POSITION)),
                        cursor.getString(cursor.getColumnIndex(MapsTable.MAPS_COLUMN_IMAGE))
                ));
            }
            cursor.close();
        }
        return result;
    }

    public static ArrayList<BugModel> getBugMildew() {

        ArrayList<BugModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " + BugTable.BUG_NAME_TABLE+
                " WHERE " +
                BugTable.BUG_COLUMN_CATEGORY_ID + " =1";
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new BugModel(
                        cursor.getString(cursor.getColumnIndex(BugTable.BUG_COLUMN_IMAGE)),
                        cursor.getString(cursor.getColumnIndex(BugTable.BUG_COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(BugTable.BUG_COLUMN_CATEGORY)),
                        cursor.getInt(cursor.getColumnIndex(BugTable.BUG_COLUMN_CATEGORY_ID))
                ));
            }
            cursor.close();
        }
        return result;
    }

    public static ArrayList<BugModel> getBugOidium() {

        ArrayList<BugModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " + BugTable.BUG_NAME_TABLE+
                " WHERE " +
                BugTable.BUG_COLUMN_CATEGORY_ID + " =2";
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new BugModel(
                        cursor.getString(cursor.getColumnIndex(BugTable.BUG_COLUMN_IMAGE)),
                        cursor.getString(cursor.getColumnIndex(BugTable.BUG_COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(BugTable.BUG_COLUMN_CATEGORY)),
                        cursor.getInt(cursor.getColumnIndex(BugTable.BUG_COLUMN_CATEGORY_ID))
                ));
            }
            cursor.close();
        }
        return result;
    }


    public ArrayList<LaboratoryModel> getLaboratoryInstruction() {

        ArrayList<LaboratoryModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " + LaboratoryInstruction.LABORATORY_INSTRUCTION_NAME_TABLE;
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new LaboratoryModel(
                        cursor.getLong(cursor.getColumnIndex(LaboratoryInstruction.LABORATORY_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(LaboratoryInstruction.LABORATORY_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(LaboratoryInstruction.LABORATORY_COLUMN_INSTRUCTION))
                ));
            }
            cursor.close();
        }
        return result;
    }

    public static ArrayList<LaboratoryTableModel> getLaboratoryTable() {

        ArrayList<LaboratoryTableModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " +
                LaboratoryTable.LABORATORY_NAME_TABLE;
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new LaboratoryTableModel(
                        cursor.getInt(cursor.getColumnIndex(LaboratoryTable.LABORATORY_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(LaboratoryTable.LABORATORY_COLUMN_SPECIFIC_GRAVITY_JUICE)),
                        cursor.getString(cursor.getColumnIndex(LaboratoryTable.LABORATORY_COLUMN_SUGAR_CONTENT_JUICE)),
                        cursor.getString(cursor.getColumnIndex(LaboratoryTable.LABORATORY_COLUMN_FORTRESS_FUTURE_WINE))
                ));
            }

        }
        Objects.requireNonNull(cursor).close();
        return result;
    }

    public static ArrayList<KitchenModel> getKitchen() {

        ArrayList<KitchenModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " + KitchenTable.KITCHEN_TABLE;
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new KitchenModel(
                        cursor.getLong(cursor.getColumnIndex(KitchenTable.KITCHEN_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(KitchenTable.KITCHEN_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(KitchenTable.KITCHEN_COLUMN_IMAGE_GLASS_ICON)),
                        cursor.getString(cursor.getColumnIndex(KitchenTable.KITCHEN_COLUMN_IMAGE_BACKGROUND)),
                        cursor.getString(cursor.getColumnIndex(KitchenTable.KITCHEN_COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(KitchenTable.KITCHEN_COLUMN_COMPONENTS)),
                        cursor.getString(cursor.getColumnIndex(KitchenTable.KITCHEN_COLUMN_RECIPE))
                ));
            }
            cursor.close();
        }
        return result;
    }


    public static ArrayList<PreparatyModel> getPreparaty() {

        ArrayList<PreparatyModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " + PreparatyTable.PREPARATY_TABLE;
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new PreparatyModel(
                        cursor.getString(cursor.getColumnIndex(PreparatyTable.PREPARATY_COLUMN_NAZVA_PREPARATU)),
                        cursor.getString(cursor.getColumnIndex(PreparatyTable.PREPARATY_COLUMN_SPEKTR_DII)),
                        cursor.getString(cursor.getColumnIndex(PreparatyTable.PREPARATY_COLUMN_VIROBNYK)),
                        cursor.getString(cursor.getColumnIndex(PreparatyTable.PREPARATY_COLUMN_KRATNIST_ZASTOSUVANNYA)),
                        cursor.getString(cursor.getColumnIndex(PreparatyTable.PREPARATY_COLUMN_STROKY_DO_SPOGIVANNYA)),
                        cursor.getString(cursor.getColumnIndex(PreparatyTable.PREPARATY_COLUMN_DIUCHA_RECHOVINA))
                ));
            }
        }
        assert cursor != null;
        cursor.close();
        return result;
    }


    public static ArrayList<PreparationDialogModel> getPreparationDialogTable() {

        ArrayList<PreparationDialogModel> result = new ArrayList<>();
        String sql = "SELECT * FROM " +
                PreparationDialogTable.PREPARATION_DIALOG_NAME_TABLE;
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new PreparationDialogModel(
                        cursor.getInt(cursor.getColumnIndex(PreparationDialogTable.PREPARATION_DIALOG_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(PreparationDialogTable.PREPARATION_DIALOG_COLUMN_SHORTER)),
                        cursor.getString(cursor.getColumnIndex(PreparationDialogTable.PREPARATION_DIALOG_COLUMN_LONGER))
                ));
            }
            cursor.close();
        }
        return result;
    }




}