package com.github.angrysoundtech.makro.api

import net.minecraft.client.Minecraft

object LookFunctions {

    private val mc = Minecraft.getMinecraft()

    val SOUTH_YAW = 0F
    val WEST_YAW = 90F
    val NORTH_YAW = -180F
    val EAST_YAW = -90F

    val STRAIGHT_PITCH = 0F

    fun getPitch(): Float {
        return mc.player.rotationPitch
    }

    fun getYaw(): Float {
        return mc.player.rotationYaw
    }

    fun look(yaw: Float, pitch: Float) {
        mc.player.rotationPitch = pitch
        mc.player.rotationYaw = yaw
    }
}