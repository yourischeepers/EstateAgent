package me.partypronl.estateagent.domain.homes.model

import kotlinx.serialization.Serializable

@Serializable
data class Home(
    val listing: Listing,
    val state: State = State.AwaitingRating,
    val details: HomeDetails? = null,
) {

    enum class State {

        AwaitingRating,
        Uninterested,
        Interested,
        Reacted,
        Rejected,
        Accepted;

        fun toOrganized(): OrganizedState {
            return when (this) {
                AwaitingRating -> OrganizedState.AwaitingRating
                Uninterested -> OrganizedState.Historic.Uninterested
                Interested -> OrganizedState.AwaitingReaction
                Reacted -> OrganizedState.AwaitingResponse
                Rejected -> OrganizedState.Historic.Rejected
                Accepted -> OrganizedState.Historic.Accepted
            }
        }
    }

    sealed interface OrganizedState {

        data object AwaitingRating : OrganizedState
        data object AwaitingReaction : OrganizedState
        data object AwaitingResponse : OrganizedState

        sealed interface Historic : OrganizedState {

            data object Uninterested : Historic
            data object Rejected : Historic
            data object Accepted : Historic
        }
    }
}
