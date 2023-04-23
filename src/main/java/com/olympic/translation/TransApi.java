package com.olympic.translation;

import java.util.HashMap;
import java.util.Map;

public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private final String appId;
    private final String securityKey;

    public TransApi(String appId, String securityKey) {
        this.appId = appId;
        this.securityKey = securityKey;
    }

    public synchronized String getTransResult(String query, String from, String to, long delaMillis) throws InterruptedException {
        Thread.sleep(delaMillis);
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("appid", appId);
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        // 签名
        String src = appId + query + salt + securityKey;
        params.put("sign", MD5.md5(src));
        return params;
    }

}
