import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestsForWoman {
    private Woman woman;
    private Man man;
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
        woman = new Woman ("Maria", "Smith", 60, null);
        Assert.assertTrue(woman.isRetired());
    }
    @Test
    public void isRetiredFalse(){
        woman = new Woman ("Maria", "Smith", 59, null);
        Assert.assertFalse(woman.isRetired());
    }
    @Test
    public void registerPartnership(){
        man = new Man ("Peter", "Black", 60, null);
        woman.registerPartnership(man);
        Assert.assertEquals(woman.getPartner(), man);
    }
    @Test
    public void deregisterPartnership(){
        Woman woman = new Woman("Maria", "Smith", 59, null);
        Man man = new Man("Peter", "Black", 65, null);
        woman.setPartner(man);
        woman.deregisterPartnership(true);
        Assert.assertNull(woman.getPartner());
    }
    @Test
    public void statusPartnersNotMarriedAtAll(){
        resetStreams();
        Woman woman = new Woman("Maria", "Smith", 59, null);
        woman.statusPartners();
        Assert.assertEquals(outContent.toString(),"This partner have not been married at all\n");
    }

    @Test
    public void statusPartnersMarried(){
        resetStreams();
        man = new Man ("Peter", "Black", 60, null);
        woman = new Woman ("Maria", "Smith", 59, null);
        woman.registerPartnership(man);
        woman.statusPartners();
        Assert.assertTrue(outContent.toString().startsWith("This partner is married\n"));
    }

    @Test
    public void statusPartnersDivorced(){
        resetStreams();
        man = new Man ("Peter", "Black", 60, null);
        woman = new Woman ("Maria", "Smith", 59, null);
        woman.registerPartnership(man);
        woman.deregisterPartnership(true);
        woman.statusPartners();

        Assert.assertTrue(outContent.toString().startsWith("This partner has been divorced\n"));
    }
}
