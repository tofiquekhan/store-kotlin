package exception

class UserNotFoundException : RuntimeException {
    constructor()
    constructor(msg: String?) : super(msg)
}
