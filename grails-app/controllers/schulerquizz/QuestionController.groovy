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
		// search for orphean answers to display them
	
		def orphanAnwers = AnswerDefault.findAllWhere( question: null )
        [questionInstance: new Question(params), orphanAnwers:orphanAnwers]
    }

    def save() {
        def questionInstance = new Question(params)
		
		def orphanAnwers = AnswerDefault.findAllWhere( question: null )
		// connect question to answers
		orphanAnwers.each {
			questionInstance.addToAnswers_default(it)
		}
	
        if (!questionInstance.save(flush: true)) {
            render(view: "create", model: [questionInstance: questionInstance])
            return
        }
		
		// connect answer to question
		orphanAnwers.each {
			it.question = questionInstance
			if(!it.save(flush: true)) System.out.println("TOTO");
		}

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
		
		def voted = false
		def questionId = String.valueOf(id)
		flash.vote = [:]
		
		if(session.vote && session.vote[questionId] && session.vote[questionId].size() > 0){
			voted = true
			flash.vote = session.vote[questionId]
		}
		
		flash.voted = voted

        [questionInstance: questionInstance]
    }
	
	def statistics(Long id) {
		def questionInstance = Question.get(id)
		if (!questionInstance.isClosed)  render(view: "statistics", id: questionInstance.id) // do somthesing else !
		
		int total = 0
		// to know how many votes
		questionInstance.answers_default.each {
			total = total + it.votes  	
		}
		
		if (total != 0 ) {
			// to get percentage of each answer
			def percentages = [:]
			questionInstance.answers_default.each {
				percentages[it.id] = (it.votes *100) /total
			}
			// At list one vote : you can display some stats
			[questionInstance: questionInstance , total_votes: total , percentages: percentages  ]
		}
		else  [questionInstance: questionInstance , total_votes: total] // no votes : avoid stats
	}
	
	def close(Long id) {
		def questionInstance = Question.get(id)
		questionInstance.isClosed = true 
		redirect(action: "show",id: questionInstance.id)
	}
	
	def open(Long id) {
		def questionInstance = Question.get(id)
		questionInstance.isClosed = false
		redirect(action: "show",id: questionInstance.id)
	}
	
	def vote(Long id) {
		if(session.vote && session.vote[params.idQuestion] && session.vote[params.idQuestion].size() > 0){
			redirect(action: "show",id: params.idQuestion)			
		}

		if(!session.vote)
			session.vote = [:]			
		
		if(params.multipleAnswer){
			//put every answer at false
			session.vote[params.idQuestion] = [:]
			
			if(params.multipleAnswer instanceof String)
				params.multipleAnswer = [params.multipleAnswer]
			
			//True to choosed answers
			params.multipleAnswer.each {
					session.vote[params.idQuestion][it] = 'true'
					def a = AnswerDefault.get(it)
					a.setVotes(a.getVotes()+1)
			}
						
		}else if(params.answers){
			session.vote[params.idQuestion] = [:]
			
			//put every answer at false
			session.vote[params.idQuestion][params.answers] = 'true'
			def a = AnswerDefault.get(params.answers)
			a.setVotes(a.getVotes()+1)
		}
		
		redirect(action: "show",id: params.idQuestion)			
	}

    def edit(Long id) {
		
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }
		
		// retrieve orphans & children answers
		def orphanAnwers = AnswerDefault.findAllWhere( question: null )
		def childrenAnwers = AnswerDefault.findAllWhere( question: questionInstance )
		  
		orphanAnwers.addAll(childrenAnwers)

        [questionInstance: questionInstance , orphanAnwers:orphanAnwers]
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

		def orphanAnwers = AnswerDefault.findAllWhere( question: null )
		// connect question to answers
		orphanAnwers.each {
			questionInstance.addToAnswers_default(it)
		}
		
        if (!questionInstance.save(flush: true)) {
            render(view: "edit", model: [questionInstance: questionInstance])
            return
        }
		
		// connect answer to question
		orphanAnwers.each {
			it.question = questionInstance
			if(!it.save(flush: true)) System.out.println("TOTO");
		}
		
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
