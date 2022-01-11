//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package launch;

import java.io.IOException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Boucle;
import model.entity.*;
import model.move.Input;
import model.move.Keyboard;
import model.move.MovePlayer;
import view.ViewManager;

public class Launcher extends Application {
    //TODO: Faire des fonction pour attacher des événements a une scène dans ViewManager
    public static ViewManager viewManager;
    public static EntityManager entityManager;
    private Thread b;

    public void start(Stage stage) throws Exception {
        entityManager=new EntityManager(); //TODO: Il faut le faire lors de la création d'un niveau
        entityManager.add(new Player("file://test.jpg","Vaisseau",100,100,80,6, 20, 20));

        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/EdenCodingIcon.png")));

        viewManager = new ViewManager(stage);
        viewManager.loadView();
        viewManager.setView("MainWindow");//DEBUG

        Input input=new Keyboard(new MovePlayer("Vaisseau"));
        b = new Thread(new Boucle() {
            @Override
            public void update() throws Exception {
                input.update();
            }
        });
        b.start();

        viewManager.show();
    }

    @Override
    public void stop() {
        b.stop();
    }
}
