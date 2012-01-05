<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE struts PUBLIC
        "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
        "http://struts.apache.org/dtds/struts-2.0.dtd">        
<struts>
    <!--${pojo.getDeclarationName()}的struts2配置文件-->
    <package name="${pojo.getDeclarationName()?lower_case}" extends="struts-default" namespace="/${pojo.getDeclarationName()?lower_case}">
        <action name="showList" class="${pojo.getDeclarationName()?lower_case}" method="showList">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_list.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showJmesaList" class="${pojo.getDeclarationName()?lower_case}" method="showJmesaList">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_list.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showTreeList" class="${pojo.getDeclarationName()?lower_case}" method="showTreeList">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_tree.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showCheckTreeList" class="${pojo.getDeclarationName()?lower_case}" method="showCheckTreeList">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_checktree.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showPrvCheckTreeList" class="${pojo.getDeclarationName()?lower_case}" method="showPrvCheckTreeList">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_prvchecktree.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showLoadTreeList" class="${pojo.getDeclarationName()?lower_case}" method="showLoadTreeList">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_loadtree.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showYUIHMenu" class="${pojo.getDeclarationName()?lower_case}" method="showYUIHMenu">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_menu.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showLoadSubTreeList" class="${pojo.getDeclarationName()?lower_case}" method="showLoadSubTreeList">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_loadtree.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showListTree" class="${pojo.getDeclarationName()?lower_case}" method="showTreeList">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_listtree.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showMainFrame" class="${pojo.getDeclarationName()?lower_case}" method="showMainFrame">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_mainframe.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showOrder" class="${pojo.getDeclarationName()?lower_case}" method="showOrder">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_order.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showView" class="${pojo.getDeclarationName()?lower_case}" method="showView">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_view.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showSave" class="${pojo.getDeclarationName()?lower_case}" method="showSave">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="showUpdate" class="${pojo.getDeclarationName()?lower_case}" method="showUpdate">
            <result name="success">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="save" class="${pojo.getDeclarationName()?lower_case}" method="save">
            <result name="success">/common/opWindow.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="update" class="${pojo.getDeclarationName()?lower_case}" method="update">
            <result name="success">/common/opWindow.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="delete" class="${pojo.getDeclarationName()?lower_case}" method="delete">
            <result name="success">/common/opWindow.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
        <action name="batchDelete" class="${pojo.getDeclarationName()?lower_case}" method="batchDelete">
            <result name="success">/common/opWindow.jsp</result>
            <result name="input">/pages/${pojo.getDeclarationName()?lower_case}/${pojo.getDeclarationName()?lower_case}_form.jsp</result>
        </action>
    </package>
</struts>