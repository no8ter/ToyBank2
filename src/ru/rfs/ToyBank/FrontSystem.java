package ru.rfs.ToyBank;

import java.util.ArrayDeque;
import java.util.Queue;

public class FrontSystem {

    public volatile Queue<Task> queue = new ArrayDeque<>();
    public synchronized int getQueueSize() {
        return queue.size();
    }

    // Adding task into the queue
    public synchronized void setTask(Task task) throws InterruptedException {
        while (queue.size() == 2) wait();
        queue.offer(task);
        notifyAll();
    }

    // Adding task into the queue
    public synchronized Task getTask() {
        try {
            return queue.poll();
        } finally {
            notifyAll();
        }
    }
}