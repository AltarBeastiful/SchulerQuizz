<%@ page import="schulerquizz.AnswerDefault" %>

<div class="fieldcontain ${hasErrors(bean: answerDefaultInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="answerDefault.name.label" default="Name" />
	</label>
	<g:textField name="name" value="${answerDefaultInstance?.name}"/>
</div>

