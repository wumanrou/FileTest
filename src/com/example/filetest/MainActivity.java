package com.example.filetest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Button read,write;
	private EditText readText,writeText;
	//设置保存的文件名
	private String fileName="content.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取“读取内容”按钮
		read=(Button)findViewById(R.id.read);
		//获取“写入内容”按钮
		write=(Button)findViewById(R.id.write);
		readText=(EditText)findViewById(R.id.readText);
		writeText=(EditText)findViewById(R.id.writeText);
		//添加事件处理
		read.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				//
				readText.setText(read());
			}
		});
		write.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				write(writeText.getText().toString());
			}
		});
	}
	public void write (String content){
		try{
			FileOutputStream fos=openFileOutput(fileName,Context.MODE_APPEND);
			PrintStream ps=new PrintStream(fos);
			ps.close();
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String read(){
		StringBuilder sbBuilder=new StringBuilder("");
		try{
			FileInputStream is=openFileInput(fileName);
			byte[]buffer=new byte[64];
			int hasRead;
			while ((hasRead=is.read(buffer))!=-1){
				sbBuilder.append(new String(buffer,0,hasRead));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sbBuilder.toString();
	}

}
