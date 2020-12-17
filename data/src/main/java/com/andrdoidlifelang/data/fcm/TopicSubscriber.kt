package com.andrdoidlifelang.data.fcm

/**
 * A class is used to subscribe users to server topic.
 */
interface TopicSubscriber {

    /**
     * Subscribe all users that opens our app to receive future movies.
     */
    fun subscribeToMovieUpdates()

    /**
     * If a user signs in, subscribe them to the "character" topic.
     */
    fun subscribeToCharacters()

    /**
     * If a user is registered and signs out, unsubscribe them from character topic to
     * stop notification.
     */
    fun unsubscribeToCharacters()
}
