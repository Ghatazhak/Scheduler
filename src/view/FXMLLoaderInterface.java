package view;

import javafx.scene.Parent;

import java.io.IOException;

public interface FXMLLoaderInterface {
    Parent getRoot(String fxml) throws IOException;
}
