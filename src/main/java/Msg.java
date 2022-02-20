/**
 * Kept for possible further use.
 */
public class Msg {

    private boolean success;
    private String msg;

    public Msg() {
        this(true, "");
    }

    public Msg(boolean success) {
        this(success, "");
    }

    public Msg(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
