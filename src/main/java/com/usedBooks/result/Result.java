package com.usedBooks.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;
    private Integer count;
    //成功时候的调用
    public static <T> Result<T> success(T data,Integer count){
        return new Result<T>(data,count);
    }

    //失败时候的调用
    public static <T> Result <T> error(CodeMsg codeMsg)
    {
        return new Result<T>(codeMsg);

    }

    private Result(T data,Integer count)
    {
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg  = CodeMsg.SUCCESS.getMsg();
        this.data = data;
        this.count = count;
    }

    private Result(int code,String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    private Result(CodeMsg codeMsg)
    {
        if(codeMsg != null)
        {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }

    public Result() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
