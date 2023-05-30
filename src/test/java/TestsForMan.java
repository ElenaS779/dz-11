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
    private Woman woman;


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeTest
    public void Before() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    void resetStreams() {
        outContent.reset();
        errContent.reset();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterTest
    public void After() {
        System.setOut(originalOut);
        System.setErr(originalErr);
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
        man = new Man ("Peter", "Black", 60, null);
        man.statusPartners();
        Assert.assertEquals(outContent.toString(),"This partner have not been married at all\n");
    }

    @Test
    public void statusPartnersMarried(){
        resetStreams();
        man = new Man ("Peter", "Black", 60, null);
        woman = new Woman ("Maria", "Smith", 59, null);
        man.registerPartnership(woman);
        man.statusPartners();
        Assert.assertTrue(outContent.toString().startsWith("This partner is married\n"));
    }

    @Test
    public void statusPartnersDivorced(){
        resetStreams();
        man = new Man ("Peter", "Black", 60, null);
        woman = new Woman ("Maria", "Smith", 59, null);
        man.registerPartnership(woman);
        man.deregisterPartnership(true);
        man.statusPartners();
        Assert.assertTrue(outContent.toString().startsWith("This partner has been divorced\n"));
    }
}
