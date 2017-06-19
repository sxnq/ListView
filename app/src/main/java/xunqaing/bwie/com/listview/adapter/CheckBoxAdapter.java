package xunqaing.bwie.com.listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import xunqaing.bwie.com.listview.R;
import xunqaing.bwie.com.listview.bean.CheckBean;

/**
 * Created by : Xunqiang
 * 2017/6/7 20:47
 */

public class CheckBoxAdapter extends BaseAdapter {

    public List<CheckBean> list;
    public Context context;
    public LayoutInflater inflater ;

    public CheckBoxAdapter(List<CheckBean> list, Context context){

        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.check_box_item,null);

            viewHolder.textView = (TextView) convertView.findViewById(R.id.checkbox_textview);

            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox_id);

            convertView.setTag(viewHolder);


        } else {
            viewHolder =(ViewHolder) convertView.getTag();
        }


        viewHolder.textView.setText(list.get(position).getContent());


        viewHolder.checkBox.setChecked(list.get(position).ischeck());


        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(list.get(position).ischeck()){
                    list.get(position).setIscheck(false);
                    viewHolder.checkBox.setChecked(false);
                } else {
                    list.get(position).setIscheck(true);
                    viewHolder.checkBox.setChecked(true);
                }

                notifyDataSetChanged();

            }
        });


        return convertView;
    }


    static class ViewHolder {
        TextView textView;
        CheckBox checkBox ;
    }
}