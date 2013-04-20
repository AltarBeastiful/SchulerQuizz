
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
									
				<g:if test="${questionInstance?.answers_default}">
					<li class="fieldcontain">	
						<g:form action="vote" >
							<g:hiddenField name="idQuestion" value="${questionInstance?.id}" />
							<li class="fieldcontain">	
								<g:render template="formDefaultAnswerVote"/>		
								<g:submitButton name="Vote" class="vote" action="vote" value="${message(code: 'default.button.add.label', default: 'Vote')}" />
							</li>
						</g:form>			
					</li>
				</g:if>			
			</ol>			
			
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${questionInstance?.id}" />
					<g:link class="edit" action="edit" id="${questionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					
				<g:if test="${questionInstance?.isClosed != true}">
					<g:actionSubmit class="close" action="close" value="${message(code: 'default.button.close.label', default: 'Close')}" onclick="return confirm('${message(code: 'default.button.close.confirm.message', default: 'Are you sure?')}');" />
				</g:if>
				
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
