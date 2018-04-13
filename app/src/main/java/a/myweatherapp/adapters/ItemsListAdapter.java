package a.myweatherapp.adapters;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a.myweatherapp.R;
import a.myweatherapp.adapters.viewholders.ItemViewHolder;
import a.myweatherapp.model.RoomItem;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<RoomItem> items = new ArrayList<>();

    public ItemsListAdapter() {}

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_rv_item, parent, false);
//        view.getHeight();
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        View v = holder.itemView;
        v.setSelected(position == 0);
        holder.setHolder(items.get(getItemCount() - position - 1));
        if (v.isSelected()) {
            v.setBackgroundColor(ResourcesCompat.getColor(v.getResources(), R.color.colorPrimary, null));
        } else {
            v.setBackgroundColor(ResourcesCompat.getColor(v.getResources(), R.color.colorPrimaryDark, null));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return getItemCount() - position - 1;
    }

    public void addItems(List<RoomItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    private void setItemSelected(View v) {
        ((TextView) v.findViewById(R.id.tvMain)).setTextSize(28f);
    }
}