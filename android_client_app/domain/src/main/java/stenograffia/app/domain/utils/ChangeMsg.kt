package stenograffia.app.domain.utils

sealed class ChangeMsg () {
    class CorrectChange() : ChangeMsg()
    class ErrorChange() : ChangeMsg()
    class ErrorConnect() : ChangeMsg()
}