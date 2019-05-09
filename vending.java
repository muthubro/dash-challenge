import java.util.HashMap;
import java.util.Scanner;


public class VendingMachine {
    private static HashMap<String, Integer> drinks;
    private static Integer minRequired;

    protected static class Coin {
        protected Integer value;
        protected Integer num;

        public Coin(Integer value) {
            this.value = value;
        }
    
        public Integer getValue() {
            return value;
        }
    
        public void setNum(Integer num) {
            this.num = num;
        }
    
        public Integer getAmount() {
            return value * num;
        }
    }
    
    private static class One extends Coin {
        public One(Integer value) {
            super(value);
        }
    }
    
    private static class Five extends Coin {
        public Five(Integer value) {
            super(value);
        }
    }
    
    private static class Ten extends Coin {
        public Ten(Integer value) {
            super(value);
        }
    }
    
    private static class TwentyFive extends Coin {
        public TwentyFive(Integer value) {
            super(value);
        }
    }

    public static void vending() {
        java.util.Scanner scanner = new Scanner(System.in);

        One ones = new One(1);
        Five fives = new Five(5);
        Ten tens = new Ten(10);
        TwentyFive twentyFives = new TwentyFive(25);

        Integer num;

        System.out.println("Enter number of 1s: ");
        num = scanner.nextInt();
        ones.setNum(num);

        System.out.println("Enter number of 5s: ");
        num = scanner.nextInt();
        fives.setNum(num);

        System.out.println("Enter number of 10s: ");
        num = scanner.nextInt();
        tens.setNum(num);

        System.out.println("Enter number of 25s: ");
        num = scanner.nextInt();
        twentyFives.setNum(num);

        Integer amount = ones.getAmount() + fives.getAmount() + tens.getAmount() + twentyFives.getAmount();

        while (amount >= minRequired) {
            System.out.println("\nChoose an item (cash remaining: " + amount + ")");
            Integer counter = 1;
            drinks.forEach((k, v) -> {
                System.out.println(String.format("%s (%d)", k, v));
            });

            System.out.println("Exit");

            System.out.println("\nChoice: ");
            Integer choice;
            choice = scanner.nextInt();

            if (choice == drinks.size() + 1) return;

            Integer used = 0;  

            if (choice > drinks.size() + 1)
                System.out.println("Please enter a valid choice.\n");

            else {
                Object key = drinks.keySet().toArray()[choice-1];
                used = drinks.get(key);
            }

            amount -= used;
        }

        System.out.println("\nYou do not have enough balance to buy anything else.\nYour remaining balance is " + amount);
    }

    public static void addDrink() {
        System.out.println("Enter the name of the new drink: ");
        java.util.Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        if (drinks.containsKey(name)) System.out.println("This drink already exists.");
        
        else {
            System.out.println("Enter the cost of the new drink: ");
            Integer cost = scanner.nextInt();

            drinks.put(name, cost);
            System.out.println("Drink successfully added.");

            if (cost < minRequired) minRequired = cost;
        }
    }

    public static void main(String[] args) {
        drinks = new HashMap<String, Integer>();

        drinks.put("Coke", 25);
        drinks.put("Pepsi", 35);
        drinks.put("Soda", 45);

        minRequired = 25;

        System.out.println("Welcome to the Dash Vending Machine");

        Scanner scanner = new Scanner(System.in);

        Integer choice = 1;
        while (choice != 3) {
            System.out.println("\nWhat do you want to do?\n1. Access Vending Machine\n2. Add a drink\n3. Go away");
            System.out.println("\nYour choice: ");

            choice = scanner.nextInt();

            if (choice == 1) vending();

            else if (choice == 2) addDrink();
            
            else if (choice == 3) return;

            else System.out.println("Please choose either 1 or 2.\n");
        }
    }
}
