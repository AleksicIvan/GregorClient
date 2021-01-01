package aleksic;

import aleksic.Servis.Igra;
import aleksic.Views.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.Socket;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Socket soketK = new Socket("127.0.0.1", 9000);

        ViewManager viewManager = new ViewManager();
        viewManager.prikaziLogin();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
