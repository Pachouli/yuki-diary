package andy.ham;

/**
 * Created by Adiministrat on 2018/6/14.
 */

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteDatabase.CursorFactory;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

public class SqliteHelper extends SQLiteOpenHelper {
    public static String TABLE="book";
    private String table = "create table book(id integer primary key autoincrement,title,content,pri_path,vedio_path,book_type)";

    public SqliteHelper(Context context, String name, CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            Log.e("Sqlite_Version", "oldVersion:" + oldVersion + "-----"
                    + "newVersion:" + newVersion);
        }

    }

}

