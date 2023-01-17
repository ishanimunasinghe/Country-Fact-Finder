import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public double[] unemployment = new double[250];
    public double[] infantMortality = new double[250];
    public long[] population = new long[250];
    public long[] GDP = new long[250];
    public double[] birthRate = new double[250];

    public int[] rank = new int[250];
    public String[] country = new String[250];

    public int countryCount = 0; // tracks how many countries are in lists

    public static void main(String[] args) {
        Main fact = new Main();
        fact.userInput();
    }

    public void loadFiles(String countryChoice, int rateChoice) {
        countryCount = 0; // always reset count

        // read and import text file arrays
        try {
            // if unemployment list chosen, import only that list
            if (rateChoice == 1) {
                String filename = "Unemployment";
                Scanner infile = new Scanner(new FileInputStream(filename));
                while (infile.hasNext()) {
                    rank[countryCount] = infile.nextInt();
                    country[countryCount] = infile.next(); // reads country name
                    unemployment[countryCount] = infile.nextDouble(); // reads unemployment rat
                    countryCount += 1;
                }
                infile.close();
                findCountry(countryChoice, rateChoice);

                // if infant mortality list chose, import only that list
            } else if (rateChoice == 2) {
                String filename = "InfantMortality";
                Scanner infile = new Scanner(new FileInputStream(filename));
                while (infile.hasNext()) {
                    rank[countryCount] = infile.nextInt();
                    country[countryCount] = infile.next(); // reads country name
                    infantMortality[countryCount] = infile.nextDouble(); // reads infant mortality rate
                    countryCount += 1;
                }
                infile.close();
                findCountry(countryChoice, rateChoice);

                // if population list chose, import only that list
            } else if (rateChoice == 3) {
                String filename = "Population";
                Scanner infile = new Scanner(new FileInputStream(filename));
                while (infile.hasNext()) {
                    rank[countryCount] = infile.nextInt();
                    country[countryCount] = infile.next(); // reads country name
                    population[countryCount] = infile.nextLong(); // reads population rate
                    countryCount += 1;
                }
                infile.close();
                findCountry(countryChoice, rateChoice);

                // if GDP list chose, import only that list
            } else if (rateChoice == 4) {
                String filename = "GDP";
                Scanner infile = new Scanner(new FileInputStream(filename));
                while (infile.hasNext()) {
                    rank[countryCount] = infile.nextInt();
                    country[countryCount] = infile.next(); // reads country name
                    GDP[countryCount] = infile.nextLong(); // reads population rate
                    countryCount += 1;
                }
                infile.close();
                findCountry(countryChoice, rateChoice);

                // if birth rate chosen, import only that list
            } else if (rateChoice == 5) {
                String filename = "BirthRate";
                Scanner infile = new Scanner(new FileInputStream(filename));
                while (infile.hasNext()) {
                    rank[countryCount] = infile.nextInt();
                    country[countryCount] = infile.next(); // reads country name
                    birthRate[countryCount] = infile.nextDouble(); // reads population rate
                    countryCount += 1;
                }
                infile.close();
                findCountry(countryChoice, rateChoice);
            }

        } catch (IOException ex) {
            countryCount = -1;
            ex.printStackTrace();
        }
    }

    public void userInput() {
        int again; // used to continue or quit program

        //  greetings
        System.out.println("Welcome to CIA Fact Finder!");
        System.out.println("Here, you can learn about the unemployment, infant mortality, population, GDP and birth rates of any country you want!");
        do {
            // initialize or reset variables
            int infoChoice = 0; // used to choose between presenting data of a country or rankings

            String countryChoice = null; // holds the value of the country user chooses
            int rateChoice = 0; // holds value of the rate user chooses
            int rankNumber = 0; // holds number of top countries user wants to search for
            again = -1; // reset continue/quit value (set outside values 1 and 0)

            do {
                // ask user which country they want to get data about
                System.out.println('\n' + "Do you want to see the data of a country (enter '1') or see the rankings of countries (enter '2') on the lists above?");
                try { // ensure that user only enters integers
                    Scanner input1 = new Scanner(System.in);
                    infoChoice = input1.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter '1' or '2'");
                }
            } while (!(infoChoice == 1 || infoChoice == 2)); // the user can only 1 or 2

            if (infoChoice == 1) { // country data
                System.out.println("Enter a county you would like to learn about! (only use letters, no spaces, and include capital letters)");
                try {
                    Scanner input2 = new Scanner(System.in); // ask for user input
                    countryChoice = input2.nextLine(); // accept user input
                } catch (InputMismatchException e) {
                    System.out.println("Please your country enter using only letters");
                }

                do { // ask user which rate they want
                    System.out.println("Which rate do you want to learn about?");
                    System.out.println("1. Unemployment Rate" + "\n" + "2. Infant Mortality" + "\n" + "3. Population" + "\n" + "4. GDP" + "\n" + "5. Birth Rate");
                    try {
                        Scanner input3 = new Scanner(System.in); // ask for user input
                        rateChoice = input3.nextInt(); // accept user input
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter one of the above options (1, 2, 3, 4, or 5)");
                    }
                } while (!(rateChoice >= 1 && rateChoice <= 5)); // user must enter number between 1-5

                loadFiles(countryChoice, rateChoice); // find country and rate in files

            } else if (infoChoice == 2) { // country rankings
                do {
                    System.out.println("How many top rankings do you want? (ie. the top 25 rankings) - enter a number less than 250");
                    try { // ensures user only enters integers
                        Scanner input5 = new Scanner(System.in);
                        rankNumber = input5.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number between 1-250.");
                    }
                } while (rankNumber >= 250 || rankNumber < 1); // user cannot go beyond array values

                do {
                    System.out.println("Which list do you want rankings for?");
                    System.out.println("1. Unemployment Rate" + "\n" + "2. Infant Mortality" + "\n" + "3. Population" + "\n" + "4. GDP" + "\n" + "5. Birth Rate");
                    try { // ensures user only enters integers
                        Scanner input6 = new Scanner(System.in);
                        rateChoice = input6.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter one of the above options (1, 2, 3, 4, or 5)");
                    }
                } while (!(rateChoice >= 1 && rateChoice <= 5)); // user must enter number between 1-5

                countryChoice = null; // country is not searched in this option
                loadFiles(countryChoice, rateChoice); // load country and rate files

                rankNumber = rankNumber - 1; // the rank matches with the array
                if (rateChoice == 1 && rankNumber >= 217) {
                    System.out.println("There are only 217 countries on the unemployment list");
                } else if (rateChoice == 2 && rankNumber >= 225) {
                    System.out.println("There are only 225 countries on the infant mortality list");
                } else if (rateChoice == 3 && rankNumber >= 237) {
                    System.out.println("There are only 237 countries on the population list");
                } else if (rateChoice == 4 && rankNumber >= 230) {
                    System.out.println("There are only 230 countries on the GDP list");
                } else if (rateChoice == 5 && rankNumber >= 226) {
                    System.out.println("There are only 226 countries on the birth rate list");
                }
                printList(rateChoice, rankNumber); // print rankings
            } else if (infoChoice > 2 || infoChoice == 0) { // user can only input 1 or 2
                System.out.println("Please enter '1' or '2'.");
            }

            do {
                System.out.println("Enter '1' to make another search or enter '0' to quit");
                try { // ensures user only enters integers
                    Scanner input3 = new Scanner(System.in);
                    again = input3.nextInt();
                } catch (InputMismatchException e){
                    System.out.println("Please enter '1' or '0'");
                }
            } while (!(again == 1 || again == 0)); // user can only input these options

        } while (!(again == 0)); // repeat until user enters 0

        System.out.println("Thank you! Have a nice day:)");
    }

    public void findCountry(String countryChoice, int rateChoice) {
        // find country in chosen list
        for (int i = 0; i < country.length; i++) {
            if (countryChoice != null) { // only search if user inputted country
                if (rank[i] != 0) { // stop checking list when all values are checked
                    // search country array for user's country
                    if (country[i].equals(countryChoice)) { // display corresponding rate if country found
                        if (rateChoice == 1) {
                            System.out.println("Country Found! - The unemployment rate of " + countryChoice + " is " + unemployment[i]);
                        } else if (rateChoice == 2) {
                            System.out.println("Country Found! - The infant mortality rate of " + countryChoice + " is " + infantMortality[i]);
                        } else if (rateChoice == 3) {
                            System.out.println("Country Found! - The population of " + countryChoice + " is " + population[i]);
                        } else if (rateChoice == 4) {
                            System.out.println("Country Found! - The GDP of " + countryChoice + " is $" + GDP[i]);
                        } else if (rateChoice == 5) {
                            System.out.println("Country Found! - The birth rate of " + countryChoice + " is " + birthRate[i]);
                        }
                        break; // break the for loop if country is found in the list
                    } else if (rank[i] == countryCount) { // country is not on the list and whole list has been searched
                        System.out.println("Country not found: " + countryChoice + " is not on the list.");
                    }
                }
            }
        }
    }

    public void printList(int rateChoice, int rankNumber) {
        for (int i = 0; i < rank[rankNumber]; i++) {
            if (rank[i] != 0) { // stops printing list as soon as all values are printed
                if (rateChoice == 1) { // prints unemployment list
                    System.out.println("Rank: " + rank[i] + " || " + "Country: " + country[i] + " || " + "Unemployment Rate: " + unemployment[i]);
                } else if (rateChoice == 2) { // prints infant mortality list
                    System.out.println("Rank: " + rank[i] + " || " + "Country: " + country[i] + " || " + "Infant Mortality Rate: " + infantMortality[i]);
                } else if (rateChoice == 3) { // prints population list
                    System.out.println("Rank: " + rank[i] + " || " + "Country: " + country[i] + " || " + "Population Rate: " + population[i]);
                } else if (rateChoice == 4) { // prints GDP list
                    System.out.println("Rank: " + rank[i] + " || " + "Country: " + country[i] + " || " + "GDP: " + GDP[i]);
                } else if (rateChoice == 5) { // prints birth rate list
                    System.out.println("Rank: " + rank[i] + " || " + "Country: " + country[i] + " || " + "Birth Rate: " + birthRate[i]);
                }
            }
        }
    }
}