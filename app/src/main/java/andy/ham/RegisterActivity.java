package andy.ham;

/**
 * Created by Adiministrat on 2018/6/14.
 */

        import android.app.Activity;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.RadioGroup.OnCheckedChangeListener;
        import android.widget.TextView;
        import android.widget.Toast;

public class RegisterActivity extends Activity {
    private TextView tv_title;
    private EditText register_name,register_psd,age;
    private RadioButton rg_man,rg_woman;
    private RadioGroup rg_group;
    private String sex=null;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        tv_title=(TextView) this.findViewById(R.id.title);
        register_name=(EditText)findViewById(R.id.register_name);
        register_psd=(EditText) findViewById(R.id.register_psd);
        age=(EditText) findViewById(R.id.age);
        rg_group=(RadioGroup) findViewById(R.id.rd_group);
        rg_man=(RadioButton) findViewById(R.id.rd_man);
        rg_woman=(RadioButton) findViewById(R.id.rd_not_man);

        tv_title.setText("注册界面");
        preferences=getSharedPreferences("userinfo", MODE_PRIVATE);

        rg_group.setOnCheckedChangeListener(onCheckedChangeListener);

    }
    private OnCheckedChangeListener onCheckedChangeListener=new OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rd_man:
                    sex=rg_man.getText().toString();
                    break;
                case R.id.rd_not_man:
                    sex=rg_woman.getText().toString();
                    break;
            }

        }
    };

    public void register_btn(View v){
        String name=register_name.getText().toString();
        String psd=register_psd.getText().toString();
        String ageinfo=age.getText().toString();

        if (name==null) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_LONG).show();
        }else if (psd==null) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
        }else if (sex==null) {
            sex="男";
        }else {
            if (ageinfo==null) {
                ageinfo="暂未填写";
            }
            editor=preferences.edit();
            editor.putString("name", name);
            editor.putString("psd", psd);
            editor.putString("sex", sex);
            editor.putString("age",ageinfo);
            editor.commit();

            Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
            this.finish();
        }



    }
}
