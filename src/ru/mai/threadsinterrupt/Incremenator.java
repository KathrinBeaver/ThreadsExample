package ru.mai.threadsinterrupt;

import ru.mai.MainThread;

public class Incremenator extends Thread {

    private volatile boolean mIsIncrement = true;
    private volatile boolean mFinish = false;

    /**
     *  Меняет действие на противоположное
     */
    public void changeAction()
    {
        mIsIncrement = !mIsIncrement;
    }

    /**
     * Инициирует завершение потока
     */
    public void finish()
    {
        System.out.println("FINISH");
        mFinish = true;
    }

    @Override
    public void run() {
        do {
            //Проверка на необходимость завершения
            if (!mFinish)
            {
                if (mIsIncrement) {
                    MainThread.mValue++;
                } else {
                    MainThread.mValue--;
                }

                System.out.print("<" + MainThread.mValue + "> ");
            } else {
                return;
            }

            try {
                //Приостановка потока на 1 сек.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.print("Error in Incremenator thread");
            }
        }
        while (true);
    }
}

