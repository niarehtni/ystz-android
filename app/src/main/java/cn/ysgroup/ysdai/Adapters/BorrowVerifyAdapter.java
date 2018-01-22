package cn.ysgroup.ysdai.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ysgroup.ysdai.Beans.Borrow.BorrowVerifyBean;
import cn.ysgroup.ysdai.R;

/**
 * Created by msi on 2018/1/11.
 */

public class BorrowVerifyAdapter extends BaseAdapter {
    private List<BorrowVerifyBean> mVerifyBeanList;
    private LayoutInflater mInflater;
    private Context context;

    public BorrowVerifyAdapter(Context context, List<BorrowVerifyBean> list) {
        this.context = context;
        this.mVerifyBeanList = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mVerifyBeanList.size();
    }

    @Override
    public BorrowVerifyBean getItem(int position) {
        return mVerifyBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.borrow_list_verify_item_layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (BorrowVerifyAdapter.ViewHolder) convertView.getTag();
        }
        if (mVerifyBeanList != null) {
            viewHolder.mBorrowItemVerifyName.setText(mVerifyBeanList.get(position).getName());
        }
        //  viewHolder.bankListViewText.setText(entity.getBankName());
        // viewHolder.bankListViewImg.setImageResource(context.getResources().getIdentifier("bank_" + entity.getBankId().toLowerCase(), "mipmap", context.getPackageName()));
        return convertView;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'bank_list_view_item_layout.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.borrow_item_verify_name)
        TextView mBorrowItemVerifyName;
        @Bind(R.id.borrow_item_verify_value)
        TextView mBorrowItemVerifyValue;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
