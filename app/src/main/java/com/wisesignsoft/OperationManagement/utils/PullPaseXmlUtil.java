package com.wisesignsoft.OperationManagement.utils;

import android.util.Log;
import android.util.Xml;

import com.wisesignsoft.OperationManagement.bean.BMForm;
import com.wisesignsoft.OperationManagement.bean.ButtonModel;
import com.wisesignsoft.OperationManagement.bean.Section;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.mview.BMFormDataLinkage;

import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by ycs on 2016/11/29.
 */

public class PullPaseXmlUtil {
    public static List pase(String xmlStr) {
        boolean isStart = false;
        boolean isStart2 = false;
        BMForm bmForm = null;
        Section section = null;
        BMFormDataLinkage bmFormDataLinkage = null;
        ButtonModel button = null;
        RealmList<WorkOrder> list = null;
        List<ButtonModel.NextNode> list2 = null;
        List datas = new ArrayList<>();
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(xmlStr));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (isStart) {
                            WorkOrder order = paseWorkOrder(parser);
                            if (list != null) {
                                list.add(order);
                            }
                        }
                        if ("Section".equals(name)) {
                            section = paseSection(parser);
                            list = new RealmList<>();
                            isStart = true;
                        }
                        if (isStart2) {
                            ButtonModel.NextNode nextNode = passNextNode(parser);
                            if (list2 != null) {
                                list2.add(nextNode);
                            }
                        }
                        if ("Button".equals(name)) {
                            button = passButton(parser);
                            list2 = new ArrayList<>();
                            isStart2 = true;
                        }
                        if ("BMForm".equals(name)) {
                            bmForm = passBMForm(parser);
                        }
                        if ("BMFormDataLinkage".equals(name)) {
                            bmFormDataLinkage = paseBMFormDataLinkage(parser);
                            datas.add(bmFormDataLinkage);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String end = parser.getName();
                        if ("Section".equals(end)) {
                            section.setSection(list);
                            datas.add(section);
                            isStart = false;
                            list = null;
                            section = null;
                        } else if ("Button".equals(end)) {
                            button.setNextNode(list2);
                            datas.add(button);
                            isStart2 = false;
                            list2 = null;
                            button = null;
                        } else if ("BMForm".equals(end)) {
                            datas.add(0, bmForm);
                            bmForm = null;
                        }
                        break;
                }
                event = parser.next();
            }
        } catch (Exception e) {
            Log.i("YCS", "pase: xml解析异常了");
            e.printStackTrace();
        }
        return datas;
    }

    private static BMFormDataLinkage paseBMFormDataLinkage(XmlPullParser parser) {
        BMFormDataLinkage bmFormDataLinkage = new BMFormDataLinkage();
        int count = parser.getAttributeCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String name = parser.getAttributeName(i);
                String value = parser.getAttributeValue(i);
                if ("dataLinkageJson".equals(name)) {
                    bmFormDataLinkage.setDataLinkageJson(value);
                }
            }
        }
        return bmFormDataLinkage;
    }

    private static BMForm passBMForm(XmlPullParser parser) {
        BMForm bmForm = new BMForm();
        int count = parser.getAttributeCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String name = parser.getAttributeName(i);
                String value = parser.getAttributeValue(i);
                if ("bmModelName".equals(name)) {
                    bmForm.setBmModelName(value);
                } else if ("superBmModelName".equals(name)) {
                    bmForm.setSuperBmModelName(value);
                } else if ("bmIcon".equals(name)) {
                    bmForm.setBmIcon(value);
                } else if ("bmHelp".equals(name)) {
                    bmForm.setBmHelp(value);
                } else if ("bmDisplayName".equals(name)) {
                    bmForm.setBmDisplayName(value);
                } else if ("width".equals(name)) {
                    bmForm.setWidth(value);
                } else if ("height".equals(name)) {
                    bmForm.setHeight(value);
                } else if ("order".equals(name)) {
                    bmForm.setOrder(value);
                } else if ("modelName".equals(name)) {
                    bmForm.setModelName(value);
                } else if ("dmDisplayName".equals(name)) {
                    bmForm.setDmDisplayName(value);
                } else if ("hasFlowChart".equals(name)) {
                    bmForm.setHasFlowChart(value);
                } else if ("hasDataRelation".equals(name)) {
                    bmForm.setHasDataRelation(value);
                } else if ("hasAuditRecord".equals(name)) {
                    bmForm.setHasAuditRecord(value);
                } else if ("hasOperLog".equals(name)) {
                    bmForm.setHasOperLog(value);
                } else if ("hasKB".equals(name)) {
                    bmForm.setHasKB(value);
                } else if ("hasbmHelp".equals(name)) {
                    bmForm.setHasbmHelp(value);
                } else if ("conditionJudgment".equals(name)) {
                    bmForm.setConditionJudgment(value);
                } else if ("sortingSet".equals(name)) {
                    bmForm.setSortingSet(value);
                } else if ("specificValueUpdate".equals(name)) {
                    bmForm.setSpecificValueUpdate(value);
                }
            }
        }
        return bmForm;
    }

    /**
     * 解析section
     *
     * @param parser
     * @return
     */
    private static Section paseSection(XmlPullParser parser) {
        Section section = new Section();
        int count = parser.getAttributeCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String name = parser.getAttributeName(i);
                if ("ID".equals(name)) {
                    section.setID(parser.getAttributeValue(i));
                } else if ("label".equals(name)) {
                    section.setLabel(parser.getAttributeValue(i));
                } else if ("isCurrent".equals(name)) {
                    section.setCurrent(Boolean.parseBoolean(parser.getAttributeValue(i)));
                }
            }
        }
        return section;
    }

    /**
     * 解析order
     *
     * @param parser
     * @return
     */
    private static WorkOrder paseWorkOrder(XmlPullParser parser) {
        WorkOrder order = new WorkOrder();
        String viewName = parser.getName();
        int count = parser.getAttributeCount();
        order.setViewName(viewName);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String name = parser.getAttributeName(i);
                String value = parser.getAttributeValue(i);
                if ("isVisible".equals(name)) {
                    order.setVisible(Boolean.parseBoolean(value));
                } else if ("modified".equals(name)) {
                    order.setModified(Boolean.parseBoolean(value));
                } else if ("seeHisWorkOrderInfo".equals(name)) {
                    order.setSeeHisWorkOrderInfo(Boolean.parseBoolean(value));
                } else if ("hasBusinessKey".equals(name)) {
                    order.setHasBusinessKey(Boolean.parseBoolean(value));
                } else if ("value".equals(name)) {
                    order.setValue(parser.getAttributeValue(i));
                } else if ("maxChars".equals(name)) {
                    order.setMaxChars(Integer.parseInt(parser.getAttributeValue(i)));
                } else if ("isDispatcherEvent".equals(name)) {
                    order.setDispatcherEvent(Boolean.parseBoolean(parser.getAttributeValue(i)));
                } else if ("bmModelName".equals(name)) {
                    order.setBmModelName(parser.getAttributeValue(i));
                } else if ("dmAttrDisplayName".equals(name)) {
                    order.setDmAttrDisplayName(parser.getAttributeValue(i));
                } else if ("required".equals(name)) {
                    order.setRequired(Boolean.parseBoolean(parser.getAttributeValue(i)));
                } else if ("ID".equals(name)) {
                    order.setID(parser.getAttributeValue(i));
                } else if ("identityRule".equals(name)) {
                    order.setIdentityRule(parser.getAttributeValue(i));
                } else if ("dmAttrName".equals(name)) {
                    order.setDmAttrName(parser.getAttributeValue(i));
                } else if ("name".equals(name)) {
                    order.setName(parser.getAttributeValue(i));
                } else if ("srclib_label".equals(name)) {
                    order.setSrclib_label(parser.getAttributeValue(i));
                } else if ("value_label".equals(name)) {
                    order.setValue_label(parser.getAttributeValue(i));
                } else if ("srclib".equals(name)) {
                    order.setSrclib(parser.getAttributeValue(i));
                } else if ("displayName".equals(name)) {
                    order.setDisplayName(parser.getAttributeValue(i));
                } else if ("isMult".equals(name)) {
                    order.setMult(Boolean.parseBoolean(value));
                } else if ("realTimeUpdate".equals(name)) {
                    order.setRealTimeUpdate(Boolean.parseBoolean(value));
                } else if ("queryScope".equals(name)) {
                    order.setQueryScope(value);
                } else if ("resModelValueJson".equals(name)) {
                    order.setResModelValueJson(value);
                } else if ("isCanAdd".equals(name)) {
                    order.setCanAdd(Boolean.parseBoolean(value));
                } else if ("isCanUpdate".equals(name)) {
                    order.setCanUpdate(Boolean.parseBoolean(value));
                } else if ("isCanDelete".equals(name)) {
                    order.setCanDelete(Boolean.parseBoolean(value));
                } else if ("allColumnsJson".equals(name)) {
                    order.setAllColumnsJson(value);
                } else if ("configModifiedField".equals(name)) {
                    order.setConfigModifiedField(value);
                } else if ("columnsJson".equals(name)) {
                    order.setColumnsJson(value);
                } else if ("isUpdateLaterDate".equals(name)) {
                    order.setUpdateLaterDate(Boolean.parseBoolean(value));
                } else if ("isUpdateBeforeDate".equals(name)) {
                    order.setUpdateBeforeDate(Boolean.parseBoolean(value));
                } else if ("hasDateTime".equals(name)) {
                    order.setHasDateTime(Boolean.parseBoolean(value));
                } else if ("inputLength".equals(name)) {
                    order.setInputLength(value);
                } else if ("precision".equals(name)) {
                    order.setPrecision(value);
                } else if ("parentRoleJSON".equals(name)) {
                    order.setParentRoleJSON(value);
                } else if ("isShowBorder".equals(name)) {
                    order.setShowBorder(Boolean.parseBoolean(value));
                } else if ("personInfo".equals(name)) {
                    order.setPersonInfo(value);
                } else if ("isFilterUser".equals(name)) {
                    order.setFilterUser(Boolean.parseBoolean(value));
                } else if ("styleJson".equals(name)) {
                    order.setStyleJson(value);
                } else if ("templateJson".equals(name)) {
                    order.setTemplateJson(value);
                } else if ("isDeleteAttachment".equals(name)) {
                    order.setDeleteAttachment(Boolean.parseBoolean(value));
                } else if ("parentDeptJSON".equals(name)) {
                    order.setParentDeptJSON(value);
                } else if ("resModelConfigure".equals(name)) {
                    order.setResModelConfigure(value);
                } else if ("toDmAttrName".equals(name)) {
                    order.setToDmAttrName(value);
                } else if ("relationBmModelObj".equals(name)) {
                    order.setRelationBmModelObj(value);
                }
            }
        }
        return order;
    }

    private static ButtonModel passButton(XmlPullParser parser) {
        ButtonModel button = new ButtonModel();
        String viewName = parser.getName();
        int count = parser.getAttributeCount();
        button.setViewName(viewName);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String name = parser.getAttributeName(i);
                if ("ID".equals(name)) {
                    button.setID(parser.getAttributeValue(i));
                }
            }
        }
        return button;
    }

    private static ButtonModel.NextNode passNextNode(XmlPullParser parser) {
        ButtonModel.NextNode nextNode = new ButtonModel.NextNode();
        String viewName = parser.getName();
        int count = parser.getAttributeCount();
        nextNode.setViewName(viewName);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String name = parser.getAttributeName(i);
                String value = parser.getAttributeValue(i);
                if ("name".equals(name)) {
                    nextNode.setName(value);
                } else if ("to".equals(name)) {
                    nextNode.setTo(value);
                } else if ("isDefaultPath".equals(name)) {
                    nextNode.setIsDefaultPath(value);
                } else if ("taskStrategy".equals(name)) {
                    nextNode.setTaskStrategy(value);
                } else if ("specificValueUpdate".equals(name)) {
                    nextNode.setSpecificValueUpdate(value);
                } else if ("nameDesc".equals(name)) {
                    nextNode.setNameDesc(value);
                } else if ("isDependCondition".equals(name)) {
                    nextNode.setIsDependCondition(value);
                }
            }
        }
        return nextNode;
    }
}
