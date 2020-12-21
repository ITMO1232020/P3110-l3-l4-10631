import java.util.ArrayList;
import java.util.List;

public abstract class Plant extends PhysicalBody{
    private final String form;
    private final String seemedForm;
    private final float height;
    private List<Fetus> fetuses;

    Plant(String name, Location location, String form, String seemedForm, float height){
        super(name, location);
        this.form = form;
        this.seemedForm = seemedForm;
        this.height = height;
        this.fetuses = new ArrayList<>();
    }

    public abstract void makeFetuses(String form, String taste, boolean isTasty,  int number);

    public static class Fetus extends Thing{
        private final String taste;
        private final String form;
        private final  boolean isTasty;

        Fetus(String name, Location location, String form, String taste, boolean isTasty){
            super(name, false, location);
            this.form = form;
            this.taste = taste;
            this.isTasty = isTasty;
        }

        public String getForm() {
            return form;
        }

        public String getTaste() {
            return taste;
        }

        public boolean getIsTasty() {
            return isTasty;
        }


    }

    public String getForm(){
        return form;
    }

    public String getSeemedForm(){
        return seemedForm;
    }

    public float getHeight() {
        return height;
    }

    public List<Fetus> getFetuses() {
        return fetuses;
    }

    public void setFetuses(List<Fetus> fetuses) {
        this.fetuses = fetuses;
    }

    public void pluckFetus(Fetus fetus){
        fetuses.remove(fetus);
    }

}