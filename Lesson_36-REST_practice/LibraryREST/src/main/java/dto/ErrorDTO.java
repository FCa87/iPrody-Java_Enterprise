
package dto;

import java.util.Date;


public class ErrorDTO {
    
    private String message;
    private Date date;

    public ErrorDTO(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public ErrorDTO() {
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" + "message=" + message + ", date=" + date + '}';
    }
     
}

