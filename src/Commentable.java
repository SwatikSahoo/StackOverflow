import java.util.List;

public interface Commentable {
    void addComment(Comment com);
    List<Comment> getComment();
}
