package cn.ysgroup.ysdai.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import cn.ysgroup.ysdai.Activities.ImagePagerActivity;
import cn.ysgroup.ysdai.Adapters.BorrowVerifyAdapter;
import cn.ysgroup.ysdai.Beans.Borrow.BorrowBean;
import cn.ysgroup.ysdai.Beans.Borrow.BorrowVerifyBean;
import cn.ysgroup.ysdai.R;
import cn.ysgroup.ysdai.Util.AppConstants;
import cn.ysgroup.ysdai.Util.PreferenceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linyujie on 16/10/14.
 */

public class MaterialFragment extends Fragment {

    private View view;
    private GridView gridView;
    private String borrowImg;
    private Context mContext;
    private int mProjectId;

    private String[] verifyKeyList = {"anCase", "cardDriving", "gcfp", "gouZhi", "guJia", "household"
            , "idCard", "income", "jdczs", "zhengXin"};
    private String[] verifyKeyValueList = {"在执行案件查询", "车辆行驶证", " 购车发票", "购置税凭证", "车辆评估报告", "户口本"
            , "身份证", "收入证明", "机动车登记证书", "征信报告"};

    private ListView mListView;

    private BorrowVerifyAdapter mVerifyAdapter;

    private String verify_url = AppConstants.URL_SUFFIX + "/rest/verifyList";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String verifyValue = (String) msg.obj;
            setUpView(verifyValue);
        }
    };

    public MaterialFragment() {

    }

    @SuppressLint({"NewApi", "ValidFragment"})
    public MaterialFragment(int projectId) {
        this.mProjectId = projectId;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // view = View.inflate(getActivity(), R.layout.activity_project_documents, null);
        view = View.inflate(getActivity(), R.layout.activity_project_verify, null);
        initView();
        //    initDate();
        requestData();
        return view;
    }

    private void initView() {
        //    gridView = (GridView) view.findViewById(R.id.doucument_girdview);
        mListView = (ListView) view.findViewById(R.id.verify_list);
    }

    private void setUpView(String msg) {
        if (msg == null)
            return;
        try {
            JSONArray array = JSON.parseArray(msg);
            if (array == null || array.size() <= 0)
                return;
            List<BorrowVerifyBean> objList = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = array.getJSONObject(i);
                BorrowVerifyBean bean = new BorrowVerifyBean();
                bean.setName(obj.getString("name"));
                bean.setVerifyValue(obj.getString("verifyValue"));
                objList.add(bean);
            }

            BorrowVerifyAdapter adapter = new BorrowVerifyAdapter(mContext, objList);
            mListView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestData() {
        if (mProjectId < 0)
            return;
        RequestForVerifyData(verify_url, mProjectId);
    }

    //请求网络数据
    public void RequestForVerifyData(String basicUrl, int projectId) {
        String url = basicUrl + "/" + projectId;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormEncodingBuilder().add("token", PreferenceUtil.getPrefString(mContext, "loginToken", "")).
                build();
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, final IOException e) {

            }

            @Override
            public void onResponse(final Response response) throws IOException {
                String s = response.body().string();
                try {
                    Message msg = mHandler.obtainMessage();
                    msg.obj = s;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    private void initDate() {
//        if (borrowImg != null) {
//            List<BorrowDocuImage> imglist = JSON.parseArray(borrowImg, BorrowDocuImage.class);
//
//            ArrayList<View> viewList = new ArrayList<View>();
//            final ArrayList<String> imageUrlList = new ArrayList<String>();
//            final ArrayList<String> nameList = new ArrayList<String>();
//            BitmapCache myBitmapCache = new BitmapCache();
//            ImageLoader imageLoader = new ImageLoader(CustomApplication.newInstance().getRequestQueue(), myBitmapCache);
//            for (int i = 0; i < imglist.size(); i++) {
//                View view = LayoutInflater.from(getActivity()).inflate(R.layout.document_view_pager_item_layout, null, false);
//                SmoothImageView img = (SmoothImageView) view.findViewById(R.id.docu_item_img);
//                TextView name = (TextView) view.findViewById(R.id.docu_item_name);
//                name.setText(imglist.get(i).getName());
//                String imgUrl = AppConstants.IMG_URL_SUFFIX + imglist.get(i).getUrl();
//                ImageLoader.ImageListener listener = ImageLoader.getImageListener(img, R.mipmap.default_image, R.mipmap.default_image);
//                imageLoader.get(imgUrl, listener);
//                viewList.add(view);
//                imageUrlList.add(imgUrl);
//                nameList.add(imglist.get(i).getName());
//            }
//            DocumentAdapter documentAdapter = new DocumentAdapter(viewList, getActivity(), imageUrlList);
//            gridView.setAdapter(documentAdapter);
//            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    imageBrower(position, imageUrlList,nameList);
//                }
//            });
//        }
//    }


    /**
     * 打开图片查看器
     *
     * @param position
     * @param urls2
     */
    protected void imageBrower(int position, ArrayList<String> urls2, ArrayList<String> urls3) {
        Intent intent = new Intent(getActivity(), ImagePagerActivity.class);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        intent.putExtra(ImagePagerActivity.EXTRA_NAME_URLS, urls3);
        getActivity().startActivity(intent);
    }
}

