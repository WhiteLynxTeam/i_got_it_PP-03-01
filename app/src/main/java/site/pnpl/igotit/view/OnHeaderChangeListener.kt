package site.pnpl.igotit.view

interface OnHeaderChangeListener {
    fun onTitleTextChange(newText: String)
    fun onBackClick()
    fun hideBackArrow()
    fun showBackArrow()
    fun hideAvatar()
    fun showAvatar()
    fun onTitleTextChange(idRes: Int)
}