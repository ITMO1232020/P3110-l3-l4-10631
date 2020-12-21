import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Korotyshka implements Reacting{
    private AirStatus airStatus;
    private Status status;
    private final String name;
    private int caution;
    private String reason;
    private Location location;
    private boolean canFight;
    private final float height;
    private Korotyshka spy;
    private List<Clothes> clothes;
    private List<Thing> things;

    Korotyshka(String name, AirStatus airStatus, int caution, float height, Location location){
        this.name = name;
        setAirStatus(airStatus);
        this.status = Status.NO_STATUS;
        this.location = location;
        this.caution = caution;
        this.height = height;
        this.canFight = true;
        clothes = new ArrayList<>();
        things = new ArrayList<>();
    }

    public Location getLocation(){
        return this.location;
    }

    public String getName() {
        return name;
    }

    public int getCaution(){
        return caution;
    }

    public List<Thing> getThings() {
        return things;
    }

    public void setLocation(Location location) {
        for (int i = 0; i < things.size(); i++){
            things.get(i).setLocation(location);
        }

        for (int i = 0; i < clothes.size(); i++){
            clothes.get(i).setLocation(location);
        }
        this.location = location;
    }

    public void setCaution(int caution) {
        this.caution = caution;
    }

    public float getHeight() {
        return height;
    }

    public boolean isCanFight() {
        return canFight;
    }

    public void reactToVulnerability(){
        System.out.println(getName() + " продолжает следить");
    }

    public void setCanFight(boolean canFight){
        this.canFight = canFight;
        if (!canFight){
            if (getSpy() != null){
                spy.reactToVulnerability();
            }
        }
    }

    public Korotyshka getSpy() {
        return spy;
    }

    public void setSpy(Korotyshka spy) {
        this.spy = spy;
    }

    public List<Clothes> getClothes() {
        return clothes;
    }

    public Korotyshka getTracking(){
        return spy;
    }

    public void setAirStatus(AirStatus airStatus){
        if (this.airStatus == AirStatus.WANT_FRESH_AIR && airStatus == AirStatus.BREATH_FREELY){
            setStatus(Status.DELUDED);
            reason = "он долго не был на свежем воздухе";
        }
        this.airStatus = airStatus;
        if (airStatus == AirStatus.SUFFOCATE){
            throw new AirException();
        }
    }

    public AirStatus getAirStatus() {
        return airStatus;
    }

    public void setStatus(Status status){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        this.status = status;
        if (status == Status.CATCHED_ONSELF){
            System.out.println(getName() + " вовремя спохватился");
            setCanFight(true);
        }
        if (status == Status.IN_TRAP){
            cry();
            setCanFight(false);
        }
    }

    public Status getStatus() {
        return status;
    }

    public void printStatus(){
        System.out.println("У " + getName() + " " + status.getStatusName());
    }

    public void wear(Clothes ... clothes) throws ThingsException {
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        for (int i = 0; i < clothes.length; i++){
            if (this.things.indexOf(clothes[i]) == -1){
                throw new ThingsException("Перед тем, чтобы надеть какую-то вещь нужно её иметь");
            }
            else{
                this.things.remove(clothes[i]);
                this.clothes.add(clothes[i]);
                clothes[i].setLocation(this.getLocation());
                if (clothes[i].getClass() == SpecialClothes.class){
                    setAirStatus(AirStatus.WANT_FRESH_AIR);
                }
            }
        }
    }

    public void printClothes() {
        for (int i = 0; i < clothes.size(); i++) {
            System.out.println(getName() + " носит на " + clothes.get(i).getPart() + " " + clothes.get(i).getDescription() + " " + clothes.get(i).getName());
        }
    }

    public void check(){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        if (status == Status.DELUDED){
            System.out.println(getName() + " заблуждается, на самом деле это было не совсем так");
            printReason();
            caution = caution + 20;
            setStatus(Status.CHEKING);
        }
    }

    private void printReason(){
        if (reason != null){
            System.out.println("Это произошло из-за того, что " + reason);
            reason = null;
        }
        else {
            System.out.println(getName() + " не знает причину произошедшей ошибки");
        }
    }

    public void have(Thing ... thing) throws ThingsException {
        for (int i = 0; i < thing.length; i++){
            if(things.indexOf(thing[i]) == -1){
                this.things.add(thing[i]);
                thing[i].setLocation(this.getLocation());
            }
            else {
                throw new ThingsException("У коротышки уже есть эта вещь!");
            }
        }
    }

    public void feel(String feeling){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        System.out.println(getName() + " почувствовал, что " + feeling);
    }

    public void cry(){
        System.out.println(getName() + " закричал от боли!");
    }

    public void cry(Korotyshka korotyshka){
        System.out.println(getName() + " закричал на " + korotyshka.getName());
        korotyshka.listen();
    }

    public void listen(){
        System.out.println(getName() + " услыша крик и испугался!");
    }

    public void reactToSpott(){
        System.out.println(getName() + " был замечен. Он не сможет неожиданно напасть на того, за кем следил");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Korotyshka that = (Korotyshka) o;
        return caution == that.caution &&
                canFight == that.canFight &&
                Float.compare(that.height, height) == 0 &&
                airStatus == that.airStatus &&
                status == that.status &&
                Objects.equals(name, that.name) &&
                Objects.equals(reason, that.reason) &&
                location == that.location &&
                Objects.equals(spy, that.spy) &&
                Objects.equals(clothes, that.clothes) &&
                Objects.equals(things, that.things);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airStatus, status, name, caution, reason, location, canFight, height, spy, clothes, things);
    }

    @Override
    public String toString() {
        return "Korotyshka{" +
                "airStatus=" + airStatus +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", caution=" + caution +
                ", reason='" + reason + '\'' +
                ", location=" + location +
                ", canFight=" + canFight +
                ", height=" + height +
                ", spy=" + spy +
                ", clothes=" + clothes +
                ", things=" + things +
                '}';
    }
}