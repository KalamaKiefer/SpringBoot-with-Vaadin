package VaadinWebApp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vocab {

    int nat = 1;

    @Id
    @GeneratedValue
    private Long index;

    private Long student_id = Long.valueOf(nat);
    private String word;
    private String definition;

    protected Vocab(){

    }

    public Vocab(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public Long getIndex() {
        return index;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
