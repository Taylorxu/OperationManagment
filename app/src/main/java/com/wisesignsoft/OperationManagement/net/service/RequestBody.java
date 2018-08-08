package com.wisesignsoft.OperationManagement.net.service;

import java.util.List;

public class RequestBody {
    public static String getgEnvelope(String nameSpace, List<String> params, String medthod) {

        StringBuffer envelope = new StringBuffer("<v:Envelope     xmlns:v=\"http://schemas.xmlsoap.org/soap/envelope/\">\n <v:Body>");
        envelope.append("\n <n0:" + medthod + " xmlns:n0=\"" + nameSpace + "\">\n");
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                String argForm = "<arg" + i + ">" + params.get(i) + "</arg" + i + ">\n";
                envelope.append(argForm);
            }
        }
        envelope.append("</n0:" + medthod + ">\n</v:Body>\n </v:Envelope>");

        return envelope.toString();
    }

}
