package exception;

/**
 * 自定义异常
 */
public class MyException extends RuntimeException{
    //异常信息
    private String message;
    //构造器
    public MyException(String message){
        super(message);
        this.message=message;
    }
    @Override
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
