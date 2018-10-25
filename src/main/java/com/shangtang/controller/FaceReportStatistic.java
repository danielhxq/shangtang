package com.shangtang.controller;

public class FaceReportStatistic {

	private long totalClient;

	private long newClient;

	private long oldClient;

	public long getTotalClient() {
		return totalClient;
	}

	public void setTotalClient(long totalClient) {
		this.totalClient = totalClient;
	}

	public long getNewClient() {
		return newClient;
	}

	public void setNewClient(long newClient) {
		this.newClient = newClient;
	}

	public long getOldClient() {
		return oldClient;
	}

	public void setOldClient(long oldClient) {
		this.oldClient = oldClient;
	}

	@Override
	public String toString() {
		return "FaceReportStatistic [totalClient=" + totalClient + ", newClient=" + newClient + ", oldClient="
				+ oldClient + "]";
	}

}
