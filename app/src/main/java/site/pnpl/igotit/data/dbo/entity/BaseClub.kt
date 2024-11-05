package site.pnpl.igotit.data.dbo.entity

import site.pnpl.igotit.domain.models.HasUuid
import java.util.UUID

open class BaseClub(
    override var uuid: UUID? = null,
    open val clubName: String,
    open val type: String,
    open val level: String,
    open val description: String,
    open val length: String,
    open val frequency: String,
    open val numberClasses: String,
    open val totalQuantity: String,
    open val about: String = "",
) : HasUuid