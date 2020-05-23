/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.mai.threads;

/**
 * @author Beaver
 */
public class TestThread implements Runnable {

    @Override
    public void run() {
        System.out.println("TestThread run 1");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException 1 thread" + ex);
        }
        System.out.println("TestThread finished 1");
    }
}
