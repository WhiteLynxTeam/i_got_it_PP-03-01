package site.pnpl.igotit.view

interface OnHeaderChangeListener {
    fun onTitleTextChange(newText: String)
    fun onBackClick()
    fun hideBackArrow()
    fun showBackArrow()
    fun onTitleTextChange(idRes: Int)
}