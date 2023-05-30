import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Woman woman = new Woman("Maria", "Smith", 59, null);
        Man man = new Man("Peter", "Black", 65, null);
        System.out.println("Is retired: " + woman.isRetired() + " " + woman.getFirstName());
        System.out.println("Is retired: " + man.isRetired() + " " + man.getFirstName());
        woman.registerPartnership(man);
        System.out.println("Partner's name " + man.getPartner().getFirstName());
        System.out.println("New last name " + woman.getLastName());
        TimeUnit.SECONDS.sleep(7);
        woman.statusPartners();
        woman.deregisterPartnership(true);
        System.out.println("Previous last name " + woman.getLastName());
        woman.statusPartners();

    }
}
