package andy.ham;

/**
 * Created by Adiministrat on 2018/6/14.
 */
        import android.app.Activity;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView tv_title;
    private EditText login_name, login_psd;
    private SharedPreferences preferences;

    private SqliteHelper helper;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_title = (TextView) findViewById(R.id.title);
        login_name = (EditText) findViewById(R.id.login_name);
        login_psd = (EditText) findViewById(R.id.login_psd);
        tv_title.setText("登录界面");
        preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
    }

    public void login_btn(View v) {
        String name = login_name.getText().toString();
        String psd = login_psd.getText().toString();

        if (!name.equals(preferences.getString("name", ""))) {
            Toast.makeText(this, "用户名不存在", Toast.LENGTH_LONG).show();
        } else if (!psd.equals(preferences.getString("psd", ""))) {
            Toast.makeText(this, "" + "密码错误", Toast.LENGTH_LONG).show();
        }else {
//			跳入主界面
           helper=new SqliteHelper(this, "books.db", null, 1);
            db=helper.getWritableDatabase();
       //     startActivity(new Intent(this, BookActivity.class));
            this.finish();
            Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        }
    }

    public void register_btn(View v) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

}
