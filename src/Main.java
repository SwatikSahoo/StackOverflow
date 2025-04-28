import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Stackoverflow system= new Stackoverflow();
        User alice= system.createUser("alice","alice@gmail.com");
        User bob= system.createUser("bob","bob@gmail.com");
        User charlie= system.createUser("charlie","charlie@gmail.com");

        Question aliceQue =system.askQuestion(alice, "What is Life", "Does anybody know it's meaning",
                Arrays.asList("Life","Death"));
        Answer bobAnswer = system.answerQuestion(bob,aliceQue,"Life is realising change is the only constant");

        system.addComment(charlie,aliceQue,"Have some Breakfast!!");
        system.addComment(alice,bobAnswer,"Death is the only constant");
        system.voteQuestion(charlie,aliceQue,1);
        system.voteAnswer(charlie,bobAnswer,1);

        system.acceptAnswer(bobAnswer);

    }
}