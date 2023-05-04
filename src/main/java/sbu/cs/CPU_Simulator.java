package sbu.cs;

import java.util.ArrayList;

/*
    For this exercise, you must simulate a CPU with a single core.
    You will receive an arraylist of tasks as input. Each task has a processing
    time which is the time it needs to run in order to fully execute.

    The CPU must choose the task with the shortest processing time and create
    a new thread for it. The main thread should wait for the task to fully
    execute and then join with it, before starting the next task.

    Once a task is fully executed, add its ID to the executed tasks arraylist.
    Use the tests provided in the test folder to ensure your code works correctly.
 */

public class CPU_Simulator
{
    public static class Task implements Runnable {
        long processingTime;
        String ID;
        public Task(String ID, long processingTime) {
            this.ID = ID;
            this.processingTime = processingTime;
        }

    /*
        Simulate running a task by utilizing the sleep method for the duration of
        the task's processingTime. The processing time is given in milliseconds.
    */
        @Override
        public void run() {
            try {
                Thread.sleep(this.processingTime);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        The startProcessing function should be called at the start of your program.
        Here the CPU selects the next shortest task to run (also known as the
        shortest task first scheduling algorithm) and creates a thread for it to run.
    */
    public ArrayList<String> startSimulation(ArrayList<Task> tasks) {
        ArrayList<String> executedTasks = new ArrayList<>();

        //sort the arraylist
        int n = tasks.size();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n-i; j++) {
                if (tasks.get(j-1).processingTime > tasks.get(j).processingTime) {
                    Task temp = tasks.get(j-1);
                    tasks.set(j-1, tasks.get(j));
                    tasks.set(j, temp);
                }
            }
        }

        //start the threads and add them to the executed list
        for (Task task : tasks) {
            Thread t = new Thread(task);
            t.start();
            executedTasks.add(task.ID);
        }

        return executedTasks;
    }

    public static void main(String[] args) {
    }
}
