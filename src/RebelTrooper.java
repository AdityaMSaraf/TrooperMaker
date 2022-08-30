public class RebelTrooper extends Trooper{
    private String name = "Rebel";
    private static int soldierCount = 0;

    public RebelTrooper(String unit, int number, String name){
        super(unit,number);
        soldierCount++;
        trooperKind = "pilot";
        marchModifier = 0.75;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getSoldierCount() {
        return soldierCount;
    }

    @Override
    public String toString(){
        return this.name + "("+super.toString() + ") a " + this.trooperKind;
    }

    @Override
    public double march(double duration) {
        return marchModifier * duration * marchSpeed;
    }
}
