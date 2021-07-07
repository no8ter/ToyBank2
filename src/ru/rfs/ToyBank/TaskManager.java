package ru.rfs.ToyBank;

public class TaskManager implements Runnable{
    private final Bank bankConnection;
    private Task task;
    private final FrontSystem fs;

    public TaskManager(Bank bankConnection, FrontSystem fs) {
        this.bankConnection = bankConnection;
        this.fs = fs;
    }

    // Only catching task from Front
    @Override
    public void run(){
        while (!Thread.currentThread().isInterrupted()) {
            if (fs.getQueueSize() >= 0) {
                task = fs.getTask();
                if (task == null) continue;
                serveTask();
            }
        }
    }

    // Unpacking received task and choose action
    private void serveTask() {
        System.out.println(Thread.currentThread().getName()+": "+task);
        switch (task.task) {
            case CREDIT:
                bankConnection.restoreMoney(task.moneyCount);
                break;
            case REPAYMENT:
                bankConnection.depositMoney(task.moneyCount);
                break;
        }
    }
}