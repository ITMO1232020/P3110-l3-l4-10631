import java.util.List;
import java.util.Objects;

public abstract class Tree extends Plant {
    private final Brunch brunch;

    Tree(String name, Location location, String form, String seemedForm, int height) {
        super(name, location, form, seemedForm, height);
        this.brunch = new Brunch();
        super.setFetuses(brunch.fetusesInBrunch);
    }

    public class Brunch extends PhysicalBody {
        private List<Fetus> fetusesInBrunch;

        Brunch() {
            super("ветка", Tree.this.getLocation());
            this.fetusesInBrunch = Tree.this.getFetuses();
        }

        public void addFetuses(Fetus fetus) {
                this.fetusesInBrunch.add(fetus);
        }

        public void removeFetusses(Fetus... fetuses) {
            for (int i = 0; i < fetuses.length; i++) {
                this.fetusesInBrunch.remove(fetuses[i]);
            }
        }

        public void printHowManyFetuses() {
            if (this.fetusesInBrunch.size() == 0) {
                System.out.println("На ветке " + Tree.this.getName() + " не было никаких плодов");
            } else if (this.fetusesInBrunch.size() == 1) {
                System.out.println("На ветке " + Tree.this.getName() + " находилось только одно " + fetusesInBrunch.get(0).getForm());
            } else {
                System.out.println("Ветка " + Tree.this.getName() + " была осыпана плодами: " + fetusesInBrunch.get(0).getForm());
            }
        }

        public List<Fetus> getFetuses() {
            return fetusesInBrunch;
        }
    }

    public Brunch getBrunch() {
        return brunch;
    }

    public List<Fetus> getFetuses(){
        return super.getFetuses();
    }

    public abstract String getNameOfFetus();

    public void pluckFetus(Fetus fetus){
        brunch.removeFetusses(fetus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return Objects.equals(brunch, tree.brunch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brunch);
    }

    @Override
    public String toString() {
        return "Tree{" +
                "brunch=" + brunch +
                '}';
    }
}