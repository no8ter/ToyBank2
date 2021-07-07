package ru.rfs.ToyBank;

public class Bank {
    private volatile int balance;

    public Bank(){ this.balance = 0; }

    public Bank(int i){ this.balance = i;}

    public synchronized int getBalance() {
        return balance;
    }

    // Storing money into the bank
    public synchronized void depositMoney(int count) {
        if (count >= 0) {
            balance = balance + count;
            System.out.println("Бек система: Заявка "+ Operations.REPAYMENT+" "+count+" ВЫПОЛНЕНА. Баланс банка: "+getBalance());
        } else {
            System.out.println("Бек система: Заявка "+ Operations.REPAYMENT+" "+count+" НЕ ВЫПОЛНЕНА. Введена отрицательная сумма. Баланс банка: "+getBalance());
        }
    }

    // Restoring money into the bank
    public synchronized void restoreMoney(int count) {
        if (count <= balance) {
            balance = balance - count;
            System.out.println("Бек система: Заявка "+ Operations.CREDIT+" "+count+" ВЫПОЛНЕНА. Баланс банка: "+getBalance());
        } else {
            System.out.println("Бек система: Заявка "+ Operations.CREDIT+" "+count+" НЕ ВЫПОЛНЕНА. Недостаточно средств в банке. Баланс банка: "+getBalance());
        }
    }
}