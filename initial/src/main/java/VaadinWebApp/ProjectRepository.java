package VaadinWebApp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Projects, Long> {

List<Projects> findByPname(String pname);
}
