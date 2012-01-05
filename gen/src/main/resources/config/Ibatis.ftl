<?xml version="1.0" encoding="GB2312"?>
<!DOCTYPE sqlMap PUBLIC "-//iBATIS.com//DTD SQL Map 2.0//EN" "http://www.ibatis.com/dtd/sql-map-2.dtd">
<sqlMap namespace="${pojo.getDeclarationName()?lower_case}">
    <!--${clazz.table.comment}(${pojo.getDeclarationName()})的ibatis配置文件-->
    <typeAlias alias="${pojo.getDeclarationName()?uncap_first}" type="${pojo.getPackageName()}.${pojo.getDeclarationName()?lower_case}.dataobject.${pojo.getDeclarationName()}"/>
    <!--映射配置-->
    <resultMap id="${pojo.getDeclarationName()?uncap_first}Result" class="${pojo.getDeclarationName()?uncap_first}">
        <#foreach field in pojo.getPropertiesIterator()>
        <#if  field==clazz.getIdentifierProperty()>
        <result column="<#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>" property="${field.name}"  javaType="${field.value.typeName}" jdbcType="VARCHAR"/>
        </#if>
        <#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field) && !c2h.isManyToMany(field)>
        <#foreach column in field.value.getColumnIterator()>
        <result column="<#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>" property="${field.name}"  javaType="${field.value.typeName}" jdbcType="VARCHAR"/>
        </#foreach>
        </#if>
        <#if  c2h.isManyToOne(field)>
        <result column="<#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>" property="${field.name}"  javaType="${field.value.typeName}" jdbcType="VARCHAR"/>
        </#if>
        </#foreach>
    </resultMap>
    <!--按条件查询的条件组合-->
    <sql id="other-condition">
        <#foreach field in pojo.getPropertiesIterator()>
        <#if  field==clazz.getIdentifierProperty()>
        <isNotEmpty prepend="and" property="${field.name}">
			<#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>=#${field.name}#
		</isNotEmpty>
        </#if>        
        <#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field) && !c2h.isManyToMany(field)>
        <#foreach column in field.value.getColumnIterator()>
        <isNotEmpty prepend="and" property="${field.name}">
			<#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>=#${field.name}#
		</isNotEmpty>
        </#foreach>
        </#if>
        <#if  c2h.isManyToOne(field)>
        <isNotEmpty prepend="and" property="${field.name}">
			<#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>=#${field.name}#
		</isNotEmpty>
        </#if>
        </#foreach>
    </sql>
    <!--插入语句-->
	<insert id="insert${pojo.getDeclarationName()}" parameterClass="${pojo.getDeclarationName()?uncap_first}">
	     ${'<'}![CDATA[
         <![CDATA[
	       insert into
	       ${clazz.table.quotedName}
	       (
        <#foreach field in pojo.getPropertiesIterator()>
        <#if  field==clazz.getIdentifierProperty()>
		<#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach><#if field_has_next>,</#if>
        </#if>
        <#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field) && !c2h.isManyToMany(field)>
        <#foreach column in field.value.getColumnIterator()>
		<#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach><#if field_has_next>,</#if>
        </#foreach>
        </#if>
        <#if  c2h.isManyToOne(field)>
	    <#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach><#if field_has_next>,</#if>
        </#if>
        </#foreach>
	       ) values
	       (
        <#foreach field in pojo.getPropertiesIterator()>
        <#if  field==clazz.getIdentifierProperty()>
		#${field.name}#<#if field_has_next>,</#if>
        </#if>
        <#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field) && !c2h.isManyToMany(field)>
		#${field.name}#<#if field_has_next>,</#if>
        </#if>
        <#if  c2h.isManyToOne(field)>
		#${field.name}#<#if field_has_next>,</#if>
        </#if>
        </#foreach>
	       )
	    ]]${'>'}
       ]]>
        
	   </insert>
    <!--更新语句-->
	<update id="update${pojo.getDeclarationName()}" parameterClass="${pojo.getDeclarationName()?uncap_first}">
	     ${"<"}![CDATA[
         <![CDATA[
    	  update ${clazz.table.quotedName}
    	  set
        <#foreach field in pojo.getPropertiesIterator()>
        <#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field) && !c2h.isManyToMany(field)>
        <#foreach column in field.value.getColumnIterator()>
		<#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>=#${field.name}#<#if field_has_next>,</#if>
        </#foreach>
        </#if>
        <#if  c2h.isManyToOne(field)>
        <#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>=#${field.name}#<#if field_has_next>,</#if>
        </#if>
        </#foreach>
        <#foreach field in pojo.getPropertiesIterator()>
        <#if  field==clazz.getIdentifierProperty()>
          where <#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>=#${field.name}#
        </#if>
        </#foreach>
	     ${']]>'}
     ]]>
        
	 </update>
    <!--删除语句-->
	<delete id="delete${pojo.getDeclarationName()}" parameterClass="${pojo.getDeclarationName()?uncap_first}">
	    ${"<"}![CDATA[
         <![CDATA[
        delete ${clazz.table.quotedName}
    <#foreach field in pojo.getPropertiesIterator()>
    <#if  field==clazz.getIdentifierProperty()>
      where <#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>=#${field.name}#
    </#if>
    </#foreach>
	     ${']]>'}
      ]]>

	 </delete>
    <!--根据ID查找相关对象-->
    <select id="queryById" resultMap="${pojo.getDeclarationName()?uncap_first}Result" parameterClass="supplyGoods">
	     ${"<"}![CDATA[
         <![CDATA[
        select
        <#foreach field in pojo.getPropertiesIterator()>
        <#if  field==clazz.getIdentifierProperty()>
                <#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>,
        </#if>
        </#foreach>
        <#foreach field in pojo.getPropertiesIterator()>
        <#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field) && !c2h.isManyToMany(field)>
        <#foreach column in field.value.getColumnIterator()>
		<#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach><#if field_has_next>,</#if>
        </#foreach>
        </#if>
        <#if  c2h.isManyToOne(field)>
        <#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach><#if field_has_next>,</#if>
        </#if>
        </#foreach>
        from ${clazz.table.quotedName}
    <#foreach field in pojo.getPropertiesIterator()>
    <#if  field==clazz.getIdentifierProperty()>
        where <#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>=#${field.name}#
    </#if>
    </#foreach>
	     ${']]>'}
     ]]>

    </select>
    <!--查找对象列表-->
    <select id="queryList" resultMap="${pojo.getDeclarationName()?uncap_first}Result" parameterClass="supplyGoods">
	     ${"<"}![CDATA[
         <![CDATA[
        select
        <#foreach field in pojo.getPropertiesIterator()>
        <#if  field==clazz.getIdentifierProperty()>
                <#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach>,
        </#if>
        </#foreach>
        <#foreach field in pojo.getPropertiesIterator()>
        <#if field!=clazz.getIdentifierProperty() &&  !c2h.isManyToOne(field) && !c2h.isOneToMany(field) && !c2h.isManyToMany(field)>
        <#foreach column in field.value.getColumnIterator()>
		<#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach><#if field_has_next>,</#if>
        </#foreach>
        </#if>
        <#if  c2h.isManyToOne(field)>
        <#foreach column in field.value.getColumnIterator()>${column.quotedName?lower_case}</#foreach><#if field_has_next>,</#if>
        </#if>
        </#foreach>
        from ${clazz.table.quotedName}
	     ${']]>'}
    ]]>
        
    </select>

</sqlMap>