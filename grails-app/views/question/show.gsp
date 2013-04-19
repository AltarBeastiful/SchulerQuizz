
<%@ page import="schulerquizz.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-question" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list question">
			
				<g:if test="${questionInstance?.answers_default}">
				<li class="fieldcontain">
					<span id="answers_default-label" class="property-label"><g:message code="question.answers_default.label" default="Answersdefault" /></span>
					
						<g:each in="${questionInstance.answers_default}" var="a">
						<span class="property-value" aria-labelledby="answers_default-label"><g:link controller="answerDefault" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.multipleAnswer}">
				<li class="fieldcontain">
					<span id="multipleAnswer-label" class="property-label"><g:message code="question.multipleAnswer.label" default="Multiple Answer" /></span>
					
						<span class="property-value" aria-labelledby="multipleAnswer-label"><g:formatBoolean boolean="${questionInstance?.multipleAnswer}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="question.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${questionInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${questionInstance?.text}">
				<li class="fieldcontain">
					<span id="text-label" class="property-label"><g:message code="question.text.label" default="Text" /></span>
					
						<span class="property-value" aria-labelledby="text-label"><g:fieldValue bean="${questionInstance}" field="text"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${questionInstance?.id}" />
					<g:link class="edit" action="edit" id="${questionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
