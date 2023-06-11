package stenograffia.app.domain.utils

sealed class StockChangeQuantityText(data: String?) {
    class CorrectUpdate(val data: String) : StockChangeQuantityText(data)
    class ErrorUpdate() : StockChangeQuantityText(null)
    class ErrorConnect() : StockChangeQuantityText(null)
}