package com.view.arc;



import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.arcproressbar.R;

public class ProgressView extends View {
	
	//π
	private double pai=3.14159265358979323846;
	// 背景矩形
	private RectF rectBg;

	// 进度槽
	private Paint progressGroove;

	// 进度条
	private Paint progressBar;

	// 进度条的圆
	private Paint progressRound;

	// 进度槽开始角度
	private int startAngle = -220;
	// 进度槽起始角度
	private int endAngle = 260;

	// 圆心
	// 直径 半径为diameter/2
	private int diameter = 450;

	// 进度宽度
	private int barStrokeWidth = 15;
	private int barWidth = 18;

	public ProgressView(Context context) {
		super(context);
		init();
	}
	public ProgressView(Context context, AttributeSet attrs) {  
		super(context, attrs);  
		init();
	}  
	float barEndAngle=0;
	private int left;
	private int top;
	private int right;
	private int bottom;
	private int scrrenwidth,scrrenheight;
	public int getScrrenwidth() {
		return scrrenwidth;
	}
	public void setScrrenwidth(int scrrenwidth) {
		this.scrrenwidth = scrrenwidth;
	}
	public int getScrrenheight() {
		return scrrenheight;
	}
	public void setScrrenheight(int scrrenheight) {
		this.scrrenheight = scrrenheight;
	}
	private void init() {
		
		// 初始化进度槽的画笔
		progressGroove = new Paint();
		progressGroove.setAntiAlias(true);
		progressGroove.setFlags(Paint.ANTI_ALIAS_FLAG);// 帮助消除锯齿  
		progressGroove.setStrokeWidth(barStrokeWidth);
		progressGroove.setColor(0XFFD64545);
		progressGroove.setStrokeCap(Cap.ROUND);//画笔样式为圆
		progressGroove.setStyle(Paint.Style.STROKE);// 设置中空的样式
		// 初始化进度条的画笔
		progressBar = new Paint();
		progressBar.setAntiAlias(true);
		progressBar.setFlags(Paint.ANTI_ALIAS_FLAG);// 帮助消除锯齿  
		progressBar.setStrokeWidth(barWidth);
		progressBar.setColor(0XFFffffff);
		progressBar.setStrokeCap(Cap.ROUND);//画笔样式为圆
		progressBar.setStyle(Paint.Style.STROKE);// 设置中空的样式
		// 初始化进度圆的画笔
		progressRound = new Paint();
		progressRound.setAntiAlias(true);

	}

	public void setProgress(int progress) {
		float a=((float)progress / 100) * 260;
		Log.i("test", "----"+a);
		barEndAngle=a;
//		barEndAngle=progress;
		
		invalidate();
	}

	

	@SuppressLint("DrawAllocation") @Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.i("test", getWidth()+"//");
		left = getWidth()/2-150;
		top =  getWidth()/2-150;
		right =  getWidth()/2+150;
		bottom =  getWidth()/2+150;
		rectBg = new RectF(left, top, right, bottom);
//		init();
		// 计算弧形的圆心和半径。
        int cx1 = (left+right) / 2;
        int cy1 = (left+right) / 2;
        int arcRadius = (right-left) / 2;
		// 画进度槽
		canvas.drawArc(rectBg, 140, 260, false, progressGroove);
		// 画进度条
		
		canvas.drawArc(rectBg, 140, barEndAngle, false, progressBar);
		
		Log.e("当前进度条增加角度", "==========" +barEndAngle);
		//画那个小圆
		Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.dian);
		
		int height=bitmap.getHeight();
		int width=bitmap.getWidth();
		float l1=(float) (cx1 + arcRadius
                * Math.cos(( (140+barEndAngle) * pai / 180)));
		float l2=(float) (cy1 + arcRadius
                * Math.sin(( (140+barEndAngle) * pai / 180)));
		canvas.drawCircle( l1, l2, barStrokeWidth / 2, progressBar);
		l1=l1-width/2;
		l2=l2-height/2;
        canvas.drawBitmap(bitmap, l1, l2, progressRound);
        
        
	}
}
