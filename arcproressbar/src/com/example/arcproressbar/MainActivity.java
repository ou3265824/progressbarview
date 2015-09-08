package com.example.arcproressbar;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.view.arc.ProgressView;

/**
 * @author Peng
 * @create 2015Äê7ÔÂ15ÈÕ
 * @version
 */
public class MainActivity extends Activity
{
    private ProgressView testConvas;
    
    private int i = 0;
    
    Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
        	testConvas.setProgress(msg.what);
        };
    };

    Thread thread=new Thread(){
    	public void run() {
    		try {
    			while(true){
    				if(i==100)
    					thread.interrupt();
            		else
            			i=i+1;

    	        	//Log.e("iii", "=========="+i);
    	        	
    				sleep(100);
    				Message msg=new Message();
    				msg.what=i;
    				handler.sendMessage(msg);
    			}

        	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	};
    };

    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testConvas = (ProgressView)findViewById(R.id.progress);// find
        thread.start();
    }
}
