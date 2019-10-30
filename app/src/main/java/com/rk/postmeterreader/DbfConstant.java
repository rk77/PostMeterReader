package com.rk.postmeterreader;

import java.util.HashMap;
import java.util.Map;

public class DbfConstant {
    public class Colume {
        public static final String CBJHBH = "CBJHBH";       //抄表计划编号        营销      VARCHAR2(20)
        public static final String CBQDBH = "CBQDBH";       //抄表区段编号        营销      VARCHAR2(20)
        public static final String CBYWLXDM = "CBYWLXDM";   //抄表业务类型代码    营销      VARCHAR2(8)
        public static final String CBQDMC = "CBQDMC";       //抄表区段名称        营销      VARCHAR2(64)
        public static final String CBSXH = "CBSXH";         //抄表顺序号          营销      NUMBER(5)
        public static final String JLDBH = "JLDBH";         //计量点编号          营销      VARCHAR2(20)
        public static final String YXDNBBS = "YXDNBBS";     //运行电能表标识      营销      VARCHAR2(16)
        public static final String SSLXDM = "SSLXDM";       //示数类型            营销      VARCHAR2(8)
        public static final String ZFBBZ = "ZFBBZ";         //主副表标志          营销      VARCHAR2(8)
        public static final String DFNY = "DFNY";           //电费年月            营销      NUMBER(6)
        public static final String BQCBCS = "BQCBCS";       //本期抄表次数        营销      NUMBER(5)
        public static final String YHBH = "YHBH";           //用户编号            营销      VARCHAR2(20)
        public static final String YHMC = "YHMC";           //用户名称            营销      VARCHAR2(200
        public static final String YDDZ = "YDDZ";           //用电地址            营销      VARCHAR2(200
        public static final String ZCBH = "ZCBH";           //资产编号            营销      VARCHAR2(32)
        public static final String CCBH = "CCBH";           //出厂编号            营销      VARCHAR2(32)
        public static final String SCBSS = "SCBSS";         //上次表示数          营销      NUMBER(13,5)
        public static final String BCBSS = "BCBSS";         //本次表示数          抄表器    NUMBER(13,5)
        public static final String BMWS = "BMWS";           //表码位数            营销      VARCHAR2(8)
        public static final String ZHBL = "ZHBL";           //综合倍率            营销      NUMBER(10,3)
        public static final String SCCBRQ = "SCCBRQ";       //上次抄表日期        营销      NUMBER(8)
        public static final String SCBJDL = "SCBJDL";       //上次表计电量        营销      NUMBER(14,2)
        public static final String CBBZDM = "CBBZDM";       //抄表标志代码        抄表器    VARCHAR2(8)
        public static final String CBZTDM = "CBZTDM";       //抄表状态代码        抄表器    VARCHAR2(8)
        public static final String CBYCDM = "CBYCDM";       //抄表异常代码        抄表器    VARCHAR2(8)
        public static final String GDDWBM = "GDDWBM";       //供电单位编码        营销      VARCHAR2(20)
        public static final String LXDH = "LXDH";           //联系电话            营销      VARCHAR2(20)
        public static final String CBSJ = "CBSJ";           //抄表时间            抄表器    VARCHAR(20)
        public static final String SFGXBZ = "SFGXBZ";       //是否新表标志        营销      VARCHAR2(8)
        public static final String QFQS = "QFQS";           //欠费期数            营销      NUMBER(8)
        public static final String QFNY = "QFNY";           //欠费年月            营销      NUMBER(8)
        public static final String ZQFJE = "ZQFJE";         //总欠费金额          营销      NUMBER(14,2)
        public static final String CFJG = "CFJG";           //催费结果            抄表器    VARCHAR2(32)
        public static final String DJMC = "DJMC";           //电价名称            营销      VARCHAR2(96)
        public static final String DJ = "DJ";               //电价                营销      NUMBER(9,7)
        public static final String SJCBFS = "SJCBFS";       //实际抄表方式        抄表器    VARCHAR2(8)
        public static final String JZQBH = "JZQBH";         //集中器编号          营销      VARCHAR2(32)
        public static final String JZQTNH = "JZQTNH";       //集中器TN号          营销      NUMBER(4)
        public static final String KHLB = "KHLB";           //客户类别            营销      VARCHAR2(8)
        public static final String JCBZ = "JCBZ";           //监抄标志            营销      CHAR(1)
        public static final String JCJL = "JCJL";           //监抄结论            抄表器    VARCHAR2(12)
        public static final String TXMBH = "TXMBH";         //条形码编号          营销      VARCHAR2(12)
        public static final String YHH = "YHH";             //原户号              营销      VARCHAR2(20)
        public static final String CKBXH = "CKBXH";         //参考表序号          营销      NUMBER(5)
        public static final String JLDLB = "JLDLB";         //计量点类别          营销      CHAR(1)
        public static final String XW = "XW";               //相位                营销      CHAR(1)
        public static final String SCCBYCBZ = "SCCBYCBZ";   //上次抄表异常标志    营销      VARCHAR2(8)
        public static final String XHWDZ = "XHWDZ";         //新红外地址          抄表器    VARCHAR2(32)
        public static final String JHWDZ = "JHWDZ";         //旧红外地址          营销      VARCHAR2(32)
        public static final String QYBZ = "QYBZ";           //区域标志            营销      CHAR(1)
        public static final String ZJDL = "ZJDL";           //增减电量            营销      NUMBER(14,2)
        public static final String HBDL = "HBDL";           //换表电量            营销      NUMBER(14,2)
        public static final String SFCDBZ = "SFCDBZ";       //是否出单标志        抄表器    VARCHAR2(1)
        public static final String DJBMBZ = "DJBMBZ";       //冻结表码标志                  VARCHAR2(1)
        public static final String DJSJ = "DJSJ";           //登记时间                      VARCHAR2(20)
        public static final String BZ = "BZ";               //备注                抄表器    VARCHAR(20)
        public static final String YCBH = "YCBH";           //原抄表号                      VARCHAR2(20)

        public final Map<String, String> COLUME_NAME_MAP = new HashMap<String, String>() {{
            put(CBJHBH, "抄表计划编号");
            put(CBQDBH, "抄表区段编号");
            put(CBYWLXDM, "抄表业务类型代码");
            put(CBQDMC, "抄表区段名称");
            put(CBSXH, "抄表顺序号");
            put(JLDBH, "计量点编号");
            put(YXDNBBS, "运行电能表标识");
            put(SSLXDM, "示数类型");
            put(ZFBBZ, "主副表标志");
            put(DFNY, "电费年月");
            put(BQCBCS, "本期抄表次数");
            put(YHBH, "用户编号");
            put(YHMC, "用户名称");
            put(YDDZ, "用电地址");
            put(ZCBH, "资产编号");
            put(CCBH, "出厂编号");
            put(SCBSS, "上次表示数");
            put(BCBSS, "本次表示数");
            put(BMWS, "表码位数");
            put(ZHBL, "综合倍率");
            put(SCCBRQ, "上次抄表日期");
            put(SCBJDL, "上次表计电量");
            put(CBBZDM, "抄表标志代码");
            put(CBZTDM, "抄表状态代码");
            put(CBYCDM, "抄表异常代码");
            put(GDDWBM, "供电单位编码");
            put(LXDH, "联系电话");
            put(CBSJ, "抄表时间");
            put(SFGXBZ, "是否新表标志");
            put(QFQS, "欠费期数");
            put(QFNY, "欠费年月");
            put(ZQFJE, "总欠费金额");
            put(CFJG, "催费结果");
            put(DJMC, "电价名称");
            put(DJ, "电价");
            put(SJCBFS, "实际抄表方式");
            put(JZQBH, "集中器编号");
            put(JZQTNH, "集中器TN号");
            put(KHLB, "客户类别");
            put(JCBZ, "监抄标志");
            put(JCJL, "监抄结论");
            put(TXMBH, "条形码编号");
            put(YHH, "原户号");
            put(CKBXH, "参考表序号");
            put(JLDLB, "计量点类别");
            put(XW, "相位");
            put(SCCBYCBZ, "上次抄表异常标志");
            put(XHWDZ, "新红外地址");
            put(JHWDZ, "旧红外地址");
            put(QYBZ, "区域标志");
            put(ZJDL, "增减电量");
            put(HBDL, "换表电量");
            put(SFCDBZ, "是否出单标志");
            put(DJBMBZ, "冻结表码标志");
            put(DJSJ, "登记时间");
            put(BZ, "备注");
            put(YCBH, "原抄表号");
        }};

        public final Map<String, String> COLUME_SOURCE_MAP = new HashMap<String, String>() {{
            put(CBJHBH, "营销");
            put(CBQDBH, "营销");
            put(CBYWLXDM, "营销");
            put(CBQDMC, "营销");
            put(CBSXH, "营销");
            put(JLDBH, "营销");
            put(YXDNBBS, "营销");
            put(SSLXDM, "营销");
            put(ZFBBZ, "营销");
            put(DFNY, "营销");
            put(BQCBCS, "营销");
            put(YHBH, "营销");
            put(YHMC, "营销");
            put(YDDZ, "营销");
            put(ZCBH, "营销");
            put(CCBH, "营销");
            put(SCBSS, "营销");
            put(BCBSS, "抄表器");
            put(BMWS, "营销");
            put(ZHBL, "营销");
            put(SCCBRQ, "营销");
            put(SCBJDL, "营销");
            put(CBBZDM, "抄表器");
            put(CBZTDM, "抄表器");
            put(CBYCDM, "抄表器");
            put(GDDWBM, "营销");
            put(LXDH, "营销");
            put(CBSJ, "抄表器");
            put(SFGXBZ, "营销");
            put(QFQS, "营销");
            put(QFNY, "营销");
            put(ZQFJE, "营销");
            put(CFJG, "抄表器");
            put(DJMC, "营销");
            put(DJ, "营销");
            put(SJCBFS, "抄表器");
            put(JZQBH, "营销");
            put(JZQTNH, "营销");
            put(KHLB, "营销");
            put(JCBZ, "营销");
            put(JCJL, "抄表器");
            put(TXMBH, "营销");
            put(YHH, "营销");
            put(CKBXH, "营销");
            put(JLDLB, "营销");
            put(XW, "营销");
            put(SCCBYCBZ, "营销");
            put(XHWDZ, "抄表器");
            put(JHWDZ, "营销");
            put(QYBZ, "营销");
            put(ZJDL, "营销");
            put(HBDL, "营销");
            put(SFCDBZ, "抄表器");
            put(DJBMBZ, "未知");
            put(DJSJ, "未知");
            put(BZ, "抄表器");
            put(YCBH, "未知");
        }};

        public final Map<Integer, String> METER_READ_SYMBOL_CODE = new HashMap<Integer, String>() {{
            put(1, "正常");
            put(2, "漏抄");
            put(3, "估抄");
            put(4, "补抄");
            put(5, "未抄");
            put(9, "其它");
        }};

        public final Map<Integer, String> METER_READ_CATEGORY_CODE = new HashMap<Integer, String>() {{
            put(0, "正常抄表");
            put(1, "非周期及业扩变更结算抄表");
            put(2, "补抄");
            put(3, "政策性退补");
            put(4, "监抄");
            put(5, "试算");
        }};

        public final Map<Integer, String> METER_READ_WAY_CODE = new HashMap<Integer, String>() {{
            put(10, "现场单户抄表");
            put(11, "现场单户手工（抄表卡）");
            put(12, "现场单户普通抄表器");
            put(13, "现场单户远红外抄表器");
            put(20, "现场集抄");
            put(21, "现场集抄普通抄表器");
            put(22, "现场集抄远红外抄表器");
            put(23, "现场集抄无线抄表器");
            put(24, "现场集抄低压载波集抄");
            put(30, "遥抄");
            put(31, "负控遥抄");
            put(32, "低压遥抄");
            put(33, "配变遥抄");
            put(34, "厂站遥抄");
            put(99, "其它");

        }};

        public final Map<Integer, String> METER_READ_STATUS_CODE = new HashMap<Integer, String>() {{
            put(0, "正常");
            put(1, "翻转");
            put(2, "倒走");
            put(3, "倒走并翻转");
        }};

        public final Map<Integer, String> METER_READ_ABNORMAL_CODE = new HashMap<Integer, String>() {{
            put(0, "无");
            put(10, "分时示数不平");
            put(11, "箱烂");
            put(12, "违约");
            put(13, "无表");
            put(14, "电表烧表");
        }};
    }
}
