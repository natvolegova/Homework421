package com.example.homework421;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemsDataAdapter extends BaseAdapter {

    private List<ItemData> items;
    private LayoutInflater LayoutInflater;

    // Конструктор, в который передается контекст
    ItemsDataAdapter(Context context, List<ItemData> items) {
        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }
        LayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //добавление нового элемента в список активити
    void addItem(ItemData item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        if (i < items.size()) {
            return items.get(i);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        //если нет данных о view, то создаем новую вьюху из item_list_view, но не добавляем ее на экран, а только считываем параметры
        if (view == null) {
            view = LayoutInflater.inflate(R.layout.item_list_view, viewGroup, false);
        }
        //считываем текущий элемент
        ItemData itemData = items.get(i);
        //определили все элементы на view
        TextView title = view.findViewById(R.id.title);
        TextView subtitle = view.findViewById(R.id.subtitle);
        ImageView img_src = view.findViewById(R.id.image);
        CheckBox checkBox = view.findViewById(R.id.checkBox);
        //присвоили значения текущим элементам
        title.setText(itemData.getTitle());
        subtitle.setText(itemData.getSubtitle());
        img_src.setImageResource(itemData.getImg_srs());
        checkBox.setChecked(itemData.isChecked());
        return view;
    }
}
