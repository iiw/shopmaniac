package ac.summer.shopmaniac.domain

data class ItemRowModel(
    val id: Int? = null,
    val text: String? = null,
    val created: Int? = null,
    val isChecked: Boolean? = null,
    val type: Int = NORMAL
) {
    companion object {
        const val NEW = 1
        const val NORMAL = 2
    }
}