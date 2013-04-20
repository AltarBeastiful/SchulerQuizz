
<%@ page import="schulerquizz.Question" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			hello World
			
			
			<g:each in="${list_answers_voted}" var="answer">
				
			</g:each>

		</div>
	</body>
</html>
