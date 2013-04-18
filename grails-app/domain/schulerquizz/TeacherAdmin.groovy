package schulerquizz

import org.apache.commons.lang.builder.HashCodeBuilder

class TeacherAdmin implements Serializable {

	Teacher teacher
	Admin admin

	boolean equals(other) {
		if (!(other instanceof TeacherAdmin)) {
			return false
		}

		other.teacher?.id == teacher?.id &&
			other.admin?.id == admin?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (teacher) builder.append(teacher.id)
		if (admin) builder.append(admin.id)
		builder.toHashCode()
	}

	static TeacherAdmin get(long teacherId, long adminId) {
		find 'from TeacherAdmin where teacher.id=:teacherId and admin.id=:adminId',
			[teacherId: teacherId, adminId: adminId]
	}

	static TeacherAdmin create(Teacher teacher, Admin admin, boolean flush = false) {
		new TeacherAdmin(teacher: teacher, admin: admin).save(flush: flush, insert: true)
	}

	static boolean remove(Teacher teacher, Admin admin, boolean flush = false) {
		TeacherAdmin instance = TeacherAdmin.findByTeacherAndAdmin(teacher, admin)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(Teacher teacher) {
		executeUpdate 'DELETE FROM TeacherAdmin WHERE teacher=:teacher', [teacher: teacher]
	}

	static void removeAll(Admin admin) {
		executeUpdate 'DELETE FROM TeacherAdmin WHERE admin=:admin', [admin: admin]
	}

	static mapping = {
		id composite: ['admin', 'teacher']
		version false
	}
}
