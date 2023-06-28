package version.java9;

public interface InterfacePrivateMethods {

    private void instancePrivate(){
        System.out.println("Instance private");
    }

    private static void staticPrivate(){
        System.out.println("Static private");
    }

    default void defaultPublic(){
        instancePrivate();
        staticPrivate();
    }

    public static void main(String[] args) {
        new InterfacePrivateMethods(){}.defaultPublic();
    }
}
