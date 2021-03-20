package aleksic;

import aleksic.Views.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewManager viewManager = new ViewManager();
        viewManager.prikaziLogin();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
