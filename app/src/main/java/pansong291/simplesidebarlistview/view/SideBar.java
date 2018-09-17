package pansong291.simplesidebarlistview.view;

/**
 * Created by pansong291 on 2018/9/12.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.graphics.Typeface;

/**
 * ListView右侧的字母索引View
 */
public class SideBar extends View
{

 public static Character[] INDEX_CHARACTER = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
  'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
  'W', 'X', 'Y', 'Z', '#'};

 private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
 private List<Character> charList;
 private int choose = -1;
 private Paint paint = new Paint();
 private TextView mTextDialog;

 public SideBar(Context context)
 {
  this(context, null);
 }

 public SideBar(Context context, AttributeSet attrs)
 {
  this(context, attrs, 0);
 }

 public SideBar(Context context, AttributeSet attrs, int defStyle)
 {
  super(context, attrs, defStyle);
  init();
 }

 private void init()
 {
  setBackgroundColor(Color.parseColor("#FFFFFF"));
  charList = Arrays.asList(INDEX_CHARACTER);
 }

 protected void onDraw(Canvas canvas)
 {
  super.onDraw(canvas);
  int height = getHeight();// 获取对应高度
  int width = getWidth();// 获取对应宽度
  int fontSize = width * 3 / 4;
  int singleHeight = height / charList.size();// 获取每一个字母的高度
  for (int i = 0; i < charList.size(); i++)
  {
   paint.setColor(Color.parseColor("#606060"));
   paint.setTypeface(Typeface.MONOSPACE);//Typeface.DEFAULT_BOLD);
   paint.setAntiAlias(true);
   paint.setFakeBoldText(true);
   paint.setTextSize(fontSize);
   // 选中的状态
   if (i == choose)
   {
    paint.setColor(Color.parseColor("#4F41FD"));
    if(fontSize + 10 <= width)
     paint.setTextSize(fontSize + 10);
    else paint.setTextSize(width);
   }
   float ww = paint.measureText(String.valueOf(charList.get(i)));
   float hh = paint.descent() - paint.ascent();
   // x坐标等于中间-字符串宽度的一半.
   float xPos = width / 2 - ww / 2;
   float yPos = singleHeight * i + singleHeight / 2 + hh / 2;
   canvas.drawText(String.valueOf(charList.get(i)), xPos, yPos, paint);
   paint.reset();// 重置画笔
  }
 }

 @Override
 public boolean dispatchTouchEvent(MotionEvent event)
 {
  final int action = event.getAction();
  final float y = event.getY();// 点击y坐标
  final int oldChoose = choose;
  final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
  // 点击y坐标所占总高度的比例*charList数组的长度就等于点击bar中的个数.
  final int index = (int) (y / getHeight() * charList.size());

  switch (action)
  {
   case MotionEvent.ACTION_UP:
    setBackgroundColor(Color.parseColor("#FFFFFF"));
    choose = -1;
    invalidate();
    if (mTextDialog != null)
    {
     mTextDialog.setVisibility(View.GONE);
    }
    break;
   default:
    setBackgroundColor(Color.parseColor("#F0F0F0"));
    if (oldChoose != index)
    {
     if (index >= 0 && index < charList.size())
     {
      if (listener != null)
      {
       listener.onTouchingLetterChanged(charList.get(index));
      }
      if (mTextDialog != null)
      {
       mTextDialog.setText(String.valueOf(charList.get(index)));
       mTextDialog.setVisibility(View.VISIBLE);
      }
      choose = index;
      invalidate();
     }
    }
    break;
  }
  return true;
 }

 public void setIndexText(ArrayList<Character> indexChars)
 {
  this.charList = indexChars;
  invalidate();
 }

 /**
  * 为SideBar设置显示当前按下的字母的TextView
  *
  * @param mTextDialog
  */
 public void setTextView(TextView mTextDialog)
 {
  this.mTextDialog = mTextDialog;
 }

 /**
  * 向外公开的方法
  *
  * @param onTouchingLetterChangedListener
  */
 public void setOnTouchingLetterChangedListener(
  OnTouchingLetterChangedListener onTouchingLetterChangedListener)
 {
  this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
 }

 /**
  * 接口
  */
 public interface OnTouchingLetterChangedListener
 {
  void onTouchingLetterChanged(char c);
 }
}

