package runner;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import backend.Guest;
import backend.GuestsList;

//23 Oct 2021
/**
 *
 * @author cen7
 *
 */
public class Main {
    private static Scanner kb = new Scanner(System.in);;

    private static final String ADD = "-Adauga o noua persoana (inscriere)";
    private static final String CHECK = "-Verifica daca o persoana este inscrisa la eveniment";
    private static final String REMOVE = "-Sterge o persoana existenta din lista";
    private static final String UPDATE = "-Actualizeaza detaliile unei persoane";
    private static final String GUESTS = "-Lista de persoane care participa la eveniment";
    private static final String WAITLIST = "-Persoanele din lista de asteptare";
    private static final String AVAILABLE = "-Numarul de locuri libere";
    private static final String GUEST_NO = "-Numarul de persoane care participa la eveniment";
    private static final String WAITLIST_NO = "-Numarul de persoane din lista de asteptare";
    private static final String SUBSCRIBE_NO = "-Numarul total de persoane inscrise";
    private static final String SEARCH = "-Cauta toti invitatii conform sirului de caractere introdus";
    private static final String QUIT = "-Inchide aplicatia";
    private static final String FORMAT = "%-14s%s%n";

    private static GuestsList gl;

    private static void printMenu() {
        System.out.printf(FORMAT, "add", ADD);
        System.out.printf(FORMAT, "check", CHECK);
        System.out.printf(FORMAT, "remove", REMOVE);
        System.out.printf(FORMAT, "update", UPDATE);
        System.out.printf(FORMAT, "guests", GUESTS);
        System.out.printf(FORMAT, "waitlist", WAITLIST);
        System.out.printf(FORMAT, "available", AVAILABLE);
        System.out.printf(FORMAT, "guests_no", GUEST_NO);
        System.out.printf(FORMAT, "waitlist_no", WAITLIST_NO);
        System.out.printf(FORMAT, "subscribe_no", SUBSCRIBE_NO);
        System.out.printf(FORMAT, "search", SEARCH);
        System.out.printf(FORMAT, "quit", QUIT);
    }

    private static boolean add() {
        System.out.printf("%nLast Name: ");
        String ln = kb.next();
        System.out.printf("%nFirst Name: ");
        String fn = kb.next();

        System.out.printf("%nEmail address: ");
        String email = kb.next();
        while (!validEmail(email)) {
            System.out.println("Invalid email\nEmail address: ");
            email = kb.next();
        }

        System.out.printf("%nPhone Number: ");
        String phone = kb.next();
        while (!validPhoneNumber(phone)) {
            System.out.println("Invalid phone\nPhone Number: ");
            phone = kb.next();
        }

        gl.addGuest(new Guest(ln, fn, email, phone));
        return true;
    }

    private static boolean validEmail(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        // Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        // Create instance of matcher
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    private static boolean validPhoneNumber(String phone) {
        for (Character c : phone.toCharArray()) {
            if (Character.isLetter(c))
                return false;
        }
        return true;
    }

    private static Guest check() {
        int i = 3;
        System.out.println("Search...");
        System.out.println("Guest email: ");
        String email = kb.next();
        while (!validEmail(email) && i > 0) {
            i--;
            System.out.println("Invalid email\nEmail address: ");
            email = kb.next();
        }
        i = 3;
        System.out.printf("%nPhone Number: ");
        String phone = kb.next();
        while (!validPhoneNumber(phone) && i > 0) {
            i--;
            System.out.println("Invalid phone\nPhone Number: ");
            phone = kb.next();
        }

        Guest g = new Guest("", "", email, phone);

        if (gl.isRegistered(g)) {
            System.out.println("Guest is already registered");
            // return the guest that has the email and phone match (+first name and last
            // name)
            return gl.listOfGuests().get(gl.listOfGuests().indexOf(g));

        }
        if (gl.isOnWaitingList(g)) {
            System.out.println("Guest is on waiting list");
            // return the guest that has the email and phone match (+first name and last
            // name)
            return gl.listOfWaitingGuests().get(gl.listOfWaitingGuests().indexOf(g));
        }
        return null;

    }

    private static boolean remove() {

        Guest g = check();
        if (g != null) {
            System.out.println("Se sterge o persoana existenta din lista:");
            gl.removeGuest(g);
            System.out.println("Stergerea s-a realizat cu succes");
            return true;
        }
        return false;
    }

    private static void guests() {
        int i = 1;
        for (Guest g : gl.listOfGuests())
            System.out.println(i++ + ". " + g);
    }

    private static void waitingGuests() {
        int i = 1;
        for (Guest g : gl.listOfWaitingGuests())
            System.out.println(i++ + ". " + g);

    }

    private static void availableSeats() {
        System.out.printf("Numarul de locuri ramase: %d%n", gl.availableSeats());
    }

    private static void guestNo() {

        System.out.printf("Numarul de participanti: %d%n", gl.noOfGuests());

    }

    private static void waitlistNo() {
        System.out.printf("Dimensiunea listei de asteptare: %d%n", gl.noOfWaitingGuests());
    }

    private static void subscribeNo() {
        System.out.printf("Numarul total de persoane: %d%n", gl.totalGuests());
    }

    private static void search() {
        System.out.println("Insert the some characters to display matching results:");
        System.out.println(gl.findGuests(kb.next()));
    }

    private static void update() {
        Guest g = check();
        System.out.println("What information would like to update?");
        System.out.printf("1. lastname\n2. firstname\n3. email\n4. phone number\n");
        int s = kb.nextInt();
        while (s < 1 || s > 4) {
            System.out.printf("1. lastname\n2. firstname\n3. email\n4. phone number\n");
            s = kb.nextInt();
        }
        switch (s) {
        case 1:
            System.out.println("Insert new last name:");
            gl.updateLastName(g, kb.next());
            break;
        case 2:
            System.out.println("Insert new first name:");
            gl.updateFirstName(g, kb.next());
            break;
        case 3:
            System.out.println("Insert new email:");
            String em = kb.next();
            while (!validEmail(em)) {
                System.out.println("invalid email\nTry again:");
                em = kb.next();
            }
            gl.updateEmail(g, em);
            break;

        default:
            System.out.println("Insert phone:");
            String ph = kb.next();
            while (!validPhoneNumber(ph)) {
                System.out.println("invalid phone Number\nTry again:");
                ph = kb.next();
            }
            gl.updatePhone(g, ph);
            break;

        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        gl = new GuestsList(args[0], Integer.valueOf(args[1]), args[2]);

        String select = "";
        select = kb.next();
        if (select.equalsIgnoreCase("help")) {

            while (!select.equalsIgnoreCase("quit")) {
                printMenu();
                select = kb.next();
                select = select.toLowerCase();

                switch (select) {
                case "add":
                    add();
                    break;
                case "check":
                    check();
                    break;
                case "remove":
                    remove();
                    break;
                case "update":
                    update();
                    break;
                case "guests":
                    guests();
                    break;
                case "waitlist":
                    waitingGuests();
                    break;
                case "available":
                    availableSeats();
                    break;
                case "guests_no":
                    guestNo();
                    break;
                case "waitlist_no":
                    waitlistNo();
                    break;
                case "subscribe_no":
                    subscribeNo();
                    break;
                case "search":
                    search();
                    break;
                default:

                }
            }

        }

        kb.close();

    }

}
