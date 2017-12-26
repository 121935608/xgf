import java.util.ArrayList;
import java.util.List;

import com.xingrongjinfu.system.syscode.model.SysCode;

public class TestReplace
{

    public static void main(String[] args)
    {
        SysCode sysCode1 = new SysCode();
        sysCode1.setCodeid("0");
        sysCode1.setCodevalue("成功");

        SysCode sysCode2 = new SysCode();
        sysCode2.setCodeid("1");
        sysCode2.setCodevalue("失败");

        SysCode sysCode3 = new SysCode();
        sysCode3.setCodeid("2");
        sysCode3.setCodevalue("处理中");

        List<SysCode> sysCodeList = new ArrayList<>();
        sysCodeList.add(sysCode1);
        sysCodeList.add(sysCode2);
        sysCodeList.add(sysCode3);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < sysCodeList.size(); i++)
        {
            SysCode sysCode = sysCodeList.get(i);
            String conditions = i == 0 ? "if" : "else if";
            sb.append(conditions + "(row.locked == " + sysCode.getCodeid() + ") {");
            sb.append("return \"" + sysCode.getCodevalue() + "\";");
            sb.append("}");
        }
        sb.append(sysCodeList.size() > 0 ? "else { return \"未匹配\"; }" : "return \"未匹配\";");

        System.out.println(sb.toString());
    }

}
