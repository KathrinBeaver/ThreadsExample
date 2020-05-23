package ru.mai;

import ru.mai.task.Result;
import ru.mai.task.ValidationExecutor;
import ru.mai.threads.SecondaryThread;
import ru.mai.threads.TestThread;
import ru.mai.threadsinterrupt.Incremenator;
import ru.mai.threadsinterrupt.IncremenatorInterrupt;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MainThread {

    // Переменная, которой оперирует Incremenator из другого потока
    public volatile static int mValue = 0;

    public static void main(String[] args) {

//        simpleThreadsExample();
//        twoThreadsExample();

//        File currentDir = new File("");
//        ValidationExecutor validationExecutor = new ValidationExecutor(Arrays.asList(currentDir.listFiles()), 3);
//        Result result = validationExecutor.start();

        System.out.println("Главный поток запущен");
        System.out.println("Количество параметров = " + args.length);

        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        if (args.length > 0) {
            if (args[0].equals("0")) {
//               Test test = new Test();
                Thread firstThread = new Thread(new TestThread());
                firstThread.start();
            } else if (args[0].equals("1")) {

                Thread secondThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Привет из побочного потока 2!");
                        double test = 1;
                        for (int i = 1; i < 10; i++) {
                            test *= i;
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException ex) {
                                System.out.println("InterruptedException 2 thread" + ex);
                            }
                            System.out.println("test = " + test);
                        }
                        System.out.println("Побочный поток завершён...");
                    }
                });
//                secondThread.setPriority(Thread.MAX_PRIORITY);
                secondThread.start();
            } else {
                System.out.println("Недопустимое значение параметра");
            }
        } else {
            System.out.println("Нет аргументов");
        }

        Thread thread3 = new Thread(new TestThread());
//        thread3.setPriority(Thread.MIN_PRIORITY);
        thread3.start();

        int tst = 0;
        for (int i = 0; i < 10; i++) {
            tst++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException" + ex);
            }
            System.out.println("tst = " + tst);
        }

        System.out.println("Главный поток завершён...");

    }

    private static void simpleThreadsExample() {

        SecondaryThread secThread = new SecondaryThread();    // Создание потока
        System.out.println("Начало работы...");

        secThread.start();            // Запуск потока

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Обработка исключения
            }

            System.out.println("Первый");
        }

        if (secThread.isAlive())    // Если второй поток активен
        {
            try {
                secThread.join();    // Подождать пока второй поток закончит работу
            } catch (InterruptedException e) {
                System.out.println("Thread InterruptedException");
            }

            System.out.println("ВТОРОЙ!!!");
        } else    // если второй поток уже завершил работу
        {
            System.out.println("ПЕРВЫЙ!");
        }
        System.out.println("Работа потоков завершена!");
    }

    private static void twoThreadsExample() {

        System.out.println("Основной поток запущен");

        System.out.println("Первый поток....");

        //Создание потока
        Incremenator mInc = new Incremenator();

        System.out.print("Значение = ");
        //mInc.setDaemon(true);

        //Запуск потока
        mInc.start();

        // Троекратное изменение действия Incremenator
        // с интервалом в i * 2 секунд
        for (int i = 1; i <= 3; i++) {
            try {
                Thread.sleep(i * 2 * 1000);        // Ожидание i * 2 сек.
            } catch (InterruptedException e) {
                System.out.println("Thread InterruptedException");
            }
            mInc.changeAction();    // Переключение действия
        }

        mInc.finish();    // Завершение побочного потока

        System.out.println("Второй поток....");

        //Создание потока
        IncremenatorInterrupt mIncInter = new IncremenatorInterrupt();
        System.out.print("Значение = ");

        //Запуск потока
        mIncInter.start();

        // Троекратное изменение действия инкременатора
        // с интервалом в i * 2 секунд
        for (int i = 1; i <= 3; i++) {
            try {
                Thread.sleep(i * 2 * 1000);        //Ожидание в течении i*2 сек.
            } catch (InterruptedException e) {
                System.out.println("Thread InterruptedException");
            }
            mIncInter.changeAction();    // Переключение действия
        }

        mIncInter.interrupt();    // Прерывание побочного потока
        System.out.println("Основной поток завершен");
    }
}
