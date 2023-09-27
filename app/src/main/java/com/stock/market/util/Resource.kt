package com.stock.market.util

/**
 * A sealed class representing a resource, which can either contain data of type [T] or an error message.
 *
 * @param data The data of type [T].
 * @param message An optional error message.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    /**
     * Represents a successful result in a resource wrapper.
     *
     * @param data The data associated with the successful result.
     *             It can be null if no data is available or if the success is a signal without data.
     * @param T The type of data being wrapped in the resource.
     */
    class Success<T>(data: T?) : Resource<T>(data)

    /**
     * Represents an error result in a resource wrapper.
     *
     * @param message A descriptive error message providing more information about the error.
     * @param data The optional data associated with the error result.
     *             It can be null if no data is available or not applicable for the specific error.
     * @param T The type of data being wrapped in the resource.
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    /**
     * Represents a loading state in a resource wrapper.
     *
     * @param isLoading A boolean value indicating whether the system is currently in a loading state.
     *                  When true, it signifies that data is being fetched or processed.
     *                  When false, it signifies that the loading process has completed.
     * @param T The type parameter indicating the potential type of data associated with the loading state,
     *          although it is set to null by default to indicate no data.
     */
    class Loading<T>(val isLoading: Boolean) : Resource<T>(null)
}