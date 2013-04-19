<%@ page import="schulerquizz.Student" %>



<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="student.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${studentInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="student.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${studentInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="student.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${studentInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="student.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${studentInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="student.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${studentInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="student.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${studentInstance?.passwordExpired}" />
</div>

