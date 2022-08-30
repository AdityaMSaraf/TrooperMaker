import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Trooper {

    private String unit;
    private int number;
    String trooperKind;
    double marchSpeed;
    double marchModifier;

    Trooper() {
        this("AA", 0);
    }

    public Trooper(String unit, int number) {
        this.unit = unit;
        this.number = number;
        this.marchSpeed = 5;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTrooperKind() {
        return trooperKind;
    }

    public void setTrooperKind(String trooperKind) {
        this.trooperKind = trooperKind;
    }

    public double getMarchSpeed() {
        return marchSpeed;
    }

    public void setMarchSpeed(double marchSpeed) {
        this.marchSpeed = marchSpeed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trooper trooper = (Trooper) o;

        if (number != trooper.number) return false;
        if (Double.compare(trooper.marchSpeed, marchSpeed) != 0) return false;
        if (Double.compare(trooper.marchModifier, marchModifier) != 0) return false;
        if (!Objects.equals(unit, trooper.unit)) return false;
        return Objects.equals(trooperKind, trooper.trooperKind);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = unit != null ? unit.hashCode() : 0;
        result = 31 * result + number;
        result = 31 * result + (trooperKind != null ? trooperKind.hashCode() : 0);
        temp = Double.doubleToLongBits(marchSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(marchModifier);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return unit + number + ":";
    }

    public static void addToUnit(HashMap<String, List<Trooper>> units, Trooper t) {
        if (t == null) {
            return;
        }
        if (!units.containsKey(t.getUnit())) {
            List<Trooper> list = new LinkedList<>();
            list.add(t);
            units.put(t.getUnit(), list);
        } else {
            units.get(t.getUnit()).add(t);
        }
    }


    public abstract double march(double duration);

    public boolean attack(Trooper target, int roll) {
        if (this.equals(target)) {
            System.out.println(this.toString() + " is targeting itself.");
            return true;
        }
        if (roll == 1) {
            System.out.println(this.toString() + " rolled a  " + roll + " and hurt itself in the confusion");
            return true;
        }
        if (this.getTrooperKind() == "StormTrooper") {
            if (target.getTrooperKind() == "pilot") {
                System.out.println(this.toString() + " rolled a " + roll + " against the rebel scum");
                if (roll > 10 && roll % 2 == 0) {
                    return true;
                }
            } else if (target.getTrooperKind() == "StormTrooper") {
                System.out.println("No treason in the ranks!");
                return false;
            } else {
                System.out.println("Acceptable collateral damage!");
                if (roll > 10 || roll % 2 == 0) {
                    return true;
                }
            }
        }

        if (this.getTrooperKind() == "pilot") {
            if (target.getTrooperKind() == "pilot") {
                System.out.println("Imperial Spy!");
                return false;
            } else if (target.getTrooperKind() == "StormTrooper") {
                System.out.println(this.toString() + " rolled a " + roll + " against the imperial scum");
                if (roll > 5 || roll % 2 == 1) {
                    return true;
                } else {
                    System.out.println("Rebels target an innocent bystander");
                    if (roll >= 18 && roll % 2 == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
