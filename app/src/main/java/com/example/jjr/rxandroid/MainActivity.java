package com.example.jjr.rxandroid;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private static String TAG="MainActivity";

    @Bind(R.id.btn_rx_01)
    public Button mBtnRxTest01;
    @Bind(R.id.tv_rx_01)
    public TextView mTvRx01;
    @Bind(R.id.tv_rx_02)
    public TextView mTvRx02;

    //变量
    private List<Student> mListS=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    @OnClick({R.id.btn_rx_01})
    public void onClick(View view){
        if(view.getId()==R.id.btn_rx_01){
//            RxTest_01();
            RxOpterOperators();
        }
    }

    //RxAndroid中的观察者和被观察者
    public void RxTest_01(){
        Observable mObservable=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("RxTest_01");
                subscriber.onCompleted();
            }
        });
        Observer<String> mObserver=new Observer<String>() {
            @Override
            public void onCompleted() {
                mTvRx02.setText("信息完成Completed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
               mTvRx01.setText("信息"+s);
            }
        };
        Action1<String> mAction=new Action1<String>() {
            @Override
            public void call(String s) {
                mTvRx01.setText("信息"+s);
            }
        };
        Action1<Throwable> mActionThread=new Action1<Throwable>() {
            @Override
            public void call(Throwable thread) {

            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                mTvRx02.setText("信息完成Completed");
            }
        };
        mObservable.subscribe(mAction,mActionThread,onCompletedAction);
    }
    //RxAndroid操作符
    public void RxOpterOperators(){
//        Observable<String> mObservable=Observable.just("张三","李四","王五");
//        mObservable.map(new Func1<String,Integer>(){
//            @Override
//            public Integer call(String s) {
//                return s.hashCode();
//            }
//        }).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.i(TAG,"输出"+integer);
//                mTvRx02.setText("内容"+integer);
//            }
//        });

//        Subscriber<String> mSubscriber=new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.i(TAG,"输出"+s);
//                mTvRx02.setText("内容"+s);
//            }
//        };

        Action1<String> mAction1=new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG,"输出Action"+s);
                mTvRx02.setText("输出Action"+s);
            }
        };

        Action1<Course> mAction2=new Action1<Course>() {
            @Override
            public void call(Course course) {
                Log.i(TAG,"输出Action课程"+course.getcName());
                mTvRx02.setText("输出Action课程"+course.getcName());
            }
        };

        Observable<Student>  mObservable=Observable.from(mListS);
//        mObservable.map(new Func1<Student,String>() {
//            @Override
//            public String call(Student student) {
//                return student.getName();
//            }
//        }).subscribe(mAction1);
       mObservable.flatMap(new Func1<Student, Observable<Course>>() {
           @Override
           public Observable<Course> call(Student student) {
               return Observable.just(student.getmCourse());
           }
       }).filter(new Func1<Course, Boolean>() {
           @Override
           public Boolean call(Course course) {
               return !TextUtils.equals(course.getcName(),"体育");
           }
       }).subscribe(mAction2);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        AndroidObservable.fromBroadcast(context, filter)
//                .subscribe(intent -> handleConnectivityChange(intent));
    }

    public void initData(){
        mListS.add(new Student("张三",new Course("物理")));
        mListS.add(new Student("李四",new Course("化学")));
        mListS.add(new Student("王五",new Course("体育")));
    }
}
