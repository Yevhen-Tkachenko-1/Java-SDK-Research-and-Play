package research.math;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

public class BigDecimalTest {

    public static void main(String[] args) {

        System.out.println(BigDecimal.ZERO.toPlainString());
        System.out.println(BigDecimal.valueOf(0.0).toPlainString());
        System.out.println();
        System.out.println(BigDecimal.ONE.toPlainString());
        System.out.println(BigDecimal.valueOf(1).toPlainString());
        System.out.println(BigDecimal.valueOf(1.0).toPlainString());
        System.out.println();
        System.out.println(BigDecimal.valueOf(0.1).toPlainString());
        System.out.println(BigDecimal.valueOf(1.1).toPlainString());
        System.out.println(BigDecimal.valueOf(1.11111111111111).toPlainString());
    }
}
