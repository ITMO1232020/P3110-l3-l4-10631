public enum Planet {
    Earth("Земля", 100),
    Moon("Луна", 90);

    private final String name;
    private final int airQuality;

    Planet(String name, int airQuality) {
        this.name = name;
        this.airQuality = airQuality;
    }

    public int getAirQuality() {
        return airQuality;
    }

    public String getName() {
        return name;
    }
}