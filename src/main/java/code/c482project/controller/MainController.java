package code.c482project.controller;

import code.c482project.Part;
import code.c482project.Inventory;
import code.c482project.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.net.URL;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;


/**This class is used to control and display the appliation data.*/
public class MainController implements Initializable
{

    public TableColumn productpartid;
    public TableColumn productpartname;
    public TableColumn productstocklevel;
    public TableColumn productprice;
    public TableColumn partpartid;
    public TableColumn partpartname;
    public TableColumn partstocklevel;
    public TableColumn partprice;
    public TableView partsTable;
    public TableView productsTable;
    public TextField queryParts;
    public TextField queryProducts;
    public Button exit;

    /**
     * This is the initialize method. This method aligns all initial value for a data object or variable.
     * @param location
     * @param resourceBundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle)
    {

        partsTable.setItems(Inventory.getAllParts());


        partpartid.setCellValueFactory(new PropertyValueFactory<>("id"));
        partpartname.setCellValueFactory(new PropertyValueFactory<>("name"));
        partstocklevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partprice.setCellValueFactory(new PropertyValueFactory<>("price"));


        productsTable.setItems(Inventory.getAllProducts());

        productpartid.setCellValueFactory(new PropertyValueFactory<>("id"));
        productpartname.setCellValueFactory(new PropertyValueFactory<>("name"));
        productstocklevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productprice.setCellValueFactory(new PropertyValueFactory<>("price"));


    }


    /**
     * This is the queryParts method. This method searches the Parts list by name and ID.
     *RUNTIME ERROR: When searching for part no found part by integer will not populate error. To fix this I added a part == null if statement that catches it and throws an error before it continues through the program.
     * @param actionEvent
     */
    public void queryParts(ActionEvent actionEvent)
    {

        String p = queryParts.getText();
        int pId = 0;
        ObservableList<Part> parts = FXCollections.observableArrayList();


        try
        {
            pId = Integer.parseInt(p);
            Part part = Inventory.lookupPart(pId);
            if (part == null)
            {
                Alert noSearchedPart = new Alert(Alert.AlertType.ERROR);
                noSearchedPart.setContentText("No Part found");
                noSearchedPart.showAndWait();
            }
            else
            {
                partsTable.getSelectionModel().select(part);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            parts = Inventory.lookupPart(p);


            boolean empty = parts.isEmpty();

            if (empty == true)
            {
                Alert noSearchedPart = new Alert(Alert.AlertType.ERROR);
                noSearchedPart.setContentText("No Part found");
                noSearchedPart.showAndWait();
            } else
            {
                partsTable.setItems(parts);
            }
        }
    }

    /**
     * This is the queryProducts method. This method searches the Parts list by name and ID.
     * @param actionEvent
     */
    public void queryProducts(ActionEvent actionEvent)
    {

        String p = queryProducts.getText();
        int pId = 0;
        ObservableList<Product> products = FXCollections.observableArrayList();


        try
        {
            pId = Integer.parseInt(p);
            Product product = Inventory.lookupProduct(pId);
            if (product == null)
            {
                Alert noSearchedProduct = new Alert(Alert.AlertType.ERROR);
                noSearchedProduct.setContentText("No Product found");
                noSearchedProduct.showAndWait();
            }
            else
            {
                productsTable.getSelectionModel().select(product);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            products = Inventory.lookupProduct(p);

            boolean empty = products.isEmpty();

            if (empty == true) {
                Alert noSearchedProduct = new Alert(Alert.AlertType.ERROR);
                noSearchedProduct.setContentText("No Product found");
                noSearchedProduct.showAndWait();
            } else {
                productsTable.setItems(products);
            }
        }

    }


    /**
     * This is the onaddPart method. This method sends the user to the AddPartController where the user can add a new part.
     * @param actionEvent
     * @throws IOException
     */
    public void onaddPart(ActionEvent actionEvent) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/code/c482project/add-part.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Part Scene");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This is the onmodifyPart method. This method sends the user to the ModifyPartController where the user can modify the selected part.
     * @param actionEvent
     * @throws IOException
     */
    public void onmodifyPart(ActionEvent actionEvent) throws IOException
    {
        Part thePart = (Part) partsTable.getSelectionModel().getSelectedItem();
        ModifyPartController.sendData(thePart);
        if(thePart == null)
        {
            System.out.println("No part is selected");
            Alert noPartSelected = new Alert(Alert.AlertType.ERROR);
            noPartSelected.setContentText("No part is selected");
            noPartSelected.showAndWait();
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("/code/c482project/modify-part.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Modify Part Scene");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This is the ondeletePart method. This method warns the user then if they proceed the method deletes the selected part from the allParts list.
     * @param actionEvent
     */
    public void ondeletePart(ActionEvent actionEvent)
    {

        Alert deletePart = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected Part, do you want to continue?");


        Part P = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (P == null)
        {
            System.out.println("No part is selected");
            Alert noDeletePartSelected = new Alert(Alert.AlertType.ERROR);
            noDeletePartSelected.setContentText("No part is selected");
            noDeletePartSelected.showAndWait();
        }
        else
        {
            Optional<ButtonType> result = deletePart.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK)
            {
                if (P == null)
                    return;

                Inventory.deletePart(P);
            }
        }
    }


    /**
     * This is the on addProduct method. This method sends the user to the AddProductController where the user can add a product.
     * @param actionEvent
     * @throws IOException
     */
    public void onaddProduct(ActionEvent actionEvent) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/code/c482project/add-product.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 645);
        stage.setTitle("Add Product Scene");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This is the onmodifyProduct method. This method sends the user to the ModifyProductController where the user can edit the selected product.
     * @param actionEvent
     * @throws IOException
     */
    public void onmodifyProduct(ActionEvent actionEvent) throws IOException
    {

        Product theProduct = (Product) productsTable.getSelectionModel().getSelectedItem();
        ModifyProductController.sendData(theProduct);
        if(theProduct == null) {
            System.out.println("No product is selected");
            Alert noProductSelcted = new Alert(Alert.AlertType.ERROR);
            noProductSelcted.setContentText("No product is selected");
            noProductSelcted.showAndWait();
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("/code/c482project/modify-product.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 645);
        stage.setTitle("Modify Product Scene");
        stage.setScene(scene);
        stage.show();

    }


    /**
     * This is the ondeleteProduct method. This method warns the user then if they proceed the method deletes the selected product from the allProducts.
     * @param actionEvent
     */
    public void ondeleteProduct(ActionEvent actionEvent)
    {
        Alert deleteProduct = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected Product, do you want to continue?");

        Product P = (Product) productsTable.getSelectionModel().getSelectedItem();
        if (P == null)
        {
            System.out.println("No product is selected");
            Alert noDeletePartSelected = new Alert(Alert.AlertType.ERROR);
            noDeletePartSelected.setContentText("No product is selected");
            noDeletePartSelected.showAndWait();
        }
        else
        {
            ObservableList<Part> associatedParts = FXCollections.observableArrayList();;

            associatedParts.addAll(P.getAllAssociatedParts());

            if (associatedParts.isEmpty())
            {
                Optional<ButtonType> result = deleteProduct.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK)
                {
                    if (P == null)
                        return;

                    Inventory.deleteProduct(P);
                }
            }
            else
            {
                System.out.println("Remove all associated parts from product before deletion.");
                Alert associatedPartsError = new Alert(Alert.AlertType.ERROR);
                associatedPartsError.setContentText("Remove all associated parts from product before deletion.");
                associatedPartsError.showAndWait();
            }
        }
    }

    /**
     * This is th onExit method. This method warns the user then if they proceed the program will exit.
     * @param actionEvent
     */
    public void onExit(ActionEvent actionEvent)
    {
        Alert exitProgram = new Alert(Alert.AlertType.CONFIRMATION, "This will exit the Program, do you want to continue?");

        Optional<ButtonType> result = exitProgram.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            Platform.exit();
        }
    }
}


