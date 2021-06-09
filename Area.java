import java.util.ArrayList; 

public class Area implements AdventureConstants 
{ 
    private int north;
    private int south;
    private int east;                                                                                                                                 
    private int west;
    private int northeast;
    private int southeast;
    private int southwest;
    private int northwest;
    private int up;
    private int down;
    private ArrayList<String> areaItems;
    private String description;

    public Area()
    { 
        north = NO_EXIT;
        south = NO_EXIT;
        east = NO_EXIT;
        west = NO_EXIT;
        northeast = NO_EXIT;
        southeast = NO_EXIT;
        southwest = NO_EXIT;
        northwest = NO_EXIT;
        up = NO_EXIT;
        down = NO_EXIT;
        areaItems = new ArrayList<String>();
        description = "NO DESCRIPTION SET YET";
    } 

    public void setNorth (int newNorth)
    {
        if(newNorth < -1)
            System.out.println("That is invalid");
        else
            north = newNorth;
    }    

    public void setSouth (int newSouth)
    {
        if(newSouth < -1)
            System.out.println("That is invalid");
        else
            south = newSouth;
    } 

    public void setEast (int newEast)
    {
        if(newEast < -1)
            System.out.println("That is invalid");
        else
            east = newEast;
    } 

    public void setWest (int newWest)
    {
        if(newWest < -1)
            System.out.println("That is invalid");
        else
            west = newWest;
    } 

    public void setNortheast (int newNortheast)
    {
        if(newNortheast < -1)
            System.out.println("That is invalid");
        else 
            northeast = newNortheast;

    } 

    public void setSoutheast (int newSoutheast)
    {
        if(newSoutheast < -1)
            System.out.println("That is invalid");
        else
            southeast = newSoutheast;
    } 

    public void setSouthwest (int newSouthwest)
    {
        if(newSouthwest < -1)
            System.out.println("That is invalid");
        else    
            southwest = newSouthwest;
    } 

    public void setNorthwest (int newNorthwest)
    {
        if(newNorthwest < -1)
            System.out.println("That is invalid");
        else    
            northwest = newNorthwest;
    } 

    public void setUp (int newUp)
    {
        if(newUp < -1)
            System.out.println("That is invalid");
        else    
            up = newUp;
    } 

    public void setDown (int newDown)
    {
        if(newDown < -1)
            System.out.println("That is an invalid method");
        else    
            down = newDown;
    } 

    public void setDescription (String newDescription)
    {
        description = newDescription;
    } 

    public int getNorth()
    {
        return north;
    }  

    public int getSouth()
    {
        return south;
    }

    public int getEast()
    {
        return east;
    }

    public int getWest()
    {
        return west;
    }

    public int getNortheast()
    {
        return northeast;
    }

    public int getSoutheast()    
    {
        return southeast;
    }

    public int getSouthwest()
    {
        return southwest;
    }

    public int getNorthwest()
    {
        return northwest;
    }

    public int getUp()
    {
        return up;
    }

    public int getDown()
    {
        return down;
    }   

    public String getdescription()
    {
        return description;
    }    

    public ArrayList<String> getAreaItems()  
    {
        return areaItems;      
    }

    //other
    public void addItem(String newItem)
    {
        if (newItem == null)
            System.out.println("Error!  Trying to add null to an area!");
        else if (newItem.equals(""))
            System.out.println("Error!  Trying to add a blank String to an area!");
        else
            areaItems.add(newItem);
    }

    public boolean removeItem(String oldItem)
    {
        int location = areaItems.indexOf(oldItem);
        
        if(location >= 0)
        {
            areaItems.remove(location);
            return true;
        }
        else
            return false;
    }        

    public void displayArea()
    {
        System.out.println("\n" + description);
        if(north == NO_EXIT && south == NO_EXIT && east == NO_EXIT && west == NO_EXIT && 
           northwest == NO_EXIT && northeast == NO_EXIT && southeast == NO_EXIT && 
           southwest == NO_EXIT && up == NO_EXIT && down == NO_EXIT)
        {
            System.out.println("\nYou cannot go anywhere from here. There are no available exits.");
            System.out.println();
        }    
        else
        {
            System.out.println("\nFrom here you can go: ");

            if(north != NO_EXIT)
            {
                System.out.print("North ");
            }

            if(south != NO_EXIT)
            {
                System.out.print("South ");
            }

            if(east != NO_EXIT)
            {
                System.out.print("East ");
            }

            if(west != NO_EXIT)
            {
                System.out.print("West ");
            }

            if(northeast != NO_EXIT)
            {
                System.out.print("Northeast ");
            }

            if(southeast != NO_EXIT)
            {
                System.out.print("Southeast ");
            }

            if(southwest != NO_EXIT)
            {
                System.out.print("Southwest ");
            }

            if(northwest != NO_EXIT)
            {
                System.out.print("Northwest ");
            }

            if(up != NO_EXIT)
            {
                System.out.print("Up ");
            }

            if(down != NO_EXIT)
            {
                System.out.print("Down ");
            }

            System.out.println("\n");
        } 

        if (areaItems.size() > 0)
        {
            System.out.println("\nLaying on the ground you can see:");
            for (String item : areaItems)
                System.out.println(item);
        }
    }
} 
