package VaadinWebApp;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocabRepository extends JpaRepository<Vocab, Long> {

    List<Vocab> findByWord(String word);
}
