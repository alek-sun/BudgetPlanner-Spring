package ftc.shift.scheduler.controller;

//import lombok.Getter;

public class BaseResponse<T> {

    //@Getter
    private String status;

    //@Getter
    private T data;

    BaseResponse(T result){

        if (result != null) {
            this.status = Resources.SUCCESS_STATUS;
            this.data = result;
        } else {
            this.status = Resources.UNSUCCESS_STATUS;
        }
    }

    BaseResponse(Boolean result){

        if (result != null && result) {

            this.status = Resources.SUCCESS_STATUS;

        } else {

            this.status = Resources.UNSUCCESS_STATUS;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}