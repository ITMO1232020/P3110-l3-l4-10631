import java.util.Objects;

public class Clothes extends Thing {
    private PartsOfBody part;
    private String description;

    Clothes(String name, String descriptipn, PartsOfBody part, Location location) {
        super(name, false, location);
        this.description = descriptipn;
        setPart(part);
    }

    public String getPart() {
        return part.getName();
    }

    public void setPart(PartsOfBody part) {
        this.part = part;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "part=" + part +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothes clothes = (Clothes) o;
        return part == clothes.part &&
                Objects.equals(description, clothes.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(part, description);
    }
}