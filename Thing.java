public abstract class Thing extends PhysicalBody {
    private final boolean canBeWeapon;
    Thing(String name, boolean canBeWeapon, Location location) {
        super(name, location);
        this.canBeWeapon = canBeWeapon;
    }

    public boolean isCanBeWeapon() {
        return canBeWeapon;
    }
}