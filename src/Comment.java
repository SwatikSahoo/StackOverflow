import java.util.Date;

public class Comment {
    private final int id;
    private final String content;
    private final User author;
    private final Date creationDate;

    public Comment(User user, String content){
        id= (int) (System.currentTimeMillis()%Integer.MAX_VALUE);
        this.content = content;
        author=user;
        creationDate=new Date();
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
