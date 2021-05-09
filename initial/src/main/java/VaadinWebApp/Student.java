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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    protected Student(){}

    public Student(String name, String major, int score){
        this.name = name;
        this.major = major;
        this.score = score;
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
