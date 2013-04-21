package schulerquizz

class AnswerDefault {
	
	String name
	int votes
	Question question
	
	 static mapping = {
        question lazy: false
    }

    static constraints = {
		name blank:false
		question(nullable: true)
    }

	@Override
	String toString() {
		return name
	}
}

