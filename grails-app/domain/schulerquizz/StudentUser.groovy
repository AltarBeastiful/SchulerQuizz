package schulerquizz

import org.apache.commons.lang.builder.HashCodeBuilder

class StudentUser implements Serializable {

	Student student
	User user

	boolean equals(other) {
		if (!(other instanceof StudentUser)) {
			return false
		}

		other.student?.id == student?.id &&
			other.user?.id == user?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (student) builder.append(student.id)
		if (user) builder.append(user.id)
		builder.toHashCode()
	}

	static StudentUser get(long studentId, long userId) {
		find 'from StudentUser where student.id=:studentId and user.id=:userId',
			[studentId: studentId, userId: userId]
	}

	static StudentUser create(Student student, User user, boolean flush = false) {
		new StudentUser(student: student, user: user).save(flush: flush, insert: true)
	}

	static boolean remove(Student student, User user, boolean flush = false) {
		StudentUser instance = StudentUser.findByStudentAndUser(student, user)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(Student student) {
		executeUpdate 'DELETE FROM StudentUser WHERE student=:student', [student: student]
	}

	static void removeAll(User user) {
		executeUpdate 'DELETE FROM StudentUser WHERE user=:user', [user: user]
	}

	static mapping = {
		id composite: ['user', 'student']
		version false
	}
}
