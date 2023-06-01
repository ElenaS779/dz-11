import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestsForWoman {
    private Woman woman;
    final String WOMEN_FIRST_NAME = "Maria";
    final String WOMEN_LAST_NAME = "Smith";
    final int WOMAN_AGE = 59;
    private Man man;
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
        woman = new Woman (WOMEN_FIRST_NAME, WOMEN_LAST_NAME, 60, null);
        Assert.assertTrue(woman.isRetired());
    }
    @Test
    public void isRetiredFalse(){
        woman = new Woman (WOMEN_FIRST_NAME, WOMEN_LAST_NAME, 59, null);
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
        Woman woman = new Woman(WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        Man man = new Man("Peter", "Black", 65, null);
        woman.setPartner(man);
        woman.deregisterPartnership(true);
        Assert.assertNull(woman.getPartner());
    }
    @Test
    public void statusPartnersNotMarriedAtAll(){
        resetStreams();
        Woman woman = new Woman(WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        woman.statusPartners();
        Assert.assertEquals(outContent.toString(),"This partner have not been married at all\n");
    }

    @Test
    public void statusPartnersMarried(){
        resetStreams();
        man = new Man ("Peter", "Black", 60, null);
        woman = new Woman (WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        woman.registerPartnership(man);
        woman.statusPartners();
        Assert.assertTrue(outContent.toString().startsWith("This partner is married\n"));
    }

    @Test
    public void statusPartnersDivorced(){
        resetStreams();
        man = new Man ("Peter", "Black", 60, null);
        woman = new Woman (WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        woman.registerPartnership(man);
        woman.deregisterPartnership(true);
        woman.statusPartners();

        Assert.assertTrue(outContent.toString().startsWith("This partner has been divorced\n"));

    }
    @Test(groups = {"GettersTest"})
    public void getFirstName(){
        woman = new Woman(WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        Assert.assertEquals(woman.getFirstName(), WOMEN_FIRST_NAME, "A first name for a woman is not equal");
    }
    @Test(groups = {"GettersTest"})
    public void getLastName(){
        woman = new Woman(WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        Assert.assertEquals(woman.getLastName(), WOMEN_LAST_NAME, "A last name for a woman is not equal");
    }
    @Test(groups = {"GettersTest"})
    public void getAge(){
        woman = new Woman(WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        Assert.assertEquals(woman.getAge(), WOMAN_AGE, "The age is not equal for woman");
    }
    @Test(groups = {"GettersTest"})
    public void getPartner(){
        woman = new Woman(WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        Assert.assertNull(woman.getPartner(), "Woman has partner");
    }
    @Test(groups = {"SettersTest"})
    public void setFirstName(){
        woman = new Woman(WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        final String womanNewFirstName = "Clara";
        woman.setFirstName(womanNewFirstName);
        Assert.assertEquals(woman.getFirstName(), womanNewFirstName, "A first new name for a woman is not equal");
    }
    @Test(groups = {"SettersTest"})
    public void setLastName(){
        woman = new Woman(WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        final String womanNewLastName = "Armstrong";
        woman.setLastName(womanNewLastName);
        Assert.assertEquals(woman.getLastName(), womanNewLastName, "A last new name for a woman is not equal");
    }
    @Test(groups = {"SettersTest"})
    public void setAge(){
        woman = new Woman(WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        final int womanNewAge = 45;
        woman.setAge(womanNewAge);
        Assert.assertEquals(woman.getAge(), womanNewAge, "The new age is not equal for woman");
    }
    @Test(groups = {"SettersTest"})
    public void setPartner(){
        Woman woman = new Woman(WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        Man man = new Man("Peter", "Black", 65, null);
        woman.setPartner(man);
        Assert.assertEquals(woman.getPartner(),man,"problem in partner test for woman");
    }
}
