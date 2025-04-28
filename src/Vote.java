public class Vote{
    private final User user;
    private final int val;

    public Vote(User user, int val){
        this.val=val;
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    public int getVal() {
        return val;
    }
}
