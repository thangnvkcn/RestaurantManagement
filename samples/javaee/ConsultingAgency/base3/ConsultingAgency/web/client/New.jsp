<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Client</title>
            <link rel="stylesheet" type="text/css" href="/ConsultingAgency/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ConsultingAgency/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Client</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{client.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Recruiter:"/>
                    <h:selectOneMenu id="recruiter" value="#{client.client.recruiter}" title="Recruiter" >
                        <f:selectItems value="#{recruiter.recruiterItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="ClientDepartmentNumber:"/>
                    <h:inputText id="clientDepartmentNumber" value="#{client.client.clientPK.clientDepartmentNumber}" title="ClientDepartmentNumber" required="true" requiredMessage="The clientDepartmentNumber field is required." />
                    <h:outputText value="ClientName:"/>
                    <h:inputText id="clientName" value="#{client.client.clientPK.clientName}" title="ClientName" required="true" requiredMessage="The clientName field is required." />
                    <h:outputText value="ContactEmail:"/>
                    <h:inputText id="contactEmail" value="#{client.client.contactEmail}" title="ContactEmail" />
                    <h:outputText value="ContactPassword:"/>
                    <h:inputText id="contactPassword" value="#{client.client.contactPassword}" title="ContactPassword" />
                    <h:outputText value="ProjectCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][client.client.projectCollection == null ? jsfcrud_null : client.client.projectCollection].jsfcrud_invoke}" title="ProjectCollection" />
                    <h:outputText value="BillingAddress:"/>
                    <h:selectOneMenu id="billingAddress" value="#{client.client.billingAddress}" title="BillingAddress" required="true" requiredMessage="The billingAddress field is required." >
                        <f:selectItems value="#{address.addressItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{client.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{client.listSetup}" value="Show All Client Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
