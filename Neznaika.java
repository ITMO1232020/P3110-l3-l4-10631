import java.util.ArrayList;
import java.util.List;

public class Neznaika extends Korotyshka implements  Eating, Exploring, MoreDoingWithClothes{
    private static Neznaika neznaika;
    private List<Location> pastLocations;
    private List<String> knownFetusesNames;

    private Neznaika(){
        super("Незнайка", AirStatus.WANT_FRESH_AIR, 10, 150, Location.SmallTrees);
        pastLocations = new ArrayList<>();
        knownFetusesNames = new ArrayList<>();
    }

    public static Neznaika get(){
        if (neznaika == null){
            neznaika = new Neznaika();
        }
        return neznaika;
    }

    public void setStatus(Status status){
        super.setStatus(status);
        if (status == Status.CALM){
            printStatus();
            wantToLaught();
        }
        if (status == Status.CATCHED_ONSELF){
            whatShouldDo();
        }
        if (status == Status.FEEL_PAIN){
            cry(getSpy());
        }
        if (status == Status.HUNGRY){
            appetiteIncreases();
        }
    }

    public class Chest{
        public void breathCheast(){
                System.out.println("Он вздохнул полной грудью");
                calmDown();
        }
        public void calmDown(){
            System.out.println("Его сердце забилось спокойнее");
            setStatus(Status.CALM);
        }
    }

    public Chest makeCheast(){
        Chest chest = new Chest();
        return chest;
    }

    public void wantToLaught(){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        System.out.println(getName() + " хочет засмеяться");
        if (getCaution() < 20){
            laught();
        }
        else {
            setStatus(Status.CATCHED_ONSELF);
        }
    }

    public void laught(){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        System.out.println(getName() + " засмеялся");
        setCanFight(false);
    }

    public void whatShouldDo(){
        boolean knowLocation = false;
        for (int i = 0; i < pastLocations.size(); i++){
            if (getLocation() == pastLocations.get(i)){
                knowLocation = true;
            }
        }
        if (!knowLocation){
            System.out.println(getName() + " не знает окружающую местность, поэтому ему следует осмотреться");
        }
        else if (getStatus() == Status.HUNGRY){
            System.out.println(getName() + " следует что-то поесть");
        }
        else if (getStatus() == Status.FEEL_PAIN){
            System.out.println(getName() + " осталось только кричать от боли!");
        }
        else {
            System.out.println("Не знает, что ему нужно сделать");
        }

        }

    public void lookAround(){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        if (getCaution() < 40 ){
            setStatus(Status.DELUDED);
        }
        System.out.println(getName() + " осматривает местность. Он увидел:");
        for (int i = 0; i < getLocation().getListObjects().size(); i++){
            if (getStatus() != Status.DELUDED) {
                if (getLocation().getListObjects().get(i) instanceof Plant) {
                    System.out.println(getName() + " увидел " + ((Plant) getLocation().getListObjects().get(i)).getForm());
                }
            }
            else {
                if (getLocation().getListObjects().get(i) instanceof Plant){
                    System.out.println(getName() + " увидел " + ((Plant)getLocation().getListObjects().get(i)).getSeemedForm());
                }
            }
        }
    }

    public void takeACloserLook(){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        System.out.println(getName() + " решил присмотреться внимательнее");
        if (getStatus() == Status.DELUDED){
            setStatus(Status.CHEKING);
            setCaution(getCaution()+20);
        }
        lookAround();
    }

    public void inspectPlant(Plant plant){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        if(plant.getFetuses().size() == 0){
            System.out.println(plant.getName() + " не имеет на себе плодов");
        }
        else {
            if (Tree.class.isAssignableFrom(plant.getClass())){
                ((Tree)plant).getBrunch().printHowManyFetuses();
            }
            else{
                System.out.println("На " + plant.getName() + " растут " + plant.getFetuses().get(0).getForm());
            }
        }
    }

    public void pluckFetus(Plant plant){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        if (plant.getTrap() == null){
            if (plant.getFetuses().size() > 0){
                System.out.println(getName() + " сорвал " + plant.getFetuses().get(0).getForm());
                getThings().add(plant.getFetuses().get(0));
                plant.getFetuses().get(0).setLocation(this.getLocation());
                tryFetus(plant.getFetuses().get(0));
                plant.pluckFetus(plant.getFetuses().get(0));
            }
        }
        else {
            plant.getTrap().close(this);
        }
    }

    public void addKnownFetusesNames(String ... names) {
        for (int i = 0; i < names.length; i++){
            knownFetusesNames.add(names[i]);
        }
    }

    public void appetiteIncreases(){
        System.out.println("У " + getName() + " только аппетит разыгрался");
        lastTimeEating();
    }

    public void lastTimeEating(){
        if (getStatus() == Status.HUNGRY){
            System.out.println("С тех пор, как " + getName() + " последний раз ел, прошло много времени");
        }
    }

    public void eatFetus(Plant.Fetus fetus){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        System.out.println(getName() + " ест " + fetus.getName());
        removeFromThings(fetus);
        fetus.setLocation(Location.None);
        setStatus(Status.EATING);
    }

    public void tryFetus(Plant.Fetus fetus) {
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        if (knownFetusesNames.indexOf(fetus.getName()) == -1) {
            System.out.println(getName() + " пробует " + fetus.getForm());
            makeSureFetus(fetus);
            knownFetusesNames.add(fetus.getName());
            if (fetus.getIsTasty()){
                eatFetus(fetus);
            }
            else {
                System.out.println("Это " + fetus.getName() + " оказалось " + fetus.getTaste() + "!");
                removeFromThings(fetus);
                fetus.setLocation(Location.None);
            }
        } else{
            if (fetus.getIsTasty()){
                eatFetus(fetus);
            }
            else {
                System.out.println(getName() + " попробовал " + fetus.getName() + ", а оно оказалось " + fetus.getTaste());
                removeFromThings(fetus);
                fetus.setLocation(Location.None);
            }
        }
    }

    public void makeSureFetus(Plant.Fetus fetus){
        System.out.println(getName() + " убедился, что это оказалась " + fetus.getName());
    }

    public void throwAway(){
        System.out.println(getName() + " отшвырнул этот плод!");
    }

    public void splitOut(){
        System.out.println(getName() + " выплюнул этот плод!");
    }

    public void hideClothesUnderPlant(Clothes clothes, Plant plant) throws ThingsException{
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        if (getThings().indexOf(clothes) > -1){
            System.out.println(getName() + " спрятал " + clothes.getName() + " под " + plant.getSeemedForm());
            this.getThings().remove(clothes);
        }
        else {
            throw new ThingsException("Он не может спрятать того, чего у него нет");
        }
    }

    public void removeFromThings(Thing thing){
        getThings().remove(thing);
    }

    public void thinkAboutClothes(){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        int index = -1;
        for (int i = 0; i < getClothes().size(); i++) {
            if (getClothes().get(i).getClass() == SpecialClothes.class) {
                index = i;
                break;
            }
        }
        if (getAirStatus() == AirStatus.WANT_FRESH_AIR){
            if(index > -1){
                comeToConclution("ему надо снять защитную одежду");
                removeClothing(getClothes().get(index));
            }
            else{
                comeToConclution("у него нет одежды, которую он мог бы снять");
            }
        }
        else {
            comeToConclution("ему не стоит снимать одежду");
        }
    }

    public void removeClothing(Clothes clothes){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        getClothes().remove(clothes);
        getThings().add(clothes);
        System.out.println(getName() + " снял с себя " + clothes.getName());
        if (clothes.getClass() == SpecialClothes.class) {
            if (getLocation().getPlanet().getAirQuality() < 25) {
                feel("задыхается от нехватки кислорода");
                setAirStatus(AirStatus.SUFFOCATE);
            } else if (getLocation().getPlanet().getAirQuality() < 50) {
                feel("ничего не изменилось");
                setAirStatus(AirStatus.WANT_FRESH_AIR);
            } else {
                feel("не только не задыхается, но даже может свободно дышать");
                setAirStatus(AirStatus.BREATH_FREELY);
            }
        }
    }

    public void go(Location location) {
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        if (location != Location.None){
            System.out.println(getName() + " делает несколько шагов и попадает в место, где находится " + location.getName());
            pastLocations.add(getLocation());
            setLocation(location);
            lookAround();
        }
    }

    public void find(String target, Location location) {
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        System.out.println(getName() + " ищет " + target);
            go(location);
    }

    public void comeToConclution(String s){
        if (getStatus() == Status.FEEL_PAIN){
            return;
        }
        System.out.println(getName() + " пришел к умозаключению, что " + s);
    }

    public void noticeSurveillance() {
        if (getCaution() > 50) {
            if (getTracking() != null) {
                System.out.println("За " + getName() + " следит " + getTracking().getName());
                System.out.println(getName() + " заметил шпиона!");
                getSpy().reactToSpott();
            } else {
                System.out.println("За " + getName() + " никто не следит");
            }
        } else {
            if (getTracking() != null){
                System.out.println("Если бы " + getName() + " был бы осторожнее, то он бы мог заметить, что за ним следят из-за " + getTracking().getLocation().getName() + " чьи-то глаза");
            }
            else {
                System.out.println(getName() + " слишком неосторожный, поэтому не может никого заметить");
            }
        }
    }

    }