package code.c482project;
/**This class is used to create an Ousourced part.*/
public class Outsourced extends Part
{
    private String companyName;


    /** This is the Outsourced method. This method creates an Outsourced part.
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName)
    {
        //this.id = id;
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }


/** @return companyName */
    public String getCompanyName()
    {
        return companyName;
    }


/** @param companyName the companyName to set*/
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
}
