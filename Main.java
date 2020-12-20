public class Main {

    public static void main(String[] args) {
        SpecialClothes spaceSuite = new SpecialClothes("космический скафандр", Location.SmallTrees);
        Clothes blazer = new Clothes("пиджак", "рыжий, протертый на локтях", PartsOfBody.UpperBody, Location.Fence);
        Clothes cap = new Clothes("кепка", "нелепая засаленная рыжая ", PartsOfBody.Head, Location.Fence);
        Clothes pants = new Clothes("штаны", "обычно затыкаемые в сапоги", PartsOfBody.LowerBody, Location.Fence);
        Clothes sandals = new Clothes("сандалии", "одетые на босу ногу", PartsOfBody.Feet, Location.Fence);

        AppleTree appleTree = new AppleTree(Location.SmallTrees, 200);
        appleTree.makeFetuses("крошечное, величиной с горошину, яблоко", "кислое", false, 10);
        PearTree pearTree = new PearTree(Location.SmallTrees,300);
        pearTree.makeFetuses("груша", "безвкусная, к тому же очень терпкая должно быть, еще незрелая", false, 1);
        MoonRaspberry moonRaspberry1 = new MoonRaspberry(Location.Fence);
        moonRaspberry1.makeFetuses("красная крошечная ягода", "", true, 2);
        MoonRaspberry moonRaspberry2 = new MoonRaspberry(Location.Fence);
        moonRaspberry2.makeFetuses("красная крошечная ягода", "", true, 2);
        Bush bush = new Bush("куст", "куст", "куст", Location.Fence, 100, true);

        Broom broom = new Broom(Location.Fence, true);
        Trap trap = new Trap("", Location.Fence, moonRaspberry2);

        try {
            Neznaika.get().have(spaceSuite);
            Fix.get().have(blazer, cap, pants, sandals, broom);
            Neznaika.get().wear(spaceSuite);
            Fix.get().wear(blazer, cap, pants, sandals);
        }
        catch (ThingsException t){
            t.printStackTrace();
        }
        Neznaika.get().addKnownFetusesNames(appleTree.getNameOfFetus(), pearTree.getNameOfFetus());

        Fix.get().hide(bush);
        Fix.get().follow(Neznaika.get());

        DoingsWithAir neznaikaAir = () -> {
            if (Neznaika.get().getStatus() == Status.FEEL_PAIN){
                return;
            }
            if (Neznaika.get().getAirStatus() == AirStatus.BREATH_FREELY){
                Neznaika.get().makeCheast().breathCheast();
            }
            else {
                System.out.println("Он не может вздохнуть полной грудью, так как воздуха нехватает");
            }
        };
        Compiring neznaikaCompirings = new Compiring() {
            @Override
            public void compireHeightWithPlant(Plant... plants){
                if (Neznaika.get().getStatus() == Status.FEEL_PAIN){
                    return;
                }
                else if (plants.length == 1){
                    if (plants[0].getHeight() - Neznaika.get().getHeight() == 0){
                        System.out.println("Рост " + plants[0].getName() + " равен росту " + Neznaika.get().getName());
                    }
                    else if (plants[0].getHeight() - Neznaika.get().getHeight() > 0){
                        System.out.println("Рост " + plants[0].getName() + " больше в " + Math.round(howHigher(plants[0])) + " раза роста " + Neznaika.get().getName());
                    }
                    else {
                        System.out.println("Рост " + plants[0].getName() + " меньше в " + Math.round(howLower(plants[0])) + " раза роста " + Neznaika.get().getName());
                    }
                }
                else if (plants.length > 1){
                    boolean isOneHigher = false;
                    boolean isOneLower = false;
                    float k;
                    float max = -1;
                    float min = howHigher(plants[0]);
                    for (int i = 0; i < plants.length; i++) {
                        k = plants[i].getHeight() - Neznaika.get().getHeight();
                        if (k >= 0) {
                            isOneHigher = true;
                            if (howHigher(plants[i]) > max) {
                                max = howHigher(plants[i]);
                            }
                            if (howHigher(plants[i]) < min) {
                                min = howHigher(plants[i]);
                            }
                            if (isOneLower) {
                                break;
                            } else if (k < 0) {
                                isOneLower = true;
                                if (howLower(plants[i]) > max) {
                                    max = howLower(plants[i]);
                                }
                                if (howLower(plants[i]) < min) {
                                    max = howLower(plants[i]);
                                }
                                if (isOneHigher) {
                                    break;
                                }
                            }
                        }
                    }
                    if (isOneHigher && isOneLower){
                        System.out.println("Некоторые растения ниже роста " + Neznaika.get().getName() + ", а некоторые - выше");
                    }
                    if (isOneHigher && !isOneLower){
                        if(Math.round(max) == Math.round(min)){
                            System.out.println("Рост растений больше примерно в " + ((float)Math.round(max*2))/2  + " раза роста " + Neznaika.get().getName());
                        }
                        else {
                            System.out.println("Рост растений больше примерно в " + ((float)Math.round(min*2))/2 + "-" + ((float)Math.round(max*2))/2 + " раза роста " + Neznaika.get().getName());
                        }
                    }
                    if(!isOneHigher && isOneLower){
                        if(Math.round(max) == Math.round(min)){
                            System.out.println("Рост растений меньше примерно в " + ((float)Math.round(max*2))/2  + " раза роста " + Neznaika.get().getName());
                        }
                        else {
                            System.out.println("Рост растений меньше примерно в " + ((float)Math.round(max*2))/2 + "-" + ((float)Math.round(min*2))/2 + " раза роста " + Neznaika.get().getName());
                        }
                    }
                }
            }

            @Override
            public void compireQualityOfAir() {
                if (Neznaika.get().getStatus() == Status.FEEL_PAIN){
                    return;
                }
                if (Neznaika.get().getStatus() == Status.DELUDED){
                    System.out.println(Neznaika.get().getName() + " показалось следующее:");
                    if (Planet.Earth.getAirQuality() > Planet.Moon.getAirQuality()){
                        System.out.println("Качество воздуха на " + Planet.Moon.getName() + " лучше, чем на " + Planet.Earth.getName());
                    }
                    else if (Planet.Earth.getAirQuality() < Planet.Moon.getAirQuality()){
                        System.out.println("Качество воздуха на " + Planet.Moon.getName() + " хуже, чем на " + Planet.Earth.getName());
                    }
                    else {
                        System.out.println("Качество воздуха на " + Planet.Moon.getName() + " не такое, как на " + Planet.Earth.getName());
                    }
                }
                else {
                    if (Planet.Earth.getAirQuality() < Planet.Moon.getAirQuality()){
                        System.out.println("Качество воздуха на " + Planet.Moon.getName() + " лучше, чем на " + Planet.Earth.getName());
                    }
                    else if (Planet.Earth.getAirQuality() > Planet.Moon.getAirQuality()){
                        System.out.println("Качество воздуха на " + Planet.Moon.getName() + " хуже, чем на " + Planet.Earth.getName());
                    }
                    else {
                        System.out.println("Качество воздуха на " + Planet.Earth.getName() + " такое же, как на " + Planet.Moon.getName());
                    }
                }
            }

            @Override
            public float howLower(Plant plant) {
                return Neznaika.get().getHeight()/plant.getHeight();
            }

            @Override
            public float howHigher(Plant plant) {
                return plant.getHeight()/Neznaika.get().getHeight();
            }
        };

        Neznaika.get().thinkAboutClothes();
        neznaikaCompirings.compireQualityOfAir();
        Neznaika.get().check();
        neznaikaAir.breath();
        try{
            Neznaika.get().hideClothesUnderPlant(spaceSuite, appleTree);
        }
        catch (ThingsException t){
            t.printStackTrace();
        }
        Neznaika.get().lookAround();
        Neznaika.get().takeACloserLook();

        neznaikaCompirings.compireHeightWithPlant(appleTree, pearTree);
        Neznaika.get().inspectPlant(appleTree);
        Neznaika.get().pluckFetus(appleTree);
        Neznaika.get().splitOut();
        Neznaika.get().pluckFetus(pearTree);
        Neznaika.get().throwAway();

        Neznaika.get().setStatus(Status.HUNGRY);
        Neznaika.get().find("чем бы поживиться", Location.Fence);
        Neznaika.get().inspectPlant(moonRaspberry1);
        Neznaika.get().pluckFetus(moonRaspberry1);
        Fix.get().printClothes();
        try{
            Fix.get().holdLikeAWeapon(broom);
        }
        catch (ThingsException t){
            t.printStackTrace();
        }
        Neznaika.get().noticeSurveillance();
        Neznaika.get().pluckFetus(moonRaspberry2);
    }
}
