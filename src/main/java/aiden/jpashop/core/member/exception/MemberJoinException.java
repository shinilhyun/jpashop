package aiden.jpashop.core.member.exception;

public class MemberJoinException extends RuntimeException {
    public MemberJoinException() {
        super();
    }

    public MemberJoinException(String message) {
        super(message);
    }

    public MemberJoinException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberJoinException(Throwable cause) {
        super(cause);
    }

    protected MemberJoinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
