package exception

class ShopNotFoundException : RuntimeException {
    constructor()
    constructor(msg: String?) : super(msg)
}