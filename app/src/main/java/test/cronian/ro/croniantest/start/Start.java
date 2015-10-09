package test.cronian.ro.croniantest.start;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import test.cronian.ro.croniantest.R;
import test.cronian.ro.croniantest.base.BaseActivity;
import test.cronian.ro.croniantest.data.DAO;
import test.cronian.ro.croniantest.data.DataSource;

/**
 * Created by Alex on 9/25/2015.
 */
public class Start extends BaseActivity {
    private final String TAG = "Start";
    private List<Integer> fibonaciList;
    private RelativeLayout fragment_container;
    public DAO dao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, HomeFragment.getInstace()).commit();

        dao = new DAO(new DataSource());


    }
}
