package VaadinWebApp;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Projects {

    @Id
    @GeneratedValue
    private Long project_id;

    private String topic;

    private String pname;

    @GeneratedValue
    @Column(name="student_id")
    private Long student_id;

    protected Projects(){

    }

    public Projects(String pname, String topic) {
        this.pname = pname;
        this.topic = topic;
    }

    public Long getProject_id() {
        return project_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Long getStudent_id() {
        return student_id;
    }

}
