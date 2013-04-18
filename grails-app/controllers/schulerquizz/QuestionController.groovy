package schulerquizz

class QuestionController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
   static defaultAction = "list"

    def list() {
        [questions: Question.list()]
    }

    def show() {
        [questionInstance: Question.get(params.id)]
    }
	
	def create() {

	}
	
	def edit() {
		
	}
	
	def delete() {
		
	}
	
}
