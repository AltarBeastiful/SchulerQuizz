<%@ page import="schulerquizz.Teacher" %>



<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="teacher.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${teacherInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="teacher.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${teacherInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'accountExpired', 'error')} "  hidden="true">
	<label for="accountExpired">
		<g:message code="teacher.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="false" />
</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'accountLocked', 'error')} "  hidden="true">
	<label for="accountLocked">
		<g:message code="teacher.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="false" />
</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="teacher.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${teacherInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'enabled', 'error')} " hidden="true">
	<label for="enabled">
		<g:message code="teacher.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="true" />
</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="teacher.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${teacherInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'passwordExpired', 'error')} " hidden="true">
	<label for="passwordExpired">
		<g:message code="teacher.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="false" />
</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'surname', 'error')} ">
	<label for="surname">
		<g:message code="teacher.surname.label" default="Surname" />
		
	</label>
	<g:textField name="surname" value="${teacherInstance?.surname}"/>
</div>

