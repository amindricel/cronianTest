package test.cronian.ro.croniantest.start;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.sax.StartElementListener;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import test.cronian.ro.croniantest.R;
import test.cronian.ro.croniantest.base.BaseActivity;
import test.cronian.ro.croniantest.base.BaseFragment;
import test.cronian.ro.croniantest.util.Animations;
import test.cronian.ro.croniantest.util.UIUtil;

/**
 * Created by Alex on 9/25/2015.
 */
public class HomeFragment extends BaseFragment {

    private List<Integer> fibonacciList;
    private final String TAG = "HomeFragment";
    private int fibonaci_limit;
    private EditText limit_input;
    private RecyclerView fibonacci_recycler;
    private HomeAdapter fibonacci_adapter;
    private Context context;
    private int lastRecyclerVisiblePosition;


    public static HomeFragment getInstace() {
        HomeFragment home = new HomeFragment();
        return home;
    }

    @Override
    public void onResume() {
        super.onResume();

        fibonaci_limit = (int) ((Start) getActivity()).getValueFormPreferences("fibonaci_limit", BaseActivity.GET_INT);
        fibonacciList = ((Start) getActivity()).dao.getValues(fibonaci_limit);

        if (((int) ((Start) getActivity()).getValueFormPreferences("fibonaci_limit", BaseActivity.GET_INT)) != 0) {
            limit_input.setText((int) ((Start) getActivity()).getValueFormPreferences("fibonaci_limit", BaseActivity.GET_INT) + "");
            limit_input.setSelection(limit_input.getText().length());
        }

        Log.d(TAG, "last visible position: " + lastRecyclerVisiblePosition);
        lastRecyclerVisiblePosition = (int) ((Start) getActivity()).getValueFormPreferences("last_visible_position", BaseActivity.GET_INT);
        ((LinearLayoutManager) fibonacci_recycler.getLayoutManager()).scrollToPosition(lastRecyclerVisiblePosition);

        if (fibonaci_limit > 0)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animations.customTranslateAnimationOnY(limit_input, 0, -getResources().getDimension(R.dimen.translate_distance), 300, View.VISIBLE);
                    Animations.customTranslateAnimationOnY(fibonacci_recycler, 0, -getResources().getDimension(R.dimen.translate_distance), 400, View.VISIBLE);
                }
            }, 400);

        fibonacciList = ((Start) getActivity()).dao.getValues(fibonaci_limit);
        fibonacci_adapter = new HomeAdapter(fibonacciList);
        fibonacci_recycler.setAdapter(fibonacci_adapter);

        for (Integer i : fibonacciList) {
            Log.d(TAG, i + "");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "on pause called");
        Log.d(TAG, "limit input hint: " + limit_input.getHint().toString());
        lastRecyclerVisiblePosition = ((LinearLayoutManager) fibonacci_recycler.getLayoutManager()).findFirstVisibleItemPosition();
        ((Start) getActivity()).saveToSharedPreferences(BaseActivity.SAVE_INT, "fibonaci_limit", fibonaci_limit);
        ((Start) getActivity()).saveToSharedPreferences(BaseActivity.SAVE_INT, "last_visible_position", lastRecyclerVisiblePosition);
        Log.d(TAG, "last visible position: " + lastRecyclerVisiblePosition);
        try {
            ((Start) getActivity()).saveToSharedPreferences(BaseActivity.SAVE_INT, "fibonacci_input", Integer.parseInt(limit_input.getText().toString()));

            if (limit_input.getHint().toString().toLowerCase().equals(getResources().getString(R.string.enter_limit).toLowerCase()))
                ((Start) getActivity()).saveToSharedPreferences(BaseActivity.SAVE_INT, "fibonacci_input", 0);
        } catch (NumberFormatException e) {
            Log.e(TAG, "not a number");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home, null);
        limit_input = (EditText) v.findViewById(R.id.fibonaci_limit);
        context = getActivity();
        fibonacci_recycler = (RecyclerView) v.findViewById(R.id.fibonacci_recycler);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fibonacci_recycler.setLayoutManager(layoutManager);
        fibonacciList = ((Start) getActivity()).dao.getValues(fibonaci_limit);
        limit_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {
                    fibonaci_limit = Integer.parseInt(limit_input.getText().toString());
                    if (s.length() == 1 && fibonacci_recycler.getVisibility() == View.INVISIBLE) {
                        Animations.customTranslateAnimationOnY(limit_input, 0, -200, 300, View.VISIBLE);
                        Animations.customTranslateAnimationOnY(fibonacci_recycler, 0, -200, 400, View.VISIBLE);
                    }
                } else if (s.length() < 1) {
                    fibonaci_limit = 0;
                    Animations.customTranslateAnimationOnY(limit_input, -200, 0, 300, View.VISIBLE);
                    Animations.customTranslateAnimationOnY(fibonacci_recycler, 0, -200, 400, View.INVISIBLE);
                    limit_input.setHint(getResources().getString(R.string.enter_limit));
                }


                fibonacciList = ((Start) getActivity()).dao.getValues(fibonaci_limit);
                fibonacci_adapter = new HomeAdapter(fibonacciList);
                fibonacci_recycler.setAdapter(fibonacci_adapter);
                fibonacci_recycler.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        limit_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    UIUtil.hideSoftKeyboard(context, limit_input);
                    ((Start) getActivity()).saveToSharedPreferences(BaseActivity.SAVE_INT, "fibonaci_limit", fibonaci_limit);

                }

                return false;
            }
        });

        return v;
    }
}
