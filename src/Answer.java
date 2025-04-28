import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer implements Votable, Commentable{
    private final int id;
    private final String content;
    private final User author;
    private final Question question;
    private boolean isAccepted;
    private final Date creationDate;
    private final List<Comment> comments;
    private final List<Vote> votes;

    public Answer(User user, Question question, String content) {
        id=(int)(System.currentTimeMillis()%Integer.MAX_VALUE);
        author=user;
        this.question=question;
        this.content=content;
        isAccepted=false;
        creationDate= new Date();
        comments= new ArrayList<>();
        votes= new ArrayList<>();

    }
    public void vote(User user, int val){
        if (val!=1 && val!=-1){
            throw new IllegalArgumentException("Vote should be 1 or -1");
        }
        votes.removeIf(vote -> vote.getUser().equals(user));
        votes.add(new Vote(user,val));
        author.updateReputation(val*10);
    }

    @Override
    public void addComment(Comment com) {
        comments.add(com);
    }

    @Override
    public List<Comment> getComment() {
        return new ArrayList<>(comments);
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(v->v.getVal()).sum();
    }
    public void markAccepted(){
        if (isAccepted){
            throw new IllegalArgumentException("Answer already Accepted");
        }
        isAccepted=true;
        author.updateReputation(15);

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

    public Question getQuestion() {
        return question;
    }

    public boolean isAccepted() {
        return isAccepted;
    }
}
