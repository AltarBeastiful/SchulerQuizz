package schulerquizz

class Question {
	String name 
	String text  
	boolean multipleAnswer  
	
	static hasMany = [answers_default:AnswerDefault]
	
    static constraints = {
    }
}
