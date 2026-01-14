package com.teddeh.api

import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.plugin.Plugin

/**
 * This event is fired when a plugin is loaded onto server.
 */
sealed class PluginLoadEvent : Event(), Cancellable {

    /**
     * This event is fired before a plugin is loaded onto server.
     */
    class Pre (
        val plugin: String,
        val notify: Boolean
    ) : PluginLoadEvent()

    /**
     * This event is fired after a plugin has been loaded onto server.
     */
    class Post (
        val plugin: Plugin
    ) : PluginLoadEvent()

    companion object {
        private val handlers: HandlerList = HandlerList()

        /**
         * @return the list of handlers for this event
         */
        @JvmStatic
        fun getHandlerList(): HandlerList = handlers
    }

    // Cancellation state of this event
    private var cancelled = false

    /**
     * @return the list of handlers for this event
     */
    override fun getHandlers(): HandlerList = Companion.handlers

    /**
     * Checks if this event is cancelled.
     * @return true if this event is cancelled, false otherwise
     */
    override fun isCancelled() = cancelled

    /**
     * Sets the cancellation state of this event.
     * @param value true if this event should be cancelled, false otherwise
     */
    override fun setCancelled(value: Boolean) {
        cancelled = value
    }

}

