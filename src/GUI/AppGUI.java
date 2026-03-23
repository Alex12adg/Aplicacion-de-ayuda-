package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/GUI/views/main-view.fxml")
        );

        Scene scene = new Scene(loader.load(), 800, 500);

        stage.setTitle("Sistema de Emergencias");
        stage.setScene(scene);
        stage.show();
    }
}
