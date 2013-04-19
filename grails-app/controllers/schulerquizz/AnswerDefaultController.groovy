package schulerquizz

import org.springframework.dao.DataIntegrityViolationException

class AnswerDefaultController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [answerDefaultInstanceList: AnswerDefault.list(params), answerDefaultInstanceTotal: AnswerDefault.count()]
    }

    def create() {
        [answerDefaultInstance: new AnswerDefault(params)]
    }
	
	def saveQuickAnswer() {
		def answerDefaultInstance = new AnswerDefault(params)
		if (!answerDefaultInstance.save(flush: true)) {
			redirect(action: "../Question/create")
		}

		if( !session.quickAnswers )
			session.quickAnswers = []
			
		session.quickAnswers << answerDefaultInstance
		redirect(action: "../Question/create")
	}
	
    def save() {
        def answerDefaultInstance = new AnswerDefault(params)
        if (!answerDefaultInstance.save(flush: true)) {
            render(view: "create", model: [answerDefaultInstance: answerDefaultInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'answerDefault.label', default: 'AnswerDefault'), answerDefaultInstance.id])
        redirect(action: "show", id: answerDefaultInstance.id)
    }

    def show(Long id) {
        def answerDefaultInstance = AnswerDefault.get(id)
        if (!answerDefaultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answerDefault.label', default: 'AnswerDefault'), id])
            redirect(action: "list")
            return
        }

        [answerDefaultInstance: answerDefaultInstance]
    }

    def edit(Long id) {
        def answerDefaultInstance = AnswerDefault.get(id)
        if (!answerDefaultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answerDefault.label', default: 'AnswerDefault'), id])
            redirect(action: "list")
            return
        }

        [answerDefaultInstance: answerDefaultInstance]
    }

    def update(Long id, Long version) {
        def answerDefaultInstance = AnswerDefault.get(id)
        if (!answerDefaultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answerDefault.label', default: 'AnswerDefault'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (answerDefaultInstance.version > version) {
                answerDefaultInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'answerDefault.label', default: 'AnswerDefault')] as Object[],
                          "Another user has updated this AnswerDefault while you were editing")
                render(view: "edit", model: [answerDefaultInstance: answerDefaultInstance])
                return
            }
        }

        answerDefaultInstance.properties = params

        if (!answerDefaultInstance.save(flush: true)) {
            render(view: "edit", model: [answerDefaultInstance: answerDefaultInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'answerDefault.label', default: 'AnswerDefault'), answerDefaultInstance.id])
        redirect(action: "show", id: answerDefaultInstance.id)
    }

    def delete(Long id) {
        def answerDefaultInstance = AnswerDefault.get(id)
        if (!answerDefaultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'answerDefault.label', default: 'AnswerDefault'), id])
            redirect(action: "list")
            return
        }

        try {
            answerDefaultInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'answerDefault.label', default: 'AnswerDefault'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'answerDefault.label', default: 'AnswerDefault'), id])
            redirect(action: "show", id: id)
        }
    }
}
