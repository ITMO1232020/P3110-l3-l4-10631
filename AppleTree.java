import java.util.List;

public class AppleTree extends Tree{
    private final Brunch brunch;

    AppleTree(Location location, int height){
        super("Яблоня", location, "карликовое дерево", "куст", height);
        brunch = new Brunch();
    }

    public void makeFetuses(String form, String taste, boolean isTasty,  int number){
        for (int i = 0; i < number; i++){
            Fetus f = new Fetus("яблоко", this.getLocation(), form, taste, isTasty);
            brunch.addFetuses(f);

        }
        setFetuses(brunch.getFetuses());
    }

    public List<Fetus> getFetuses(){
        return super.getFetuses();
    }

    public String getNameOfFetus() {
        if (brunch.getFetuses().size() > -1) {
            return brunch.getFetuses().get(0).getName();
        } else {
            return null;
        }
    }
}