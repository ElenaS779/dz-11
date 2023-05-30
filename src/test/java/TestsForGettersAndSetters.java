import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

public class TestsForGettersAndSetters {


    private Woman woman;
    final String WOMEN_FIRST_NAME = "Maria";
    final String WOMEN_LAST_NAME = "Smith";
    final int WOMAN_AGE = 59;
    private Man man;
    final String MAN_FIRST_NAME = "Peter";
    final String MAN_LAST_NAME = "Black";
    final int MAN_AGE = 65;

    @BeforeTest
    public void BeforeTestBlaBla() {
        woman = new Woman(WOMEN_FIRST_NAME, WOMEN_LAST_NAME, WOMAN_AGE, null);
        man = new Man(MAN_FIRST_NAME, MAN_LAST_NAME, MAN_AGE, null);
        //woman.setPartner(man);
    }
    @Test
    public void getFirstName(){
        Assert.assertEquals(woman.getFirstName(), WOMEN_FIRST_NAME, "A first name for a woman is not equal");
        Assert.assertEquals(man.getFirstName(), MAN_FIRST_NAME, "A first name for a man is not equal");
    }
    @Test
    public void getLastName(){
        Assert.assertEquals(woman.getLastName(), WOMEN_LAST_NAME, "A last name for a woman is not equal");
        Assert.assertEquals(man.getLastName(), MAN_LAST_NAME, "A last name for a man is not equal");
    }
    @Test
    public void getAge(){
        Assert.assertEquals(woman.getAge(), WOMAN_AGE, "The age is not equal for woman");
        Assert.assertEquals(man.getAge(), MAN_AGE, "The age is not equal for man");
    }
    @Test
    public void getPartner(){
        Assert.assertNull(woman.getPartner(), "Woman has partner");
        Assert.assertNull(man.getPartner(), "Man has partner");
    }
    @Test
    public void setFirstName(){
       final String womanNewFirstName = "Clara";
       woman.setFirstName(womanNewFirstName);
        Assert.assertEquals(woman.getFirstName(), womanNewFirstName, "A first new name for a woman is not equal");
        final String manFirstName = "Rob";
        man.setFirstName(manFirstName);
        Assert.assertEquals(man.getFirstName(), manFirstName, "A first new name for a man is not equal");

    }
    @Test
    public void setLastName(){
        final String womanNewLastName = "Armstrong";
        woman.setLastName(womanNewLastName);
        Assert.assertEquals(woman.getLastName(), womanNewLastName, "A last new name for a woman is not equal");
        final String manNewLastName = "Eilish";
        man.setLastName(manNewLastName);
        Assert.assertEquals(man.getLastName(), manNewLastName, "A last new name for a man is not equal");
    }
    @Test
    public void setAge(){
        final int womanNewAge = 45;
        woman.setAge(womanNewAge);
        Assert.assertEquals(woman.getAge(), womanNewAge, "The new age is not equal for woman");
        final int manNewAge = 53;
        man.setAge(manNewAge);
        Assert.assertEquals(man.getAge(), manNewAge, "The new age is not equal for man");

    }
    @Test
    public void setPartner(){
        Woman woman = new Woman("Maria", "Smith", 59, null);
        Man man = new Man("Peter", "Black", 65, null);
        woman.setPartner(man);
        Assert.assertEquals(woman.getPartner(),man,"problem in partner test for woman");
        man.setPartner(woman);
        Assert.assertEquals(man.getPartner(),woman,"problem in partner test for man");

    }
}
