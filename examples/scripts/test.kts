import com.github.angrysoundtech.makro.api.Macro
import com.github.angrysoundtech.makro.api.facade.ChatFacade
import com.github.angrysoundtech.makro.api.facade.MovementFacade
import com.github.angrysoundtech.makro.api.facade.LookFacade

class TestMacro : Macro() {

    override fun run() {
        ChatFacade.log("TEST")

        lookTest()
        moveTest()
        chatTest()
    }

    fun lookTest() {
        val pitch = LookFacade.getPitch()
        val yaw = LookFacade.getYaw()

        LookFacade.look(LookFacade.NORTH_YAW, LookFacade.STRAIGHT_PITCH)
        Thread.sleep(1000)
        LookFacade.look(LookFacade.SOUTH_YAW, LookFacade.STRAIGHT_PITCH)
        Thread.sleep(1000)
        LookFacade.look(LookFacade.EAST_YAW, LookFacade.STRAIGHT_PITCH)
        Thread.sleep(1000)
        LookFacade.look(LookFacade.WEST_YAW, LookFacade.STRAIGHT_PITCH)
        Thread.sleep(1000)
    }

    fun moveTest() {
        MovementFacade.forward(1000L)
        MovementFacade.back(1000L)
        MovementFacade.left(1000L)
        MovementFacade.right(1000L)

        Thread.sleep(100L)

        MovementFacade.jump()

        Thread.sleep(200L)

        MovementFacade.sprint(true)
        MovementFacade.forward(2000L)
    }

    fun chatTest() {
        for (i in 1..10) {
            ChatFacade.log("This is a test $i")
        }
    }
}

TestMacro()
