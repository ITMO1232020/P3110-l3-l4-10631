public class Fix extends Korotyshka implements Fighting{
    private static Fix fix;
    private Korotyshka target;
    private final Hands hands;
    private Bush hidePlace;

    private Fix(){
        super("Фикс", AirStatus.BREATH_FREELY, 90, 160, Location.Bushes);
        this.hands = new Hands();
    }

    public static Fix get(){
        if (fix == null){
            fix = new Fix();
        }
        return fix;
    }

    public class Hands{
        private Thing thing;
        public void keepInHands(Thing thing){
            this.thing = thing;
            System.out.println("В руках у " + Fix.this.getName() + " находится " + thing.getName());
        }

        public Thing getThing() {
            return thing;
        }
    }

    public void setTarget(Korotyshka target) {
        this.target = target;
    }

    public void follow(Korotyshka korotyshka){
        class Eyes{
            private final String description;

            Eyes(String description){
                this.description = description;
            }

            public void printGetTarget(){
                System.out.println("Принадлежащие " + Fix.this.getName() + " " + description + " глаза следят за " + target.getName() + " из-за " + hidePlace.getName());
            }
        }
        if (hidePlace.isCanBePlaceForHide()){
            setTarget(korotyshka);
            Eyes eyes = new Eyes("внимательные");
            eyes.printGetTarget();
            korotyshka.setSpy(this);
            setStatus(Status.FOLLOW);
        }
        else {
            System.out.println(getName() + " следовало бы спрятаться");
        }
    }

    public void reactToVulnerability(){
        bankTarget(target);
    }

    public void reactToSpott(){
        System.out.println(getName() + " был замечен. Он не сможет неожиданно напасть на " + target.getName());
        setCanFight(false);
    }

    public void bankTarget(Korotyshka korotyshka){
        if (!isCanFight()){
            return;
        }
        if (hands.getThing() != null){
            if (hands.getThing().isCanBeWeapon()){
                System.out.println(getName() + " ударил " + korotyshka.getName() + " " + hands.getThing().getName() + " по " + PartsOfBody.Head.getName());
            }
        }
        else {
            System.out.println(getName() + " стукнул его голыми руками!");
        }
        setStatus(Status.FIGHT);
        korotyshka.setStatus(Status.FEEL_PAIN);
    }

    public void listen(){
        if(getStatus() == Status.FIGHT){
            System.out.println(getName() + " не услышал его");
        }
        else {
            System.out.println(getName() + " услышал его, но ничего не предпринял");
        }
    }

    public void hide(Bush bush){
            hidePlace = bush;
    }

    public void holdLikeAWeapon(Thing thing) throws ThingsException{
        if(getThings().indexOf(thing) == -1){
            throw new ThingsException("Коротышка не может держать то, чего у него нет");
        }
        hands.keepInHands(thing);
        if (thing.isCanBeWeapon()){
            System.out.println(getName() + " держит этот предмет наперевес, словно оружие");
        }
        else {
            System.out.println(getName() + " держит эту вещь как оружие, и это очень смешно!");
        }
    }
}
