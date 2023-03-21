package code.c482project;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**This class is used to manage and control the inventory within the application.
 */
public class Inventory
{

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    public static int uniqueid;
    public static int productuniqueid;


    /**
     * This is the addPart method. This method adds a new part to the allParts list.
     * @param part
     */
    public static void addPart(Part part)
    {
        allParts.add(part);
    }


    /**
     * This is the getAllParts method. This method is used to return a list of parts from the allParts list.
     * @return
     */
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }


    /** This is the addProduct method. This method adds a selected part to the allProducts list.
     * @param product
     */
    public static void addProduct(Product product)
    {
        allProducts.add(product);
    }


    /**
     * This is the getAllProducts method. This method is used to return a list of products from the allProducts list.
     * @return
     */
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }


    /**@return the id
     * This is the getPartUniqueId method. This method increments the current unique id by 1 and assigns it to the part.
     * @return
     */
    public static int getUniqueid()
    {
        uniqueid = uniqueid + 1;
        return uniqueid;
    }


    /**
     * This is the getProductUniqueId method. This method increments the current unique id by 1 and assigns it to the product.
     * @return
     */
    public static int getProductuniqueid()
    {
        productuniqueid = productuniqueid + 1;
        return productuniqueid;
    }


    /**
     * This is the lookupPart method. This version of the lookupPart method searches parts based on the string partName.
     * @param partName
     * @return
     */
    public static ObservableList<Part> lookupPart(String partName)
    {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part P : allParts)
        {
            if (P.getName().toLowerCase().contains(partName.toLowerCase()))
            {
                namedParts.add(P);
            }
        }

        return namedParts;
    }


    /**
     * This is the lookupPart method. This version of the lookupPart method searches parts based on the integer partId.
     * @param partId
     * @return
     */
    public static Part lookupPart(int partId)
    {
        Part searchedPart = null;

        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part P : allParts)
        {
            if (P.getId() == partId)
            {
                searchedPart = P;
            }
        }

        return searchedPart;

    }


    /**
     * This is the lookupProduct method. This version of the lookupProduct method searches products based on the string partName.
     * @param partName
     * @return
     */
    public static ObservableList<Product> lookupProduct(String partName)
    {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();

        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (Product P : allProducts)
        {
            if (P.getName().toLowerCase().contains(partName.toLowerCase()))
            {
                namedProducts.add(P);
            }
        }

        return namedProducts;
    }


    /**
     * This is the lookupProduct method. This version of the lookupProduct method searches products based on the integer productId.
     * @param productId
     * @return
     */
    public static Product lookupProduct(int productId)
    {
        Product searchedProduct = null;

        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (Product P : allProducts)
        {
            if (P.getId() == productId)
            {
                searchedProduct = P;
            }
        }

        return searchedProduct;
    }


    /**
     * This is the updatePart method. This method updates the selected part with the newly modified part.
     * @param index
     * @param selectedPart
     */
    public static void updatePart(int index, Part selectedPart)
    {
        allParts.set(index, selectedPart);
    }


    /**
     * This is the updateProduct method. This method updates the selected product with the newly modified product.
     * @param index
     * @param selectedProduct
     */
    public static void updateProduct(int index, Product selectedProduct)
    {
        allProducts.set(index, selectedProduct);
    }


    /**
     * This is the deletePart method. This method deletes a part from the allParts list.
     * @param selectedPart
     * @return
     */
    public static boolean deletePart(Part selectedPart)
    {
        return allParts.remove(selectedPart);
    }


    /**
     * This is the deleteProduct method. This method deletes a product from the allProducts list.
     * @param selectedProduct
     * @return
     */
    public static boolean deleteProduct(Product selectedProduct)
    {
        return allProducts.remove(selectedProduct);
    }

}





