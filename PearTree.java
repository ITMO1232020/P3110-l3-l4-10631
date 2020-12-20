import java.util.List;

public class PearTree extends Tree{
    private Brunch brunch;

    PearTree(Location location, int height){
        super("Груша", location, "карликовое дерево", "куст", height);
        brunch = new Brunch();
    }

    public void makeFetuses(String form, String taste, boolean isTasty,  int number){
        for (int i = 0; i < number; i++){
            Fetus f = new Fetus("груша", this.getLocation(), form, taste, isTasty);
            brunch.addFetuses(f);
        }
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