package pansong291.simplesidebarlistview.ui;

/**
 * Created by pansong291 on 2018/9/12.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pansong291.simplesidebarlistview.Other.PinyinComparator;
import pansong291.simplesidebarlistview.Other.SortModel;
import pansong291.simplesidebarlistview.R;
import pansong291.simplesidebarlistview.adapter.SortAdapter;
import pansong291.simplesidebarlistview.view.SideBar;

public class MainActivity extends Activity 
{
 private ListView listAccount;
 private SideBar sideBar;
 private TextView txtDialog;
 private SortAdapter sortAdapter;
 private List<SortModel> sourceDateList;

 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  listAccount = (ListView)findViewById(R.id.list);
  sideBar = (SideBar)findViewById(R.id.sidebar);
  txtDialog = (TextView)findViewById(R.id.txt_dialog);
  sideBar.setTextView(txtDialog);

  //设置右侧触摸监听
  sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
    @Override
    public void onTouchingLetterChanged(char c)
    {
     //该字母首次出现的位置
     int position = sortAdapter.getPositionForSection(c);
     if (position != -1)
     {
      listAccount.setSelection(position);
     }
    }
   });

  //ListView的点击事件
  listAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id)
    {
     SortModel sm = (SortModel) sortAdapter.getItem(position);
     Toast.makeText(getApplication(), sm.getName(), Toast.LENGTH_SHORT).show();
    }
   });

  sourceDateList = filledData(getResources().getStringArray(R.array.string_array));
  Collections.sort(sourceDateList, PinyinComparator.getPyComp());
  sortAdapter = new SortAdapter(this, sourceDateList);
  listAccount.setAdapter(sortAdapter);  
 }

 /**
  * 为ListView填充数据
  * @param date
  * @return
  */
 private List<SortModel> filledData(String[] date)
 {
  List<SortModel> mSortList = new ArrayList<SortModel>();
  ArrayList<Character> indexChar = new ArrayList<Character>();

  for (int i = 0; i < date.length; i++)
  {
   SortModel sortModel = new SortModel(date[i]);
   char sortChar = sortModel.getSortChar();
   if (!indexChar.contains(sortChar))
   {
    indexChar.add(sortChar);
   }
   mSortList.add(sortModel);
  }
  Collections.sort(indexChar);
  if (indexChar.contains('#'))
  {
   indexChar.remove(0);
   indexChar.add('#');
  }
  sideBar.setIndexText(indexChar);
  return mSortList;
 }

}
