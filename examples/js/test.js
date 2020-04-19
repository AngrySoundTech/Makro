var Thread = Java.type('java.lang.Thread');
var ChatFacade = Java.type('com.github.angrysoundtech.makro.api.facade.ChatFacade');
var MovementFacade = Java.type('com.github.angrysoundtech.makro.api.facade.MovementFacade');
var LookFacade = Java.type('com.github.angrysoundtech.makro.api.facade.LookFacade');

function lookTest() {
    var pitch = LookFacade.getPitch()
    var yaw = LookFacade.getYaw()

    LookFacade.look(LookFacade.NORTH_YAW, LookFacade.STRAIGHT_PITCH)
    Thread.sleep(1000)
    LookFacade.look(LookFacade.SOUTH_YAW, LookFacade.STRAIGHT_PITCH)
    Thread.sleep(1000)
    LookFacade.look(LookFacade.EAST_YAW, LookFacade.STRAIGHT_PITCH)
    Thread.sleep(1000)
    LookFacade.look(LookFacade.WEST_YAW, LookFacade.STRAIGHT_PITCH)
    Thread.sleep(1000)
}

function moveTest() {
    MovementFacade.forward(1000)
    MovementFacade.back(1000)
    MovementFacade.left(1000)
    MovementFacade.right(1000)
    Thread.sleep(100)

    MovementFacade.jump()

    Thread.sleep(200)

    MovementFacade.sprint(true)
    MovementFacade.forward(2000)
}

function chatTest() {
    for (var i in [1,2]) {
        ChatFacade.log("$i")
    }
}

chatTest()
lookTest()
moveTest()