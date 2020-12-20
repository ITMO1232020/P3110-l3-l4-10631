import java.util.Objects;

public class Bush extends Plant{
    private final boolean canBePlaceForHide;
    Bush(String name, String form, String seemedForm, Location location, float height, boolean canBePlaceForHide){
        super(name, location, form, seemedForm, height);
        this.canBePlaceForHide = canBePlaceForHide;
    }

    public boolean isCanBePlaceForHide() {
        return canBePlaceForHide;
    }

    public void makeFetuses(String form, String taste, boolean isTasty, int number) {
        System.out.println(getName() + " не имеет при себе каких-то плодов");
    }

    @Override
    public String toString() {
        return "Bush{" +
                "canBePlaceForHide=" + canBePlaceForHide +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bush bush = (Bush) o;
        return canBePlaceForHide == bush.canBePlaceForHide;
    }

    @Override
    public int hashCode() {
        return Objects.hash(canBePlaceForHide);
    }
}