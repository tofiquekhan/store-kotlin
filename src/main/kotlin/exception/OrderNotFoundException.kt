package exception

class OrderNotFoundException : RuntimeException {
    constructor()
    constructor(msg: String?) : super(msg)
}