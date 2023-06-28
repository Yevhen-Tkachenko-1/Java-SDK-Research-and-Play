package version.java9;

import java.io.Closeable;
import java.io.IOException;

public class TryWithResourcesNewSyntax {

    public static void main(String[] args) throws IOException {

        MyCloseable closeable = new MyCloseable();
        //closeable = new MyCloseable(); // Variable used as a try-with-resources resource should be final or effectively final
        System.out.println(closeable.isClosed());
        try(closeable){
            System.out.println(closeable.isClosed());
        }
        System.out.println(closeable.isClosed());
    }

    static class MyCloseable implements Closeable {

        boolean isClosed = false;

        public boolean isClosed() {
            return isClosed;
        }

        @Override
        public void close() {
            // close resource
            isClosed = true;
        }
    }
}
