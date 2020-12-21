import java.util.ArrayList;
import java.util.List;

public enum  Location {
    SmallTrees("карликовые деревья", Planet.Moon),
    Fence("высокий дощатый забор", Planet.Moon),
    Bushes("куст", Planet.Moon),
    None("", Planet.Moon);

    private final String name;
    private final Planet planet;
    private List<PhysicalBody> objects;

    Location(String name, Planet planet){
        this.name = name;
        this.planet = planet;
        objects = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public Planet getPlanet() {
        return this.planet;
    }

    public void addObjects(PhysicalBody object){
        objects.add(object);
    }

    public void removeObjects(PhysicalBody object) throws ThingsException{
        if (objects.size() >= 0){
            objects.remove(object);
        }
        else {
            throw new ThingsException();
        }
    }

    public List<PhysicalBody> getListObjects(){
        return objects;
    }
}