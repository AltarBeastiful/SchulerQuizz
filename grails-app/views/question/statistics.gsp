
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
				<sec:ifAllGranted roles="ROLE_ADMIN">	
					<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				</sec:ifAllGranted>
			</ul>
		</div>
		<div id="show-question-stat" class="content scaffold-show" role="main">
			<h1><g:message code="default.stat.label" args="[entityName]" /></h1>
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
					
				<div id="list-answer" class="content scaffold-list" role="main">
					<h1>Answers Stats : ${total_votes} votes</h1> 
					<g:if test="${questionInstance?.answers_default && questionInstance?.isClosed  && total_votes  }">
						<li class="fieldcontain">	
							<g:if test="${flash.message}">
								<div class="message" role="status">
									${flash.message}
								</div>
							</g:if>
							<table>
								<thead>
									<tr>
										<g:sortableColumn property="name" title="Answers" />
	
										<g:sortableColumn property="amount" title="Votes Amount" />
										
										<g:sortableColumn property="stats" title="Statistics" />
									</tr>
								</thead>
								<tbody>
									<g:each in="${questionInstance.answers_default}" var="answer">
										<tr>
											<!-- <td><g:link action="show" id="${answer.id}">${fieldValue(bean: answerDefault, field: "name")}</g:link></td> -->
	
											<td><g:fieldValue bean="${answer}" field="name" /></td>
											
											<td><g:field name= "amount" type="text" value="${answer.votes}" readonly="readonly" /></td>
								
											<td><g:field name= "percentage" type="text" value="${percentages[answer.id]} %" readonly="readonly"  /></td>
										</tr>
									</g:each>
								</tbody>
							</table>
						</li>
					</g:if>	
				</div>	
			</ol>	
			<sec:ifAllGranted roles="ROLE_ADMIN">	
				<g:form>
					<fieldset class="buttons">
						<g:hiddenField name="id" value="${questionInstance?.id}" />
						<g:link class="edit" action="edit" id="${questionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
							<g:if test="${questionInstance?.isClosed != true}">
								<g:actionSubmit action="close" value="${message(code: 'default.button.close.label', default: 'Close')}" onclick="return confirm('${message(code: 'default.button.close.confirm.message', default: 'Are you sure?')}');" />
							</g:if>
							<g:else>
								<g:link action="statistics" id="${questionInstance.id}"><g:message code="default.button.statistics.label" default='Statistics'/></g:link>
								<g:link action="open" id="${questionInstance.id}"><g:message code="default.button.open.label" default='Open'/></g:link>
							</g:else>
					</fieldset>
				</g:form>
			</sec:ifAllGranted>
		</div>
	</body>
</html>
