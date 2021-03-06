package com.example.testanimate;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.view.ViewHelper;
import com.sz.main.test.R;

/**
 * @author shizhe
 *
 */
public class GridAnimateActivity extends Activity {
	private ImageView test_img ,gift_img;
	private Button test_anim_back1;
	private EditText gift_start;
	private EditText gift_end;

	DisplayMetrics dm = null;
	
	private GiftPositionB[] pppp  = new GiftPositionB[10];
	private GridView grid_view;
	
	private ArrayList<Integer> ic_list = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_giftannim);
		
		getScreen();//初始界面属性
		
		for(int i =0;i<8 ; i++){
			ic_list.add(R.drawable.avatar_default);
		}
		grid_view = (GridView) findViewById(R.id.grid_view);
		grid_view.setAdapter(new MaiWeiAdapter());
		grid_view.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick ( AdapterView<?> parent, View view, int position, long id ) {
				int[] ll = new int[2];
				ImageView iv = (ImageView) view.findViewById(R.id.gift); 
				
				//初始化每一个item的屏幕位置
				iv.getLocationOnScreen(ll);  
				System.out.println( "ll[0] " + ll[0] + "=== ll[1] " +  ll[1]);
				if( pppp[position] == null ){
					pppp[position] = new GiftPositionB();
					pppp[position].index = position;
					pppp[position].px = ll[0];
					pppp[position].py = ll[1];
				}
//				animate1( position , 9 , 8  );
			}
		});
		test_img = (ImageView)findViewById(R.id.test_img);
		gift_img = (ImageView)findViewById(R.id.gift_img);
		test_anim_back1 = (Button)findViewById(R.id.test_anim_back1);
		gift_start = (EditText)findViewById(R.id.gift_start);
		gift_end = (EditText)findViewById(R.id.gift_end);
		
		
		ViewTreeObserver vto2 = test_img.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				initPPPPP();
				test_img.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
		
		test_anim_back1.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String start = gift_start.getText().toString();
				String end = gift_end.getText().toString();
				
				if( TextUtils.isEmpty(start)  ){
					start = "0";
				}
				
				if( TextUtils.isEmpty(end)  ){
					end = "8";
				}
				System.out.println("start" +  start + "end" + end);
				GiftAnimation.animate1(gift_img, Integer.parseInt(start) , Integer.parseInt(end) ,pppp);
			}
		});
	}
	
	private void getScreen(){
		dm = new DisplayMetrics();
		GridAnimateActivity.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
	}
	
	private void initPPPPP() {
		int[] ll = new int[2];
		test_img.getLocationOnScreen(ll);
//		test_img.getLocationInWindow(ll);
		System.out.println( "ll[0] " + ll[0] + "=== ll[1] " +  ll[1]);
		
		//麦主位置
		pppp[8] = new GiftPositionB();
		pppp[8].index = 8;
		pppp[8].px = ll[0];
		pppp[8].py = ll[1];
		
		//中点位置
		gift_img.getLocationOnScreen(ll);
		System.out.println( "ll[0] " + ll[0] + "=== ll[1] " +  ll[1]);
		pppp[9] = new GiftPositionB();
		pppp[9].index = 9;
		pppp[9].px = ll[0] ;
		pppp[9].py = ll[1] ;
	}
	
	private class MaiWeiAdapter  extends BaseAdapter{
		
		@Override
		public int getCount() {
			return 8;
		}

		@Override
		public Object getItem(int arg0) {
			return ic_list.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int position, View arg1, ViewGroup arg2) {
			View v = View.inflate(getBaseContext(), R.layout.item_gift, null);
			ImageView iv = (ImageView) v.findViewById(R.id.gift);
			iv.setImageResource(ic_list.get(position));
			
//			int[] ll = new int[2];
//			//初始化每一个item的屏幕位置
//			iv.getLocationOnScreen(ll);  
//			System.out.println( "ll[0] " + ll[0] + "=== ll[1] " +  ll[1]);
//			if( pppp[position] == null ){
//				pppp[position] = new Position();
//				pppp[position].index = position;
//				pppp[position].px = ll[0];
//				pppp[position].py = ll[1];
//			}
			return v;
		}
		
	}
	
}
