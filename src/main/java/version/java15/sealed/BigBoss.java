package version.java15.sealed;

public class BigBoss extends NonSealedBoss {

    private String name;

    public String getName() {
        return name;
    }

    public BigBoss(String name) {
        this.name = name;
    }
}
