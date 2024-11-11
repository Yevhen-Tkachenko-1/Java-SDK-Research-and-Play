# Java Multithreading Demo

_Learn and play with Concurrent and Parallel programming using Java._

Implemented based on LinkedIn learning courses:

- [**Concurrent Programming with Java**](https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-1 'https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-1')
- [**Parallel Programming with Java**](https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-2 'https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-2')

Content:

* [Multithreading bases](#multithreading-bases)
  * [Demo 1: Thread vs Process](#demo-1-thread-vs-process)
  * [Demo 2: Executing Scheduling](#demo-2-executing-scheduling)
  * [Demo 3: Thread Lifecycle](#demo-3-thread-lifecycle)
* [Concurrency in Java](#concurrency-in-java)
  * [Demo 4: Mutual Exclusion - resolving Data Race problem](#demo-4-mutual-exclusion---resolving-data-race-problem)
  * [Demo 5: Nested Lock](#demo-5-nested-lock)
  * [Demo 6: Non-Blocking Lock](#demo-6-non-blocking-lock)
  * [Demo 7: Read-Write Lock](#demo-7-read-write-lock)
  * [Demo 8: Multiple Locks: Deadlock problem](#demo-8-multiple-locks-deadlock-problem)
  * [Demo 9: Multiple Locks: Livelock problem](#demo-9-multiple-locks-livelock-problem)
  * [Demo 10: Exception Handling: Abandoned Lock problem](#demo-10-exception-handling-abandoned-lock-problem)

### Multithreading bases

#### Demo 1: Thread vs Process

1. On the pc under Windows OS open Task Manager and switch to Performance tab.<br>
   On CPU pane you can see overall CPU utilization which is about 10% in regular state.
2. Start [app](src/main/java/yevhent/demo/multithreading/bases/ThreadVsProcess.java) and see how CPU utilization is
   increased: can be up to 100%.
3. Get Process ID value from console output, e.g. 7428.<br>
   Now in Task Manager use Open Resource Monitor link to see more details.
   In Resource Monitor window on CPU tab in Processes pane you can find our Java app by that Process ID.
   There we can see number of Threads and CPU utilization in percents.
   Number of Threads is usually greater than created by our program.
   Others background Threads are created for util functions like garbage collection and runtime compilations.
4. Stop our app and see overall CPU utilization in Task Manager.<br>
   It should be decreased to regular state about 10%.

#### Demo 2: Executing Scheduling

1. Start [app](src/main/java/yevhent/demo/multithreading/bases/ExecutingScheduling.java)
2. Check console output.
3. There we have 11 rounds of execution.<br>
   Each time we start 2 Threads with the same order: Baron first, Olivia next.
   By this, it is expected that Baron wins each time by chopping more vegetables as it goes first.
   However, actual results is unpredictable and depends on Threads scheduling by system.
   For example, I have 7 times when Baron chopped more vegetables, and Olivia wins 4 times.

#### Demo 3: Thread Lifecycle

1. Start [app](src/main/java/yevhent/demo/multithreading/bases/ThreadLifecycle.java)
2. Check console output.
3. There we have all possible states:
    - `NEW` - A thread that has not yet started is in this state.
    - `RUNNABLE` - A thread executing in the Java virtual machine is in this state.
    - `BLOCKED` - A thread that is blocked waiting for a monitor lock is in this state.
    - `WAITING` - A thread that is waiting indefinitely for another thread to perform a particular action is in this
      state.
    - `TIMED_WAITING` - A thread that is waiting for another thread to perform an action for up to a specified waiting
      time is in this state.
    - `TERMINATED` - A thread that has exited is in this state.

### Concurrency in Java

#### Demo 4: Mutual Exclusion - resolving Data Race problem

1. Start [app](src/main/java/yevhent/demo/multithreading/concurrency/datarace/MutualExclusionDemo.java)
2. Check console output.
3. Review code in this [package](src/main/java/yevhent/demo/multithreading/concurrency/datarace).

- **Problem**: There we have 2 same Threads that increase counter 10_000_000 times.
  However, since these Threads use the same shared data class, calculation goes wrong way.
  Finally, we have some unexpected value like 11_149_076 instead of 20_000_000.
- **Solution**: We have Thread safe shared data classes implemented based on:
    - ReentrantLook
    - Synchronized method
    - Synchronized block
    - Atomic variable

#### Demo 5: Nested Lock

1. Start [app](src/main/java/yevhent/demo/multithreading/concurrency/locks/nested/NestedReentrantLockDemo.java).
2. Check console output.
3. Review code in this [package](src/main/java/yevhent/demo/multithreading/concurrency/locks/nested).

- **Nested Look Problem**: Using locks we can have situation when Thread blocks himself by acquiring same lock
  twice (without releasing after first lock).
  In java we have Reentrant Lock by default which allows specific Thread to acquire same lock several times.
  To release lock completely, Thread should unlock the same number of times as it was acquired.
  ReentrantLock class allows you to see number of holds made by Thread.

#### Demo 6: Non-Blocking Lock

1. Start [app](src/main/java/yevhent/demo/multithreading/concurrency/locks/nonblocking/NonBlockingLockDemo.java).
2. Check console output.
3. Review code in this [package](src/main/java/yevhent/demo/multithreading/concurrency/locks/nonblocking).

- **Non-Blocking Look**: using locks solves problem of data race, but requires you to wait unit lock is released.
  In case you don't need immediate result and have some other job to do you can use **lock try**.
  If lock is free you will take it, otherwise you will skip locked part and do alternative or just next job.

#### Demo 7: Read-Write Lock

1. Start [app](src/main/java/yevhent/demo/multithreading/concurrency/locks/readwrite/ReadWriteLockDemo.java).
2. Check console output.
3. Review code in this [package](src/main/java/yevhent/demo/multithreading/concurrency/locks/readwrite).

- Let's imagine situation when only one Thread is changing variable and many Threads reading it.
  With usual approach we lock both reading and writing access.
  That works fine in terms of synchronization, but makes program slow.
  Taking into account that most of the time Data is needed for reading only, we can soften the lock.
  When Data is not blocked by changing, it accessible for reading without blocking,
  so can be accessible by many Threads at the same time.

#### Demo 8: Multiple Locks: Deadlock problem

1. Start [app](src/main/java/yevhent/demo/multithreading/concurrency/locks/deadlock/DeadLockDemo.java).
2. Check console output.
3. Review code in this [package](src/main/java/yevhent/demo/multithreading/concurrency/locks/deadlock).

- **Problem**: when several Threads use several shared Locks, it might be situation when Threads blocked by each other and stuck with
  no progress.

For example, we have Thread1 and Thread2 which both use Lock1 and Lock2.
Dead Lock happens with next steps: 
1. Thread1 acquires Lock1. 
2. Thread2 acquires Lock2.
3. Thread1 tries to acquire Lock2 in blocking manner.
4. Thread1 becomes blocked as Lock2 already taken by Thread2.
5. Thread2 tries to acquire Lock1 in blocking manner.
6. Thread2 becomes blocked as Lock1 already taken by Thread1.

As a result, Thread1 and Thread2 got stuck waiting each other in blocked state. 

- **Solution**: we can use Locks Prioritizing.
  With this Thread1 and Thread2 should first try to acquire Lock1 and only then Lock2.

#### Demo 9: Multiple Locks: Livelock problem

1. Start [app](src/main/java/yevhent/demo/multithreading/concurrency/locks/livelock/LiveLockDemo.java).
2. Check console output.
3. Review code in this [package](src/main/java/yevhent/demo/multithreading/concurrency/locks/livelock).

- **Problem**: When several Threads use several shared Locks, it might be situation when Threads do some job, but with no actual progress.

**For example**, we have Thread1 and Thread2 which both use Lock1 and Lock2.
Live Lock happens with next steps:

1. Thread1 acquires Lock1.
2. Thread2 acquires Lock2.<br>
Then Thread1 does next:
3. Thread1 tries to acquire Lock2 in non-blocking manner.
4. Thread1 has no lock acquired as Lock2 already taken by Thread2.
5. Thread1 is not blocked so can try to do something else.
6. Thread1 releases Lock1.
7. Thread1 does some other job.
8. Thread1 returns to do the same: repeats steps 1, 3-6.<br>
At the same time Thread2 does similar things:
9. Thread2 tries to acquire Lock1 in non-blocking manner.
10. Thread2 has no lock acquired as Lock1 already taken by Thread1.
11. Thread2 is not blocked so can try to do something else.
12. Thread2 releases Lock2.
13. Thread2 does some other job.
14. Thread2 returns to do the same: repeats steps 2, 9-14.

**As a result**, it seems like Thread1 and Thread2 do something, but useful job is not accomplished.
They doings are nothing more than just checking for Locks availability.

- **Solution**: we can use access randomization.
  With this Thread1 and Thread2 will acquire Locks in different times so number of unsuccessful tries will be decreased.

#### Demo 10: Exception Handling: Abandoned Lock problem

1. Start [app](src/main/java/yevhent/demo/multithreading/concurrency/locks/abandoned/AbandonedLockDemo.java).
2. Check console output.
3. Review code in this [package](src/main/java/yevhent/demo/multithreading/concurrency/locks/abandoned).

- **Problem**: it's possible that Thread has some Exception thrown in runtime and going to finish its execution.
  In case such Thread managed to acquire Lock and ends without Lock releasing, 
  other threads will not be able to acquire this Lock at any time in the future.

- **Solution**: always surround critical section in try-catch-finally block. 
  In try section we acquire Lock and do work needed, and in finally block we release Lock,
  so that Lock will be released in any case: success or fail. 