package org.apache.shiro.web.filter.security;

import java.text.MessageFormat;
import java.util.List;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.xingrongjinfu.system.permission.model.Permission;
import com.xingrongjinfu.system.permission.service.IPermissionService;
import com.xingrongjinfu.utils.ObjectUtil;

/**
 * 加载权限过滤器
 * 
 * @author y
 */
public class SecurityFilterChainDefinitionSource implements FactoryBean<Ini.Section>
{

    private final static Logger logger = LoggerFactory.getLogger(SecurityFilterChainDefinitionSource.class);

    @Autowired
    private IPermissionService permissionService;

    private String filterChainDefinitions;

    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    public void setFilterChainDefinitions(String filterChainDefinitions)
    {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    public Section getObject() throws Exception
    {
        List<Permission> permissions = permissionService.queryPermissions();
        Ini ini = new Ini();
        ini.load(filterChainDefinitions);

        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        if (!CollectionUtils.isEmpty(permissions))
        {
            for (Permission permission : permissions)
            {
                if (ObjectUtil.isNotEmpty(permission.getPermsUrl()) && ObjectUtil.isNotEmpty(permission.getPermsKey()))
                {
                    // <!--系统查询需要权限 ，使用注解授权方式 -->
                    logger.info("系统角色[{}][{}]", new Object[] { permission.getPermsKey(), permission.getPermsUrl() });
                    section.put(permission.getPermsUrl(),
                            MessageFormat.format(PREMISSION_STRING, permission.getPermsKey()));
                }
            }
        }
        section.put("/**", "sysUser,user");
        return section;
    }

    public Class<?> getObjectType()
    {
        return this.getClass();
    }

    public boolean isSingleton()
    {
        return false;
    }

}