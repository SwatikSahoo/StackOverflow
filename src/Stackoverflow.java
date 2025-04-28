import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Stackoverflow {
    private final Map<Integer, User> users;
    private final Map<Integer, Question> questions;
    private final Map<Integer, Answer> answers;
    private final Map<String, Tag> tags;

    public Stackoverflow(){
        users = new ConcurrentHashMap<>();
        questions= new ConcurrentHashMap<>();
        answers= new ConcurrentHashMap<>();
        tags= new ConcurrentHashMap<>();
    }
    public User createUser(String name,String email){
        int id= users.size()+1;
        User user= new User(id, name, email);
        users.put(id, user);
        return user;
    }
    public Question askQuestion(User user, String title, String content, List<String> tags){
        Question que= user.askQuestion(title,content,tags);
        questions.put(que.getId(), que);
        for (Tag tag:que.getTags()){
            this.tags.putIfAbsent(tag.getName(), tag);
        }
        return que;
    }
    public Answer answerQuestion(User user, Question que, String content){
        Answer ans= user.answerQuestion(que,content);
        answers.put(ans.getId(), ans);
        return ans;
    }
    public Comment addComment(User user, Commentable comment, String content){
        return user.addComment(comment,content);
    }
    public void voteQuestion(User user, Question que, int val){
        que.vote(user,val);
    }
    public void voteAnswer(User user, Answer ans, int val){
        ans.vote(user,val);
    }
    public void acceptAnswer(Answer ans){
        ans.markAccepted();
    }
    public List<Question> searchQuestions(String query){
        return questions.values().stream().filter(v-> v.getTitle().toLowerCase().
                        contains(query.toLowerCase()) || v.getContent().toLowerCase().
                contains(query.toLowerCase()) || v.getTags().stream().
                anyMatch(t->t.getName().equalsIgnoreCase(query))).
                collect(Collectors.toList());
    }
    public List<Question> getQuestionsByUser(User user){
        return user.getQuestions();
    }
    public User getUser(int id) { return users.get(id); }

}
