import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import services.Singleton;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletonTest {
    Singleton unique1, unique2;

    @Test
    @DisplayName("Singletons sind identisch")
    public void identischeSingletons(){
        unique1 = Singleton.getInstance();
        unique2 = Singleton.getInstance();
        assertSame(unique1, unique2);
    }

    @RepeatedTest(value=10, name="Singleton ist thread-safe")
    public void synchronisierteSingletons(){
        Thread t1 = new Thread(){
            public void run(){
                unique1 = Singleton.getInstance();
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                unique2 = Singleton.getInstance();
            }
        };
        t1.start();
        t2.start();
        assertSame(unique1, unique2);
    }
}
