package ru.mai.threadsinterrupt;

import ru.mai.MainThread;

public class IncremenatorInterrupt extends Thread {

    private volatile boolean mIsIncrement = true;

    public void changeAction()
    {
        mIsIncrement = !mIsIncrement;
    }

    @Override
    public void run() {
        do {
            if (!Thread.interrupted())
            {
                if (mIsIncrement) {
                    MainThread.mValue++;
                } else {
                    MainThread.mValue--;
                }

                System.out.print("[" + MainThread.mValue + "] ");
            } else {
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
        while (true);
    }
}

