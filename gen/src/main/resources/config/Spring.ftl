<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="
			http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
			http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-2.5.xsd
			http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-2.5.xsd"
       default-autowire="byName">
    <!--${pojo.getDeclarationName()}的spring配置文件-->
    <bean id="${pojo.getDeclarationName()?lower_case}DAO" class="${pojo.getPackageName()}.${pojo.getDeclarationName()?lower_case}.dao.hibernate.${pojo.getDeclarationName()}DAOHibernate">
        <property name="hibernateTemplate" ref="hibernateTemplate"/>
    </bean>

    <bean id="${pojo.getDeclarationName()?lower_case}Service" parent="baseTxService">
        <property name="target">
            <bean id="${pojo.getDeclarationName()?lower_case}ServiceTarget" class="${pojo.getPackageName()}.${pojo.getDeclarationName()?lower_case}.service.${pojo.getDeclarationName()}ServiceImpl">
                <property name="dao" ref="${pojo.getDeclarationName()?lower_case}DAO"></property>
            </bean>
        </property>
    </bean>

    <bean name="${pojo.getDeclarationName()?lower_case}" class="${pojo.getPackageName()}.${pojo.getDeclarationName()?lower_case}.action.${pojo.getDeclarationName()}Action">
        <property name="service" ref="${pojo.getDeclarationName()?lower_case}Service"/>
    </bean>

</beans>