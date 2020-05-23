package ru.mai.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ValidationExecutor {

	private ExecutorService executor;
	private List<Callable<Result>> tasks;

	public ValidationExecutor(List<File> files, int threadsCount) {
		executor = Executors.newFixedThreadPool(threadsCount);
		tasks = createTasks(files);
	}

	private List<Callable<Result>> createTasks(List<File> files) {
		List<Callable<Result>> tasks = new ArrayList<>();
		files.stream().forEach(folder -> tasks.add(new ProccessFileTask()));
		return tasks;
 	}

	/**
	 * Запустить обработку файлов
	 */
	public Result start() {

		Result finalResult = new Result();

		try {

			executor.invokeAll(tasks)
					.stream()
					.map(future -> {
						try {
							return future.get();
						}
						catch (Exception e) {
							throw new IllegalStateException(e);
						}
					})
					.forEach(result -> finalResult.addResult(result));
		} catch (InterruptedException e) {
			System.out.println("InterruptedException " + e);
		}
		finally {
			executor.shutdown();
			System.out.println("Finished");
		}

		return finalResult;
	}

}
