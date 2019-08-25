package com.zhuyifan.lock;
/** 
* @Author zhuyifan
* @Time 2019年6月26日 上午11:23:37 
* @Version 1.0
* <p>Description:Run.java:</p>
*/
public class Run {

    public static void main(String[] args) {

        HasSelfPrivateNum numRef1 = new HasSelfPrivateNum();
        HasSelfPrivateNum numRef2 = new HasSelfPrivateNum();

        ThreadA athread = new ThreadA(numRef1);
        athread.start();

        ThreadB bthread = new ThreadB(numRef2);
        bthread.start();

    }

}

