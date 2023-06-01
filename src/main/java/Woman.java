public class Woman extends Person {
    private String previousLastName;
    public Woman(String firstName, String lastName, int age, Person partner) {
        super(firstName, lastName, age, partner);
        previousLastName = lastName;
    }
    @Override
    public boolean isRetired() {
        return getAge() >= 60;
    }
    @Override
    public void registerPartnership(Person partner) {
        if (getPartner() != null)
            return;

        previousLastName = getLastName();
        setLastName(partner.getLastName());
        super.registerPartnership(partner);
    }
    @Override
    public void deregisterPartnership(boolean returnToPreLastName) {
        if (returnToPreLastName) {
            setLastName(previousLastName);
        }
        super.deregisterPartnership(returnToPreLastName);
    }
}
