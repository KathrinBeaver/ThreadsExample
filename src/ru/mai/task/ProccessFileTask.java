package ru.mai.task;

import java.util.concurrent.Callable;

public class ProccessFileTask implements Callable<Result> {

	public ProccessFileTask() {
		// Подготовка данных
	}

	@Override
	public Result call() {

		// Необходимая обработка
		Result result = validate();

		return result;
	}


	private Result validate() {
		// Обработка
		return new Result();
	}

}
