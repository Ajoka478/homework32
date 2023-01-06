public class NotEnoughMoneyException extends RuntimeException{
    private String message;
    private long remaining;

    public NotEnoughMoneyException(String message, long remaining) {
        this.message = message;
        this.remaining = remaining;
    }
    @Override public String getMessage(){ return message + remaining; }


}
