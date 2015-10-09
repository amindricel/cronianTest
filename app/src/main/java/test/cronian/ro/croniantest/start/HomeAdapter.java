package test.cronian.ro.croniantest.start;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import test.cronian.ro.croniantest.R;

/**
 * Created by Alex on 9/25/2015.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<Integer> data_set;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_text;

        public ViewHolder(View rv) {
            super(rv);
            item_text=(TextView) rv.findViewById(R.id.fibonacci_text_item);

        }
    }

    public HomeAdapter(List<Integer> data_set) {
        this.data_set = data_set;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_text.setText(data_set.get(position) + "");
    }

    @Override
    public int getItemCount() {
        return data_set.size();
    }
}
