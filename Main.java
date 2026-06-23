import java.util.Scanner;
import java.util.ArrayList;
void main() { //Name: Sean Fridman
    Scanner sc = new Scanner(System.in);
    boolean menu = true;
    int averageRating = 0;
    ArrayList<String> titleList = new ArrayList<>();
    titleList.add("Outrageous");
    titleList.add("Slave 4 U");
    titleList.add("Fortnight");
    titleList.add("Moon");
    titleList.add("Selfish");
    ArrayList<String> artistList = new ArrayList<>();
    artistList.add("Britney Spears");
    artistList.add("Britney Spears");
    artistList.add("Taylor Swift");
    artistList.add("Bjork");
    artistList.add("Britney Spears");
    ArrayList<Integer> ratingList = new ArrayList<>();
    ratingList.add(84);
    ratingList.add(96);
    ratingList.add(56);
    ratingList.add(74);
    ratingList.add(34);
    while (menu) {
        System.out.println("Select an action \n1. Add a song\n" + "2. Average rating of the library\n" +
                "3. Minimum and Maximum rated song\n" + "4. Display the list of songs\n" +
                "5. Display the Rating Distribution\n" + "6. Search a song\n" +
                "7. Display the top and bottom 20%\n" + "8. Exit the program");
        int action = sc.nextInt();
        switch (action) {
            case 1 -> { //Enter a Song
                boolean reenter = true;
                int i = 0;
                while (reenter && i < 2) {
                    System.out.println("Enter \nSong Title \nArtist \nRating");
                    titleList.add(sc.nextLine());
                    artistList.add(sc.nextLine());
                    sc.nextLine();
                    sc.nextInt();
                    i++;
                    if (i == 1) {
                        System.out.println("Do you want to enter another song? y/n");
                        sc.nextLine();
                        String reentry = sc.nextLine();
                            if (reentry.equalsIgnoreCase("n")) {
                                reenter = false;
                            }
                    }
                }
                System.out.println();
            }
            case 2 -> { //Calculate Average Rating
                for (Integer i : ratingList) {
                    averageRating += i;
                }
                averageRating /= ratingList.size();
                System.out.println("Average Rating: " + averageRating);
                System.out.println();
            }
            case 3 -> { //Maximum and Minimum rated songs
                int min = ratingList.getFirst();
                int max = ratingList.getFirst();
                for (int i = 1; i < ratingList.size(); i++) {
                    if (ratingList.get(i) > max) {
                        max = ratingList.get(i);
                    } else if (ratingList.get(i) < min) {
                        min = (ratingList.get(i));
                    }
                }
                System.out.println("Max: " + max + " Min: " + min);
                System.out.println();
            }
            case 4 -> { //Display Song List
                for (int i = 0; i < titleList.size(); i++) {
                    System.out.println((i + 1) + ": " + titleList.get(i) + " by " + artistList.get(i) + " (" + ratingList.get(i) + ")");
                }
                System.out.println();
                System.out.println("Would you like to \n1. Edit a song \n2. Remove a song \n3. Return to menu");
                int songDisplay = sc.nextInt();
                switch (songDisplay) {
                    case 1 -> { //Edit a song
                        System.out.println("Enter the song's ID");
                        int ID = sc.nextInt() - 1;
                        System.out.println("Enter the new information \nSong \nArtist \nRating");
                        titleList.set(ID, sc.nextLine());
                        sc.nextLine();
                        artistList.set(ID, sc.nextLine());
                        ratingList.set(ID, sc.nextInt());
                    }
                    case 2 -> { //delete a song
                        System.out.println("Enter the song's ID");
                        int ID = sc.nextInt() - 1;
                        titleList.remove(ID);
                        artistList.remove(ID);
                        ratingList.remove(ID);
                    }
                    case 3 -> System.out.println(); // Back to Lobby
                    default -> System.out.println("Error. Please enter a number from 1-3");
                }
            }
            case 5 -> { //Song Distribution
                int[] distribution = new int[5];
                for (Integer integer : ratingList) {
                    if (integer < 40) {
                        distribution[0]++;
                    } else if (integer < 60) {
                        distribution[1]++;
                    } else if (integer < 75) {
                        distribution[2]++;
                    } else if (integer < 90) {
                        distribution[3]++;
                    } else {
                        distribution[4]++;
                    }
                }
                int highestSet = 0;
                for (int i = 1; i < distribution.length; i++) {
                    if (distribution[i] > distribution[highestSet]) {
                        highestSet = i;
                    }
                }
                for (int i = distribution[highestSet]; i > 0; i--) {
                    for (int k : distribution) {
                        if (k >= i) {
                            System.out.print(" * ");
                        } else {
                            System.out.print("   ");
                        }
                    }
                }
                System.out.println();
                System.out.println("______________");
                System.out.println(" 1* 2* 3* 4* 5*");
                System.out.println();
            }
            case 6 -> { //Search a song
                System.out.println("Search for...");
                sc.nextLine();
                String search = sc.nextLine();
                boolean found = false;
                for (Integer i : ratingList) {
                    averageRating += i;
                }
                averageRating /= ratingList.size();
                for (int j = 0; j < titleList.size(); j++) {
                    if (titleList.get(j).toLowerCase().contains(search.toLowerCase()) || artistList.get(j).toLowerCase().contains(search.toLowerCase())) {
                        found = true;
                        if (ratingList.get(j) < averageRating) {
                            System.out.println(titleList.get(j) + " " + artistList.get(j) + " " + ratingList.get(j) + " (below average rating)");
                        }
                        if (ratingList.get(j) > averageRating) {
                            System.out.println(titleList.get(j) + " " + artistList.get(j) + " " + ratingList.get(j) + " (above average rating)");
                        }
                        if (ratingList.get(j) == averageRating) {
                            System.out.println(titleList.get(j) + " by " + artistList.get(j) + " " + ratingList.get(j) + " (average rating)");
                        }
                    }
                }
                if (!found) {
                    System.out.println(search + " Not found");
                }
                System.out.println();
            }
            case 7 -> {//Top and Bottom 20%
                int twentyPercent = (int) Math.ceil(titleList.size() * 0.2);
                if (twentyPercent == 0) {
                    System.out.println();
                }
                int[] high20 = new int[twentyPercent];
                int[] low20 = new int[twentyPercent];
                int size = 0;
                for (int i = 0; i < ratingList.size(); i++) {
                    if (size < twentyPercent) {
                        high20[size] = i;
                        low20[size] = i;
                        size++;
                    } else {
                        int minTop = 0;
                        int maxBottom = 0;
                        for (int j = 1; j < twentyPercent; j++) {
                            if (ratingList.get(high20[j]) < ratingList.get(high20[minTop])) {
                                minTop = j;
                            }
                            if (low20[j] > low20[maxBottom]) {
                                maxBottom = j;
                            }
                        }
                        if (ratingList.get(high20[minTop]) < ratingList.get(i)) {
                            high20[minTop] = i;
                        }
                        if (ratingList.get(low20[maxBottom]) > ratingList.get(i)) {
                            low20[maxBottom] = i;
                        }
                    }
                }
                System.out.println("Top 20%");
                for (int i = 0; i < size; i++) {
                    System.out.println(titleList.get(high20[i]) + " " + artistList.get(high20[i]) + " " + ratingList.get(high20[i]));
                }
                System.out.println();
                System.out.println("Bottom 20%");
                for (int i = 0; i < size; i++) {
                    System.out.println(titleList.get(low20[i]) + " " + artistList.get(low20[i]) + " " + ratingList.get(low20[i]));
                }
                System.out.println();
            }
            case 8 -> {
                System.out.println("Thank you for using the MLM!");
                menu = false;
            }
            default -> System.out.println("Error, please select a number from 1-8");
        }
    }
}