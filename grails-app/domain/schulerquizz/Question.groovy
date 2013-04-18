package schulerquizz

class Question {
	String name 
	String text 
	String type 
	boolean multipleAnswer  
	
	static hasMany = [answers:Answer]
	
    static constraints = {
    }
}
