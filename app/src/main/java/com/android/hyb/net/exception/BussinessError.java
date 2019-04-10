package com.android.hyb.net.exception;

public class BussinessError extends RuntimeException {


    public int code;

    public String status;
    public String msg;


    /**
     * 错误状态 0:正常 1:通用错误状态
     */
    public int state;

   /* public BussinessError(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.state = 0;
    }
*/
    public BussinessError(String status, String msg) {
        super(msg);
        this.status = status;
        this.msg = msg;
    }

  /*  public BussinessError(int code, String msg, int state) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.state = state;
    }*/

}
