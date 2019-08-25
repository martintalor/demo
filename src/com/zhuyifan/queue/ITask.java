package com.zhuyifan.queue;

//办事的人。
public interface ITask {
 // 办事，我们把办事的方法给办事的人，也就是你要办什么事，由你自己决定。
 void run();
}