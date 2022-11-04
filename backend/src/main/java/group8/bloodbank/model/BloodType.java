package group8.bloodbank.model; /***********************************************************************
 * Module:  BloodType.java
 * Author:  david
 * Purpose: Defines the Class BloodType
 ***********************************************************************/

public enum BloodType {
    A_POSITIVE("Apos"),
    A_NEGATIVE("Aneg"),
    O_NEGATIVE("Opos"),
    O_POSITIVE("Oneg"),
    B_POSITIVE("Bpos"),
    B_NEGATIVE("Bneg"),
    AB_POSITIVE("ABpos"),
    AB_NEGATIVE("ABneg")

    ;

    private final String label;

    private BloodType(String label) { this.label = label; }

    @Override
    public String toString() { return label; }

    public static BloodType valueOfLabel(String label) {
        for (BloodType e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}