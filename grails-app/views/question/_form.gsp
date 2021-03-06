<%@ page import="schulerquizz.Question" %>



<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'answers_default', 'error')} ">
	<label for="answers_default">
		<g:message code="question.answers_default.label" default="Answersdefault" />
		
	</label>
	<!-- <g:select name="answers_default" from="${session.quickAnswers}" multiple="multiple" optionKey="id" size="5" value="${questionInstance?.answers_default*.id}" class="many-to-many"/> -->
	<g:select name="answers_default" from="${orphanAnwers}" multiple="multiple" optionKey="id" size="5" value="${questionInstance?.answers_default*.id}" class="many-to-many"/>
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

