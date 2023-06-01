import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Person partner;
    private LocalDateTime startOfPartnership;
    private LocalDateTime endOfPartnership;

    public Person(String firstName, String lastName, int age, Person partner) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.partner = partner;
    }

    public abstract boolean isRetired();

    public void registerPartnership(Person partner) {
        if (this.partner != null)
            return;
        this.partner = partner;
        this.partner.registerPartnership(this);
        startOfPartnership = LocalDateTime.now();
    }

    public void deregisterPartnership(boolean returnToPreLastName) {
        if (this.partner == null)
            return;
        this.partner.setPartner(null);
        this.partner = null;
        endOfPartnership = LocalDateTime.now();
    }

    public void statusPartners() {
        if (partner == null && startOfPartnership == null) {
            System.out.println("This partner have not been married at all");
            return;
        }
        if (partner == null && startOfPartnership != null) {
            System.out.println("This partner has been divorced");
            Duration period = Duration.between(startOfPartnership, endOfPartnership);
            System.out.println("Was married " + period.toSeconds() + " seconds");
        }
        if (partner != null && startOfPartnership != null) {
            if (endOfPartnership == null) {
                System.out.println("This partner is married");
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd,MMMM,yyyy,HH:mm:ss");
                System.out.println("Wedding was in this day " + startOfPartnership.format(fmt));
                return;
            } else {
                System.out.println("This partner has been divorced");
                Duration period = Duration.between(startOfPartnership, endOfPartnership);
                System.out.println("Was married " + period.toSeconds() + " seconds");
            }
        }

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }
}

