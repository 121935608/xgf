package org.framework.core.uitl;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Map通用处理方法
 * 
 * @author y
 */
public class MapDataUtil
{

    public static Map convertDataMap(HttpServletRequest request)
    {
        Map properties = request.getParameterMap();
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext())
        {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj)
            {
                value = "";
            }
            else if (valueObj instanceof String[])
            {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++)
                {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            }
            else
            {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

}
