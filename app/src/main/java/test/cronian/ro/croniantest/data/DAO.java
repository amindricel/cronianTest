package test.cronian.ro.croniantest.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 9/26/2015.
 */
public class DAO {
    private DataSource dataSource;

    public DAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Integer> getValues(int limit) {
        return dataSource.getFibonaciSequence(limit);
    }
}
