package com.example.usuario.pr6_profile.ui.cat_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.pr6_profile.R;
import com.example.usuario.pr6_profile.base.model.Avatar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jannu on 4/11/17.
 */

public class CatListAdapter extends BaseAdapter {
    private final List<Avatar> mData;
    private final LayoutInflater mLayoutInflater;

    public CatListAdapter(Context context, @NonNull ArrayList<Avatar> data) {
        mData = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.cat_list_item, parent, false);
            viewHolder = onCreateViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(viewHolder, position);
        return convertView;
    }

    private ViewHolder onCreateViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }


    static class ViewHolder {
        @BindView(R.id.cat_List_imgCat)
        ImageView catListImgCat;
        @BindView(R.id.cat_List_lblName)
        TextView catListLblName;
        @BindView(R.id.cat_List_lblMail)
        TextView catListLblMail;
        @BindView(R.id.cat_List_lblPhone)
        TextView catListLblPhone;
        @BindView(R.id.cat_List_txtWeb)
        TextView catListTxtWeb;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }


        public void bind(final Avatar avatar) {
            catListImgCat.setImageResource(avatar.getCatTag());
            catListLblName.setText(avatar.getName());
            catListLblMail.setText(avatar.getEmail());
            catListLblPhone.setText(avatar.getPhone());
            catListTxtWeb.setText(avatar.getWeb());
        }

    }

}
