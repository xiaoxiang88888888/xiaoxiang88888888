#!/bin/bash

ant genTable -DtableName=area  -DtemplateDir=vo  -DtemplateName=VO.vm -DfileName=Area.java
ant genTable -DtableName=usergroup  -DtemplateDir=vo  -DtemplateName=VO.vm -DfileName=UserGroup.java
ant genTable -DtableName=role  -DtemplateDir=vo  -DtemplateName=VO.vm -DfileName=Role.java