package code.c482project.controller;

import code.c482project.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**This class is used to modify products in your application.*/
public class ModifyProductController implements Initializable {
    public TableColumn partpartid;
    public TableColumn partpartname;
    public TableColumn partstocklevel;
    public TableColumn partprice;
    public TableView partsTable;
    public TableColumn associatedpartprice;
    public TableColumn associatedpartid;
    public TableColumn associatedpartname;
    public TableColumn associatedpartstocklevel;
    public TableView associatedpartsTable;
    public TextField mininput;
    public TextField nameinput;
    public TextField stockinput;
    public TextField priceinput;
    public TextField maxinput;
    public TextField idinput;

    private ObservableList<Part> tempassociatedParts = FXCollections.observableArrayList();

    private static Product sentProduct = null;


    /**
     * This is the sendData method. This method retrieves the product that was selected for modification on the main screen.
     * @param product
     */
    public static void sendData(Product product)
    {
        sentProduct = product;
    }


    /**
     * This is the initialize method. This method aligns all initial value for a data object or variable.
     * @param location
     * @param resourceBundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle)
    {


        idinput.setText(Integer.toString(sentProduct.getId()));
        nameinput.setText(sentProduct.getName());
        stockinput.setText(Integer.toString(sentProduct.getStock()));
        priceinput.setText(Double.toString(sentProduct.getPrice()));
        maxinput.setText(Integer.toString(sentProduct.getMax()));
        mininput.setText(Integer.toString(sentProduct.getMin()));


        partsTable.setItems(Inventory.getAllParts());
        associatedpartsTable.setItems(tempassociatedParts);

        tempassociatedParts.addAll(sentProduct.getAllAssociatedParts());


        partpartid.setCellValueFactory(new PropertyValueFactory<>("id"));
        partpartname.setCellValueFactory(new PropertyValueFactory<>("name"));
        partstocklevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partprice.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedpartid.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedpartname.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedpartstocklevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedpartprice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    /**
     * This is the toMain method. This method sends the user back to the main screen without making any changes to the selected product.
     * @param actionEvent
     * @throws IOException
     */
    public void toMain(ActionEvent actionEvent) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/code/c482project/main-view.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 400);
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This is the onRemove method. This method removes the selected part from the list of associated parts.
     * @param actionEvent
     */
    public void onRemove(ActionEvent actionEvent)
    {
        Alert removePart = new Alert(Alert.AlertType.CONFIRMATION, "This will remove the selected Part, do you want to continue?");

        Optional<ButtonType> result = removePart.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            System.out.println("Part Removed");

            Part p = (Part) associatedpartsTable.getSelectionModel().getSelectedItem();

            if (p == null)
                return;

            tempassociatedParts.remove(p);
        }
    }


    /**
     * This is the onAdd method. This method adds the selected part to the list of associated parts.
     * @param actionEvent
     */
    public void onAdd (ActionEvent actionEvent)
    {
        Alert addPart = new Alert(Alert.AlertType.CONFIRMATION, "This will add the selected Part, do you want to continue?");

        Optional<ButtonType> result = addPart.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            System.out.println("Part Added");

            Part p = (Part) partsTable.getSelectionModel().getSelectedItem();

            if (p == null)
                return;

            tempassociatedParts.add(p);

            associatedpartsTable.setItems(tempassociatedParts);

            associatedpartid.setCellValueFactory(new PropertyValueFactory<>("id"));
            associatedpartname.setCellValueFactory(new PropertyValueFactory<>("name"));
            associatedpartstocklevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
            associatedpartprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        }
    }


    /**
     * This is the onSave method. This method takes the adjusted product, removes the old product, and adds the adjusted product to the allProducts list.
     * @param actionEvent
     * @throws IOException
     */
    public void onSave(ActionEvent actionEvent) throws IOException
    {
        String idS = idinput.getText();
        String name = nameinput.getText();
        String priceS = priceinput.getText();
        String stockS = stockinput.getText();
        String minS = mininput.getText();
        String maxS = maxinput.getText();

        int sentProductIndex = Inventory.getAllProducts().indexOf(sentProduct);


        if(name.isBlank())
        {
            System.out.println("Name Input is Blank");
            return;
        }


        if(stockS.isBlank())
        {
            System.out.println("Inventory Input is Blank");
            return;
        }

        int pStock = 0;
        try
        {
            pStock = Integer.parseInt(stockS);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Stock not a number");
            return;
        }


        if(priceS.isBlank())
        {
            System.out.println("Price Input is Blank");
            return;
        }
        Double pPrice = 0.00;
        try
        {
            pPrice = Double.parseDouble(priceS);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Price not a number");
            return;
        }


        if(minS.isBlank())
        {
            System.out.println("Min Input is Blank");
            return;
        }
        int pMin = 0;
        try
        {
            pMin = Integer.parseInt(minS);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Min not a number");
            return;
        }


        if(maxS.isBlank())
        {
            System.out.println("Max Input is Blank");
            return;
        }
        int pMax = 0;
        try
        {
            pMax = Integer.parseInt(maxS);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Max not a number");
            return;
        }


        int id = Integer.parseInt(idS);
        double price = Double.parseDouble(priceS);
        int stock = Integer.parseInt(stockS);
        int min = Integer.parseInt(minS);
        int max = Integer.parseInt(maxS);


        if(min > stock)
        {
            System.out.println("Stock must be greater than Min Value");
            return;
        }
        if (max < stock)
        {
            System.out.println("Stock must be less than Max Value");
            return;
        }
        if(min > max)
        {
            System.out.println(("Min cannot be greater than Max"));
        }


        Product modProduct = new Product(id, name, price, stock, min, max);
        for(Part temp: tempassociatedParts)
        {
            modProduct.addAssociatedPart(temp);
        }


        Inventory.updateProduct(sentProductIndex, modProduct);

        System.out.println("Added InHouse Product to Table");

        Parent root = FXMLLoader.load(getClass().getResource("/code/c482project/main-view.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 400);
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
    }
}

