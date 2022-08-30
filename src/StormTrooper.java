public class StormTrooper extends Trooper{
    private  String name = "";
    private static int soldierCount = 0;

    public StormTrooper(String unit, int number){
        super(unit,number);
        soldierCount++;
        trooperKind = "StormTrooper";
        marchModifier = 1.10;
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
