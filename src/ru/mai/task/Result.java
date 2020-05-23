package ru.mai.task;

public class Result {

	private int allRecords;
	private int validRecords;

	public Result() {
	}

	public Result(int allRecords, int validRecords, int invalidRecords, int duplicatesRecords) {
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

	public void setAllRecords(int allRecords) {
		this.allRecords = allRecords;
	}

	public int getValidRecords() {
		return validRecords;
	}

	public void setValidRecords(int validRecords) {
		this.validRecords = validRecords;
	}

	@Override
	public String toString() {
		return "Result{" +
				"allRecords=" + allRecords +
				", validRecords=" + validRecords +
				'}';
	}
}
