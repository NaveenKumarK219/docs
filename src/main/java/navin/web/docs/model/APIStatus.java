package navin.web.docs.model;

import java.util.Date;

public class APIStatus {

    private String apiName;
    private String status;
    private Object data;
    private String errorMessage;
    private Date startTime;
    private Date endTime;
    private String executionTime;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public static APIStatus initApiStatus(String apiName){
        APIStatus apiStatus = new APIStatus();
        apiStatus.setApiName(apiName);
        apiStatus.setStartTime(new Date());
        apiStatus.setStatus("Started");
        return apiStatus;
     }

     public static APIStatus closeApiStatus(APIStatus  apiStatus){
         apiStatus.setEndTime(new Date());
         apiStatus.setExecutionTime((apiStatus.getEndTime().getTime() - apiStatus.getStartTime().getTime()) + "ms");
         return apiStatus;
     }
     
     @Override
    public String toString() {
    	return "{"
    			+ "apiName: "+ apiName +","
    		    + "data: "+ data + ","
    		    + "status: "+ status +","
    		    + "errorMessage: "+ errorMessage +","
    		    + "startTime: "+ startTime +","
    		    + "endTime: "+ endTime +","
    		    + "executionTime: "+ executionTime +","
    			+ "}";
    }
}
