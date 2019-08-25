package com.zhuyifan.lock;
/** 
* @Author zhuyifan
* @Time 2019年6月26日 上午11:22:26 
* @Version 1.0
* <p>Description:ThreadA.java:</p>
*/
public class ThreadA extends Thread {

    private HasSelfPrivateNum numRef;

    public ThreadA(HasSelfPrivateNum numRef) {
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("a");
    }

}

