public enum Status{
    NO_STATUS(""),
    FOLLOW("следит"),
    DELUDED("заблуждается"),
    CHEKING("проверяет"),
    CALM("на душе весело и легко"),
    CATCHED_ONSELF("спохватился"),
    HUNGRY("голоден"),
    EATING("кушает"),
    IN_TRAP("попал в ловушку"),
    FEEL_PAIN("чувствует боль"),
    FIGHT("дерется");


    private final String statusName;

    Status(String statusName){
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}