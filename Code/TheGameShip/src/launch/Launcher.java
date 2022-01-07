//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package launch;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import model.entity.*;
import view.ViewManager;

public class Launcher extends Application {

    public void start(Stage primaryStage) throws IOException {
        Scene defaultScene=new Scene(FXMLLoader.load(this.getClass().getResource("/FXML/Fenetre.fxml")));
        ViewManager viewManager=new ViewManager();
        viewManager.addScene("Fenetre",defaultScene);
        viewManager.addScene("Fenetre2",new Scene(FXMLLoader.load(this.getClass().getResource("/FXML/Fenetre2.fxml"))));
        /*
        //Déplacement
        Set<String> input = new HashSet<String>();
        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            input.add(code);
        });
        scene.setOnKeyReleased((EventHandler<KeyEvent>) e -> {
            String code = e.getCode().toString();
            if (input.contains("LEFT")){
                System.out.println("LEFT");
            }else if (input.contains("RIGHT")){
                System.out.println("RIGHT");
            }else if (input.contains("DOWN")){
                System.out.println("DOWN");
            }else if (input.contains("UP")){
                System.out.println("UP");
            }else if (input.contains("A")){
                System.out.println("A");
            }
            input.remove( code );
        });*/
        primaryStage.setScene(viewManager.getScene("Fenetre2",defaultScene));
        primaryStage.setTitle("TheGameShip");
        primaryStage.show();
    }
}
