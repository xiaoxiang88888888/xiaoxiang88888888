package ${pojo.getPackageName()}.${pojo.getDeclarationName()?lower_case}.action;

import com.xiaoxiang.base.jmesa.CheckboxEditor;
import com.xiaoxiang.base.jmesa.CheckboxHeaderEditor;
import com.xiaoxiang.base.web.struts2.BaseAction;
import com.xiaoxiang.base.utils.SpringContextUtils;
import com.xiaoxiang.base.utils.StringUtils;                      
import com.xiaoxiang.base.utils.ConstantUtils;
import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.TreeDirector;
import net.jcreate.e3.tree.TreeModel;
import net.jcreate.e3.tree.UserDataUncoder;
import net.jcreate.e3.tree.ext.OutlookExtTreeBuilder;
import net.jcreate.e3.tree.support.*;
import net.jcreate.e3.tree.xtree.XLoadSubTreeBuilder;
import net.jcreate.e3.tree.xtree.XLoadTreeBuilder;
import net.jcreate.e3.tree.xtree.CheckXTreeBuilder;
import net.jcreate.e3.tree.xtree.PrvCheckXTreeBuilder;
import net.jcreate.e3.tree.xtree.XTreeBuilder;
import net.jcreate.e3.tree.yui.YUIMenuBuilder;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeImpl;
import static org.jmesa.limit.ExportType.*;
import org.jmesa.limit.Limit;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.hibernate.criterion.Projections;                     
import org.hibernate.criterion.Restrictions; 
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.ArrayList;
import ${pojo.getPackageName()}.${pojo.getDeclarationName()?lower_case}.e3.${pojo.getDeclarationName()}UnCoder;
import ${pojo.getPackageName()}.model.${pojo.getDeclarationName()};
<#assign temp="" >
<#foreach field in pojo.getPropertiesIterator()>
<#if c2h.isManyToOne(field) >
<#if  "${pojo.getJavaTypeName(field, jdk5)}"!=temp>
import ${pojo.getPackageName()}.model.${pojo.getJavaTypeName(field, jdk5)};
import ${pojo.getPackageName()}.${pojo.getJavaTypeName(field, jdk5)?lower_case}.service.${pojo.getJavaTypeName(field, jdk5)}Service;
<#assign temp="${pojo.getJavaTypeName(field, jdk5)}" >
</#if>
 <#if  "${pojo.getJavaTypeName(field, jdk5)}"==temp>
<#assign temp="${pojo.getJavaTypeName(field, jdk5)}" >
</#if>
</#if>
</#foreach>
                      
/**
 * ${pojo.getDeclarationName()} 的action
 * ${clazz.table.comment}
 * @author xiaoxiang
 */
public class ${pojo.getDeclarationName()}Action extends BaseAction<${pojo.getDeclarationName()}, String> {

<#assign temp="" >
<#foreach field in pojo.getPropertiesIterator()>
<#if c2h.isManyToOne(field) >
<#if  "${pojo.getJavaTypeName(field, jdk5)}"!=temp>
    private ${pojo.getJavaTypeName(field, jdk5)}Service ${pojo.getJavaTypeName(field, jdk5)?lower_case}Service = (${pojo.getJavaTypeName(field, jdk5)}Service) SpringContextUtils.getBean("${pojo.getJavaTypeName(field, jdk5)?lower_case}Service");
<#assign temp="${pojo.getJavaTypeName(field, jdk5)}" >
</#if>
 <#if  "${pojo.getJavaTypeName(field, jdk5)}"==temp>
<#assign temp="${pojo.getJavaTypeName(field, jdk5)}" >
</#if>
</#if>
</#foreach>

    protected ${pojo.getDeclarationName()} entity;

    protected String id;  //实体ID
                      
    protected String jmesaid = "jmesaid"; //  Jmesa 列表表格上ID属性值  默认值为"jmesaid"

<#foreach field in pojo.getPropertiesIterator()>
<#if c2h.isManyToOne(field)>
    protected String <#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>;//<#foreach column in field.value.getColumnIterator()>${column.comment}</#foreach>
    protected String <#foreach column in field.value.getColumnIterator()>${column.name?lower_case}_name</#foreach>;//<#foreach column in field.value.getColumnIterator()>${column.comment}</#foreach>的显示内容                     
</#if>
</#foreach>

<#foreach field in pojo.getPropertiesIterator()>
<#if c2h.isManyToOne(field)>
    <#--${pojo.getPropertyGetModifiers(field)} String get<#foreach column in field.value.getColumnIterator()>${column.name?lower_case?cap_first}</#foreach>() {
        return this.<#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>;
    }
-->
    ${pojo.getPropertySetModifiers(field)} void set<#foreach column in field.value.getColumnIterator()>${column.name?lower_case?cap_first}</#foreach>(String <#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>) {
        this.<#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach> = <#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>;
    }
                      
    ${pojo.getPropertySetModifiers(field)} void set<#foreach column in field.value.getColumnIterator()>${column.name?lower_case?cap_first}</#foreach>_name(String <#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>_name) {
        this.<#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>_name = <#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>_name;
    }
</#if>
</#foreach>

    public void setJmesaid(String jmesaid) {
        this.jmesaid = jmesaid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ${pojo.getDeclarationName()} getModel() {
        //return null;
        return entity;
    }

    /**
     * 等同于prepare()的内部函数.
     */
    protected void prepareModel() throws Exception {
        String id = request.getParameter("id");
        if (id != null) {
            entity = service.findEntityBykey(id);
        } else {
            entity = new ${pojo.getDeclarationName()}();
        }
    }


    /**
     * Action函数,显示Entity列表.
     * return SUCCESS.
     */
    public String showList() throws Exception {
        entities = getService().getAllEntity();
        return SUCCESS;
    }

    /**
     * 使用Jmesa进行列表显示
     *
     * @return
     * @throws Exception
     */
    public String showJmesaList() throws Exception {
        boolean flag=true;
        //用于数据库分页
        String jmesaid_p_ = request.getParameter("jmesaid_p_"); //当前页数
        String maxRows = request.getParameter("maxRows"); //分页行数
        if (jmesaid_p_ == null) {
            jmesaid_p_ = "0";
        } else {
            jmesaid_p_ = new Integer((Integer.parseInt(jmesaid_p_) - 1)).toString();
        }
        if (maxRows == null) {
            maxRows = ConstantUtils.getPreference(null, "jmesa.properties", ConstantUtils.LIMIT_ROWSELECT_MAXROWS);
        }
        int totalRows = 0;              
<#foreach field in pojo.getPropertiesIterator()>
<#if c2h.isManyToOne(field)>
<#foreach column in field.value.getColumnIterator()>
        if (${column.name?lower_case} != null && !"".equals(${column.name?lower_case})){//<#foreach column in field.value.getColumnIterator()>${column.comment}</#foreach>
            if ("root".equalsIgnoreCase(${column.name?lower_case})) {
                entities = getService().createCriteria().
                        setFirstResult(Integer.parseInt(jmesaid_p_)*Integer.parseInt(maxRows)).
                        setMaxResults(Integer.parseInt(maxRows)).
                        addOrder(Order.asc("orderno"))
                        .list();
                totalRows = ((Integer)(getService().createCriteria().setProjection(Projections.rowCount()).uniqueResult())).intValue(); //总记录数
            } else {
                entities = getService().createCriteria(Restrictions.eq("${field.name}.${c2h.getToUpperCamelCase(field)?uncap_first}", ${column.name?lower_case})).
                        setFirstResult(Integer.parseInt(jmesaid_p_)*Integer.parseInt(maxRows)).
                        setMaxResults(Integer.parseInt(maxRows)).
                        addOrder(Order.asc("orderno"))
                        .list();
                totalRows = ((Integer)(getService().createCriteria(Restrictions.eq("${field.name}.${c2h.getToUpperCamelCase(field)?uncap_first}", ${column.name?lower_case})).setProjection(Projections.rowCount()).uniqueResult())).intValue(); //总记录数                              
            }
            flag=false;
        }
</#foreach>
</#if>
</#foreach>
        if (flag) {
            entities = getService().createCriteria().
                    setFirstResult(Integer.parseInt(jmesaid_p_) * Integer.parseInt(maxRows)).
                    setMaxResults(Integer.parseInt(maxRows)).
                    addOrder(Order.asc("orderno"))
                    .list();
            totalRows = ((Integer) (getService().createCriteria().setProjection(Projections.rowCount()).uniqueResult())).intValue();
        }
        TableFacade tableFacade = new TableFacadeImpl("jmesaid", request);
        tableFacade.setEditable(true);
        tableFacade.setTotalRows(totalRows);

        tableFacade.setItems(entities);
        tableFacade.setColumnProperties(<#foreach field in pojo.getPropertiesIterator()><#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field)>"${field.name}"<#if field_has_next>,</#if></#if></#foreach>);
        tableFacade.setExportTypes(response, CSV, EXCEL, PDFP); // Tell the tableFacade what exports to use.
        Limit limit = tableFacade.getLimit();
        if (limit.isExported()) {
            tableFacade.getTable().setCaption("${clazz.table.comment?js_string}".replaceAll("\\r\\n","").replaceAll("\"",""));
            <#foreach field in pojo.getPropertiesIterator()><#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field)>
            tableFacade.getTable().getRow().getColumn("${field.name}").setTitle("<#foreach column in field.value.getColumnIterator()>${column.comment}</#foreach>");
            </#if></#foreach>         
            tableFacade.render();
            return null;
        }
        String html = getHtml(tableFacade);
        request.setAttribute("entities", html);
        return SUCCESS;
    }

    /**
     * Action函数,显示框架页.
     * return SUCCESS.
     */
    public String showMainFrame() throws Exception {          
        return SUCCESS;
    }

    /**
     * Action函数,显示Entity菜单，一次性加载，E3 xtree .
     * return SUCCESS.
     */
    public String showTreeList() throws Exception {
        List ${pojo.getDeclarationName()?lower_case}s = getService().getAllEntity();
        ${pojo.getDeclarationName()} root = new ${pojo.getDeclarationName()}();
        <#foreach field in pojo.getAllPropertiesIterator()>
         <#if field==clazz.getIdentifierProperty()>
        root.set${pojo.getPropertyName(field)}("root");               
        root.set${c2h.getToUpperCamelCase_name_self(clazz.getIdentifierProperty())[0]?cap_first}("${clazz.table.comment?js_string}".replaceAll("\\r\\n","").replaceAll("\"",""));
         </#if>
         <#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field)>
        </#if>
        </#foreach>
        ${pojo.getDeclarationName()?lower_case}s.add(0, root);
        UserDataUncoder ${pojo.getDeclarationName()?lower_case}UnCoder = new ${pojo.getDeclarationName()}UnCoder();
        AbstractWebTreeModelCreator treeModelCreator =
                new AbstractWebTreeModelCreator() {
                    protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
                        ${pojo.getDeclarationName()} ${pojo.getDeclarationName()?lower_case} = (${pojo.getDeclarationName()}) pUserData;
                        WebTreeNode result = new WebTreeNode(${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}(), "${pojo.getDeclarationName()?lower_case}_" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}());
                        result.setName(${pojo.getDeclarationName()?lower_case}.get${c2h.getToUpperCamelCase_name_self(clazz.getIdentifierProperty())[0]?cap_first}()); 
                        result.setValue("${pojo.getDeclarationName()?lower_case}_" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}());
                        result.setIcon("js/xtree/images/${pojo.getDeclarationName()?lower_case}.gif");
                        result.setOpenIcon("js/xtree/images/${pojo.getDeclarationName()?lower_case}.gif");
                        result.setTarget("/${pojo.getDeclarationName()?lower_case}/showTreeList.action");
                        result.setAction("javascript:nodeClickFunHander('" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}() +"','" + result.getName()+"','" + result.getTarget() +"','${pojo.getDeclarationName()?lower_case}','" + result.getValue() + "')");
                        return result;
                    }
                };
        treeModelCreator.init(request);
        TreeModel treeModel = treeModelCreator.create(${pojo.getDeclarationName()?lower_case}s, ${pojo.getDeclarationName()?lower_case}UnCoder);
        TreeDirector director = new DefaultTreeDirector();
        director.setComparator(new AbstractNodeComparator() {
            protected Comparable getComparableProperty(Node pNode) {
                ${pojo.getDeclarationName()} ${pojo.getDeclarationName()?lower_case} = (${pojo.getDeclarationName()}) pNode.getUserData();
                return ${pojo.getDeclarationName()?lower_case}.getOrderno();
            }
        });
        XTreeBuilder treeBuilder = new XTreeBuilder();    
        treeBuilder.init(request);
        director.build(treeModel, treeBuilder);
        String treeScript = treeBuilder.getTreeScript();
        request.setAttribute("treeScript", treeScript);
        return SUCCESS;
    }

    /**
     * Action函数,一次性加载选择树 ，当子节点选中时，父节点不会自动选中 E3 xtree .
     * return SUCCESS.
     */
    public String showCheckTreeList() throws Exception {
        List ${pojo.getDeclarationName()?lower_case}s = getService().getAllEntity();
        ${pojo.getDeclarationName()} root = new ${pojo.getDeclarationName()}();
        <#foreach field in pojo.getAllPropertiesIterator()>
         <#if field==clazz.getIdentifierProperty()>
        root.set${pojo.getPropertyName(field)}("root");               
        root.set${c2h.getToUpperCamelCase_name_self(clazz.getIdentifierProperty())[0]?cap_first}("${clazz.table.comment?js_string}".replaceAll("\\r\\n","").replaceAll("\"",""));
         </#if>
         <#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field)>
        </#if>
        </#foreach>
        ${pojo.getDeclarationName()?lower_case}s.add(0, root);
        UserDataUncoder ${pojo.getDeclarationName()?lower_case}UnCoder = new ${pojo.getDeclarationName()}UnCoder();
        AbstractWebTreeModelCreator treeModelCreator =
                new AbstractWebTreeModelCreator() {
                    protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
                        ${pojo.getDeclarationName()} ${pojo.getDeclarationName()?lower_case} = (${pojo.getDeclarationName()}) pUserData;
                        WebTreeNode result = new WebTreeNode(${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}(), "${pojo.getDeclarationName()?lower_case}_" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}());
                        result.setName(${pojo.getDeclarationName()?lower_case}.get${c2h.getToUpperCamelCase_name_self(clazz.getIdentifierProperty())[0]?cap_first}());
                        result.setValue("${pojo.getDeclarationName()?lower_case}_" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}());
                        result.setIcon("js/xtree/images/${pojo.getDeclarationName()?lower_case}.gif");
                        result.setOpenIcon("js/xtree/images/${pojo.getDeclarationName()?lower_case}.gif");
                        result.setTarget("/${pojo.getDeclarationName()?lower_case}/showTreeList.action");
                        result.setAction("javascript:nodeClickFunHander('" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}() +"','" + result.getName()+"','" + result.getTarget() +"','${pojo.getDeclarationName()?lower_case}','" + result.getValue() + "')");
                        return result;
                    }
                };
        treeModelCreator.init(request);
        TreeModel treeModel = treeModelCreator.create(${pojo.getDeclarationName()?lower_case}s, ${pojo.getDeclarationName()?lower_case}UnCoder);
        TreeDirector director = new DefaultTreeDirector();
        director.setComparator(new AbstractNodeComparator() {
            protected Comparable getComparableProperty(Node pNode) {
                ${pojo.getDeclarationName()} ${pojo.getDeclarationName()?lower_case} = (${pojo.getDeclarationName()}) pNode.getUserData();
                return ${pojo.getDeclarationName()?lower_case}.getOrderno();
            }
        });
        CheckXTreeBuilder treeBuilder = new CheckXTreeBuilder();        
        treeBuilder.init(request);
        director.build(treeModel, treeBuilder);
        String treeScript = treeBuilder.getTreeScript();
        request.setAttribute("treeScript", treeScript);
        return SUCCESS;
    }

    /**
     * Action函数,一次性加载选择树 ，当子节点选中时，父节点自动选中 E3 xtree .
     * return SUCCESS.
     */
    public String showPrvCheckTreeList() throws Exception {
        List ${pojo.getDeclarationName()?lower_case}s = getService().getAllEntity();
        ${pojo.getDeclarationName()} root = new ${pojo.getDeclarationName()}();
        <#foreach field in pojo.getAllPropertiesIterator()>
         <#if field==clazz.getIdentifierProperty()>
        root.set${pojo.getPropertyName(field)}("root");
        root.set${c2h.getToUpperCamelCase_name_self(clazz.getIdentifierProperty())[0]?cap_first}("${clazz.table.comment?js_string}".replaceAll("\\r\\n","").replaceAll("\"",""));
         </#if>
         <#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field)>
        </#if>
        </#foreach>
        ${pojo.getDeclarationName()?lower_case}s.add(0, root);
        UserDataUncoder ${pojo.getDeclarationName()?lower_case}UnCoder = new ${pojo.getDeclarationName()}UnCoder();
        AbstractWebTreeModelCreator treeModelCreator =
                new AbstractWebTreeModelCreator() {
                    protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
                        ${pojo.getDeclarationName()} ${pojo.getDeclarationName()?lower_case} = (${pojo.getDeclarationName()}) pUserData;
                        WebTreeNode result = new WebTreeNode(${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}(), "${pojo.getDeclarationName()?lower_case}_" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}());
                        result.setName(${pojo.getDeclarationName()?lower_case}.get${c2h.getToUpperCamelCase_name_self(clazz.getIdentifierProperty())[0]?cap_first}());
                        result.setValue("${pojo.getDeclarationName()?lower_case}_" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}());
                        result.setIcon("js/xtree/images/${pojo.getDeclarationName()?lower_case}.gif");
                        result.setOpenIcon("js/xtree/images/${pojo.getDeclarationName()?lower_case}.gif");
                        result.setTarget("/${pojo.getDeclarationName()?lower_case}/showTreeList.action");
                        result.setAction("javascript:nodeClickFunHander('" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}() +"','" + result.getName()+"','" + result.getTarget() +"','${pojo.getDeclarationName()?lower_case}','" + result.getValue() + "')");
                        return result;
                    }
                };
        treeModelCreator.init(request);
        TreeModel treeModel = treeModelCreator.create(${pojo.getDeclarationName()?lower_case}s, ${pojo.getDeclarationName()?lower_case}UnCoder);
        TreeDirector director = new DefaultTreeDirector();
        director.setComparator(new AbstractNodeComparator() {
            protected Comparable getComparableProperty(Node pNode) {
                ${pojo.getDeclarationName()} ${pojo.getDeclarationName()?lower_case} = (${pojo.getDeclarationName()}) pNode.getUserData();
                return ${pojo.getDeclarationName()?lower_case}.getOrderno();
            }
        });
        PrvCheckXTreeBuilder treeBuilder = new PrvCheckXTreeBuilder();
        treeBuilder.init(request);
        director.build(treeModel, treeBuilder);
        String treeScript = treeBuilder.getTreeScript();
        request.setAttribute("treeScript", treeScript);
        return SUCCESS;
    }

    /**
     * Action函数,显示Entity菜单，延迟加载，E3 xtreeload
     * return SUCCESS.
     */
    public String showLoadTreeList() throws Exception {
        WebTreeDynamicNode rootNode = new WebTreeDynamicNode("${clazz.table.comment?js_string}".replaceAll("\\r\\n","").replaceAll("\"",""), "root");
        rootNode.setSubTreeURL(RequestUtil.getUrl("/${pojo.getDeclarationName()?lower_case}/showLoadSubTreeList.action?parentID=root", request));
        DefaultTreeModel treeModel = new DefaultTreeModel();
        treeModel.addRootNode(rootNode);
        TreeDirector director = new DefaultTreeDirector();
        //director.setComparator(new DefaultNodeComparator());
        director.setComparator(new AbstractNodeComparator() {
            protected Comparable getComparableProperty(Node pNode) {
                ${pojo.getDeclarationName()} ${pojo.getDeclarationName()?lower_case} = (${pojo.getDeclarationName()}) pNode.getUserData();
                return ${pojo.getDeclarationName()?lower_case}.getOrderno();
            }
        });
        WebTreeBuilder treeBuilder = new XLoadTreeBuilder();
        treeBuilder.init(request);
        director.build(treeModel, treeBuilder);
        String treeScript = treeBuilder.getTreeScript();
        request.setAttribute("treeScript", treeScript);
        return SUCCESS;
    }

    /**
     * Action函数,显示Entity菜单，延迟加载子树，E3 xtreeload
     * return SUCCESS.
     */
    public String showLoadSubTreeList() throws Exception {
        final String parentID = request.getParameter("parentID");
        List list = new ArrayList();
<#assign flagparent="true" >
<#foreach field in pojo.getPropertiesIterator()>
<#if c2h.isManyToOne(field) && pojo.getJavaTypeName(field, jdk5)==pojo.getDeclarationName()>
        if(parentID!=null && parentID.equalsIgnoreCase("root")){
           list = getService().createCriteria(Restrictions.isNull("${field.name}.${c2h.getToUpperCamelCase(field)?uncap_first}")).list();
        } else {
           list = getService().findByProperty("${field.name}.${c2h.getToUpperCamelCase(field)?uncap_first}",parentID);
        }
        <#assign flagparent="false" >
</#if>
</#foreach>
<#if flagparent=="true" >
       if(parentID!=null && parentID.equalsIgnoreCase("root")){
           list = getService().getAllEntity();
        } else {
           list = new ArrayList();;
        }
</#if>
        UserDataUncoder ${pojo.getDeclarationName()?lower_case}UnCoder = new ${pojo.getDeclarationName()}UnCoder();
        AbstractWebTreeModelCreator treeModelCreator =
                new AbstractWebTreeModelCreator() {
                    protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
                        ${pojo.getDeclarationName()} ${pojo.getDeclarationName()?lower_case} = (${pojo.getDeclarationName()}) pUserData;
                        WebTreeDynamicNode result = new WebTreeDynamicNode(${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}(), "${pojo.getDeclarationName()?lower_case}_" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}());
                        result.setSubTreeURL(getUrl("/${pojo.getDeclarationName()?lower_case}/showLoadSubTreeList.action?parentID="+${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}()));
                        result.setName(${pojo.getDeclarationName()?lower_case}.get${c2h.getToUpperCamelCase_name_self(clazz.getIdentifierProperty())[0]?cap_first}());
                        result.setValue("${pojo.getDeclarationName()?lower_case}_" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}());
                        result.setIcon("js/xtree/images/${pojo.getDeclarationName()?lower_case}.gif");
                        result.setOpenIcon("js/xtree/images/${pojo.getDeclarationName()?lower_case}.gif");
                        result.setTarget("/${pojo.getDeclarationName()?lower_case}/showTreeList.action");
                        result.setAction("javascript:nodeClickFunHander('" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}() +"','" + result.getName()+"','" + result.getTarget() +"','${pojo.getDeclarationName()?lower_case}','" + result.getValue() + "')");
                        return result;
                    }
                };
        treeModelCreator.init(request);
        TreeModel treeModel = treeModelCreator.create(list, ${pojo.getDeclarationName()?lower_case}UnCoder);
        TreeDirector director = new DefaultTreeDirector();
        //director.setComparator(new DefaultNodeComparator());
        director.setComparator(new AbstractNodeComparator() {
            protected Comparable getComparableProperty(Node pNode) {
                ${pojo.getDeclarationName()} ${pojo.getDeclarationName()?lower_case} = (${pojo.getDeclarationName()}) pNode.getUserData();
                return ${pojo.getDeclarationName()?lower_case}.getOrderno();
            }
        });
        WebTreeBuilder treeBuilder = new XLoadSubTreeBuilder();
        treeBuilder.init(request);
        director.build(treeModel, treeBuilder);
        String treeScript = treeBuilder.getTreeScript();
        return renderXML(treeScript);
        //return renderHtml(treeScript);
    }

    /**
     * 构造菜单
     *
     * @return
     * @throws Exception
     */
    public String showYUIHMenu() throws Exception {
        //业务数据
        List ${pojo.getDeclarationName()?lower_case}s = getService().getAllEntity();
        ${pojo.getDeclarationName()} root = new ${pojo.getDeclarationName()}();
        root.set${pojo.getPropertyName(clazz.getIdentifierProperty())}("${clazz.table.comment?js_string}".replaceAll("\\r\\n","").replaceAll("\"",""));       
        ${pojo.getDeclarationName()?lower_case}s.add(0, root);
        UserDataUncoder ${pojo.getDeclarationName()?lower_case}UnCoder = new ${pojo.getDeclarationName()}UnCoder();
        //Tree模型构造器，用于生成树模型
        AbstractWebTreeModelCreator treeModelCreator =
                new AbstractWebTreeModelCreator() {
                    //该方法负责将业务数据映射到树型节点
                    protected Node createNode(Object pUserData, UserDataUncoder pUncoder) {
                        ${pojo.getDeclarationName()} ${pojo.getDeclarationName()?lower_case} = (${pojo.getDeclarationName()}) pUserData;
                        WebTreeNode result = new WebTreeNode(${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}(), "${pojo.getDeclarationName()?lower_case}_" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}());
                        result.setName(${pojo.getDeclarationName()?lower_case}.get${c2h.getToUpperCamelCase_name_self(clazz.getIdentifierProperty())[0]?cap_first}());
                        result.setValue("${pojo.getDeclarationName()?lower_case}_" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}());
                        result.setIcon("js/xtree/images/${pojo.getDeclarationName()?lower_case}.gif");
                        result.setOpenIcon("js/xtree/images/${pojo.getDeclarationName()?lower_case}.gif");
                        result.setTarget("/${pojo.getDeclarationName()?lower_case}/showTreeList.action");
                        result.setAction("javascript:nodeClickFunHander('" + ${pojo.getDeclarationName()?lower_case}.get${pojo.getPropertyName(clazz.getIdentifierProperty())}() +"','" + result.getName()+"','" + result.getTarget() +"','${pojo.getDeclarationName()?lower_case}','" + result.getValue() + "')");
                        return result;
                    }
                };
        treeModelCreator.init(request);
        TreeModel treeModel = treeModelCreator.create(${pojo.getDeclarationName()?lower_case}s, ${pojo.getDeclarationName()?lower_case}UnCoder);
        TreeDirector director = new DefaultTreeDirector();//构造树导向器
        director.setComparator(new AbstractNodeComparator() {
            protected Comparable getComparableProperty(Node pNode) {
                ${pojo.getDeclarationName()} ${pojo.getDeclarationName()?lower_case} = (${pojo.getDeclarationName()}) pNode.getUserData();
                return ${pojo.getDeclarationName()?lower_case}.getOrderno();
            }
        });
        WebTreeBuilder treeBuilder = new YUIMenuBuilder();//构造树Builder
        treeBuilder.init(request);
        director.build(treeModel, treeBuilder);//执行构造
        String treeScript = treeBuilder.getTreeScript();//获取构造树的脚本
        request.setAttribute("treeScript", treeScript);//保存到request,以便页面使用
        return renderHtml(treeScript);
        //return SUCCESS;
    }

    /**
     * Action函数,显示排序页.
     * return SUCCESS.
     */
    public String showOrder() throws Exception {
        return SUCCESS;
    }

    /**
     * Action函数,显示查看页面.
     * return SUCCESS.
     */
    public String showView() throws Exception {
        return SUCCESS;
    }

    /**
     * Action函数,在增加之前的准备工作.
     * return SHOWSAVE.
     *
     * @return
     */
    public String showSave() throws Exception {
        request.setAttribute("operate", "save");
        request.setAttribute("${pojo.getPropertyName(clazz.getIdentifierProperty())?uncap_first}", StringUtils.getIdentifierGenerator());
        return SUCCESS;
    }

    /**
     * Action函数,在修改之前的准备工作
     * return SHOWUPDATE.
     *
     * @return
     */
    public String showUpdate() throws Exception {
        String id = request.getParameter("id");
        entity = service.findEntityBykey(id);
<#foreach field in pojo.getPropertiesIterator()>
<#if c2h.isManyToOne(field)>      
        if (entity.${pojo.getGetterSignature(field)}() != null) <#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach> = entity.${pojo.getGetterSignature(field)}().get${c2h.getToUpperCamelCase(field)?cap_first}();//<#foreach column in field.value.getColumnIterator()>${column.comment}</#foreach>
        if (entity.${pojo.getGetterSignature(field)}() != null) <#foreach column in field.value.getColumnIterator()>${column.name?lower_case}_name</#foreach> = entity.${pojo.getGetterSignature(field)}().get${c2h.getToUpperCamelCase_name(field)[0]?cap_first}();//${c2h.getToUpperCamelCase_name(field)[1]}
        request.setAttribute("<#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>",<#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>);
        request.setAttribute("<#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>_name",<#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>_name);
</#if>
</#foreach>
        request.setAttribute("operate", "update");
        return SUCCESS;
    }

    /**
     * Action函数,新增Entity.
     * return SAVE.
     *
     * @return
     */
    public String save() throws Exception {
<#assign temp="" >
<#foreach field in pojo.getPropertiesIterator()>
<#if c2h.isManyToOne(field)>
<#if  "${pojo.getJavaTypeName(field, jdk5)}"!=temp>
        ${pojo.getJavaTypeName(field, jdk5)} ${pojo.getJavaTypeName(field, jdk5)?lower_case} = ${pojo.getJavaTypeName(field, jdk5)?lower_case}Service.findEntityBykey(<#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>);//<#foreach column in field.value.getColumnIterator()>${column.comment}</#foreach>
        entity.set${field.name?cap_first}(${pojo.getJavaTypeName(field, jdk5)?lower_case});
</#if>
<#if  "${pojo.getJavaTypeName(field, jdk5)}"==temp>
        ${pojo.getJavaTypeName(field, jdk5)?lower_case} = null;
        ${pojo.getJavaTypeName(field, jdk5)?lower_case} = ${pojo.getJavaTypeName(field, jdk5)?lower_case}Service.findEntityBykey(<#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>);//<#foreach column in field.value.getColumnIterator()>${column.comment}</#foreach>
        entity.set${field.name?cap_first}(${pojo.getJavaTypeName(field, jdk5)?lower_case});
</#if>
<#assign temp="${pojo.getJavaTypeName(field, jdk5)}" >
</#if>
</#foreach>
        service.saveOrUpdateEntity(entity);
        //optype:   error,windowClose,windowLocation,windowParent,windowParentClose,moduleWindow
        request.setAttribute("optype", "moduleWindow");
        request.setAttribute("alert", "保存成功！！");
        return SUCCESS;
    }

    /**
     * Action函数,修改Entity.
     * return UPDATE.
     *
     * @return
     */
    public String update() throws Exception {
<#assign temp="" >
<#foreach field in pojo.getPropertiesIterator()>
<#if c2h.isManyToOne(field)>
<#if  "${pojo.getJavaTypeName(field, jdk5)}"!=temp>
        ${pojo.getJavaTypeName(field, jdk5)} ${pojo.getJavaTypeName(field, jdk5)?lower_case} = ${pojo.getJavaTypeName(field, jdk5)?lower_case}Service.findEntityBykey(<#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>);//<#foreach column in field.value.getColumnIterator()>${column.comment}</#foreach>
        entity.set${field.name?cap_first}(${pojo.getJavaTypeName(field, jdk5)?lower_case});
</#if>
<#if  "${pojo.getJavaTypeName(field, jdk5)}"==temp>
        ${pojo.getJavaTypeName(field, jdk5)?lower_case} = null;
        ${pojo.getJavaTypeName(field, jdk5)?lower_case} = ${pojo.getJavaTypeName(field, jdk5)?lower_case}Service.findEntityBykey(<#foreach column in field.value.getColumnIterator()>${column.name?lower_case}</#foreach>);//<#foreach column in field.value.getColumnIterator()>${column.comment}</#foreach>
        entity.set${field.name?cap_first}(${pojo.getJavaTypeName(field, jdk5)?lower_case});
</#if>
<#assign temp="${pojo.getJavaTypeName(field, jdk5)}" >
</#if>
</#foreach>
        service.saveOrUpdateEntity(entity);
        request.setAttribute("optype", "moduleWindow");
        request.setAttribute("alert", "保存成功！！");
        return SUCCESS;
    }

    /**
     * Action函数,删除Entity.
     * return RELOAD.
     */
    public String delete() throws Exception {
        getService().deleteEntity(entity);
        return SUCCESS;
    }

    /**
     * Action函数,删除Entity.
     *
     * @return batchDelete.
     */
    public String batchDelete() throws Exception {
        String[] ids = request.getParameter("ids").split(",");
        getService().deleteBatchEntityByKeys(ids);
        return SUCCESS;
    }

    /**
     * @return Get the html for the table.
     */
    private String getHtml(TableFacade tableFacade) {
        // set the column properties
        String checkbox = new CheckboxHeaderEditor().getValue().toString();
        tableFacade.setColumnProperties("selected",<#foreach field in pojo.getPropertiesIterator()><#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field)>"${field.name}"<#if field_has_next>,</#if></#if></#foreach>);

        HtmlTable table = (HtmlTable) tableFacade.getTable();
        //table.setCaption("${clazz.table.comment?js_string}".replaceAll("\\r\\n","").replaceAll("\"",""));
        table.getTableRenderer().setWidth("100%");

        HtmlRow row = table.getRow();
        row.setUniqueProperty("<#foreach field in pojo.getAllPropertiesIterator()><#if field==clazz.getIdentifierProperty()>${field.name}</#if></#foreach>");

        HtmlColumn chkbox = row.getColumn("selected");
        chkbox.getCellRenderer().setWorksheetEditor(new CheckboxEditor());
        chkbox.setTitle("全选" + checkbox);
        chkbox.setFilterable(false);
        chkbox.setSortable(false);
        chkbox.getCellRenderer().setStyle("text-align:center");

        <#foreach field in pojo.getPropertiesIterator()><#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field)>
        HtmlColumn ${field.name} = row.getColumn("${field.name}");
        ${field.name}.setTitle("<#foreach column in field.value.getColumnIterator()>${column.comment}</#foreach>");
        ${field.name}.setEditable(false);

          </#if></#foreach>

        return tableFacade.render();
    }


}

                      