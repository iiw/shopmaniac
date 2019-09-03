package ac.summer.shopmaniac.domain

data class ItemRowModel(
    val id: Int? = null,
    val text: String? = null,
    val created: Int? = null,
    var isChecked: Boolean? = null,
    var type: Int = NORMAL
) {

    fun done() {
        isChecked = true
        type = DONE
    }

    companion object {
        const val NEW = 1
        const val NORMAL = 2
        const val DONE = 3
    }
}