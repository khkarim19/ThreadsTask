package com.karim;

import java.util.Scanner;

class Thread1 implements Runnable {

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Поток работает");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Поток отработал");
    }
}

class Thread2 implements Runnable {

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите целое число: ");
        int num = in.nextInt();
        System.out.printf("Введенное число: %d \n", num);
        in.close();
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("Основной поток начал свою работу...");
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);
        t1.setDaemon(true);
        t1.start();
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
