package ru.rfs.ToyBank;

public class Task {
    String threadName;
    int moneyCount;
    Operations task;

    public Task(int moneyCount, Operations task) {
        this.threadName = "";
        this.moneyCount = moneyCount;
        this.task = task;
    }

    @Override
    public String toString() {
        return "{clientThreadName='"+threadName+
                "', amount="+moneyCount+
                ", requestType="+task+"}"
                ;
    }
}