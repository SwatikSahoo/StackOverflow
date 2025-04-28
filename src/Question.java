import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question implements Votable, Commentable {
    private final int id;
    private final String content;
    private final Date creationDate;
    private final User author;
    private final String title;
    private final List<Answer> answers;
    private final List<Comment> comments;
    private final List<Tag> tags;
    private final List<Vote> votes;

    public Question(User user, String title, String content, List<String> tags) {
        this.content = content;
        id = (int)(System.currentTimeMillis()%Integer.MAX_VALUE);
        creationDate = new Date();
        author = user;
        this.title = title;
        answers = new ArrayList<>();
        comments = new ArrayList<>();
        votes = new ArrayList<>();
        this.tags = new ArrayList<>();
        for (String t:tags){
            this.tags.add(new Tag(t));
        }
    }
    public void addAnswer(Answer answer) {
        if (!answers.contains(answer)){
            answers.add(answer);
        }
    }
    public void addComment(Comment com){
        comments.add(com);
    }

    @Override
    public List<Comment> getComment() {
        return new ArrayList<>(comments);
    }

    @Override
    public void vote(User user, int val) {
        if (val!=1 && val!=-1){
            throw new IllegalArgumentException("Vote value should be 1 or -1");
        }
        votes.removeIf(v-> v.getUser().equals(user)); // Checks duplicate
        votes.add(new Vote(user, val));
        author.updateReputation(val*5);
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(Vote::getVal).sum();
    }

    public int getId() {
        return id;
    }

    public List<Tag> getTags() {
        return new ArrayList<>(tags);
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public List<Answer> getAnswers() {
        return new ArrayList<>(answers);
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }
}
