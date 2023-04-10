package com.example.communityepidemicassistant;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//二级Fragment，用于显示任务列表的TaskFragment，实现从本地数据库加载任务信息摘要到列表中和点击任务列表的一项跳转到任务明细activity的功能
public class TaskFragment extends Fragment {
    SimpleAdapter mSimpleAdapter;
    private ListView listView_task;
    private TaskDBOpenHelper mTaskDBOpenHelper;     //声明TaskDBOpenHelper对象，这玩意儿用来创建数据表
    SQLiteDatabase dbr;
    private List mData;
    private ImageView iv_iffinished;

    private List<Map<String, Object>> list;
    private Integer imgId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTaskDBOpenHelper = new TaskDBOpenHelper(getContext());
        mTaskDBOpenHelper.add("关于加强新型冠状病毒感染的肺炎疫情社区防控工作的通知", "2020-01-20", "社区疫情防控必须从严做好，容不得任何丝毫大意。",
                "       一、完善网格化管理。防控疫情在基层社区蔓延，应严格实行地毯式追踪、网格化管理，将防控措施落实到户、落实到人，做到“防输入、防蔓延、防输出”，实现“早发现、早报告、早隔离、早诊断、早治疗”，有效遏制疫情扩散。\n" +
                        "       二、加强医疗力量配备。农村等基层社区的医疗资源相对薄弱，遇到疫情，更显专业力量不足。各地应探索建设专兼职结合的工作队伍，充分发挥街道（乡镇）和社区（村）干部、基层医疗卫生机构医务人员和家庭医生队伍的合力，积极借助大数据、互联网等先进科技手段，提高医疗服务的精细化程度。各级医疗卫生机构应与社区加强配合，积极指导社区做好疫情的发现、防控和应急处置，有效落实密切接触者的排查等措施，做到无缝衔接。\n" +
                        "       三、加强环境卫生整治力度。除了医疗条件薄弱，个别地方的环境卫生状况不容乐观。必须在防范疫情传播、打击非法贩卖野生动物的同时，及时广泛地开展一场爱国卫生运动，加强卫生知识宣传，管理好基层社区、农贸市场、垃圾中转站等病菌易生多发地点的环境卫生，把环境治理举措落实到每一个社区和家庭。\n" +
                        "       四、各地有关部门应做好相应保障工作，确保社区群众必要的生产生活物资供应不受太大影响。", "no", R.drawable.unfinished);
        mTaskDBOpenHelper.add("各社区做好防疫抗疫工作", "2020-01-15", "迅速行动、主动担当、多措并举,积极开展各项防疫工作。",
                "       当前是抗击新冠肺炎疫情的关键时刻，基层社区既是前沿阵地，也是抗疫的重要战场,响应中央号召，结合本地实际，迅速行动、主动担当、多措并举,积极开展各项防疫工作。\n" +
                        "       各社区应组织相关工作小组，开展社区自排自查，加强社区管理。对排查到的重点地区人员，社区卫生服务中心医务人员应第一时间上门，进行体温测量，告知隔离健康观察相关要求，填写《隔离健康观察承诺书》。" +
                        "对无相关症状的重点地区来沪人员实施隔离健康观察，指导做好家庭消毒和防控措施。对于体温出现异常的，按程序转运至本市医疗机构发热门诊。重点地区来沪人员的隔离健康观察期为14天。" +
                        "隔离观察期满后，无异样症状的，解除隔离。隔离期间，体温出现异常或出现相关症状的被隔离人员，按规范流程，转至本市医疗机构发热门诊作进一步诊疗，社区对其居所按规范进行消毒。", "no",
                R.drawable.unfinished);//
        mTaskDBOpenHelper.add("积极进行防疫宣传", "2020-01-12", "依托新媒体平台开展防疫宣传工作。",
                "       为深入贯彻落实习近平总书记关于新型冠状病毒感染肺炎疫情防控的一系列重要指示精神，" +
                        "全面落实市、区委关于疫情防控的工作部署，社区充分发挥共青团作为党的助手和后备军的积极作用，" +
                        "社区应积极进行防疫宣传，尤其应依托新媒体平台开展防疫宣传工作，根据防控工作需要，不定期推送信息。", "no", R.drawable.unfinished);//
        mTaskDBOpenHelper.add("为节后出省务工和返岗农民工提供免费健康服务", "2020-01-14", "启动2020年春节后出省务工和返岗农民工行前免费健康服务工作，用暖心之举为农民工保驾护航。",
                "       启动2020年春节后出省务工和返岗农民工行前免费健康服务工作，用暖心之举为农民工保驾护航，用务实之策细化疫情防控。\n" +
                        "       卫生健康和人力资源社会保障部门加强配合，制定细化的实施方案和操作指南。\n" +
                        "       卫生健康部门负责组织乡镇卫生院和社区卫生服务中心家庭医生团队提供免费服务，" +
                        "内容包括流行病史咨询，体温、血压、心率测量，呼吸道症状筛查和职业健康防护建议等，" +
                        "并为其出具《2020年春节后出省务工和返岗农民工健康状况随访记录表》。\n" +
                        "       人力资源社会保障部门负责宣传和组织报名，确保服务工作有序开展。", "no", R.drawable.unfinished);
        mTaskDBOpenHelper.add("疫情防控中建立“三人小组”", "2020-01-06", "建立由(社区、村委会)干部、乡村医生、基层民警组成的“三人小组”，加强基层机构感染防控。",
                "       加强基层机构感染防控，在疫情防控中建立由(社区、村委会)干部、乡村医生、基层民警组成的“三人小组”，明确乡村医生在网络化管理和三人小组中的职责分工。\n" +
                        "       由基层医务人员负责医学检查，社区干部负责引路介绍，基层民警保障人员配合。同时，乡村医生协助社区(村)开展电话问询、摸排登记、派发宣传资料、开展居家医学观察者生活保障等工作。\n" +
                        "       为更好的指导乡村医生开展防控工作，提高村医防控和防护能力，开通全省乡村医生短信免费群发功能，根据防控工作需要，不定期推送信息。", "no", R.drawable.unfinished);
        mTaskDBOpenHelper.add("关于充分发挥家庭医生在新冠肺炎疫情防控中作用的通知", "2020-01-02", "充分发挥家庭医生在疫情防控中的作用，让家庭医生当好“三师一员”，助力疫情防控。",
                "       一当好家庭健康的保健师。\n      疫情防控期间，家庭医生应通过电话、微信、家庭医生签约APP等方式至少开展一次随访，指导签约居民加强个人防护、家庭消毒，宣传防控知识，做好心理疏导。对行动不便的重点人员，可提供上门服务。对居家隔离的签约对象加强指导，做好医学观察。\n" +
                        "       二当好常见病多发病的治疗师。\n        对高血压、糖尿病等慢病特病患者，可提供一个月长处方;对行动不便的患者，可由家庭医生代购药品送药上门。\n" +
                        "       三当好疾病恢复期的康复师。\n     对康复出院的新冠肺炎患者，第一时间与其签订家庭医生服务协议，与定点医院医生组成家庭医生团队，指导做好至少14天的居家医学观察及记录，通知患者定期到定点医院开展复查。\n" +
                        "       四是当好就医转诊的引导员。\n     对有发热症状的患者首先由家庭医生进行有效排查，引导其到定点发热门诊就医，减少签约居民就医转诊的盲目性，尽力避免交叉感染。", "no", R.drawable.unfinished);

    }

    @Override
    public void onResume() {
        super.onResume();

        refresh();
        dbr = mTaskDBOpenHelper.getReadableDatabase();
        mData = getData();
        mSimpleAdapter = new SimpleAdapter(getContext(),
                mData,
                R.layout.listitem_task,
                new String[]{"title", "date", "abstract", "img"},
                new int[]{R.id.tv_listitem_title, R.id.tv_listitem_date, R.id.tv_listitem_abstract, R.id.iv_iffinished}
        );
        listView_task.setAdapter(mSimpleAdapter);

        listView_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(TaskDetailActivity.EXTRA_ID, (int) id);
                intent.setClass(getContext(), TaskDetailActivity.class);
                startActivity(intent);
            }
        });

        listView_task.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                HashMap<Integer, Integer> map = (HashMap<Integer, Integer>) parent.getItemAtPosition(position);
                imgId = map.get("img");

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setIcon(R.drawable.warns);
                builder.setTitle("删除任务");
                builder.setMessage("确认删除任务？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    public void onClick(DialogInterface dialog, int which) {
                        //点确定时判断任务是否已确认完成，若完成则删除并隐藏此对话框；若未完成则提示先确认完成任务
                        int img = 2131165326;  //未完成的图片id
                        if (imgId == img) {
                            Toast.makeText(getContext(), "该任务未确认完成，不可删除！", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            list.remove(position);   //删除item
                            mSimpleAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "删除成功！", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点击取消时只隐藏对话框
                        dialog.dismiss();
                    }
                });
                builder.create().show();


                return true;//return true才不会和click冲突
            }
        });

    }

    //重写onCreateview方法，将fragment2_task动态加载进来
    //在此方法中初始化页面
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_task, container, false);

        iv_iffinished = (ImageView) view.findViewById(R.id.iv_iffinished);
        listView_task = (ListView) view.findViewById(R.id.listView_task);
        return view;
    }

    private void init() {
        mData = getData();
        mSimpleAdapter = new SimpleAdapter(getContext(),
                mData,
                R.layout.listitem_task,
                new String[]{"title", "date", "abstract", "img"},
                new int[]{R.id.tv_listitem_title, R.id.tv_listitem_date, R.id.tv_listitem_abstract, R.id.iv_iffinished}
        );
        listView_task.setAdapter(mSimpleAdapter);
    }

    public List<Map<String, Object>> getData() {
        list = new ArrayList<>();
        //查询数据
        Cursor c = dbr.query("task", //query函数返回一个游标c
                null,
                null,  //筛选条件
                null,  //筛选值
                null,
                null,
                "iffinished,date DESC");

        if (c.getCount() > 0) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                String title = c.getString(c.getColumnIndexOrThrow("title"));
                String date = c.getString(c.getColumnIndexOrThrow("date"));
                String task_abstract = c.getString(c.getColumnIndexOrThrow("abstract"));
                String task_iffinished = c.getString(c.getColumnIndexOrThrow("iffinished"));
                String task_img = c.getString(c.getColumnIndexOrThrow("img"));
                int img = Integer.parseInt(task_img);
                //把值添加到listview的数据集中
                Map<String, Object> map = new HashMap<>();
                map.put("title", title);
                map.put("date", date);
                map.put("abstract", task_abstract);
                map.put("iffinished", task_iffinished);
                map.put("img", img);
                list.add(map);
                c.moveToNext();
            }
        }
        c.close();
        dbr.close();
        return list;
    }

    private void refresh() {
        onCreate(null);
    }
}
