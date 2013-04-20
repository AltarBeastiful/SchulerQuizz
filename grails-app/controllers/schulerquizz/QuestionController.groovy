package schulerquizz

import org.springframework.dao.DataIntegrityViolationException

class QuestionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	static defaultAction = "list"
    
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [questionInstanceList: Question.list(params), questionInstanceTotal: Question.count()]
    }

    def create() {
        [questionInstance: new Question(params)]
    }

    def save() {
        def questionInstance = new Question(params)
        if (!questionInstance.save(flush: true)) {
            render(view: "create", model: [questionInstance: questionInstance])
            return
        }
		
		session.quickAnswers.each {
			questionInstance.addToAnswers_default(it)
		}
		
		session.quickAnswers = []

        flash.message = message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), questionInstance.id])
        redirect(action: "show", id: questionInstance.id)
    }

    def show(Long id) {
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        [questionInstance: questionInstance]
    }
	
	def statistics(Long id) {
		def questionInstance = Question.get(id)
		if (!questionInstance.isClosed)  render(view: "statistics", id: questionInstance.id) // do somthesing else !
		
		int total = 500
		// to know how many votes
		questionInstance.answers_default.each {
			total += it.votes  	
		}
		
		if (total != 0 ) {
			// to get percentage of each answer
			def percentages = [:]
			questionInstance.answers_default.each {
				//percentages[it.id] = (it.votes *100) /total
				percentages[it.id] = (it.id *100) /total // to erase when vote system done 
			}
			
			[questionInstance: questionInstance , total_votes: total , percentages: percentages  ]
		}
		else return
		
	}
	
	def close(Long id) {
		def questionInstance = Question.get(id)
		questionInstance.isClosed = true 
		redirect(action: "show",id: questionInstance.id)
	}
	
	def vote() {
		// TODO 
	}

    def edit(Long id) {
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        [questionInstance: questionInstance]
    }

    def update(Long id, Long version) {
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (questionInstance.version > version) {
                questionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'question.label', default: 'Question')] as Object[],
                          "Another user has updated this Question while you were editing")
                render(view: "edit", model: [questionInstance: questionInstance])
                return
            }
        }

        questionInstance.properties = params

        if (!questionInstance.save(flush: true)) {
            render(view: "edit", model: [questionInstance: questionInstance])
            return
        }
		/* TODO : check if needed
		session.quickAnswers.each {
			questionInstance.addToAnswers_default(it)
		}
		
		session.quickAnswers = []
		*/

        flash.message = message(code: 'default.updated.message', args: [message(code: 'question.label', default: 'Question'), questionInstance.id])
        redirect(action: "show", id: questionInstance.id)
    }

    def delete(Long id) {
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        try {
            questionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "show", id: id)
        }
    }
}
