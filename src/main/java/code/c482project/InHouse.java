package code.c482project;

/**This class is used to create an InHouse part.*/
public class InHouse extends Part
{
    private int machineId;


    /** This is the InHouse method. This method creates an InHouse part.
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId)
    {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }


/** @return MachineId*/
    public int getMachineId()
    {
        return machineId;
    }


/** @param machineId the machineId to set*/
    public void setMachineId(int machineId)
    {
        this.machineId = machineId;
    }
}
