# hilos-java
This project shows how different types of thread perform with different type of logics.
It was created for education purposes and covers just the pure java part of the problem.

## Structure
This project contains a main package (com.manquius.hilos) where the executable classes can be found.
* SimpleThreads: All the threads are created as new Thread(), showing the heavy overhead for each thread creation.
* ThreadPool: Shows that it reduces overhead by reusing the threads, but limiting to 2, it can easily show how its blocking mechanism can be harmful to performance.
* Lambdas: Starts the threads using lambdas.
* Parallel: Equals to Lambdas, but uses parallelStream() to start the threads.
* ParallelWeb: Use the parallelStream() to start WebProcessing and ReactiveProcessing, comparing blocking and non-blocking I/O


On the (com.manquius.hilos.runnables) you will find the classes extending Thread class.
* HeavyProcessing: Makes a loop until time has passed.
* SleepProcessing: Calls Thread.sleep(), which is not actively using CPU, but blocks the thread.
* WebProcessing: Uses Spring WebClient to make a GET call http://www.google.com in a blocking way.
* WebProcessing: Uses Spring WebClient to make a GET call http://www.google.com in a non-blocking way.

## Usage
The executable classes has two modifiable attributes
* numThreads: changes the number of threads that will be executed. It is recommended to start with 10, and see how each thread is started and finished. Then repeat the experiment with 1000 threads and see how it is affected.
* printInternals: Enables and disables the printing on each thread start-finish. Recommended true for the first 10 threads experiment and false for the second one.

Start comparing Simple Threads and ThreadPool, Lambdas with Parallel and Web with ParallelWeb, and on each case, see the difference of both types of threads.