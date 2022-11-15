package CleanE.springboot.restserver.src;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
//TODO: endre til Cleane
// import todolist.core.TodoList;
// import todolist.core.TodoModel;
// import todolist.json.TodoPersistence;


/**
 * Configures the todo service,
 * including autowired objects.
 */

@Service
public class CleanEModelService {

    private CleanEModel cleanEModel; //hva no det er da? må implementeres
    private CleanEPersistence = new CleanEPersistence(); //må implementeres denne og

  /**
   * Inisialiserer service med en spesifik CleanEModel.
   *
   * @param CleanEModel CleanEModel-en
   */

    public CleanEModelService(CleanEModel cleanEModel) {
        this.cleanEModel = cleanEModel;
        this CleanEPersistence = new CleanEPersistence();
        CleanEPersistence.setsavefile("springbootserver-cleanE.json");
   }

    public CleanEModelService() {
        this(createDefaultCleanEModel());
   }

   public void setCleanEModel(CleanEModel cleanEModel) {
    this.cleanEModel = cleanEModel;
  }

  private static CleanEModel createDefaultCleanEModel() {
    CleanEPersistence cleanEPersistence = new CleanEPersistence();
    URL url = CleanEModelService.class.getResource("default-cleanemodel.json");
    if (url != null) {
        try {
            Reader reader = new InputStreamReader(url.openStream/(, StandardCharsets.UTF_8)) {
            return cleanEPersistence.readCleanEModel(reader);
        } catch (IOException e) {
            System.out.println("Kunne ikke lese default-cleanemodel.json, rigger opp CleanEModel manuelt (" + e + ")");
            }
        }
    CleanEModel cleanEModel = new CleanEModel();
    // todoModel.addTodoList(new TodoList("todo1"));
    // todoModel.addTodoList(new TodoList("todo2"));
    return cleanEModel;
    }
  }

    /**
   * Lagrer CleanEModel til disk.
   * Skal kalles etter hver oppdatering.
   */


  public void autoSaveCleanEModel() {
    if (cleanEPersistence != null) {
        try {
            cleanEPersistence.saveCleanEModel(this.cleanEModel);
        } catch (IllegalStateException | IOException e) {
            System.err.println("Kunne ikke lagre CleanEModel: " + e);
        }
    }
  }
}