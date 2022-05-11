package lunets.co.henez.utis

class RecordLocatorGenerator {
    companion object {
        fun generateOrderLocator(): String? {
            val recordLocatorChars = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789")
            val sb = StringBuilder(7)
            for (i in 0 until 7) {
                val index = (recordLocatorChars.length
                        * Math.random()).toInt()
                sb.append(recordLocatorChars[index])
            }
            return sb.toString()
        }
    }
}