package cn.ysgroup.ysdai.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ysgroup.ysdai.Beans.Borrow.RankBean;
import cn.ysgroup.ysdai.R;


public class RankListAdapter extends BaseAdapter {
    private List<RankBean> mRankBeanList;
    private LayoutInflater mInflater;
    private Context context;

    private static final int NO1 = 1;
    private static final int NO2 = 2;
    private static final int NO3 = 3;

    public RankListAdapter(Context context, List<RankBean> list) {
        this.context = context;
        this.mRankBeanList = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mRankBeanList.size();
    }

    @Override
    public RankBean getItem(int position) {
        return mRankBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.rank_list_item_layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (mRankBeanList != null) {
            viewHolder.mName.setText(mRankBeanList.get(position).getRealName());
            viewHolder.mMoney.setText(mRankBeanList.get(position).getSumAccount() + "");
            switch (position) {
                case NO1:
                    viewHolder.mImg.setImageDrawable(context.getResources().getDrawable(R.drawable.rank_img_1));
                    break;
                case NO2:
                    viewHolder.mImg.setImageDrawable(context.getResources().getDrawable(R.drawable.rank_img_2));
                    break;
                case NO3:
                    viewHolder.mImg.setImageDrawable(context.getResources().getDrawable(R.drawable.rank_img_3));
                    break;
                default:
                    viewHolder.mImg.setImageDrawable(context.getResources().getDrawable(R.drawable.rank_img_3));
                    break;
            }

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
        @Bind(R.id.rank_item_user)
        TextView mName;
        @Bind(R.id.rank_item_money)
        TextView mMoney;
        @Bind(R.id.rank_item_img)
        ImageView mImg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void notifyDataSetChanged(List<RankBean> list) {
        this.mRankBeanList = list;
        this.notifyDataSetChanged();
    }
}
