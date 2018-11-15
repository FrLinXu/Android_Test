package com.example.progressbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    showReminderDialog();
                    break;
                case R.id.button3:
                    showConfirmDialog();
                    break;
                case R.id.button4:
                    showSingleChoiceDialog();
                    break;
                case R.id.button5:
                    showMultiChoiceDialog();
                    break;
                case R.id.btn_progress:
                    showProgressDialog();
                    break;
                case R.id.btn_progressBar:
                    showProgressBarDialog();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1= (Button ) findViewById(R.id.button) ;
        Button btn2 = (Button) findViewById(R.id.button3);
        Button btn3 = (Button) findViewById(R.id.button4);
        Button btn4 = (Button) findViewById(R.id.button5);
        Button btn5 = (Button) findViewById(R.id.btn_progress);
        Button btn6 = (Button) findViewById(R.id.btn_progressBar);

        btn1.setOnClickListener(clickListener);
        btn2.setOnClickListener(clickListener);
        btn3.setOnClickListener(clickListener);
        btn4.setOnClickListener(clickListener);
        btn5.setOnClickListener(clickListener);
        btn6.setOnClickListener(clickListener);

    }

    public void showReminderDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this );
        builder.setTitle("提示对话框");
        builder.setMessage("这里显示提示信息");
        builder.setPositiveButton("确定", null);
        builder.create().show();

    }

    public void showConfirmDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("确认对话框")
                .setMessage("这里显示确认对话框信息");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"你点击了确定按钮",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"你点击了取消按钮",Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    public void showSingleChoiceDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher).setTitle("单选对话框");

        final String[] items = new String[] {"北京", "纽约", "曼谷", "伦敦"};

        builder.setSingleChoiceItems(items, -1,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,
                                "你选择了:" + items[i], Toast.LENGTH_SHORT).show();
                    }
                });

        builder.setPositiveButton("确定", null);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

   public void showMultiChoiceDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher).setTitle("多选对话框");

        final String[] items = new String[] {"北京", "纽约", "曼谷", "伦敦"};

        builder.setMultiChoiceItems(items,
                new boolean[]{true, false, true, false},
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        Toast.makeText(MainActivity.this, "你选择了:" + items[i], Toast.LENGTH_SHORT).show();
                    }
                });

        builder.setPositiveButton("确定", null);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

  public void showProgressDialog() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("下载进度");
        progressDialog.setMessage("正在现在最新资源文件...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
    }

    public void showProgressBarDialog(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("更新音乐列表信息...");
        progressDialog.setMax(100);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Thread progressThread = new Thread() {
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    progressDialog.setProgress(i);

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.dismiss();
            }
        };

        progressThread.start();
    }
}
