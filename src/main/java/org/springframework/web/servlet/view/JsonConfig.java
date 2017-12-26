package org.springframework.web.servlet.view;

import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.AbstractView;
import com.alibaba.fastjson.JSON;

/**
 * 标签工具处理方法
 * 
 * 分页表格json数据转换处理
 */
public class JsonConfig extends AbstractView
{
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(model));
        out.flush();
        out.close();
    }
}
