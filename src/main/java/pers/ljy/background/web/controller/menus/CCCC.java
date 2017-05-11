package pers.ljy.background.web.controller.menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;

import com.alibaba.fastjson.JSONObject;

public class CCCC {

	public static void main(String[] args) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new NameValuePair("ProjectId","1111111"));
    	params.add(new NameValuePair("Collection","1"));
    	params.add(new NameValuePair("Latefee","2"));
    	params.add(new NameValuePair("Mobile","110"));

        Map<String, Object> contentMap = new HashMap<>();
        contentMap.put("resource", "1111122222");
        contentMap.put("overdue", 	"1100.00");
    	params.add(new NameValuePair("ParamString",JSONObject.toJSON(contentMap).toString()));
        
    	System.out.println(JSONObject.toJSONString(params));
	}

}
