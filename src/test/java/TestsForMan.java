import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;


public class TestsForMan {
    private Man man;
    final String MAN_FIRST_NAME = "Peter";
    final String MAN_LAST_NAME = "Black";
    final int MAN_AGE = 65;
    private Woman woman;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    void resetStreams() {
        outContent.reset();
        errContent.reset();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

   @Test
    public void isRetiredTrue(){
       man = new Man ("Peter", "Black", 65, null);
       Assert.assertTrue(man.isRetired());
    }
    @Test
    public void isRetiredFalse(){
        man = new Man ("Peter", "Black", 60, null);
        Assert.assertFalse(man.isRetired());
    }
    @Test
    public void registerPartnership(){
        woman = new Woman ("Maria", "Smith", 59, null);
        man.registerPartnership(woman);
        Assert.assertEquals(man.getPartner(), woman);
    }
    @Test
    public void deregisterPartnership(){
        Man man = new Man("Peter", "Black", 65, null);
        Woman woman = new Woman("Maria", "Smith", 59, null);
        man.setPartner(woman);
        man.deregisterPartnership(true);
        Assert.assertNull(man.getPartner());
    }
    @Test
    public void statusPartnersNotMarriedAtAll(){
        resetStreams();
        man = new Man(MAN_FIRST_NAME, MAN_LAST_NAME, MAN_AGE, null);
        man.statusPartners();
        Assert.assertEquals(outContent.toString(),"This partner have not been married at all\n");
    }

    @Test
    public void statusPartnersMarried(){
        resetStreams();
        man = new Man(MAN_FIRST_NAME, MAN_LAST_NAME, MAN_AGE, null);
        woman = new Woman ("Maria", "Smith", 59, null);
        man.registerPartnership(woman);
        man.statusPartners();
        Assert.assertTrue(outContent.toString().startsWith("This partner is married\n"));
    }

    @Test
    public void statusPartnersDivorced(){
        resetStreams();
        man = new Man(MAN_FIRST_NAME, MAN_LAST_NAME, MAN_AGE, null);
        woman = new Woman ("Maria", "Smith", 59, null);
        man.registerPartnership(woman);
        man.deregisterPartnership(true);
        man.statusPartners();
        Assert.assertTrue(outContent.toString().startsWith("This partner has been divorced\n"));
    }
    @Test(groups = {"GettersTest"})
    public void getFirstName(){
        man = new Man(MAN_FIRST_NAME, MAN_LAST_NAME, MAN_AGE, null);
        Assert.assertEquals(man.getFirstName(), MAN_FIRST_NAME, "A first name for a man is not equal");
    }
    @Test(groups = {"GettersTest"})
    public void getLastName(){
        man = new Man(MAN_FIRST_NAME, MAN_LAST_NAME, MAN_AGE, null);
        Assert.assertEquals(man.getLastName(), MAN_LAST_NAME, "A last name for a man is not equal");
    }
    @Test(groups = {"GettersTest"})
    public void getAge(){
        man = new Man(MAN_FIRST_NAME, MAN_LAST_NAME, MAN_AGE, null);
        Assert.assertEquals(man.getAge(), MAN_AGE, "The age is not equal for man");
    }
    @Test(groups = {"GettersTest"})
    public void getPartner(){
        man = new Man(MAN_FIRST_NAME, MAN_LAST_NAME, MAN_AGE, null);
        Assert.assertNull(man.getPartner(), "Man has partner");
    }
    @Test(groups = {"SettersTest"})
    public void setFirstName(){
        man = new Man(MAN_FIRST_NAME, MAN_LAST_NAME, MAN_AGE, null);
        final String manFirstName = "Rob";
        man.setFirstName(manFirstName);
        Assert.assertEquals(man.getFirstName(), manFirstName, "A first new name for a man is not equal");
    }
    @Test(groups = {"SettersTest"})
    public void setLastName(){
        man = new Man(MAN_FIRST_NAME, MAN_LAST_NAME, MAN_AGE, null);
        final String manNewLastName = "Eilish";
        man.setLastName(manNewLastName);
        Assert.assertEquals(man.getLastName(), manNewLastName, "A last new name for a man is not equal");
    }
    @Test(groups = {"SettersTest"})
    public void setAge(){
        man = new Man(MAN_FIRST_NAME, MAN_LAST_NAME, MAN_AGE, null);
        final int manNewAge = 53;
        man.setAge(manNewAge);
        Assert.assertEquals(man.getAge(), manNewAge, "The new age is not equal for man");
    }
    @Test(groups = {"SettersTest"})
    public void setPartner(){
        Woman woman = new Woman("Maria", "Smith", 59, null);
        man = new Man(MAN_FIRST_NAME, MAN_LAST_NAME, MAN_AGE, null);
        man.setPartner(woman);
        Assert.assertEquals(man.getPartner(),woman,"problem in partner test for man");
    }
}
