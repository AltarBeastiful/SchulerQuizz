<%@ page import="schulerquizz.Question" %>



<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'answers', 'error')} ">
	<label for="answers">
		<g:message code="question.answers.label" default="Answers" />
		
	</label>
	<g:select name="answers" from="${schulerquizz.Answer.list()}" multiple="multiple" optionKey="id" size="5" value="${questionInstance?.answers*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'multipleAnswer', 'error')} ">
	<label for="multipleAnswer">
		<g:message code="question.multipleAnswer.label" default="Multiple Answer" />
		
	</label>
	<g:checkBox name="multipleAnswer" value="${questionInstance?.multipleAnswer}" />
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="question.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${questionInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'text', 'error')} ">
	<label for="text">
		<g:message code="question.text.label" default="Text" />
		
	</label>
	<g:textField name="text" value="${questionInstance?.text}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="question.type.label" default="Type" />
		
	</label>
	<g:textField name="type" value="${questionInstance?.type}"/>
</div>

