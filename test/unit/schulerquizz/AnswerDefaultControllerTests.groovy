package schulerquizz



import org.junit.*
import grails.test.mixin.*

@TestFor(AnswerDefaultController)
@Mock(AnswerDefault)
class AnswerDefaultControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/answerDefault/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.answerDefaultInstanceList.size() == 0
        assert model.answerDefaultInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.answerDefaultInstance != null
    }

    void testSave() {
        controller.save()

        assert model.answerDefaultInstance != null
        assert view == '/answerDefault/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/answerDefault/show/1'
        assert controller.flash.message != null
        assert AnswerDefault.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/answerDefault/list'

        populateValidParams(params)
        def answerDefault = new AnswerDefault(params)

        assert answerDefault.save() != null

        params.id = answerDefault.id

        def model = controller.show()

        assert model.answerDefaultInstance == answerDefault
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/answerDefault/list'

        populateValidParams(params)
        def answerDefault = new AnswerDefault(params)

        assert answerDefault.save() != null

        params.id = answerDefault.id

        def model = controller.edit()

        assert model.answerDefaultInstance == answerDefault
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/answerDefault/list'

        response.reset()

        populateValidParams(params)
        def answerDefault = new AnswerDefault(params)

        assert answerDefault.save() != null

        // test invalid parameters in update
        params.id = answerDefault.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/answerDefault/edit"
        assert model.answerDefaultInstance != null

        answerDefault.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/answerDefault/show/$answerDefault.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        answerDefault.clearErrors()

        populateValidParams(params)
        params.id = answerDefault.id
        params.version = -1
        controller.update()

        assert view == "/answerDefault/edit"
        assert model.answerDefaultInstance != null
        assert model.answerDefaultInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/answerDefault/list'

        response.reset()

        populateValidParams(params)
        def answerDefault = new AnswerDefault(params)

        assert answerDefault.save() != null
        assert AnswerDefault.count() == 1

        params.id = answerDefault.id

        controller.delete()

        assert AnswerDefault.count() == 0
        assert AnswerDefault.get(answerDefault.id) == null
        assert response.redirectedUrl == '/answerDefault/list'
    }
}
