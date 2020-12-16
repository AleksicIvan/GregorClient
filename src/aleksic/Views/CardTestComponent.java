package aleksic.Views;

import aleksic.Controllers.FXMLCardTest;
import aleksic.TransferObjekat.TransferObjekatIgrac;
import aleksic.Views.ViewManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CardTestComponent extends VBox {
    FXMLCardTest fxmlCardTest;
    ViewManager vm;
    TransferObjekatIgrac toi;

    public CardTestComponent() throws IOException {
        this.fxmlCardTest = fxmlCardTest;
//        this.vm = viewManager;
//        this.soketK = viewManager.getSoketK();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "FXMLCardTest.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void cardTestOnClick () throws IOException {
        System.out.println("Card button clicked!");
    }

//    @Override
//    public ViewManager getVm() {
//        return vm;
//    }
//
//    @Override
//    public void setToi(TransferObjekatIgrac toi) {
//        this.toi = toi;
//    }
}
