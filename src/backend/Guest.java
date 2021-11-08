package backend;

//23 Oct 2021
/**
 *
 * @author cen7
 *
 */
public class Guest {

    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
    // add ID for guest

    /**
     * @param lastName
     * @param firstName
     * @param email
     * @param phoneNumber
     */
    public Guest(String lastName, String firstName, String email, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
//        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
//        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Guest))
            return false;

        Guest other = (Guest) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
//        if (firstName == null) {
//            if (other.firstName != null)
//                return false;
//        } else if (!firstName.equals(other.firstName))
//            return false;
//        if (lastName == null) {
//            if (other.lastName != null)
//                return false;
//        } else if (!lastName.equals(other.lastName))
//            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Nume:" + lastName + " " + firstName + ", Email:" + email + ", Telefon:" + phoneNumber + "\n";
    }

}
