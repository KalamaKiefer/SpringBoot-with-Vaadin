package VaadinWebApp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String major;

    private String word;

    private String definition;


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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    protected Student(){}

    public Student(String name, String major, String word, String definition){
        this.name = name;
        this.major = major;
        this.word = word;
        this.definition = definition;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name){ this.name = name;}

    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("Student[id=%d, Name='%s', major='%s']", id,
                name, major);
    }
}
