package com.braze.fourroosters.domain

sealed class UIEvent {
    data class ShowHowToPlayDialog(val wantToShow: Boolean): UIEvent()
    data class ShowWinDialog(val wantToShow: Boolean): UIEvent()
    data class ShowLostDialog(val wantToShow: Boolean): UIEvent()
    object NewGame: UIEvent()
    object EndOfTheGame: UIEvent()
}