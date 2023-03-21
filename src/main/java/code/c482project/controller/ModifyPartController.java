package code.c482project.controller;

import code.c482project.InHouse;
import code.c482project.Inventory;
import code.c482project.Outsourced;
import code.c482project.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import java.net.URL;
import java.io.IOException;



/**This class is used to modify parts in the application.*/
public class ModifyPartController implements Initializable {

    public Label machineidORcompanyname;
    public RadioButton inhouse;
    public RadioButton outsourced;
    public ToggleGroup inoutgroup;
    public TextField nameinput;
    public TextField stockinput;
    public TextField priceinput;
    public TextField maxinput;
    public TextField idinput;
    public TextField mininput;
    public Button toMain;
    public TextField machineidinput;


    private static Part sentPart = null;


    /**This is the sendData method. This method retrieves the part that was selected for modification on the main screen.
     *
     * @param part
     */
    public static void sendData(Part part)
    {
        sentPart = part;
    }


    /**This is the initialize method. This method aligns all initial value for a data object or variable.
     *
     * @param location
     * @param resourceBundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle)
    {


        idinput.setText(Integer.toString(sentPart.getId()));
        nameinput.setText(sentPart.getName());
        stockinput.setText(Integer.toString(sentPart.getStock()));
        priceinput.setText(Double.toString(sentPart.getPrice()));
        maxinput.setText(Integer.toString(sentPart.getMax()));
        mininput.setText(Integer.toString(sentPart.getMin()));

        if(sentPart instanceof InHouse)
        {
            machineidinput.setText(Integer.toString(((InHouse) sentPart).getMachineId()));
            inhouse.setSelected(true);
            machineidORcompanyname.setText("Machine ID");
        }
        else
        {
            machineidinput.setText(((Outsourced) sentPart).getCompanyName());
            outsourced.setSelected(true);
            machineidORcompanyname.setText("Company Name");
        }
    }


    /**
     * This is the toMain method. This method sends the user back to the main screen without making any changes to the selected part
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


    /**This is the oninhouse method. This message changes the machineidORcompanyname text to "Machine ID".
     * @param actionEvent
     */
    public void oninhouse(ActionEvent actionEvent)
    {
        machineidORcompanyname.setText("Machine ID");
    }


    /**This is the onoutsourced method. This message changes the machineidORcompanyname text to "Company Name.
     *
     * @param actionEvent
     */
    public void onoutsourced(ActionEvent actionEvent)
    {
        machineidORcompanyname.setText("Company Name");
    }

    /**
     * This is the onSave method. This method takes the adjusted part, removes the old part, and adds the adjusted part to the allParts list.
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

        int sentPartIndex = Inventory.getAllParts().indexOf(sentPart);


        if(name.isBlank())
        {
            System.out.println("Name Input is Blank");

            Alert nameValidation = new Alert(Alert.AlertType.ERROR);
            nameValidation.setContentText("Name Input is Blank");
            nameValidation.showAndWait();
            return;

        }


        if(stockS.isBlank())
        {
            System.out.println("Stock Input is Blank");
            Alert stockValidation = new Alert(Alert.AlertType.ERROR);
            stockValidation.setContentText("Stock Input is Blank");
            stockValidation.showAndWait();
            return;
        }

        int pStock = 0;
        try
        {
            pStock = Integer.parseInt(stockS);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Stock Value is not a number");
            Alert stockValidationValue = new Alert(Alert.AlertType.ERROR);
            stockValidationValue.setContentText("Stock Value is not a number");
            stockValidationValue.showAndWait();
            return;
        }


        if(priceS.isBlank())
        {
            System.out.println("Price Input is Blank");
            Alert priceValidation = new Alert(Alert.AlertType.ERROR);
            priceValidation.setContentText("Price Input is Blank");
            priceValidation.showAndWait();
            return;
        }
        Double pPrice = 0.00;
        try
        {
            pPrice = Double.parseDouble(priceS);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Price value is not a number");
            Alert priceValidation = new Alert(Alert.AlertType.ERROR);
            priceValidation.setContentText("Price value is not a number");
            priceValidation.showAndWait();
            return;
        }


        if(minS.isBlank())
        {
            System.out.println("Min Input is Blank");
            Alert minValidation = new Alert(Alert.AlertType.ERROR);
            minValidation.setContentText("Min Input is Blank");
            minValidation.showAndWait();
            return;
        }
        int pMin = 0;
        try
        {
            pMin = Integer.parseInt(minS);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Min value is not a number");
            Alert minValidationValue = new Alert(Alert.AlertType.ERROR);
            minValidationValue.setContentText("Min value is not a number");
            minValidationValue.showAndWait();
            return;
        }


        if(maxS.isBlank())
        {
            System.out.println("Max Input is Blank");
            Alert maxValidation = new Alert(Alert.AlertType.ERROR);
            maxValidation.setContentText("Max Input is Blank");
            maxValidation.showAndWait();
            return;
        }
        int pMax = 0;
        try
        {
            pMax = Integer.parseInt(maxS);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Max value is not a number");
            Alert maxValidationValue = new Alert(Alert.AlertType.ERROR);
            maxValidationValue.setContentText("Max value is not a number");
            maxValidationValue.showAndWait();
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
            Alert minGreaterStock = new Alert(Alert.AlertType.ERROR);
            minGreaterStock.setContentText("Stock must be greater than Min Value");
            minGreaterStock.showAndWait();
            return;
        }
        if (max < stock)
        {
            System.out.println("Stock must be less than Max Value");
            Alert maxLesserStock = new Alert(Alert.AlertType.ERROR);
            maxLesserStock.setContentText("Stock must be less than Max Value");
            maxLesserStock.showAndWait();
            return;
        }
        if(min > max)
        {
            System.out.println(("Min cannot be greater than Max"));
            Alert minGreaterMax = new Alert(Alert.AlertType.ERROR);
            minGreaterMax.setContentText("Min cannot be greater than Max");
            minGreaterMax.showAndWait();
            return;
        }


        if (inhouse.isSelected())
        {
            String machineidS = machineidinput.getText();


            if(machineidS.isBlank())
            {
                System.out.println("MachineID Input is Blank");
                Alert machineIdValidation = new Alert(Alert.AlertType.ERROR);
                machineIdValidation.setContentText("MachineID value is not a number");
                machineIdValidation.showAndWait();
                return;
            }
            int pMachineID = 0;
            try
            {
                pMachineID = Integer.parseInt(machineidS);
            }
            catch(NumberFormatException e)
            {
                System.out.println("MachineID value is not a number");
                Alert machineIdValidationValue = new Alert(Alert.AlertType.ERROR);
                machineIdValidationValue.setContentText("MachineID value is not a number");
                machineIdValidationValue.showAndWait();
                return;
            }

            int machineid = Integer.parseInt(machineidS);

            Part modPart = new InHouse(id, name, price, stock, min, max, machineid);


            Inventory.updatePart(sentPartIndex, modPart);

            System.out.println("Added InHouse Part to Table");
        }

        else if (outsourced.isSelected())
        {
            String companyname = machineidinput.getText();


            if(companyname.isBlank())
            {
                System.out.println("CompanyName Input is Blank");
                Alert companyNameValidation = new Alert(Alert.AlertType.ERROR);
                companyNameValidation.setContentText("CompanyName Input is Blank");
                companyNameValidation.showAndWait();
                return;
            }

            Part modPart = new Outsourced(id, name, price, stock, min, max, companyname);


            Inventory.updatePart(sentPartIndex, modPart);

            System.out.println("Added Outsourced Part to Table");

        }
        Parent root = FXMLLoader.load(getClass().getResource("/code/c482project/main-view.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1050, 400);
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
    }
}

