<%@ page import="schulerquizz.AnswerDefault" %>



<div class="fieldcontain ${hasErrors(bean: answerDefaultInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="answerDefault.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${answerDefaultInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: answerDefaultInstance, field: 'votes', 'error')} required">
	<label for="votes">
		<g:message code="answerDefault.votes.label" default="Votes" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="votes" type="number" value="${answerDefaultInstance.votes}" required=""/>
</div>

