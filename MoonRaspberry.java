public class MoonRaspberry extends Bush{
    MoonRaspberry(Location location){
        super("лунная малина", "колючий куст", "куст", location, 40, false);
    }

    public void makeFetuses(String form, String taste, boolean isTasty,  int number){
        for (int i = 0; i < number; i++){
            Fetus f = new Fetus("лунная карликовая малина", this.getLocation(), form, taste, isTasty);
            getFetuses().add(f);
        }
    }
}