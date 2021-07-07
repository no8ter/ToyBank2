package ru.rfs.ToyBank;

public class Main {


    public static void main(String[] args) throws InterruptedException {


        // Initialization block
        System.out.println("--------------------------------------Toy bank--------------------------------------");
        Bank bank = new Bank(5000);
        FrontSystem fs = new FrontSystem();

        // Initialize task managers
        Thread tm1 = new Thread(new TaskManager(bank, fs));
        Thread tm2 = new Thread(new TaskManager(bank, fs));
        tm1.setDaemon(true);
        tm2.setDaemon(true);
        tm1.setName("Обработчик заявок 1");
        tm2.setName("Обработчик заявок 2");

        // Initialize clients
        Thread client1 = new Thread(new Client(new Task(randomInt(4000), Operations.REPAYMENT), fs));
        Thread client2 = new Thread(new Client(new Task(randomInt(5000), Operations.CREDIT), fs));
        Thread client3 = new Thread(new Client(new Task(randomInt(6000), Operations.REPAYMENT), fs));
        Thread client4 = new Thread(new Client(new Task(randomInt(7000), Operations.CREDIT), fs));
        client1.setName("Клиент 1");
        client2.setName("Клиент 2");
        client3.setName("Клиент 3");
        client4.setName("Клиент 4");
        // End Initialization block



        // Startup block
        client1.start();
        client2.start();
        client3.start();
        client4.start();
        tm1.start();
        tm2.start();

        client1.join();
        client2.join();
        client3.join();
        client4.join();
        // End startup block



        // Alive check
        while (true) {
            if (fs.getQueueSize() <=0) {
                System.out.println("<<All's gone>>");
                System.exit(0);
            } else {
                System.out.println("<<Still alive>>");
                Thread.sleep(100L);
            }
        }
    }

    public static int randomInt(int max) {
        return (int) (Math.random() * max);
    }
}