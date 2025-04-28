import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private final String name;
    private final String email;
    private int reputation;
    private final List<Question> questions;
    private final List<Answer> answers;
    private final List<Comment> comments;

    private static final int QueReputation = 5;
    private static final int AnsReputation = 10;
    private static final int ComReputation = 2;

    public User(int id, String name, String email){
        this.id=id;
        this.name=name;
        this.email=email;
        this.reputation=0;
        this.questions= new ArrayList<>();
        this.answers= new ArrayList<>();
        this.comments= new ArrayList<>();
    }
    public Question askQuestion(String title, String content, List<String> tags){
        Question question= new Question(this, title, content, tags);
        questions.add(question);
        updateReputation(QueReputation);
        return question;
    }


    public Answer answerQuestion(Question question, String content){
        Answer answer= new Answer(this, question, content);
        answers.add(answer);
        question.addAnswer(answer);
        updateReputation(AnsReputation);
        return answer;
    }
    public synchronized void updateReputation(int val) {
        reputation+=val;
        if (reputation<0){
            reputation=0;
        }
    }
    public Comment addComment(Commentable commentable, String content){
        Comment comment= new Comment(this,content);
        comments.add(comment);
        commentable.addComment(comment);
        updateReputation(ComReputation);
        return comment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getReputation() {
        return reputation;
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    public List<Answer> getAnswers() {
        return new ArrayList<>(answers);
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }
}
