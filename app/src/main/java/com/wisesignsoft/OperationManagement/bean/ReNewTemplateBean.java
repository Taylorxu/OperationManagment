package com.wisesignsoft.OperationManagement.bean;

import com.wisesignsoft.OperationManagement.net.response.BaseResponse;

import java.util.List;


public class ReNewTemplateBean extends BaseResponse {
    private boolean state;
    private ResultBean result;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String formDocument;
        private String PIKEY;
        private String taskNodeType;

        private TempletMapBean templetMap;
        private String CURRENT_TASKID;
        private String firstrequest;

        public String getFormDocument() {
            return formDocument;
        }

        public void setFormDocument(String formDocument) {
            this.formDocument = formDocument;
        }

        public String getPIKEY() {
            return PIKEY;
        }

        public void setPIKEY(String PIKEY) {
            this.PIKEY = PIKEY;
        }

        public String getTaskNodeType() {
            return taskNodeType;
        }

        public void setTaskNodeType(String taskNodeType) {
            this.taskNodeType = taskNodeType;
        }

        public TempletMapBean getTempletMap() {
            return templetMap;
        }

        public void setTempletMap(TempletMapBean templetMap) {
            this.templetMap = templetMap;
        }

        public String getCURRENT_TASKID() {
            return CURRENT_TASKID;
        }

        public void setCURRENT_TASKID(String CURRENT_TASKID) {
            this.CURRENT_TASKID = CURRENT_TASKID;
        }

        public String getFirstrequest() {
            return firstrequest;
        }

        public void setFirstrequest(String firstrequest) {
            this.firstrequest = firstrequest;
        }

        public static class TempletMapBean {
            private String SR_SECT;
            private String SR_STAT;
            private String KNO_AN;
            private String SR_RJSJF;
            private String IS_WHA;
            private String SE_THING;
            private String SR_NUMB_JJ;
            private String PRO_DEAL_ATTACHMENT;
            private String SE_CUSTOMER;
            private String IS_WT;
            private String RELEATED_WORKORDERS;
            private String KNO_MAN_SUB_TIME;
            private String SR_DLAACS;
            private String SR_PAP;
            private String IS_TJZSZJ;
            private String PRO_REVIEW_COMMENTS;
            private String IS_FAO;
            private String SR_RJTZF;
            private String SR_CP;
            private String IS_BXBJ;
            private String SE_SUPPORT;
            private String SE_PJ;
            private String PRO_CAUSES;
            private String IS_KCPD;
            private String PRO_ADJUNT;
            private String SR_NUMB_II;
            private String SR_SECH;
            private String SR_SECB;
            private String SR_CT;
            private String SR_SL;
            private String SR_CHECK;
            private String KNO_NUM;
            private String SR_SJQYF;
            private String PROCESS_STATE;
            private String IS_DIS;
            private String SE_CUSTOMERMUMBER;
            private String KNO_COM_PER;
            private String SR_NUMB_HH;
            private String SE_COME;
            private String KNO_MAN_REL_TIME;
            private String TPMEN;
            private String KNO_MAN_CHE_TIME;
            private String IS_JSYF;
            private String SR_PJAZF;
            private String IS_JSPX;
            private String SR_STIME;
            private String SE_SURE_PEOPLE;
            private String SR_BGCLSJ;
            private String SR_TT;
            private String RUNNING_STATE;
            private String SR_PNA;
            private String IS_CLRB;
            private String IS_CLRA;
            private String PRO_CLO_CODE;
            private String SR_NUMB_GG;
            private String IS_FASH;
            private String KNO_ZJJJFA;
            private String KNO_MAN_CHE_PER;
            private String IS_PJWX;
            private String KNO_MAN_COM_EXP;
            private String SR_PNO;
            private String SR_B;
            private String IS_CUSTNA;
            private String taskNodeType;
            private String CREATOR;
            private String SR_IB;
            private String KNO_ZJJJGC;
            private String KNO_OPE;
            private String IS_JSFX;
            private String SR_NUMB_FF;
            private String SR_ERXIAN;
            private String PRO_SFTG;
            private String SR_XNTYF;
            private String PIKEY;
            private String PRO_SOL;
            private String SR_QSSJ;
            private String IS_KE;
            private String PRO_DES;
            private String IS_INSERT_Q;
            private String SR_FWBGTJZQ;
            private String SR_HB;
            private String SR_NUMB_EE;
            private String SR_EQSN;
            private String SR_ENDT;
            private String PRO_PRI;
            private String SR_FII;
            private String SR_FU;
            private String SE_SOLVER;
            private String IS_INSERT_I;
            private String IS_INSERT_J;
            private String IS_INSERT_K;
            private String IS_INSERT_L;
            private String outCome;
            private String IS_INSERT_M;
            private String IS_INSERT_N;
            private String IS_NO;
            private String KNO_CON;
            private String IS_INSERT_O;
            private String IS_INSERT_P;
            private String IS_INSERT_A;
            private String IS_INSERT_B;
            private String IS_INSERT_C;
            private String IS_INSERT_D;
            private String IS_INSERT_E;
            private String SR_NUMB_DD;
            private String SR_OMM;
            private String IS_INSERT_F;
            private String IS_INSERT_G;
            private String IS_INSERT_H;
            private String taskId;
            private String IS_BSFA;
            private String SR_MTIME;
            private String KNO_ZJDCGK;
            private String SR_SECHT;
            private String IS_SBJD;
            private String PRO_GBR;
            private String PRO_GBT;
            private String PRO_OPI;
            private String SR_JSJL;
            private String PRO_CSR;
            private String PRO_CST;
            private String IS_MS;
            private String SR_NUMB_CC;
            private String TITLE;
            private String SR_JSJLF;
            private String IS_PS;
            private String SR_TRIR;
            private String APP_TIME;
            private String SR_BGCLR;
            private String SR_CLRA;
            private String SR_PMAN;
            private String KNO_TYPE;
            private String SR_FWDD;
            private String IS_FEOTC;
            private String SR_NUMB_B;
            private String PRO_VER;
            private String SR_NUMB_A;
            private String SR_CLS;
            private String KNO_MAN_APP_TIME;
            private String SR_CHECKWT;
            private String PRO_SRC;
            private String CUST_LOCATE;
            private String IS_ZJCS;
            private String SR_STYP;
            private String LIN_EVE_NUM;
            private SpecificValueUpdateBean specificValueUpdate;
            private String PRO_ANALYZE;
            private String KNO_MAN_REL_PER;
            private String SR_QTFWF;
            private String SR_PJAZ;
            /**
             * strategyKey : assignee
             * strategyValue : bd93c152-a32b-400e-af8b-fd26b26ac752,c5b8bbd9-d2ed-457b-be86-dbaf88d778b7
             */

            private TaskStrategyBean taskStrategy;
            private String IS_EOTC;
            private String SR_BGFL;
            private String IS_AO;
            private String IS_PJCS;
            private String SR_RJAZF;
            private String SR_JSFWF;
            private String KNO_ZJGBYYFX;
            private String SR_CHECKDL;
            private String SR_NUMB_AA;
            private String SR_JFBQF;
            private String SR_EQDM;
            private String IS_QTZC;
            private String KNO_MAN__APP_PER;
            private String SR_JSFW;
            private String SR_XCZBF;
            private String SR_NUMB_M;
            private String SR_NUMB_K;
            private String IS_PRONO;
            private String outComeDesc;
            private String SE_TIME;
            private String SR_NUMB_L;
            private String SR_NUMB_I;
            private String SR_NUMB_J;
            private String SR_CHECKT;
            private String KNO_LIN_SEL_PAK;
            private String SR_NUMB_G;
            private String SR_NUMB_H;
            private String SR_NUMB_E;
            private String SR_CNA;
            private String SR_NUMB_F;
            private String SR_NUMB_C;
            private String SR_NUMB_BB;
            private String SR_NUMB_D;
            private String SR_RJSJ;
            private String CREATEDATE;
            private String SR_IP;
            private String IS_PRONA;
            private String PRO_DEBUGER;
            private String PRO_INVESTIGATE;
            private String SR_XNTY;
            private String PRO_ANLYZEPEOPLE;
            private String SR_ETIME;
            private String PRO_URG;
            private String PRO_PLA_DATE;
            private String KNO_KEYWORD;
            private String SR_SRTABLE;
            private String PRO_CLT;
            private String IS_EI;
            private String IS_CLSJB;
            private String IS_CLSJA;
            private String SR_JSSJ;
            private String IS_ZJPZ;
            private String PRO_CLR;
            private String SR_RJTZ;
            private String REL_APP;
            private String PRO_SOLUTION;
            private String SR_SJQY;
            private String SR_CLSJA;
            private String SR_QTZYCN;
            private String SR_QTFW;
            private String IS_ST;
            private String SR_XCZB;
            private String SR_WOTA;
            private String SR_PL;
            private String SR_SBAZF;
            private String SR_SFZBXM;
            private String SOL_PRO;
            private String SR_NUMB_MM;
            private String IS_RCZJBG;
            private String SR_RJAZ;
            private String SR_CON;
            private String APP_OPI;
            private String OBJ_PROI;
            private String IS_PJPZ;
            private String IS_ZJPD;
            private String SE_SOUND;
            private String PRO_CJR;
            private String PRO_CJT;
            private String CHE_OPI;
            private String SR_SERT;
            private String SR_NUMB_LL;
            private String CHE_PER;
            private String IS_BU;
            private String SE_PERFEVT;
            private String IS_DIS_D;
            private String IS_DIS_E;
            private String SR_NO;
            private String IS_DIS_B;
            private String KNO_YYFX;
            private String IS_DIS_C;
            private String IS_DIS_A;
            private String IS_DIS_K;
            private String IS_DIS_J;
            private String IS_DIS_M;
            private String IS_DIS_L;
            private String IS_DL;
            private String IS_ZSKJL;
            private String IS_DIS_G;
            private String IS_DIS_I;
            private String SR_SECSP;
            private String IS_DIS_H;
            private String SR_SBQYF;
            private String SR_NUMB_KK;
            private String IS_DIS_O;
            private String IS_DIS_N;
            private String IS_DIS_Q;
            private String IS_DIS_P;
            private String SR_SBAZ;
            private String SE_PERFECT;
            private String SR_JFBQ;
            private String SR_SBQY;
            private String SR_MP;
            private String SR_QUYU;
            private String PROCESS_KEY;
            private String currentAssignee;

            public String getSR_SECT() {
                return SR_SECT;
            }

            public void setSR_SECT(String SR_SECT) {
                this.SR_SECT = SR_SECT;
            }

            public String getSR_STAT() {
                return SR_STAT;
            }

            public void setSR_STAT(String SR_STAT) {
                this.SR_STAT = SR_STAT;
            }

            public String getKNO_AN() {
                return KNO_AN;
            }

            public void setKNO_AN(String KNO_AN) {
                this.KNO_AN = KNO_AN;
            }

            public String getSR_RJSJF() {
                return SR_RJSJF;
            }

            public void setSR_RJSJF(String SR_RJSJF) {
                this.SR_RJSJF = SR_RJSJF;
            }

            public String getIS_WHA() {
                return IS_WHA;
            }

            public void setIS_WHA(String IS_WHA) {
                this.IS_WHA = IS_WHA;
            }

            public String getSE_THING() {
                return SE_THING;
            }

            public void setSE_THING(String SE_THING) {
                this.SE_THING = SE_THING;
            }

            public String getSR_NUMB_JJ() {
                return SR_NUMB_JJ;
            }

            public void setSR_NUMB_JJ(String SR_NUMB_JJ) {
                this.SR_NUMB_JJ = SR_NUMB_JJ;
            }

            public String getPRO_DEAL_ATTACHMENT() {
                return PRO_DEAL_ATTACHMENT;
            }

            public void setPRO_DEAL_ATTACHMENT(String PRO_DEAL_ATTACHMENT) {
                this.PRO_DEAL_ATTACHMENT = PRO_DEAL_ATTACHMENT;
            }

            public String getSE_CUSTOMER() {
                return SE_CUSTOMER;
            }

            public void setSE_CUSTOMER(String SE_CUSTOMER) {
                this.SE_CUSTOMER = SE_CUSTOMER;
            }

            public String getIS_WT() {
                return IS_WT;
            }

            public void setIS_WT(String IS_WT) {
                this.IS_WT = IS_WT;
            }

            public String getRELEATED_WORKORDERS() {
                return RELEATED_WORKORDERS;
            }

            public void setRELEATED_WORKORDERS(String RELEATED_WORKORDERS) {
                this.RELEATED_WORKORDERS = RELEATED_WORKORDERS;
            }

            public String getKNO_MAN_SUB_TIME() {
                return KNO_MAN_SUB_TIME;
            }

            public void setKNO_MAN_SUB_TIME(String KNO_MAN_SUB_TIME) {
                this.KNO_MAN_SUB_TIME = KNO_MAN_SUB_TIME;
            }

            public String getSR_DLAACS() {
                return SR_DLAACS;
            }

            public void setSR_DLAACS(String SR_DLAACS) {
                this.SR_DLAACS = SR_DLAACS;
            }

            public String getSR_PAP() {
                return SR_PAP;
            }

            public void setSR_PAP(String SR_PAP) {
                this.SR_PAP = SR_PAP;
            }

            public String getIS_TJZSZJ() {
                return IS_TJZSZJ;
            }

            public void setIS_TJZSZJ(String IS_TJZSZJ) {
                this.IS_TJZSZJ = IS_TJZSZJ;
            }

            public String getPRO_REVIEW_COMMENTS() {
                return PRO_REVIEW_COMMENTS;
            }

            public void setPRO_REVIEW_COMMENTS(String PRO_REVIEW_COMMENTS) {
                this.PRO_REVIEW_COMMENTS = PRO_REVIEW_COMMENTS;
            }

            public String getIS_FAO() {
                return IS_FAO;
            }

            public void setIS_FAO(String IS_FAO) {
                this.IS_FAO = IS_FAO;
            }

            public String getSR_RJTZF() {
                return SR_RJTZF;
            }

            public void setSR_RJTZF(String SR_RJTZF) {
                this.SR_RJTZF = SR_RJTZF;
            }

            public String getSR_CP() {
                return SR_CP;
            }

            public void setSR_CP(String SR_CP) {
                this.SR_CP = SR_CP;
            }

            public String getIS_BXBJ() {
                return IS_BXBJ;
            }

            public void setIS_BXBJ(String IS_BXBJ) {
                this.IS_BXBJ = IS_BXBJ;
            }

            public String getSE_SUPPORT() {
                return SE_SUPPORT;
            }

            public void setSE_SUPPORT(String SE_SUPPORT) {
                this.SE_SUPPORT = SE_SUPPORT;
            }

            public String getSE_PJ() {
                return SE_PJ;
            }

            public void setSE_PJ(String SE_PJ) {
                this.SE_PJ = SE_PJ;
            }

            public String getPRO_CAUSES() {
                return PRO_CAUSES;
            }

            public void setPRO_CAUSES(String PRO_CAUSES) {
                this.PRO_CAUSES = PRO_CAUSES;
            }

            public String getIS_KCPD() {
                return IS_KCPD;
            }

            public void setIS_KCPD(String IS_KCPD) {
                this.IS_KCPD = IS_KCPD;
            }

            public String getPRO_ADJUNT() {
                return PRO_ADJUNT;
            }

            public void setPRO_ADJUNT(String PRO_ADJUNT) {
                this.PRO_ADJUNT = PRO_ADJUNT;
            }

            public String getSR_NUMB_II() {
                return SR_NUMB_II;
            }

            public void setSR_NUMB_II(String SR_NUMB_II) {
                this.SR_NUMB_II = SR_NUMB_II;
            }

            public String getSR_SECH() {
                return SR_SECH;
            }

            public void setSR_SECH(String SR_SECH) {
                this.SR_SECH = SR_SECH;
            }

            public String getSR_SECB() {
                return SR_SECB;
            }

            public void setSR_SECB(String SR_SECB) {
                this.SR_SECB = SR_SECB;
            }

            public String getSR_CT() {
                return SR_CT;
            }

            public void setSR_CT(String SR_CT) {
                this.SR_CT = SR_CT;
            }

            public String getSR_SL() {
                return SR_SL;
            }

            public void setSR_SL(String SR_SL) {
                this.SR_SL = SR_SL;
            }

            public String getSR_CHECK() {
                return SR_CHECK;
            }

            public void setSR_CHECK(String SR_CHECK) {
                this.SR_CHECK = SR_CHECK;
            }

            public String getKNO_NUM() {
                return KNO_NUM;
            }

            public void setKNO_NUM(String KNO_NUM) {
                this.KNO_NUM = KNO_NUM;
            }

            public String getSR_SJQYF() {
                return SR_SJQYF;
            }

            public void setSR_SJQYF(String SR_SJQYF) {
                this.SR_SJQYF = SR_SJQYF;
            }

            public String getPROCESS_STATE() {
                return PROCESS_STATE;
            }

            public void setPROCESS_STATE(String PROCESS_STATE) {
                this.PROCESS_STATE = PROCESS_STATE;
            }

            public String getIS_DIS() {
                return IS_DIS;
            }

            public void setIS_DIS(String IS_DIS) {
                this.IS_DIS = IS_DIS;
            }

            public String getSE_CUSTOMERMUMBER() {
                return SE_CUSTOMERMUMBER;
            }

            public void setSE_CUSTOMERMUMBER(String SE_CUSTOMERMUMBER) {
                this.SE_CUSTOMERMUMBER = SE_CUSTOMERMUMBER;
            }

            public String getKNO_COM_PER() {
                return KNO_COM_PER;
            }

            public void setKNO_COM_PER(String KNO_COM_PER) {
                this.KNO_COM_PER = KNO_COM_PER;
            }

            public String getSR_NUMB_HH() {
                return SR_NUMB_HH;
            }

            public void setSR_NUMB_HH(String SR_NUMB_HH) {
                this.SR_NUMB_HH = SR_NUMB_HH;
            }

            public String getSE_COME() {
                return SE_COME;
            }

            public void setSE_COME(String SE_COME) {
                this.SE_COME = SE_COME;
            }

            public String getKNO_MAN_REL_TIME() {
                return KNO_MAN_REL_TIME;
            }

            public void setKNO_MAN_REL_TIME(String KNO_MAN_REL_TIME) {
                this.KNO_MAN_REL_TIME = KNO_MAN_REL_TIME;
            }

            public String getTPMEN() {
                return TPMEN;
            }

            public void setTPMEN(String TPMEN) {
                this.TPMEN = TPMEN;
            }

            public String getKNO_MAN_CHE_TIME() {
                return KNO_MAN_CHE_TIME;
            }

            public void setKNO_MAN_CHE_TIME(String KNO_MAN_CHE_TIME) {
                this.KNO_MAN_CHE_TIME = KNO_MAN_CHE_TIME;
            }

            public String getIS_JSYF() {
                return IS_JSYF;
            }

            public void setIS_JSYF(String IS_JSYF) {
                this.IS_JSYF = IS_JSYF;
            }

            public String getSR_PJAZF() {
                return SR_PJAZF;
            }

            public void setSR_PJAZF(String SR_PJAZF) {
                this.SR_PJAZF = SR_PJAZF;
            }

            public String getIS_JSPX() {
                return IS_JSPX;
            }

            public void setIS_JSPX(String IS_JSPX) {
                this.IS_JSPX = IS_JSPX;
            }

            public String getSR_STIME() {
                return SR_STIME;
            }

            public void setSR_STIME(String SR_STIME) {
                this.SR_STIME = SR_STIME;
            }

            public String getSE_SURE_PEOPLE() {
                return SE_SURE_PEOPLE;
            }

            public void setSE_SURE_PEOPLE(String SE_SURE_PEOPLE) {
                this.SE_SURE_PEOPLE = SE_SURE_PEOPLE;
            }

            public String getSR_BGCLSJ() {
                return SR_BGCLSJ;
            }

            public void setSR_BGCLSJ(String SR_BGCLSJ) {
                this.SR_BGCLSJ = SR_BGCLSJ;
            }

            public String getSR_TT() {
                return SR_TT;
            }

            public void setSR_TT(String SR_TT) {
                this.SR_TT = SR_TT;
            }

            public String getRUNNING_STATE() {
                return RUNNING_STATE;
            }

            public void setRUNNING_STATE(String RUNNING_STATE) {
                this.RUNNING_STATE = RUNNING_STATE;
            }

            public String getSR_PNA() {
                return SR_PNA;
            }

            public void setSR_PNA(String SR_PNA) {
                this.SR_PNA = SR_PNA;
            }

            public String getIS_CLRB() {
                return IS_CLRB;
            }

            public void setIS_CLRB(String IS_CLRB) {
                this.IS_CLRB = IS_CLRB;
            }

            public String getIS_CLRA() {
                return IS_CLRA;
            }

            public void setIS_CLRA(String IS_CLRA) {
                this.IS_CLRA = IS_CLRA;
            }

            public String getPRO_CLO_CODE() {
                return PRO_CLO_CODE;
            }

            public void setPRO_CLO_CODE(String PRO_CLO_CODE) {
                this.PRO_CLO_CODE = PRO_CLO_CODE;
            }

            public String getSR_NUMB_GG() {
                return SR_NUMB_GG;
            }

            public void setSR_NUMB_GG(String SR_NUMB_GG) {
                this.SR_NUMB_GG = SR_NUMB_GG;
            }

            public String getIS_FASH() {
                return IS_FASH;
            }

            public void setIS_FASH(String IS_FASH) {
                this.IS_FASH = IS_FASH;
            }

            public String getKNO_ZJJJFA() {
                return KNO_ZJJJFA;
            }

            public void setKNO_ZJJJFA(String KNO_ZJJJFA) {
                this.KNO_ZJJJFA = KNO_ZJJJFA;
            }

            public String getKNO_MAN_CHE_PER() {
                return KNO_MAN_CHE_PER;
            }

            public void setKNO_MAN_CHE_PER(String KNO_MAN_CHE_PER) {
                this.KNO_MAN_CHE_PER = KNO_MAN_CHE_PER;
            }

            public String getIS_PJWX() {
                return IS_PJWX;
            }

            public void setIS_PJWX(String IS_PJWX) {
                this.IS_PJWX = IS_PJWX;
            }

            public String getKNO_MAN_COM_EXP() {
                return KNO_MAN_COM_EXP;
            }

            public void setKNO_MAN_COM_EXP(String KNO_MAN_COM_EXP) {
                this.KNO_MAN_COM_EXP = KNO_MAN_COM_EXP;
            }

            public String getSR_PNO() {
                return SR_PNO;
            }

            public void setSR_PNO(String SR_PNO) {
                this.SR_PNO = SR_PNO;
            }

            public String getSR_B() {
                return SR_B;
            }

            public void setSR_B(String SR_B) {
                this.SR_B = SR_B;
            }

            public String getIS_CUSTNA() {
                return IS_CUSTNA;
            }

            public void setIS_CUSTNA(String IS_CUSTNA) {
                this.IS_CUSTNA = IS_CUSTNA;
            }

            public String getTaskNodeType() {
                return taskNodeType;
            }

            public void setTaskNodeType(String taskNodeType) {
                this.taskNodeType = taskNodeType;
            }

            public String getCREATOR() {
                return CREATOR;
            }

            public void setCREATOR(String CREATOR) {
                this.CREATOR = CREATOR;
            }

            public String getSR_IB() {
                return SR_IB;
            }

            public void setSR_IB(String SR_IB) {
                this.SR_IB = SR_IB;
            }

            public String getKNO_ZJJJGC() {
                return KNO_ZJJJGC;
            }

            public void setKNO_ZJJJGC(String KNO_ZJJJGC) {
                this.KNO_ZJJJGC = KNO_ZJJJGC;
            }

            public String getKNO_OPE() {
                return KNO_OPE;
            }

            public void setKNO_OPE(String KNO_OPE) {
                this.KNO_OPE = KNO_OPE;
            }

            public String getIS_JSFX() {
                return IS_JSFX;
            }

            public void setIS_JSFX(String IS_JSFX) {
                this.IS_JSFX = IS_JSFX;
            }

            public String getSR_NUMB_FF() {
                return SR_NUMB_FF;
            }

            public void setSR_NUMB_FF(String SR_NUMB_FF) {
                this.SR_NUMB_FF = SR_NUMB_FF;
            }

            public String getSR_ERXIAN() {
                return SR_ERXIAN;
            }

            public void setSR_ERXIAN(String SR_ERXIAN) {
                this.SR_ERXIAN = SR_ERXIAN;
            }

            public String getPRO_SFTG() {
                return PRO_SFTG;
            }

            public void setPRO_SFTG(String PRO_SFTG) {
                this.PRO_SFTG = PRO_SFTG;
            }

            public String getSR_XNTYF() {
                return SR_XNTYF;
            }

            public void setSR_XNTYF(String SR_XNTYF) {
                this.SR_XNTYF = SR_XNTYF;
            }

            public String getPIKEY() {
                return PIKEY;
            }

            public void setPIKEY(String PIKEY) {
                this.PIKEY = PIKEY;
            }

            public String getPRO_SOL() {
                return PRO_SOL;
            }

            public void setPRO_SOL(String PRO_SOL) {
                this.PRO_SOL = PRO_SOL;
            }

            public String getSR_QSSJ() {
                return SR_QSSJ;
            }

            public void setSR_QSSJ(String SR_QSSJ) {
                this.SR_QSSJ = SR_QSSJ;
            }

            public String getIS_KE() {
                return IS_KE;
            }

            public void setIS_KE(String IS_KE) {
                this.IS_KE = IS_KE;
            }

            public String getPRO_DES() {
                return PRO_DES;
            }

            public void setPRO_DES(String PRO_DES) {
                this.PRO_DES = PRO_DES;
            }

            public String getIS_INSERT_Q() {
                return IS_INSERT_Q;
            }

            public void setIS_INSERT_Q(String IS_INSERT_Q) {
                this.IS_INSERT_Q = IS_INSERT_Q;
            }

            public String getSR_FWBGTJZQ() {
                return SR_FWBGTJZQ;
            }

            public void setSR_FWBGTJZQ(String SR_FWBGTJZQ) {
                this.SR_FWBGTJZQ = SR_FWBGTJZQ;
            }

            public String getSR_HB() {
                return SR_HB;
            }

            public void setSR_HB(String SR_HB) {
                this.SR_HB = SR_HB;
            }

            public String getSR_NUMB_EE() {
                return SR_NUMB_EE;
            }

            public void setSR_NUMB_EE(String SR_NUMB_EE) {
                this.SR_NUMB_EE = SR_NUMB_EE;
            }

            public String getSR_EQSN() {
                return SR_EQSN;
            }

            public void setSR_EQSN(String SR_EQSN) {
                this.SR_EQSN = SR_EQSN;
            }

            public String getSR_ENDT() {
                return SR_ENDT;
            }

            public void setSR_ENDT(String SR_ENDT) {
                this.SR_ENDT = SR_ENDT;
            }

            public String getPRO_PRI() {
                return PRO_PRI;
            }

            public void setPRO_PRI(String PRO_PRI) {
                this.PRO_PRI = PRO_PRI;
            }

            public String getSR_FII() {
                return SR_FII;
            }

            public void setSR_FII(String SR_FII) {
                this.SR_FII = SR_FII;
            }

            public String getSR_FU() {
                return SR_FU;
            }

            public void setSR_FU(String SR_FU) {
                this.SR_FU = SR_FU;
            }

            public String getSE_SOLVER() {
                return SE_SOLVER;
            }

            public void setSE_SOLVER(String SE_SOLVER) {
                this.SE_SOLVER = SE_SOLVER;
            }

            public String getIS_INSERT_I() {
                return IS_INSERT_I;
            }

            public void setIS_INSERT_I(String IS_INSERT_I) {
                this.IS_INSERT_I = IS_INSERT_I;
            }

            public String getIS_INSERT_J() {
                return IS_INSERT_J;
            }

            public void setIS_INSERT_J(String IS_INSERT_J) {
                this.IS_INSERT_J = IS_INSERT_J;
            }

            public String getIS_INSERT_K() {
                return IS_INSERT_K;
            }

            public void setIS_INSERT_K(String IS_INSERT_K) {
                this.IS_INSERT_K = IS_INSERT_K;
            }

            public String getIS_INSERT_L() {
                return IS_INSERT_L;
            }

            public void setIS_INSERT_L(String IS_INSERT_L) {
                this.IS_INSERT_L = IS_INSERT_L;
            }

            public String getOutCome() {
                return outCome;
            }

            public void setOutCome(String outCome) {
                this.outCome = outCome;
            }

            public String getIS_INSERT_M() {
                return IS_INSERT_M;
            }

            public void setIS_INSERT_M(String IS_INSERT_M) {
                this.IS_INSERT_M = IS_INSERT_M;
            }

            public String getIS_INSERT_N() {
                return IS_INSERT_N;
            }

            public void setIS_INSERT_N(String IS_INSERT_N) {
                this.IS_INSERT_N = IS_INSERT_N;
            }

            public String getIS_NO() {
                return IS_NO;
            }

            public void setIS_NO(String IS_NO) {
                this.IS_NO = IS_NO;
            }

            public String getKNO_CON() {
                return KNO_CON;
            }

            public void setKNO_CON(String KNO_CON) {
                this.KNO_CON = KNO_CON;
            }

            public String getIS_INSERT_O() {
                return IS_INSERT_O;
            }

            public void setIS_INSERT_O(String IS_INSERT_O) {
                this.IS_INSERT_O = IS_INSERT_O;
            }

            public String getIS_INSERT_P() {
                return IS_INSERT_P;
            }

            public void setIS_INSERT_P(String IS_INSERT_P) {
                this.IS_INSERT_P = IS_INSERT_P;
            }

            public String getIS_INSERT_A() {
                return IS_INSERT_A;
            }

            public void setIS_INSERT_A(String IS_INSERT_A) {
                this.IS_INSERT_A = IS_INSERT_A;
            }

            public String getIS_INSERT_B() {
                return IS_INSERT_B;
            }

            public void setIS_INSERT_B(String IS_INSERT_B) {
                this.IS_INSERT_B = IS_INSERT_B;
            }

            public String getIS_INSERT_C() {
                return IS_INSERT_C;
            }

            public void setIS_INSERT_C(String IS_INSERT_C) {
                this.IS_INSERT_C = IS_INSERT_C;
            }

            public String getIS_INSERT_D() {
                return IS_INSERT_D;
            }

            public void setIS_INSERT_D(String IS_INSERT_D) {
                this.IS_INSERT_D = IS_INSERT_D;
            }

            public String getIS_INSERT_E() {
                return IS_INSERT_E;
            }

            public void setIS_INSERT_E(String IS_INSERT_E) {
                this.IS_INSERT_E = IS_INSERT_E;
            }

            public String getSR_NUMB_DD() {
                return SR_NUMB_DD;
            }

            public void setSR_NUMB_DD(String SR_NUMB_DD) {
                this.SR_NUMB_DD = SR_NUMB_DD;
            }

            public String getSR_OMM() {
                return SR_OMM;
            }

            public void setSR_OMM(String SR_OMM) {
                this.SR_OMM = SR_OMM;
            }

            public String getIS_INSERT_F() {
                return IS_INSERT_F;
            }

            public void setIS_INSERT_F(String IS_INSERT_F) {
                this.IS_INSERT_F = IS_INSERT_F;
            }

            public String getIS_INSERT_G() {
                return IS_INSERT_G;
            }

            public void setIS_INSERT_G(String IS_INSERT_G) {
                this.IS_INSERT_G = IS_INSERT_G;
            }

            public String getIS_INSERT_H() {
                return IS_INSERT_H;
            }

            public void setIS_INSERT_H(String IS_INSERT_H) {
                this.IS_INSERT_H = IS_INSERT_H;
            }

            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
            }

            public String getIS_BSFA() {
                return IS_BSFA;
            }

            public void setIS_BSFA(String IS_BSFA) {
                this.IS_BSFA = IS_BSFA;
            }

            public String getSR_MTIME() {
                return SR_MTIME;
            }

            public void setSR_MTIME(String SR_MTIME) {
                this.SR_MTIME = SR_MTIME;
            }

            public String getKNO_ZJDCGK() {
                return KNO_ZJDCGK;
            }

            public void setKNO_ZJDCGK(String KNO_ZJDCGK) {
                this.KNO_ZJDCGK = KNO_ZJDCGK;
            }

            public String getSR_SECHT() {
                return SR_SECHT;
            }

            public void setSR_SECHT(String SR_SECHT) {
                this.SR_SECHT = SR_SECHT;
            }

            public String getIS_SBJD() {
                return IS_SBJD;
            }

            public void setIS_SBJD(String IS_SBJD) {
                this.IS_SBJD = IS_SBJD;
            }

            public String getPRO_GBR() {
                return PRO_GBR;
            }

            public void setPRO_GBR(String PRO_GBR) {
                this.PRO_GBR = PRO_GBR;
            }

            public String getPRO_GBT() {
                return PRO_GBT;
            }

            public void setPRO_GBT(String PRO_GBT) {
                this.PRO_GBT = PRO_GBT;
            }

            public String getPRO_OPI() {
                return PRO_OPI;
            }

            public void setPRO_OPI(String PRO_OPI) {
                this.PRO_OPI = PRO_OPI;
            }

            public String getSR_JSJL() {
                return SR_JSJL;
            }

            public void setSR_JSJL(String SR_JSJL) {
                this.SR_JSJL = SR_JSJL;
            }

            public String getPRO_CSR() {
                return PRO_CSR;
            }

            public void setPRO_CSR(String PRO_CSR) {
                this.PRO_CSR = PRO_CSR;
            }

            public String getPRO_CST() {
                return PRO_CST;
            }

            public void setPRO_CST(String PRO_CST) {
                this.PRO_CST = PRO_CST;
            }

            public String getIS_MS() {
                return IS_MS;
            }

            public void setIS_MS(String IS_MS) {
                this.IS_MS = IS_MS;
            }

            public String getSR_NUMB_CC() {
                return SR_NUMB_CC;
            }

            public void setSR_NUMB_CC(String SR_NUMB_CC) {
                this.SR_NUMB_CC = SR_NUMB_CC;
            }

            public String getTITLE() {
                return TITLE;
            }

            public void setTITLE(String TITLE) {
                this.TITLE = TITLE;
            }

            public String getSR_JSJLF() {
                return SR_JSJLF;
            }

            public void setSR_JSJLF(String SR_JSJLF) {
                this.SR_JSJLF = SR_JSJLF;
            }

            public String getIS_PS() {
                return IS_PS;
            }

            public void setIS_PS(String IS_PS) {
                this.IS_PS = IS_PS;
            }

            public String getSR_TRIR() {
                return SR_TRIR;
            }

            public void setSR_TRIR(String SR_TRIR) {
                this.SR_TRIR = SR_TRIR;
            }

            public String getAPP_TIME() {
                return APP_TIME;
            }

            public void setAPP_TIME(String APP_TIME) {
                this.APP_TIME = APP_TIME;
            }

            public String getSR_BGCLR() {
                return SR_BGCLR;
            }

            public void setSR_BGCLR(String SR_BGCLR) {
                this.SR_BGCLR = SR_BGCLR;
            }

            public String getSR_CLRA() {
                return SR_CLRA;
            }

            public void setSR_CLRA(String SR_CLRA) {
                this.SR_CLRA = SR_CLRA;
            }

            public String getSR_PMAN() {
                return SR_PMAN;
            }

            public void setSR_PMAN(String SR_PMAN) {
                this.SR_PMAN = SR_PMAN;
            }

            public String getKNO_TYPE() {
                return KNO_TYPE;
            }

            public void setKNO_TYPE(String KNO_TYPE) {
                this.KNO_TYPE = KNO_TYPE;
            }

            public String getSR_FWDD() {
                return SR_FWDD;
            }

            public void setSR_FWDD(String SR_FWDD) {
                this.SR_FWDD = SR_FWDD;
            }

            public String getIS_FEOTC() {
                return IS_FEOTC;
            }

            public void setIS_FEOTC(String IS_FEOTC) {
                this.IS_FEOTC = IS_FEOTC;
            }

            public String getSR_NUMB_B() {
                return SR_NUMB_B;
            }

            public void setSR_NUMB_B(String SR_NUMB_B) {
                this.SR_NUMB_B = SR_NUMB_B;
            }

            public String getPRO_VER() {
                return PRO_VER;
            }

            public void setPRO_VER(String PRO_VER) {
                this.PRO_VER = PRO_VER;
            }

            public String getSR_NUMB_A() {
                return SR_NUMB_A;
            }

            public void setSR_NUMB_A(String SR_NUMB_A) {
                this.SR_NUMB_A = SR_NUMB_A;
            }

            public String getSR_CLS() {
                return SR_CLS;
            }

            public void setSR_CLS(String SR_CLS) {
                this.SR_CLS = SR_CLS;
            }

            public String getKNO_MAN_APP_TIME() {
                return KNO_MAN_APP_TIME;
            }

            public void setKNO_MAN_APP_TIME(String KNO_MAN_APP_TIME) {
                this.KNO_MAN_APP_TIME = KNO_MAN_APP_TIME;
            }

            public String getSR_CHECKWT() {
                return SR_CHECKWT;
            }

            public void setSR_CHECKWT(String SR_CHECKWT) {
                this.SR_CHECKWT = SR_CHECKWT;
            }

            public String getPRO_SRC() {
                return PRO_SRC;
            }

            public void setPRO_SRC(String PRO_SRC) {
                this.PRO_SRC = PRO_SRC;
            }

            public String getCUST_LOCATE() {
                return CUST_LOCATE;
            }

            public void setCUST_LOCATE(String CUST_LOCATE) {
                this.CUST_LOCATE = CUST_LOCATE;
            }

            public String getIS_ZJCS() {
                return IS_ZJCS;
            }

            public void setIS_ZJCS(String IS_ZJCS) {
                this.IS_ZJCS = IS_ZJCS;
            }

            public String getSR_STYP() {
                return SR_STYP;
            }

            public void setSR_STYP(String SR_STYP) {
                this.SR_STYP = SR_STYP;
            }

            public String getLIN_EVE_NUM() {
                return LIN_EVE_NUM;
            }

            public void setLIN_EVE_NUM(String LIN_EVE_NUM) {
                this.LIN_EVE_NUM = LIN_EVE_NUM;
            }

            public SpecificValueUpdateBean getSpecificValueUpdate() {
                return specificValueUpdate;
            }

            public void setSpecificValueUpdate(SpecificValueUpdateBean specificValueUpdate) {
                this.specificValueUpdate = specificValueUpdate;
            }

            public String getPRO_ANALYZE() {
                return PRO_ANALYZE;
            }

            public void setPRO_ANALYZE(String PRO_ANALYZE) {
                this.PRO_ANALYZE = PRO_ANALYZE;
            }

            public String getKNO_MAN_REL_PER() {
                return KNO_MAN_REL_PER;
            }

            public void setKNO_MAN_REL_PER(String KNO_MAN_REL_PER) {
                this.KNO_MAN_REL_PER = KNO_MAN_REL_PER;
            }

            public String getSR_QTFWF() {
                return SR_QTFWF;
            }

            public void setSR_QTFWF(String SR_QTFWF) {
                this.SR_QTFWF = SR_QTFWF;
            }

            public String getSR_PJAZ() {
                return SR_PJAZ;
            }

            public void setSR_PJAZ(String SR_PJAZ) {
                this.SR_PJAZ = SR_PJAZ;
            }

            public TaskStrategyBean getTaskStrategy() {
                return taskStrategy;
            }

            public void setTaskStrategy(TaskStrategyBean taskStrategy) {
                this.taskStrategy = taskStrategy;
            }

            public String getIS_EOTC() {
                return IS_EOTC;
            }

            public void setIS_EOTC(String IS_EOTC) {
                this.IS_EOTC = IS_EOTC;
            }

            public String getSR_BGFL() {
                return SR_BGFL;
            }

            public void setSR_BGFL(String SR_BGFL) {
                this.SR_BGFL = SR_BGFL;
            }

            public String getIS_AO() {
                return IS_AO;
            }

            public void setIS_AO(String IS_AO) {
                this.IS_AO = IS_AO;
            }

            public String getIS_PJCS() {
                return IS_PJCS;
            }

            public void setIS_PJCS(String IS_PJCS) {
                this.IS_PJCS = IS_PJCS;
            }

            public String getSR_RJAZF() {
                return SR_RJAZF;
            }

            public void setSR_RJAZF(String SR_RJAZF) {
                this.SR_RJAZF = SR_RJAZF;
            }

            public String getSR_JSFWF() {
                return SR_JSFWF;
            }

            public void setSR_JSFWF(String SR_JSFWF) {
                this.SR_JSFWF = SR_JSFWF;
            }

            public String getKNO_ZJGBYYFX() {
                return KNO_ZJGBYYFX;
            }

            public void setKNO_ZJGBYYFX(String KNO_ZJGBYYFX) {
                this.KNO_ZJGBYYFX = KNO_ZJGBYYFX;
            }

            public String getSR_CHECKDL() {
                return SR_CHECKDL;
            }

            public void setSR_CHECKDL(String SR_CHECKDL) {
                this.SR_CHECKDL = SR_CHECKDL;
            }

            public String getSR_NUMB_AA() {
                return SR_NUMB_AA;
            }

            public void setSR_NUMB_AA(String SR_NUMB_AA) {
                this.SR_NUMB_AA = SR_NUMB_AA;
            }

            public String getSR_JFBQF() {
                return SR_JFBQF;
            }

            public void setSR_JFBQF(String SR_JFBQF) {
                this.SR_JFBQF = SR_JFBQF;
            }

            public String getSR_EQDM() {
                return SR_EQDM;
            }

            public void setSR_EQDM(String SR_EQDM) {
                this.SR_EQDM = SR_EQDM;
            }

            public String getIS_QTZC() {
                return IS_QTZC;
            }

            public void setIS_QTZC(String IS_QTZC) {
                this.IS_QTZC = IS_QTZC;
            }

            public String getKNO_MAN__APP_PER() {
                return KNO_MAN__APP_PER;
            }

            public void setKNO_MAN__APP_PER(String KNO_MAN__APP_PER) {
                this.KNO_MAN__APP_PER = KNO_MAN__APP_PER;
            }

            public String getSR_JSFW() {
                return SR_JSFW;
            }

            public void setSR_JSFW(String SR_JSFW) {
                this.SR_JSFW = SR_JSFW;
            }

            public String getSR_XCZBF() {
                return SR_XCZBF;
            }

            public void setSR_XCZBF(String SR_XCZBF) {
                this.SR_XCZBF = SR_XCZBF;
            }

            public String getSR_NUMB_M() {
                return SR_NUMB_M;
            }

            public void setSR_NUMB_M(String SR_NUMB_M) {
                this.SR_NUMB_M = SR_NUMB_M;
            }

            public String getSR_NUMB_K() {
                return SR_NUMB_K;
            }

            public void setSR_NUMB_K(String SR_NUMB_K) {
                this.SR_NUMB_K = SR_NUMB_K;
            }

            public String getIS_PRONO() {
                return IS_PRONO;
            }

            public void setIS_PRONO(String IS_PRONO) {
                this.IS_PRONO = IS_PRONO;
            }

            public String getOutComeDesc() {
                return outComeDesc;
            }

            public void setOutComeDesc(String outComeDesc) {
                this.outComeDesc = outComeDesc;
            }

            public String getSE_TIME() {
                return SE_TIME;
            }

            public void setSE_TIME(String SE_TIME) {
                this.SE_TIME = SE_TIME;
            }

            public String getSR_NUMB_L() {
                return SR_NUMB_L;
            }

            public void setSR_NUMB_L(String SR_NUMB_L) {
                this.SR_NUMB_L = SR_NUMB_L;
            }

            public String getSR_NUMB_I() {
                return SR_NUMB_I;
            }

            public void setSR_NUMB_I(String SR_NUMB_I) {
                this.SR_NUMB_I = SR_NUMB_I;
            }

            public String getSR_NUMB_J() {
                return SR_NUMB_J;
            }

            public void setSR_NUMB_J(String SR_NUMB_J) {
                this.SR_NUMB_J = SR_NUMB_J;
            }

            public String getSR_CHECKT() {
                return SR_CHECKT;
            }

            public void setSR_CHECKT(String SR_CHECKT) {
                this.SR_CHECKT = SR_CHECKT;
            }

            public String getKNO_LIN_SEL_PAK() {
                return KNO_LIN_SEL_PAK;
            }

            public void setKNO_LIN_SEL_PAK(String KNO_LIN_SEL_PAK) {
                this.KNO_LIN_SEL_PAK = KNO_LIN_SEL_PAK;
            }

            public String getSR_NUMB_G() {
                return SR_NUMB_G;
            }

            public void setSR_NUMB_G(String SR_NUMB_G) {
                this.SR_NUMB_G = SR_NUMB_G;
            }

            public String getSR_NUMB_H() {
                return SR_NUMB_H;
            }

            public void setSR_NUMB_H(String SR_NUMB_H) {
                this.SR_NUMB_H = SR_NUMB_H;
            }

            public String getSR_NUMB_E() {
                return SR_NUMB_E;
            }

            public void setSR_NUMB_E(String SR_NUMB_E) {
                this.SR_NUMB_E = SR_NUMB_E;
            }

            public String getSR_CNA() {
                return SR_CNA;
            }

            public void setSR_CNA(String SR_CNA) {
                this.SR_CNA = SR_CNA;
            }

            public String getSR_NUMB_F() {
                return SR_NUMB_F;
            }

            public void setSR_NUMB_F(String SR_NUMB_F) {
                this.SR_NUMB_F = SR_NUMB_F;
            }

            public String getSR_NUMB_C() {
                return SR_NUMB_C;
            }

            public void setSR_NUMB_C(String SR_NUMB_C) {
                this.SR_NUMB_C = SR_NUMB_C;
            }

            public String getSR_NUMB_BB() {
                return SR_NUMB_BB;
            }

            public void setSR_NUMB_BB(String SR_NUMB_BB) {
                this.SR_NUMB_BB = SR_NUMB_BB;
            }

            public String getSR_NUMB_D() {
                return SR_NUMB_D;
            }

            public void setSR_NUMB_D(String SR_NUMB_D) {
                this.SR_NUMB_D = SR_NUMB_D;
            }

            public String getSR_RJSJ() {
                return SR_RJSJ;
            }

            public void setSR_RJSJ(String SR_RJSJ) {
                this.SR_RJSJ = SR_RJSJ;
            }

            public String getCREATEDATE() {
                return CREATEDATE;
            }

            public void setCREATEDATE(String CREATEDATE) {
                this.CREATEDATE = CREATEDATE;
            }

            public String getSR_IP() {
                return SR_IP;
            }

            public void setSR_IP(String SR_IP) {
                this.SR_IP = SR_IP;
            }

            public String getIS_PRONA() {
                return IS_PRONA;
            }

            public void setIS_PRONA(String IS_PRONA) {
                this.IS_PRONA = IS_PRONA;
            }

            public String getPRO_DEBUGER() {
                return PRO_DEBUGER;
            }

            public void setPRO_DEBUGER(String PRO_DEBUGER) {
                this.PRO_DEBUGER = PRO_DEBUGER;
            }

            public String getPRO_INVESTIGATE() {
                return PRO_INVESTIGATE;
            }

            public void setPRO_INVESTIGATE(String PRO_INVESTIGATE) {
                this.PRO_INVESTIGATE = PRO_INVESTIGATE;
            }

            public String getSR_XNTY() {
                return SR_XNTY;
            }

            public void setSR_XNTY(String SR_XNTY) {
                this.SR_XNTY = SR_XNTY;
            }

            public String getPRO_ANLYZEPEOPLE() {
                return PRO_ANLYZEPEOPLE;
            }

            public void setPRO_ANLYZEPEOPLE(String PRO_ANLYZEPEOPLE) {
                this.PRO_ANLYZEPEOPLE = PRO_ANLYZEPEOPLE;
            }

            public String getSR_ETIME() {
                return SR_ETIME;
            }

            public void setSR_ETIME(String SR_ETIME) {
                this.SR_ETIME = SR_ETIME;
            }

            public String getPRO_URG() {
                return PRO_URG;
            }

            public void setPRO_URG(String PRO_URG) {
                this.PRO_URG = PRO_URG;
            }

            public String getPRO_PLA_DATE() {
                return PRO_PLA_DATE;
            }

            public void setPRO_PLA_DATE(String PRO_PLA_DATE) {
                this.PRO_PLA_DATE = PRO_PLA_DATE;
            }

            public String getKNO_KEYWORD() {
                return KNO_KEYWORD;
            }

            public void setKNO_KEYWORD(String KNO_KEYWORD) {
                this.KNO_KEYWORD = KNO_KEYWORD;
            }

            public String getSR_SRTABLE() {
                return SR_SRTABLE;
            }

            public void setSR_SRTABLE(String SR_SRTABLE) {
                this.SR_SRTABLE = SR_SRTABLE;
            }

            public String getPRO_CLT() {
                return PRO_CLT;
            }

            public void setPRO_CLT(String PRO_CLT) {
                this.PRO_CLT = PRO_CLT;
            }

            public String getIS_EI() {
                return IS_EI;
            }

            public void setIS_EI(String IS_EI) {
                this.IS_EI = IS_EI;
            }

            public String getIS_CLSJB() {
                return IS_CLSJB;
            }

            public void setIS_CLSJB(String IS_CLSJB) {
                this.IS_CLSJB = IS_CLSJB;
            }

            public String getIS_CLSJA() {
                return IS_CLSJA;
            }

            public void setIS_CLSJA(String IS_CLSJA) {
                this.IS_CLSJA = IS_CLSJA;
            }

            public String getSR_JSSJ() {
                return SR_JSSJ;
            }

            public void setSR_JSSJ(String SR_JSSJ) {
                this.SR_JSSJ = SR_JSSJ;
            }

            public String getIS_ZJPZ() {
                return IS_ZJPZ;
            }

            public void setIS_ZJPZ(String IS_ZJPZ) {
                this.IS_ZJPZ = IS_ZJPZ;
            }

            public String getPRO_CLR() {
                return PRO_CLR;
            }

            public void setPRO_CLR(String PRO_CLR) {
                this.PRO_CLR = PRO_CLR;
            }

            public String getSR_RJTZ() {
                return SR_RJTZ;
            }

            public void setSR_RJTZ(String SR_RJTZ) {
                this.SR_RJTZ = SR_RJTZ;
            }

            public String getREL_APP() {
                return REL_APP;
            }

            public void setREL_APP(String REL_APP) {
                this.REL_APP = REL_APP;
            }

            public String getPRO_SOLUTION() {
                return PRO_SOLUTION;
            }

            public void setPRO_SOLUTION(String PRO_SOLUTION) {
                this.PRO_SOLUTION = PRO_SOLUTION;
            }

            public String getSR_SJQY() {
                return SR_SJQY;
            }

            public void setSR_SJQY(String SR_SJQY) {
                this.SR_SJQY = SR_SJQY;
            }

            public String getSR_CLSJA() {
                return SR_CLSJA;
            }

            public void setSR_CLSJA(String SR_CLSJA) {
                this.SR_CLSJA = SR_CLSJA;
            }

            public String getSR_QTZYCN() {
                return SR_QTZYCN;
            }

            public void setSR_QTZYCN(String SR_QTZYCN) {
                this.SR_QTZYCN = SR_QTZYCN;
            }

            public String getSR_QTFW() {
                return SR_QTFW;
            }

            public void setSR_QTFW(String SR_QTFW) {
                this.SR_QTFW = SR_QTFW;
            }

            public String getIS_ST() {
                return IS_ST;
            }

            public void setIS_ST(String IS_ST) {
                this.IS_ST = IS_ST;
            }

            public String getSR_XCZB() {
                return SR_XCZB;
            }

            public void setSR_XCZB(String SR_XCZB) {
                this.SR_XCZB = SR_XCZB;
            }

            public String getSR_WOTA() {
                return SR_WOTA;
            }

            public void setSR_WOTA(String SR_WOTA) {
                this.SR_WOTA = SR_WOTA;
            }

            public String getSR_PL() {
                return SR_PL;
            }

            public void setSR_PL(String SR_PL) {
                this.SR_PL = SR_PL;
            }

            public String getSR_SBAZF() {
                return SR_SBAZF;
            }

            public void setSR_SBAZF(String SR_SBAZF) {
                this.SR_SBAZF = SR_SBAZF;
            }

            public String getSR_SFZBXM() {
                return SR_SFZBXM;
            }

            public void setSR_SFZBXM(String SR_SFZBXM) {
                this.SR_SFZBXM = SR_SFZBXM;
            }

            public String getSOL_PRO() {
                return SOL_PRO;
            }

            public void setSOL_PRO(String SOL_PRO) {
                this.SOL_PRO = SOL_PRO;
            }

            public String getSR_NUMB_MM() {
                return SR_NUMB_MM;
            }

            public void setSR_NUMB_MM(String SR_NUMB_MM) {
                this.SR_NUMB_MM = SR_NUMB_MM;
            }

            public String getIS_RCZJBG() {
                return IS_RCZJBG;
            }

            public void setIS_RCZJBG(String IS_RCZJBG) {
                this.IS_RCZJBG = IS_RCZJBG;
            }

            public String getSR_RJAZ() {
                return SR_RJAZ;
            }

            public void setSR_RJAZ(String SR_RJAZ) {
                this.SR_RJAZ = SR_RJAZ;
            }

            public String getSR_CON() {
                return SR_CON;
            }

            public void setSR_CON(String SR_CON) {
                this.SR_CON = SR_CON;
            }

            public String getAPP_OPI() {
                return APP_OPI;
            }

            public void setAPP_OPI(String APP_OPI) {
                this.APP_OPI = APP_OPI;
            }

            public String getOBJ_PROI() {
                return OBJ_PROI;
            }

            public void setOBJ_PROI(String OBJ_PROI) {
                this.OBJ_PROI = OBJ_PROI;
            }

            public String getIS_PJPZ() {
                return IS_PJPZ;
            }

            public void setIS_PJPZ(String IS_PJPZ) {
                this.IS_PJPZ = IS_PJPZ;
            }

            public String getIS_ZJPD() {
                return IS_ZJPD;
            }

            public void setIS_ZJPD(String IS_ZJPD) {
                this.IS_ZJPD = IS_ZJPD;
            }

            public String getSE_SOUND() {
                return SE_SOUND;
            }

            public void setSE_SOUND(String SE_SOUND) {
                this.SE_SOUND = SE_SOUND;
            }

            public String getPRO_CJR() {
                return PRO_CJR;
            }

            public void setPRO_CJR(String PRO_CJR) {
                this.PRO_CJR = PRO_CJR;
            }

            public String getPRO_CJT() {
                return PRO_CJT;
            }

            public void setPRO_CJT(String PRO_CJT) {
                this.PRO_CJT = PRO_CJT;
            }

            public String getCHE_OPI() {
                return CHE_OPI;
            }

            public void setCHE_OPI(String CHE_OPI) {
                this.CHE_OPI = CHE_OPI;
            }

            public String getSR_SERT() {
                return SR_SERT;
            }

            public void setSR_SERT(String SR_SERT) {
                this.SR_SERT = SR_SERT;
            }

            public String getSR_NUMB_LL() {
                return SR_NUMB_LL;
            }

            public void setSR_NUMB_LL(String SR_NUMB_LL) {
                this.SR_NUMB_LL = SR_NUMB_LL;
            }

            public String getCHE_PER() {
                return CHE_PER;
            }

            public void setCHE_PER(String CHE_PER) {
                this.CHE_PER = CHE_PER;
            }

            public String getIS_BU() {
                return IS_BU;
            }

            public void setIS_BU(String IS_BU) {
                this.IS_BU = IS_BU;
            }

            public String getSE_PERFEVT() {
                return SE_PERFEVT;
            }

            public void setSE_PERFEVT(String SE_PERFEVT) {
                this.SE_PERFEVT = SE_PERFEVT;
            }

            public String getIS_DIS_D() {
                return IS_DIS_D;
            }

            public void setIS_DIS_D(String IS_DIS_D) {
                this.IS_DIS_D = IS_DIS_D;
            }

            public String getIS_DIS_E() {
                return IS_DIS_E;
            }

            public void setIS_DIS_E(String IS_DIS_E) {
                this.IS_DIS_E = IS_DIS_E;
            }

            public String getSR_NO() {
                return SR_NO;
            }

            public void setSR_NO(String SR_NO) {
                this.SR_NO = SR_NO;
            }

            public String getIS_DIS_B() {
                return IS_DIS_B;
            }

            public void setIS_DIS_B(String IS_DIS_B) {
                this.IS_DIS_B = IS_DIS_B;
            }

            public String getKNO_YYFX() {
                return KNO_YYFX;
            }

            public void setKNO_YYFX(String KNO_YYFX) {
                this.KNO_YYFX = KNO_YYFX;
            }

            public String getIS_DIS_C() {
                return IS_DIS_C;
            }

            public void setIS_DIS_C(String IS_DIS_C) {
                this.IS_DIS_C = IS_DIS_C;
            }

            public String getIS_DIS_A() {
                return IS_DIS_A;
            }

            public void setIS_DIS_A(String IS_DIS_A) {
                this.IS_DIS_A = IS_DIS_A;
            }

            public String getIS_DIS_K() {
                return IS_DIS_K;
            }

            public void setIS_DIS_K(String IS_DIS_K) {
                this.IS_DIS_K = IS_DIS_K;
            }

            public String getIS_DIS_J() {
                return IS_DIS_J;
            }

            public void setIS_DIS_J(String IS_DIS_J) {
                this.IS_DIS_J = IS_DIS_J;
            }

            public String getIS_DIS_M() {
                return IS_DIS_M;
            }

            public void setIS_DIS_M(String IS_DIS_M) {
                this.IS_DIS_M = IS_DIS_M;
            }

            public String getIS_DIS_L() {
                return IS_DIS_L;
            }

            public void setIS_DIS_L(String IS_DIS_L) {
                this.IS_DIS_L = IS_DIS_L;
            }

            public String getIS_DL() {
                return IS_DL;
            }

            public void setIS_DL(String IS_DL) {
                this.IS_DL = IS_DL;
            }

            public String getIS_ZSKJL() {
                return IS_ZSKJL;
            }

            public void setIS_ZSKJL(String IS_ZSKJL) {
                this.IS_ZSKJL = IS_ZSKJL;
            }

            public String getIS_DIS_G() {
                return IS_DIS_G;
            }

            public void setIS_DIS_G(String IS_DIS_G) {
                this.IS_DIS_G = IS_DIS_G;
            }

            public String getIS_DIS_I() {
                return IS_DIS_I;
            }

            public void setIS_DIS_I(String IS_DIS_I) {
                this.IS_DIS_I = IS_DIS_I;
            }

            public String getSR_SECSP() {
                return SR_SECSP;
            }

            public void setSR_SECSP(String SR_SECSP) {
                this.SR_SECSP = SR_SECSP;
            }

            public String getIS_DIS_H() {
                return IS_DIS_H;
            }

            public void setIS_DIS_H(String IS_DIS_H) {
                this.IS_DIS_H = IS_DIS_H;
            }

            public String getSR_SBQYF() {
                return SR_SBQYF;
            }

            public void setSR_SBQYF(String SR_SBQYF) {
                this.SR_SBQYF = SR_SBQYF;
            }

            public String getSR_NUMB_KK() {
                return SR_NUMB_KK;
            }

            public void setSR_NUMB_KK(String SR_NUMB_KK) {
                this.SR_NUMB_KK = SR_NUMB_KK;
            }

            public String getIS_DIS_O() {
                return IS_DIS_O;
            }

            public void setIS_DIS_O(String IS_DIS_O) {
                this.IS_DIS_O = IS_DIS_O;
            }

            public String getIS_DIS_N() {
                return IS_DIS_N;
            }

            public void setIS_DIS_N(String IS_DIS_N) {
                this.IS_DIS_N = IS_DIS_N;
            }

            public String getIS_DIS_Q() {
                return IS_DIS_Q;
            }

            public void setIS_DIS_Q(String IS_DIS_Q) {
                this.IS_DIS_Q = IS_DIS_Q;
            }

            public String getIS_DIS_P() {
                return IS_DIS_P;
            }

            public void setIS_DIS_P(String IS_DIS_P) {
                this.IS_DIS_P = IS_DIS_P;
            }

            public String getSR_SBAZ() {
                return SR_SBAZ;
            }

            public void setSR_SBAZ(String SR_SBAZ) {
                this.SR_SBAZ = SR_SBAZ;
            }

            public String getSE_PERFECT() {
                return SE_PERFECT;
            }

            public void setSE_PERFECT(String SE_PERFECT) {
                this.SE_PERFECT = SE_PERFECT;
            }

            public String getSR_JFBQ() {
                return SR_JFBQ;
            }

            public void setSR_JFBQ(String SR_JFBQ) {
                this.SR_JFBQ = SR_JFBQ;
            }

            public String getSR_SBQY() {
                return SR_SBQY;
            }

            public void setSR_SBQY(String SR_SBQY) {
                this.SR_SBQY = SR_SBQY;
            }

            public String getSR_MP() {
                return SR_MP;
            }

            public void setSR_MP(String SR_MP) {
                this.SR_MP = SR_MP;
            }

            public String getSR_QUYU() {
                return SR_QUYU;
            }

            public void setSR_QUYU(String SR_QUYU) {
                this.SR_QUYU = SR_QUYU;
            }

            public String getPROCESS_KEY() {
                return PROCESS_KEY;
            }

            public void setPROCESS_KEY(String PROCESS_KEY) {
                this.PROCESS_KEY = PROCESS_KEY;
            }

            public String getCurrentAssignee() {
                return currentAssignee;
            }

            public void setCurrentAssignee(String currentAssignee) {
                this.currentAssignee = currentAssignee;
            }

            public static class SpecificValueUpdateBean {
                /**
                 * formItemValue : {"value":"currentTime","name":"当前时间"}
                 * formItem : {"columnsJson":null,"id":"datadisplaydate_1","nodeType":"DataDisplayDate","dmAttrDisplayName":"知识管理提交时间","isCanAdd":true,"precision":null,"isCanUpdate":true,"seeHisWorkOrderInfo":true,"isCanDelete":true,"ID":"datadisplaydate_1","configModifiedField":null,"bmModelName":"KNO","name":"知识管理提交时间","value_label":null,"isVisible":true,"value":"","modified":false,"modelName":"KNO","sectionId":"Section0","srclib":"","required":false,"isExecute":false,"dmAttrName":"KNO_MAN_SUB_TIME","maxChars":null,"dataProvider":null,"srclib_label":null}
                 */

                private List<ValuesBean> values;

                public List<ValuesBean> getValues() {
                    return values;
                }

                public void setValues(List<ValuesBean> values) {
                    this.values = values;
                }

                public static class ValuesBean {
                    /**
                     * value : currentTime
                     * name : 当前时间
                     */

                    private FormItemValueBean formItemValue;
                    /**
                     * columnsJson : null
                     * id : datadisplaydate_1
                     * nodeType : DataDisplayDate
                     * dmAttrDisplayName : 知识管理提交时间
                     * isCanAdd : true
                     * precision : null
                     * isCanUpdate : true
                     * seeHisWorkOrderInfo : true
                     * isCanDelete : true
                     * ID : datadisplaydate_1
                     * configModifiedField : null
                     * bmModelName : KNO
                     * name : 知识管理提交时间
                     * value_label : null
                     * isVisible : true
                     * value :
                     * modified : false
                     * modelName : KNO
                     * sectionId : Section0
                     * srclib :
                     * required : false
                     * isExecute : false
                     * dmAttrName : KNO_MAN_SUB_TIME
                     * maxChars : null
                     * dataProvider : null
                     * srclib_label : null
                     */

                    private FormItemBean formItem;

                    public FormItemValueBean getFormItemValue() {
                        return formItemValue;
                    }

                    public void setFormItemValue(FormItemValueBean formItemValue) {
                        this.formItemValue = formItemValue;
                    }

                    public FormItemBean getFormItem() {
                        return formItem;
                    }

                    public void setFormItem(FormItemBean formItem) {
                        this.formItem = formItem;
                    }

                    public static class FormItemValueBean {
                        private String value;
                        private String name;

                        public String getValue() {
                            return value;
                        }

                        public void setValue(String value) {
                            this.value = value;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }

                    public static class FormItemBean {
                        private Object columnsJson;
                        private String id;
                        private String nodeType;
                        private String dmAttrDisplayName;
                        private boolean isCanAdd;
                        private Object precision;
                        private boolean isCanUpdate;
                        private boolean seeHisWorkOrderInfo;
                        private boolean isCanDelete;
                        private String ID;
                        private Object configModifiedField;
                        private String bmModelName;
                        private String name;
                        private Object value_label;
                        private boolean isVisible;
                        private String value;
                        private boolean modified;
                        private String modelName;
                        private String sectionId;
                        private String srclib;
                        private boolean required;
                        private boolean isExecute;
                        private String dmAttrName;
                        private Object maxChars;
                        private Object dataProvider;
                        private Object srclib_label;

                        public Object getColumnsJson() {
                            return columnsJson;
                        }

                        public void setColumnsJson(Object columnsJson) {
                            this.columnsJson = columnsJson;
                        }

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }

                        public String getNodeType() {
                            return nodeType;
                        }

                        public void setNodeType(String nodeType) {
                            this.nodeType = nodeType;
                        }

                        public String getDmAttrDisplayName() {
                            return dmAttrDisplayName;
                        }

                        public void setDmAttrDisplayName(String dmAttrDisplayName) {
                            this.dmAttrDisplayName = dmAttrDisplayName;
                        }

                        public boolean isIsCanAdd() {
                            return isCanAdd;
                        }

                        public void setIsCanAdd(boolean isCanAdd) {
                            this.isCanAdd = isCanAdd;
                        }

                        public Object getPrecision() {
                            return precision;
                        }

                        public void setPrecision(Object precision) {
                            this.precision = precision;
                        }

                        public boolean isIsCanUpdate() {
                            return isCanUpdate;
                        }

                        public void setIsCanUpdate(boolean isCanUpdate) {
                            this.isCanUpdate = isCanUpdate;
                        }

                        public boolean isSeeHisWorkOrderInfo() {
                            return seeHisWorkOrderInfo;
                        }

                        public void setSeeHisWorkOrderInfo(boolean seeHisWorkOrderInfo) {
                            this.seeHisWorkOrderInfo = seeHisWorkOrderInfo;
                        }

                        public boolean isIsCanDelete() {
                            return isCanDelete;
                        }

                        public void setIsCanDelete(boolean isCanDelete) {
                            this.isCanDelete = isCanDelete;
                        }

                        public String getID() {
                            return ID;
                        }

                        public void setID(String ID) {
                            this.ID = ID;
                        }

                        public Object getConfigModifiedField() {
                            return configModifiedField;
                        }

                        public void setConfigModifiedField(Object configModifiedField) {
                            this.configModifiedField = configModifiedField;
                        }

                        public String getBmModelName() {
                            return bmModelName;
                        }

                        public void setBmModelName(String bmModelName) {
                            this.bmModelName = bmModelName;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public Object getValue_label() {
                            return value_label;
                        }

                        public void setValue_label(Object value_label) {
                            this.value_label = value_label;
                        }

                        public boolean isIsVisible() {
                            return isVisible;
                        }

                        public void setIsVisible(boolean isVisible) {
                            this.isVisible = isVisible;
                        }

                        public String getValue() {
                            return value;
                        }

                        public void setValue(String value) {
                            this.value = value;
                        }

                        public boolean isModified() {
                            return modified;
                        }

                        public void setModified(boolean modified) {
                            this.modified = modified;
                        }

                        public String getModelName() {
                            return modelName;
                        }

                        public void setModelName(String modelName) {
                            this.modelName = modelName;
                        }

                        public String getSectionId() {
                            return sectionId;
                        }

                        public void setSectionId(String sectionId) {
                            this.sectionId = sectionId;
                        }

                        public String getSrclib() {
                            return srclib;
                        }

                        public void setSrclib(String srclib) {
                            this.srclib = srclib;
                        }

                        public boolean isRequired() {
                            return required;
                        }

                        public void setRequired(boolean required) {
                            this.required = required;
                        }

                        public boolean isIsExecute() {
                            return isExecute;
                        }

                        public void setIsExecute(boolean isExecute) {
                            this.isExecute = isExecute;
                        }

                        public String getDmAttrName() {
                            return dmAttrName;
                        }

                        public void setDmAttrName(String dmAttrName) {
                            this.dmAttrName = dmAttrName;
                        }

                        public Object getMaxChars() {
                            return maxChars;
                        }

                        public void setMaxChars(Object maxChars) {
                            this.maxChars = maxChars;
                        }

                        public Object getDataProvider() {
                            return dataProvider;
                        }

                        public void setDataProvider(Object dataProvider) {
                            this.dataProvider = dataProvider;
                        }

                        public Object getSrclib_label() {
                            return srclib_label;
                        }

                        public void setSrclib_label(Object srclib_label) {
                            this.srclib_label = srclib_label;
                        }
                    }
                }
            }

            public static class TaskStrategyBean {
                private String strategyKey;
                private String strategyValue;

                public String getStrategyKey() {
                    return strategyKey;
                }

                public void setStrategyKey(String strategyKey) {
                    this.strategyKey = strategyKey;
                }

                public String getStrategyValue() {
                    return strategyValue;
                }

                public void setStrategyValue(String strategyValue) {
                    this.strategyValue = strategyValue;
                }
            }
        }
    }
}
