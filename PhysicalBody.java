public abstract class PhysicalBody{
    private String name;
    private Location location;
    private Trap trap;

    PhysicalBody(String name, Location location){
        this.name = name;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        try{
            this.location.removeObjects(this);
            this.location = location;
            this.location.addObjects(this);
        }
        catch (ThingsException t){
            t.printStackTrace();
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrap(Trap trap){
        this.trap = trap;
    }

    public Trap getTrap() {
        return trap;
    }


}