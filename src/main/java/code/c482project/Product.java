package code.c482project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**This class is used to create a Product*/
public class Product
{

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;


    /**This is the Product method. This method is used to create a product.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */
    public Product(int id, String name, double price, int stock, int min, int max)
    {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.min = min;
        this.max = max;

    }


    /**
     * This is the getAllAssociatedParts method. This method is used to return a list of parts from the associatedParts list.
     * @return
     */
    public ObservableList<Part> getAllAssociatedParts()
    {
        return associatedParts;
    }


    /**
     * This is the addAssociatedPart method. This method is used to add an associatedPart to the associatedParts list.
     * @param p
     */
    public void addAssociatedPart(Part p)
    {
        associatedParts.add(p);
    }


    /**
     * This is the deleteAssociatedPart method. This method is used to remove an associatedPart from the associateParts list.
     * @param selectedAssociatedPart
     * @return
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart)
    {
        associatedParts.remove(selectedAssociatedPart);
        return true;
    }


    /**
     * @return the id
     * */
        public int getId()
        {
            return id;
        }


    /**
     * @param id the id to set
     * */
        public void setId(int id)
        {
            this.id = id;
        }


    /**
     * @return the Name
     * */
        public String getName()
        {
            return name;
        }


    /**
     * @param Name the name to set
     * */
        public void setName(String Name)
        {
            this.name = name;
        }


    /**
     * @return the stock
     * */
        public int getStock()
        {
            return stock;
        }


    /**
     * @param stock the stock to set
     * */
        public void setStock(int stock)
        {
            this.stock = stock;
        }


    /**
     * @return the price
     * */
        public double getPrice()
        {
            return price;
        }


    /**
     * @param price the price to set
     * */
        public void setPrice(double price)
        {
            this.price = price;
        }


     /**
     * @return the stock
     * */
        public int getMax()
        {
            return max;
        }


     /**
     *@param max the stock to set
     * */
        public void setMax(int max)
        {
            this.max = max;
        }


     /**
     * @return the stock
     * */
        public int getMin()
        {
            return min;
        }


     /**
     * @param min the stock to set
     * */
        public void setMin(int min)
        {
            this.min = min;
        }


}