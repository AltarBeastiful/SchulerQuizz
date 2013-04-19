import schulerquizz.Admin

class BootStrap {

    def init = { servletContext ->
		def teacherRole = Admin.findByAuthority('ROLE_ADMIN') ?: new Admin(authority: 'ROLE_ADMIN').save(failOnError: true)
    }
    def destroy = {
    }
}
