package version.java15;

import version.java15.sealed.BigBoss;
import version.java15.sealed.SealedPerson;

public class PatternMatchingInstanceofBinding {

    public static void main(String[] args) {

        SealedPerson person = new BigBoss("VIP");
        if (person instanceof BigBoss boss && boss.getName().equals("VIP")) { // we can use boss here
            System.out.println(boss); // we can use boss here
        }

        if (!(person instanceof BigBoss boss)) {
            //System.out.println(boss); // Cannot resolve symbol 'boss'
        }else {
            System.out.println(boss); // we can use boss here
        }
    }
}
