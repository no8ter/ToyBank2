package ru.rfs.ToyBank;

public class Client implements Runnable {

    private final Task task;
    private final FrontSystem fs;

    public Client(Task task, FrontSystem fs) {
        this.task = task;
        this.fs = fs;
    }

    @Override
    public void run() {
        task.threadName = Thread.currentThread().getName();
        System.out.println(task.threadName+": Заявка "+ task +" отправлена в банк");
        try {
            fs.setTask(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.currentThread().interrupt();
    }
}