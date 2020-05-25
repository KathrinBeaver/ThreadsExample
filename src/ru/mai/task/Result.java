package ru.mai.task;

public class Result {

	private int allRecords;
	private int validRecords;

	public Result() {
	}

	public Result(int allRecords, int validRecords) {
		this.allRecords = allRecords;
		this.validRecords = validRecords;
	}

	public void addResult(Result result) {
		this.allRecords += result.getAllRecords();
		this.validRecords += result.getValidRecords();
	}

	public int getAllRecords() {
		return allRecords;
	}

	public int getValidRecords() {
		return validRecords;
	}

	@Override
	public String toString() {
		return "Result{" +
				"allRecords=" + allRecords +
				", validRecords=" + validRecords +
				'}';
	}
}
