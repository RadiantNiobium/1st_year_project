import java.util.Scanner;

public class InventoryManagementSystem {
    //create scanner to get user inout through the program
    public static Scanner userInput = new Scanner(System.in);

    //create variable to track how many items have been added to the inventory
    public static int count = 0;

    public static void main(String[] args) {
        //variable to end program
        boolean isRunning = true;

        //array to store item names
        String [] itemName;

        //array to store each item cost
        double [] itemCost;

        //array to store item selling price info
        double [] itemSellingPrice;

        //array to store index numbers
        String [] serialNumber;

        //obtain array length from user
        System.out.println("******** Welcome to the Inventory & Profit Management system ********");
        System.out.println(" ");
        System.out.print("Please enter the number of items in your inventory: ");
        //System.out.println(" ");
        int numberOfItems = userInput.nextInt();

        //assign array lengths to user input
        itemName = new String [numberOfItems];
        itemCost = new double [numberOfItems];
        itemSellingPrice = new double [numberOfItems];
        serialNumber = new String [numberOfItems];

        //enter main program loop
        while(isRunning){
            System.out.println("------------------------------------------------------------------------");
            System.out.println("    Inventory & Profit management system: what do you wish to do(1-5)?");
            if (count > 0){
                System.out.println("    NOTE: You have "+(itemName.length-count)+" inventory space(s) left.");
            }
            System.out.println(" 1. Add item to inventory.");
            System.out.println(" 2. Display inventory.");
            System.out.println(" 3. Calculate percentage profit on sale.");
            System.out.println(" 4. Search for item by serial number.");
            System.out.println(" 5. Exit program.");
            System.out.println(" ");
            System.out.print("Number: ");

            int choice = userInput.nextInt();

            switch (choice) {
                case 1 ->
                    //add item to inventory
                        addItem(itemName, itemCost, itemSellingPrice, serialNumber);
                case 2 ->
                    //display inventory
                        displayInventory(itemName, itemCost, itemSellingPrice, serialNumber);
                case 3 ->
                    //calculate percentage profit on sale
                    calculateProfit(itemName,itemCost,itemSellingPrice);

                case 4 ->
                       //Search item using serialNumber
                        searchItem(itemName, itemCost, itemSellingPrice, serialNumber);
                case 5 ->
                       //Exit the program
                        isRunning = false;
                default -> System.out.println("Invalid entry, pick between 1 and 5");
            }

        }
    }

    //Methods used are listed below.

    //Method for Case1. add items
    public static void addItem (String[]itemName, double[]itemCost, double[]itemSellingPrice, String[]serialNumber){
        System.out.println("------------------------------------------------------------------------");
        if (count >= itemName.length) {
            System.out.println("Your inventory is already full.");
            System.out.println(" ");
        }
        else{
            serialNumber[count] = ((count+1)+"");
            System.out.print("Enter the name of item number "+serialNumber[count]+": ");
            itemName[count] = userInput.next().toUpperCase();

            System.out.print("Cost price?  ");
            itemCost[count] = userInput.nextDouble();

            System.out.print("Selling price?  ");
            itemSellingPrice[count] = userInput.nextDouble();

            count++;

            System.out.println(" ");
            System.out.println("Item added successfully.");
        }
    }

    //Method for Case2. display items
    public static void displayInventory (String[]itemName, double[]itemCost, double[]itemSellingPrice, String[]serialNumber){
        System.out.println("------------------------------------------------------------------------");
        System.out.println("-----------------------------INVENTORY LIST-----------------------------");
        for (int i = 0; i < count; i++){
            serialNumber[i] = (i+1)+" ";
              System.out.print("Item name: "+itemName[i]+"               |           ");
            System.out.println("Cost price: D"+itemCost[i]);
              System.out.print("Item Serial number: "+serialNumber[i]+"            |           ");
            System.out.println("Selling price: D"+itemSellingPrice[i]);
            System.out.println("------------------------------------------------------------------------");
            System.out.println(" ");
//Item Serial number: "+serialNumber[i]

        }
    }

    //Method for Case3. calculate profit on sale
    public static void calculateProfit(String[]itemName, double[]itemCost, double[]itemSellingPrice){
        System.out.println("------------------------------------------------------------------------");
        System.out.print("Enter Item serial number(limit is "+(itemName.length)+"): ");
        int index = userInput.nextInt();

        if (index>count || index>itemName.length){
            System.out.println("Inventory space is empty");
        }
        else {
            System.out.print("How many pieces of " + itemName[(index - 1)] + " did you sell? ");
            int quantity = userInput.nextInt();
            double profit = (itemSellingPrice[index - 1] - itemCost[index - 1]) * quantity;
            double percentageProfit = ((itemSellingPrice[index - 1]-itemCost[index - 1])/itemCost[index - 1])*100;
            System.out.println("Total profit on this sale is: D"+profit);
            System.out.printf("You made a profit of %.2f",percentageProfit);
            System.out.println("% on each unit");
        }
        System.out.println(" ");
    }

    //Method for Case4. search for item using serial number
    public static void searchItem(String[]itemName, double[]itemCost, double[]itemSellingPrice, String[]serialNumber){
        System.out.println("------------------------------------------------------------------------");
        System.out.print("Enter the item serial number(limit is "+(itemName.length)+"): ");
        int serial = userInput.nextInt();
        if (serial>=0 && serial<(itemCost.length+1)){
            serial = serial-1;
            if (itemCost[serial]!= 0.0 && itemName[serial]!=null && itemSellingPrice[serial]!= 0.0 && serialNumber[serial]!=null){
                System.out.println("------------------------------------------------------------------------");
                System.out.println("----------------------------ITEM INFORMATION----------------------------");
                System.out.print("Item name: "+itemName[serial]+"               |           ");
                System.out.println("Cost price: D"+itemCost[serial]);
                System.out.print("Item Serial number: "+serialNumber[serial]+"            |           ");
                System.out.println("Selling price: D"+itemSellingPrice[serial]);
                System.out.println("------------------------------------------------------------------------");
                System.out.println(" ");
            }
            else {
                System.out.println("No item found");
            }
        }
        else{
            System.out.println("Invalid serial number");
        }
    }
}
