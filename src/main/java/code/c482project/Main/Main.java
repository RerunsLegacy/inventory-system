package code.c482project.Main;

import code.c482project.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**This class is used to start the application.
 * JavaDocs Folder Location: In this project
 * FUTURE ENHANCEMENT: Colorblind features and text to voice for visually impaired.
 *
 * */
public class Main extends Application {

    /**This is the start method. This method starts the application and shows the initial screen.*/
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/code/c482project/main-view.fxml"));
        primaryStage.setTitle("Main Scene");
        primaryStage.setScene(new Scene(root, 1050, 400));
        primaryStage.show();
    }

    /**This is the main method. This method is used to create test data for the application.*/
    public static void main(String[] args) {

        Part p1 = new InHouse(Inventory.getUniqueid(), "Ram", 1.00, 3,1,4,704 );
        Inventory.addPart(p1);

        Part p2 = new Outsourced(Inventory.getUniqueid(), "CPU", 22.00, 3,1,4,"CyberPower" );
        Inventory.addPart(p2);

        Product pro1 = new Product(Inventory.getProductuniqueid(), "Computer", 200.00, 27, 1, 28);
        Inventory.addProduct(pro1);

        launch(args);
    }
}

