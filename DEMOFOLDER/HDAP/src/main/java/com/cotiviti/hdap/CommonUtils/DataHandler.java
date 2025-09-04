package com.cotiviti.hdap.CommonUtils;

public class DataHandler {

	private String testCaseName;
	private String startTime;
	private String endTime;
	private String status;
	private String failReason;
	private String comments;
	private String requestGroup;
	private String chaseRequest;
	private String CotivitiClaimNumber;
	private String Provider;
	private String ChaseStatus;
	// Getters and Setters

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRequestGroup() {
		return requestGroup;
	}

	public void setRequestGroup(String requestGroup) {
		this.requestGroup = requestGroup;
	}

	public String getChaseRequest() {
		return chaseRequest;
	}

	public void setChaseRequest(String chaseRequest) {
		this.chaseRequest = chaseRequest;
	}

	public String getCotivitiClaimNumber() {
		return CotivitiClaimNumber;
	}

	public void setCotivitiClaimNumber(String CCN) {
		this.CotivitiClaimNumber = CCN;
	}

	public String getProvider() {
		return Provider;
	}

	public void setProvider(String Provider) {
		this.Provider = Provider;
	}

	public String getChaseStatus() {
		return ChaseStatus;
	}

	public void setChaseStatus(String ChaseStatus) {
		this.ChaseStatus = ChaseStatus;
	}
//	@Override
//	public String toString() {
//		return "DataHandler{" + "testCaseName='" + testCaseName + '\'' + ", startTime='" + startTime + '\''
//				+ ", endTime='" + endTime + '\'' + ", status='" + status + '\'' + ", failReason='" + failReason + '\''
//				+ ", comments='" + comments + '\'' + '}';
//	}
}
