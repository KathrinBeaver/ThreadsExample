/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mai.threads;

/**
 * @author Beaver
 */
public class SecondaryThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                sleep(1000);    // Приостанавливает поток на 1 секунду
            } catch (InterruptedException e) {
                // Обработка исключения
            }

            System.out.println("Второй!");
        }
    }
}
