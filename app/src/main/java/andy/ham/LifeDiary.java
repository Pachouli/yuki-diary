package andy.ham;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import andy.ham.Fields.DiaryColumns;
//继承来自listView
public class LifeDiary extends ListActivity {
	// 插入一条新纪录
	public static final int MENU_ITEM_INSERT = Menu.FIRST;
	// 编辑内容
//	public static final int MENU_ITEM_EDIT = Menu.FIRST + 1;
	public static final int MENU_ITEM_DELETE = Menu.FIRST + 1;
	private static final String[] PROJECTION = 
		new String[] { DiaryColumns._ID,
			DiaryColumns.TITLE, DiaryColumns.CREATED };
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_list);
        
        Intent intent = getIntent();
		if (intent.getData() == null) {
			intent.setData(DiaryColumns.CONTENT_URI);
		}
		Cursor cursor = managedQuery(getIntent().getData(), 
				PROJECTION, null,null, DiaryColumns.DEFAULT_SORT_ORDER);
		/*SimpleCursorAdapter允许你绑定一个游标的列到ListView上
		并使用自定义的layout显示每个项目。
		SimpleCursorAdapter的创建，需要传入
		当前的上下文、一个layout资源，一个游标和两个数组
		两个数组：1使用的列的名字，2（相同大小）数组包含View中的资源ID
		用于显示相应列的数据值。*/
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.activity_main, cursor, new String[]
				{ DiaryColumns.TITLE,DiaryColumns.CREATED }, 
				new int[] { R.id.text1,R.id.created });
		setListAdapter(adapter);
    }   
    //添加选择菜单
    public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, MENU_ITEM_INSERT, 0, R.string.menu_insert);
		menu.add(Menu.NONE, MENU_ITEM_DELETE, 0, R.string.menu_delete);
		return true;
	}
    //添加选择菜单的选择事件
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// 插入一条数据
		case MENU_ITEM_INSERT:
			Intent intent0 = new Intent(this, DiaryEditor.class);
			intent0.setAction(DiaryEditor.INSERT_DIARY_ACTION);
			intent0.setData(getIntent().getData());
			startActivity(intent0);
			return true;
			// 编辑当前数据内容通过单击日记名实现
			// 删除当前数据
		case MENU_ITEM_DELETE:
			Uri uri = ContentUris.withAppendedId(getIntent().getData(),
					getListView().getSelectedItemId());
			getContentResolver().delete(uri, null, null);
			renderListView();
		}
		return super.onOptionsItemSelected(item);
	} 
	protected void onListItemClick
	(ListView l, View v, int position, long id) {
		Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);
		startActivity(new Intent(DiaryEditor.EDIT_DIARY_ACTION, uri));
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		//renderListView();
	}
	private void renderListView() {
		Cursor cursor = managedQuery(getIntent().getData(), PROJECTION,
				null,null, DiaryColumns.DEFAULT_SORT_ORDER);

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
			R.layout.diary_row, cursor, new String[] { DiaryColumns.TITLE,
			DiaryColumns.CREATED }, new int[] { R.id.text1,R.id.created });
		setListAdapter(adapter);
	}
}
    