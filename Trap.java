import java.util.Objects;

public class Trap extends Thing{
    private PhysicalBody guardedOvject;
    Trap(String name, Location location, PhysicalBody guardedOvject){
        super(name, true, location);
        this.guardedOvject = guardedOvject;
        guardedOvject.setTrap(this);
    }

    public void close(Korotyshka korotyshka){
        System.out.println(korotyshka.getName() + " попал в капкан, который находился у " + guardedOvject.getName());
        korotyshka.setStatus(Status.IN_TRAP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trap trap = (Trap) o;
        return Objects.equals(guardedOvject, trap.guardedOvject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guardedOvject);
    }

    @Override
    public String toString() {
        return "Trap{" +
                "guardedOvject=" + guardedOvject +
                '}';
    }
}