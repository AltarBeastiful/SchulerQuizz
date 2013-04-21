package schulerquizz

class Question {
	String name 
	String text  
	boolean isClosed = false
	boolean multipleAnswer  

	
	static hasMany = [answers_default:AnswerDefault]
	
    static constraints = {
		name blank:false
    }
}
