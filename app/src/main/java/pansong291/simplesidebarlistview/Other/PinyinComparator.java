package pansong291.simplesidebarlistview.Other;

/**
 * Created by pansong291 on 2018/9/12.
 */
import java.util.Comparator;

/**
 * 用来对ListView中的数据根据A-Z进行排序
 */
public class PinyinComparator implements Comparator<SortModel>
{

 private static PinyinComparator pyComp=new PinyinComparator();

 private PinyinComparator(){}

 public static PinyinComparator getPyComp()
 {
  return pyComp;
 }

 public int compare(SortModel o1, SortModel o2)
 {
  //同组则对拼音排序
  if (o1.getSortChar() == o2.getSortChar())
   return o1.getPinyin().compareTo(o2.getPinyin());
  //不同组先把数字及其他符号放在末尾
  else if (o1.getSortChar() == '#') return 1;
  else if (o2.getSortChar() == '#') return -1;
  //剩余的比较组号
  else return (o1.getSortChar() - o2.getSortChar());
 }
}

