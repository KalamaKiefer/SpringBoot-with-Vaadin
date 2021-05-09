package VaadinWebApp.Views;


import VaadinWebApp.Student;
import VaadinWebApp.StudentRepository;
import VaadinWebApp.Vocab;
import VaadinWebApp.VocabRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.Random;


@PageTitle("Flashcards | Laulima Major Based Games")
@Route(value = "flash", layout = MainView.class)
public class FlashGameView extends VerticalLayout {
    private VocabRepository vocabRepository;
    private Vocab vocab;
    private Long ind;
    private int done[] = new int[100];
    TextField def;
    Grid<Vocab> grid;

    Random r = new Random();

    public FlashGameView(VocabRepository vocabRepository){

        this.grid = new Grid<>(Vocab.class);
        this.vocabRepository = vocabRepository;
        this.def = new TextField("Definition");

        def.addValueChangeListener(e -> checkDef(e.getValue()));

        Button check = new Button("Check", VaadinIcon.CHECK.create());

        grid.setHeight("100px");
        grid.setColumns("index" , "word");
        grid.getColumnByKey("index").setWidth("50px").setFlexGrow(0);

        for(int z = 51; z < 101; z++){
            int a = 0;
            done[a] = z;
            a++;
        }


        setSpacing(true);

        add(grid, def, check);
        showWord();
        }

        private Long checkIndex(){
             Long value;

            for(int n = 0; n < 50; n++) {
                value = Long.valueOf(n);
                if(vocabRepository.findById(value).isPresent() && done[n] != n){
                    done[n] = n;
                    ind = value;
                    return value;
                }
            }

            return null;
        }

        private void showWord(){
            Long id = checkIndex();
            if(id != null) {
                grid.setItems(vocabRepository.findById(id).get());
            }else{
                Notification shows = new Notification("Vocab Game Finished!", 2000, Notification.Position.MIDDLE);
                shows.getElement().getThemeList().add("primary");
                shows.open();
                UI.getCurrent().navigate(FlashcardView.class);
            }
        }

        // get word from database through id and compare
        private void checkDef(String d){
            vocab = vocabRepository.findById(ind).get();

            if(d.equals(vocab.getDefinition())){
                Notification shows = new Notification("Correct!", 2000, Notification.Position.MIDDLE);
                shows.getElement().getThemeList().add("primary");
                shows.open();
                showWord();

            }else{
                Notification shows = new Notification("Incorrect", 2000, Notification.Position.MIDDLE);
                shows.getElement().getThemeList().add("error");
                shows.open();
            }

        }

}
