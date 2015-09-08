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
	
	//��
	private double pai=3.14159265358979323846;
	// ��������
	private RectF rectBg;

	// ���Ȳ�
	private Paint progressGroove;

	// ������
	private Paint progressBar;

	// ��������Բ
	private Paint progressRound;

	// ���Ȳۿ�ʼ�Ƕ�
	private int startAngle = -220;
	// ���Ȳ���ʼ�Ƕ�
	private int endAngle = 260;

	// Բ��
	// ֱ�� �뾶Ϊdiameter/2
	private int diameter = 450;

	// ���ȿ��
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
		
		// ��ʼ�����Ȳ۵Ļ���
		progressGroove = new Paint();
		progressGroove.setAntiAlias(true);
		progressGroove.setFlags(Paint.ANTI_ALIAS_FLAG);// �����������  
		progressGroove.setStrokeWidth(barStrokeWidth);
		progressGroove.setColor(0XFFD64545);
		progressGroove.setStrokeCap(Cap.ROUND);//������ʽΪԲ
		progressGroove.setStyle(Paint.Style.STROKE);// �����пյ���ʽ
		// ��ʼ���������Ļ���
		progressBar = new Paint();
		progressBar.setAntiAlias(true);
		progressBar.setFlags(Paint.ANTI_ALIAS_FLAG);// �����������  
		progressBar.setStrokeWidth(barWidth);
		progressBar.setColor(0XFFffffff);
		progressBar.setStrokeCap(Cap.ROUND);//������ʽΪԲ
		progressBar.setStyle(Paint.Style.STROKE);// �����пյ���ʽ
		// ��ʼ������Բ�Ļ���
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
		// ���㻡�ε�Բ�ĺͰ뾶��
        int cx1 = (left+right) / 2;
        int cy1 = (left+right) / 2;
        int arcRadius = (right-left) / 2;
		// �����Ȳ�
		canvas.drawArc(rectBg, 140, 260, false, progressGroove);
		// ��������
		
		canvas.drawArc(rectBg, 140, barEndAngle, false, progressBar);
		
		Log.e("��ǰ���������ӽǶ�", "==========" +barEndAngle);
		//���Ǹ�СԲ
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
