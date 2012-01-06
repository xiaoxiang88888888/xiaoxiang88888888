@echo off

@rem 批量生成
call ant genTable -DtableName=area  -DtemplateDir=vo  -DtemplateName=VO.vm -DfileName=Area.java
call ant genTable -DtableName=usergroup  -DtemplateDir=vo  -DtemplateName=VO.vm -DfileName=UserGroup.java
call ant genTable -DtableName=role  -DtemplateDir=vo  -DtemplateName=VO.vm -DfileName=Role.java


