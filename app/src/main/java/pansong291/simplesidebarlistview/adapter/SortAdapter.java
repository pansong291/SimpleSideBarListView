package pansong291.simplesidebarlistview.adapter;

/**
 * Created by pansong291 on 2018/9/12.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import java.util.List;
import pansong291.simplesidebarlistview.Other.SortModel;
import pansong291.simplesidebarlistview.R;

public class SortAdapter extends BaseAdapter implements SectionIndexer
{
 private List<SortModel> list = null;
 private Context mContext;

 public SortAdapter(Context mContext, List<SortModel> list)
 {
  this.mContext = mContext;
  this.list = list;
 }

 /**
  * 当ListView数据发生变化时,调用此方法来更新ListView
  *
  * @param list
  */
 public void updateListView(List<SortModel> list)
 {
  this.list = list;
  notifyDataSetChanged();
 }

 public int getCount()
 {
  return this.list.size();
 }

 public Object getItem(int position)
 {
  return list.get(position);
 }

 public long getItemId(int position)
 {
  return position;
 }

 public View getView(int position, View view, ViewGroup arg2)
 {
  ViewHolder viewHolder = null;
  SortModel mContent = list.get(position);
  if (view == null)
  {
   viewHolder = new ViewHolder();
   view = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
   viewHolder.txtName = (TextView) view.findViewById(R.id.txt_name);
   viewHolder.txtPinyin = (TextView) view.findViewById(R.id.txt_pinyin);
   viewHolder.vhline = view.findViewById(R.id.view_hline);
   viewHolder.txtSortChar = (TextView) view.findViewById(R.id.txt_catagory);
   view.setTag(viewHolder);
  }
  else
  {
   viewHolder = (ViewHolder) view.getTag();
  }

  int section = getSectionForPosition(position);

  if (position == getPositionForSection(section))
  {
   viewHolder.txtSortChar.setVisibility(View.VISIBLE);
   viewHolder.txtSortChar.setText(String.valueOf(mContent.getSortChar()));
   viewHolder.vhline.setVisibility(View.GONE);
  }
  else
  {
   viewHolder.txtSortChar.setVisibility(View.GONE);
   viewHolder.vhline.setVisibility(View.VISIBLE);
  }

  viewHolder.txtName.setText(mContent.getName());
  viewHolder.txtPinyin.setText(mContent.getPinyin());

  return view;

 }


 private final static class ViewHolder
 {
  TextView txtSortChar;
  View vhline;
  TextView txtName;
  TextView txtPinyin;
 }

 public int getSectionForPosition(int position)
 {
  return list.get(position).getSortChar();
 }

 public int getPositionForSection(int section)
 {
  for (int i = 0; i < getCount(); i++)
  {
   if (list.get(i).getSortChar() == section)
   {
    return i;
   }
  }
  return -1;
 }

 @Override
 public Object[] getSections()
 {
  return null;
 }
}

