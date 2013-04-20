<%@ page import="schulerquizz.AnswerDefault" %>


<div id="list-answer" class="content scaffold-list" role="main">
	<h1>List answers</h1>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
	<table>
		<thead>
			<tr>					
				<g:sortableColumn property="name" title="Answer" />
			
				<g:sortableColumn property="button" title="Choose your answer(s)" />
			</tr>
		</thead>
		
		<tbody>
			<g:each in="${questionInstance.answers_default}" var="answer">
				<tr>
					<!-- <td><g:link action="show" id="${answer.id}">${fieldValue(bean: answerDefault, field: "name")}</g:link></td> -->
					
					<td><g:fieldValue bean="${answer}" field="name"/></td>
				
					<g:if test="${questionInstance.multipleAnswer }">
						<td><g:checkBox name="answer.id" value="false" /></td>
					</g:if>
					<g:else>
						<td><g:radio name="answer.id" value="answer.id" checked="false"/></td>
					</g:else>
				</tr>
			</g:each>
		</tbody>
	</table>
</div>	