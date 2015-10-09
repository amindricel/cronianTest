package test.cronian.ro.croniantest.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 9/25/2015.
 */
public class DataSource {

    private int a, b, c, d;

    public List<Integer> getFibonaciSequence(int limit) {
        List<Integer> fibonacci_list = new ArrayList<>();
        a = 0;
        b = 1;
        while (b < limit) {
            fibonacci_list.add(a);
            fibonacci_list.add(b);

            c = a + b;
            d = b + c;
            a = c;
            b = d;

        }

        return fibonacci_list;
    }
}
