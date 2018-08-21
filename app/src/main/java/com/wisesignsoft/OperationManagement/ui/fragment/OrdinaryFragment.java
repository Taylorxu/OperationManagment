package com.wisesignsoft.OperationManagement.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.BaseFragment;
import com.wisesignsoft.OperationManagement.OrdinaryAdapter;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.OrdinaryModel;
import com.wisesignsoft.OperationManagement.bean.ReturnValue;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.db.MySharedpreferences;
import com.wisesignsoft.OperationManagement.mview.DividerGridItemDecoration;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.mview.MyDialog;
import com.wisesignsoft.OperationManagement.net.response.BaseResponse;
import com.wisesignsoft.OperationManagement.net.response.LoginResponse;
import com.wisesignsoft.OperationManagement.net.response.QueryUserResourceResponse;
import com.wisesignsoft.OperationManagement.net.response.QueryValidUsersByAccountResponse;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.ui.activity.SolvingActivity;
import com.wisesignsoft.OperationManagement.utils.GsonHelper;
import com.wisesignsoft.OperationManagement.utils.OrdinaryUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ycs on 2016/11/18.
 */

public class OrdinaryFragment extends BaseFragment implements View.OnClickListener {
    /*待处理*/
    private RelativeLayout rl_ordinary_solving;
    /*待处理数量*/
    private TextView tv_ordinary_num;
    /*已完成*/
    private RelativeLayout rl_ordinary_solved;
    /*grid view*/
    private RecyclerView rv_ordinary;


    private List<QueryUserResourceResponse.ResourcesBean> datas;
    private List<OrdinaryModel> models;
    private OrdinaryAdapter adapter;
    private LoadingView loadingView;

    public static OrdinaryFragment newInstance() {
        OrdinaryFragment fragment = new OrdinaryFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_ordinary, container, false);
        rl_ordinary_solving = (RelativeLayout) view.findViewById(R.id.rl_ordinary_solving);
        tv_ordinary_num = (TextView) view.findViewById(R.id.tv_ordinary_num);
        rl_ordinary_solved = (RelativeLayout) view.findViewById(R.id.rl_ordinary_solved);
        rv_ordinary = (RecyclerView) view.findViewById(R.id.rv_ordinary);
        rl_ordinary_solving.setOnClickListener(this);
        rl_ordinary_solved.setOnClickListener(this);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        rv_ordinary.setLayoutManager(manager);
        rv_ordinary.addItemDecoration(new DividerGridItemDecoration(getContext()));
        request();
        return view;
    }

    private void request() {
        loadingView = LoadingView.getLoadingView(getContext());
        loadingView.show();
        final List<String> list = new ArrayList<>();
        list.add(User.getUserFromRealm().getUsername());
        ApiService.Creator.get().queryUserResource(RequestBody.getgEnvelope(Protocol.yxyw_name_space, list, Protocol.queryUserResource))
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        loadingView.stop(loadingView);
                        if (response.body() != null) {
                            QueryUserResourceResponse result = GsonHelper.build().getObjectByJson(response.body(), QueryUserResourceResponse.class);
                            datas = result.getResources();
                            initData(datas);
                            queryValidUsersByAccount(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        loadingView.stop(loadingView);
                    }
                });
        ApiService.Creator.get().queryUnhandleProcessCount(RequestBody.getgEnvelope(Protocol.yxyw_name_space, list, Protocol.QueryUnhandleProcessCount))
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        loadingView.stop(loadingView);
                        if (response.body() != null) {
                            ReturnValue result = GsonHelper.build().getObjectByJson(response.body(), ReturnValue.class);
                            String num = result.getReturnValue();
                            if (!TextUtils.isEmpty(num)) {
                                tv_ordinary_num.setVisibility(View.VISIBLE);
                                int num_int = Integer.parseInt(num);
                                String temp;
                                if (num_int > 99) {
                                    temp = "99+";
                                } else {
                                    temp = num;
                                }
                                tv_ordinary_num.setText(temp);
                            } else {
                                tv_ordinary_num.setVisibility(View.GONE);
                            }
                            loadingView.stop(loadingView);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        loadingView.stop(loadingView);
                    }
                });

    }

    /**
     * 设置权限资源数据
     *
     * @param datas
     */
    private void initData(List<QueryUserResourceResponse.ResourcesBean> datas) {
        if (datas != null && datas.size() > 0) {
            models = OrdinaryUtil.toOrdinaryModel(datas);
            adapter = new OrdinaryAdapter(getContext(), models, this);
            rv_ordinary.setAdapter(adapter);

            adapter.setOnIOrdinary(new OrdinaryAdapter.IOrdinary() {
                @Override
                public void setOnIOrdinary(OrdinaryModel resUrl) {
                    toOtherPager(resUrl);
                }
            });
        }
    }

    private void toOtherPager(OrdinaryModel resUrl) {
        Log.i("YCS", "toOtherPager: name：" + resUrl);
        Intent intent;
        switch (resUrl.getResUrl()) {
           /* case "NEW_TASK":
                intent = new Intent(getActivity(), NewWorkOrderActivity.class);
                startActivity(intent);
                break;
            case "NEW_TEMPLATE"://新建模板
                intent = new Intent(getActivity(), NewTemplateActivity.class);
                startActivity(intent);
                break;
            case "MY_TEMPLATE"://我的模板
                intent = new Intent(getActivity(), MyTemplateActivity.class);
                startActivity(intent);
                break;
            case "CONSTRACT_SEARCH"://合同信息
                intent = new Intent(getActivity(), ContractInfoActivity.class);
                intent.putExtra("key", resUrl.getResPar());
                startActivity(intent);
                break;*/
            case "CHANGE_STATUS"://更改状态
                updateStatue();
                break;
           /* case "SR_SEARCH"://服务报告
                intent = new Intent(getActivity(), ServiceReportActivity.class);
                intent.putExtra("key", resUrl.getResPar());
                startActivity(intent);
                break;
            case "KNOWLEDGE_SEARCH"://知识检索
            case "KNOWLEDGE_QUERY":
                intent = new Intent(getActivity(), KnowSearchActivity.class);
                intent.putExtra("key", resUrl.getResPar());
                startActivity(intent);
                break;
            case "ACCOUNT_BOOK_QUERY":
                String resPar = resUrl.getResPar();
                if ("res".equals(resPar)) {
                    intent = new Intent(getActivity(), ServiceReportActivity.class);
                    intent.putExtra("key", resPar);
                    startActivity(intent);
                } else if ("CONTRACTS".equals(resPar)) {
                    intent = new Intent(getActivity(), ContractInfoActivity.class);
                    intent.putExtra("key", resPar);
                    startActivity(intent);
                }
                break;

            case "REPORT_LOCATION":
                LocationHelper.uploadLoaction(getActivity());
                break;*/
        }
    }

    /*
        0   忙
        1   闲
     */
    private void updateStatue() {
        int statue = User.getUserFromRealm().getStatue();
        if (statue == 0) {
            setStatue(1, "设置状态为闲");
        } else {
            setStatue(0, "设置状态为忙");
        }
    }

    private void setStatue(final int statue, String title) {
        final MyDialog myDialog = new MyDialog(getContext());
        myDialog.setData(title, myDialog, new MyDialog.IMyDialog() {
            @Override
            public void setMyDialog() {
                final LoadingView loadingView = LoadingView.getLoadingView(getContext());
                loadingView.show();
                List<String> list = new ArrayList<>();
                list.add(User.getUserFromRealm().getUsername());
                list.add(statue + "");
                ApiService.Creator.get().updateUserSta(RequestBody.getgEnvelope(Protocol.user_name_space, list, Protocol.updateUserPos)).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        changeStateData(statue);
                        adapter.notifyDataSetChanged();
                        loadingView.stop(loadingView);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
        myDialog.show();
    }

    /**
     * 修改状态 调用接口返回后更新adapter 里数据的状态
     *
     * @param state
     */
    public void changeStateData(int state) {
        User.updateUserState(User.getUserFromRealm().getUserId(), state);

        if (models.size() > 0) {
            for (OrdinaryModel bean : models) {
                if (bean.getName().equals("更改状态")) {
                    if (state == 1) {
                        bean.setReId(R.mipmap.state_false);
                    } else {
                        bean.setReId(R.mipmap.changestate);
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rl_ordinary_solving:
                intent = new Intent();
                intent.setClass(getActivity(), SolvingActivity.class);
                startActivity(intent);
                break;
           /* case R.id.rl_ordinary_solved:
                intent = new Intent();
                intent.setClass(getActivity(), SovledActivity.class);
                startActivity(intent);
                break;*/
        }
    }

    /**
     * 更改状态菜单初始化
     *
     * @param list
     */
    private void queryValidUsersByAccount(List<String> list) {

        ApiService.Creator.get().queryValidUsersByAccount(RequestBody.getgEnvelope(Protocol.yxyw_name_space, list, Protocol.queryValidUsersByAccount)).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                loadingView.stop(loadingView);
                if (response.body() != null) {
                    QueryValidUsersByAccountResponse result = GsonHelper.build().getObjectByJson(response.body(), QueryValidUsersByAccountResponse.class);
                    String stute = result.getReturnValue().getUserState();
                    if ("1".equals(stute)) {
                        changeStateData(1);
                    } else {
                        changeStateData(0);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                loadingView.stop(loadingView);
                t.printStackTrace();
            }
        });

    }
}
