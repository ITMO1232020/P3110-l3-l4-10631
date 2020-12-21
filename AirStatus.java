public enum AirStatus{
    SUFFOCATE("задыхается"),
    WANT_FRESH_AIR("отвык от свежего воздуха"),
    BREATH_FREELY("дышит свободно");

    private final String statusName;

    AirStatus(String statusName){
        this.statusName = statusName;
    }
}