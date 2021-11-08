package backend;

import java.util.ArrayList;
import java.util.List;

//23 Oct 2021
/**
 *
 * @author cen7
 *
 */
public class GuestsList {

    private Event event;
    private List<Guest> registeredGuests;
    private List<Guest> waitingGuests;

    public GuestsList(String eventName, int eventCapacity, String eventHost) {
        event = new Event(eventName, eventCapacity, eventHost);
        registeredGuests = new ArrayList<>();
        waitingGuests = new ArrayList<>();

    }

    public boolean isRegistered(Guest newGuest) {
        if (!registeredGuests.contains(newGuest))
            return false;
        return true;
    }

    public boolean isOnWaitingList(Guest newGuest) {
        if (!waitingGuests.contains(newGuest))
            return false;
        return true;
    }

    public int addGuest(Guest newGuest) {
        if (isRegistered(newGuest))
            return -1;
        if (isOnWaitingList(newGuest))
            return waitingGuests.indexOf(newGuest) + 1;

        if (registeredGuests.size() < event.getEventCapacity()) {
            registeredGuests.add(newGuest);
            System.out.printf("[%s %s] Felicitari! Locul tau la eveniment este confirmat. Te asteptam!%n",
                    newGuest.getLastName(), newGuest.getFirstName());
        } else {
            waitingGuests.add(newGuest);
            System.out.printf(
                    "Te-ai inscris cu succes in lista de asteptare si ai primit numarul "
                            + "de ordine %d. Te vom notifica daca un loc devine disponibil%n",
                    waitingGuests.indexOf(newGuest) + 1);
        }
        return 0;
    }

    public boolean isOnEventList(Guest newGuest) {
        return isRegistered(newGuest) || isOnWaitingList(newGuest);
    }

    public boolean removeGuest(Guest existingGuest) {
        if (isOnWaitingList(existingGuest)) {
            return waitingGuests.remove(existingGuest);
        }

        if (isRegistered(existingGuest)) {
            registeredGuests.remove(existingGuest);
            if (!waitingGuests.isEmpty()) {
                Guest movedGuest = waitingGuests.get(0);
                waitingGuests.remove(movedGuest);
                // maybe make null the removed elements
                registeredGuests.add(movedGuest);
                System.out.printf("%s este inscris la eveniment cu succes%n", movedGuest.getEmail());
            }
            return true;
        }

        return false;
    }

    //////////////// *****////////////////
    // update guest details ///////////////
    //////////////// ********/////////////////

    public void updateLastName(Guest guest, String newLastName) {
        if (registeredGuests.remove(guest)) {
            guest.setLastName(newLastName);
            registeredGuests.add(guest);
            System.out.println("updated last name in registered guests");
            return;
        }
        if (waitingGuests.remove(guest)) {
            guest.setLastName(newLastName);
            waitingGuests.add(guest);
            System.out.println("updated last name in waiting guests");
            return;
        }

        return;
    }

    public void updateFirstName(Guest guest, String newFirstName) {
        if (registeredGuests.remove(guest)) {
            guest.setFirstName(newFirstName);
            registeredGuests.add(guest);
            System.out.println("updated first name in registered guests");
            return;
        }
        if (waitingGuests.remove(guest)) {
            guest.setFirstName(newFirstName);
            waitingGuests.add(guest);
            System.out.println("updated first name in waiting guests");
            return;
        }

        return;
    }

    public void updateEmail(Guest guest, String email) {
        if (registeredGuests.remove(guest)) {
            guest.setEmail(email);
            registeredGuests.add(guest);
            System.out.println("updated email in registered guests");
            return;
        }
        if (waitingGuests.remove(guest)) {
            guest.setEmail(email);
            waitingGuests.add(guest);
            System.out.println("updated email in waiting guests");
            return;
        }

        return;
    }

    public void updatePhone(Guest guest, String phoneNumber) {
        if (registeredGuests.remove(guest)) {
            guest.setPhoneNumber(phoneNumber);
            registeredGuests.add(guest);
            System.out.println("updated phone in registered guests");
            return;
        }
        if (waitingGuests.remove(guest)) {
            guest.setPhoneNumber(phoneNumber);
            waitingGuests.add(guest);
            System.out.println("updated phone in waiting guests");
            return;
        }

        return;
    }

    public List<Guest> listOfGuests() {
        if(registeredGuests.isEmpty())
            System.out.println("Niciun participant inscris");
        return registeredGuests;
    }

    public List<Guest> listOfWaitingGuests() {
        if(waitingGuests.isEmpty())
            System.out.println("Lista de asteptare este goala...");
        return waitingGuests;
    }

    public int availableSeats() {
        int init = event.getEventCapacity();
        int act = registeredGuests.size();

        return init - act;
    }

    public int noOfGuests() {
        return registeredGuests.size();
    }

    public int noOfWaitingGuests() {
        return waitingGuests.size();
    }

    public int totalGuests() {
        return registeredGuests.size() + waitingGuests.size();
    }

    // searching by wild cards
    public String findGuests(String wildcard) {
        if (wildcard == null || wildcard == "")
            return null;

        wildcard = wildcard.toLowerCase();

        List<Guest> result = new ArrayList<>();

        for (Guest guest : registeredGuests) {
            if (guest.getLastName().toLowerCase().contains(wildcard)
                    || guest.getFirstName().toLowerCase().contains(wildcard)
                    || guest.getEmail().toLowerCase().contains(wildcard) || guest.getPhoneNumber().contains(wildcard)) {
                result.add(guest);
            }
        }

        return result.toString();
    }

}
