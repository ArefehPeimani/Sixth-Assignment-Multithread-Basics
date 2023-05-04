# Sixth Assignment Report

## Introduction
The purpose of the assignment is getting familiar with threads and how to utilize them.

## Design and Implementation
- In the `CPU_Simulator` exercise, I completed the constructor and run method.
In the `startSimulation` method, first I sort the tasks based on their processing time
with bubble sort algorithm and then make threads and start them one by one.
- In the `FindMultiples` exercise, I declared a runnable called `Multiples` which
takes a number and an interval and finds multiples of the number in the interval.
In `getSum` method, I used a Fixed Thread Pool to find the multiples simultaneously.
In a Fixed Thread Pool, there are a number of threads and a queue of tasks; 
Each thread fetch a task from the queue and execute it and do it again
if there are remaining tasks till all of them are finished.
However, in a Cashed Thread Pool the number of threads is not fixed.
Here the tasks queue holds only one task. Everytime a new tasks is
added, it will try to find a free thread. If there are none, a new thread
is created to execute the task. Also, if a thread is not executing anything
for more than a minute, it will be killed.
- In the `UserInterrupts` exercise, I added exception messages in the first two classes
and used the system time to calculate the run time of the threads.

## Testing and Evaluation
The exercises pass all the tests.
I had a problem with getting the sum of the unique multiplies in the
second exercise and couldn't pass two tests, but I could fix it.

## Conclusion
I learned about threads and thread pools and how to use them.
