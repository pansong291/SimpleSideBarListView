package pansong291.simplesidebarlistview.Other;

/**
 * Created by pansong291 on 2018/9/12.
 */
import java.util.ArrayList;
import java.util.List;

public class SortModel
{

 private String name;//显示的数据
 private String pinyin;//数据对应的拼音
 private char sortChar;//显示数据拼音的首字母

 public SortModel(String name)
 {
  this.name = name;
  this.pinyin = PinyinUtils.getPingYin(name);
  this.sortChar = pinyin.charAt(0);
  if (sortChar<'A'||sortChar>'Z')
  {
   sortChar = '#';
  }
 }

 public void setPinyin(String pinyin)
 {
  this.pinyin = pinyin.toUpperCase();
 }

 public String getPinyin()
 {
  return pinyin;
 }

 public String getName()
 {
  return name;
 }

 public void setName(String name)
 {
  this.name = name;
 }

 public char getSortChar()
 {
  return sortChar;
 }

}

